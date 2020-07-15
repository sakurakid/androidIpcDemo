package com.example.hasee.androidipcdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class AIDLActivity extends AppCompatActivity {
    private final String TAG = "AIDLActivity";
    private static final int MESSAGE_NEW_BOOK_ARRIVED = 1;

    private IBookManger mRemoteBookManager;

    private Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_NEW_BOOK_ARRIVED:
                    Log.d(TAG,"receview new Book"+msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    private ServiceConnection mServiceConnection= new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IBookManger iBookManger = IBookManger.Stub.asInterface(iBinder);
            Book book = new Book("红楼梦",56);
            try {
                iBookManger.addBook(book);
                List<Book> mList = iBookManger.getBookList();
                for(int i = 0;i<mList.size();i++){
                    Book book1 = mList.get(i);
                    Log.d(TAG,book1.bookName+"     "+book1.bookId);
                }
                Log.d("callback","3");
                iBookManger.registerListener(monNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mRemoteBookManager = null;

        }
    };
    private IOnNewBookArrivedListener monNewBookArrivedListener = new IOnNewBookArrivedListener.Stub(){


        @Override
        public void onNewBookArrived(Book newBook) throws RemoteException {
            Log.d("callback","4");
            mHandle.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED,newBook).sendToTarget();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        Intent intent = new Intent(this,AIDLService.class);
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        if (mRemoteBookManager != null&&mRemoteBookManager.asBinder().isBinderAlive()){
            try {
                mRemoteBookManager.unregisterListener(monNewBookArrivedListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mServiceConnection);
        super.onDestroy();
    }
}
