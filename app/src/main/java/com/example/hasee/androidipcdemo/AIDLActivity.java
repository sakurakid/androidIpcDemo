package com.example.hasee.androidipcdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class AIDLActivity extends AppCompatActivity {
    private final String TAG = "AIDLActivity";

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
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        Intent intent = new Intent(this,AIDLService.class);
        bindService(intent,mServiceConnection, Context.BIND_AUTO_CREATE);
    }

}
