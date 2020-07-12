package com.example.hasee.androidipcdemo;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * 使用Messager 发生信息的
 */
public class MessangerActivity extends AppCompatActivity {
    private static final String TAG = "MessengerActivity";
    private Messenger mService;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = new Messenger(iBinder);
            Message msg = Message.obtain(null,1);
            Bundle date = new Bundle();
            date.putString("msg","hello this is client");
            msg.setData(date);
            msg.replyTo = mGetReplyMessenger;
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());
    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 2:
                    Log.d(TAG,"收到了收到了"+msg.getData().getString("reply"));
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messanger);
        Intent intent = new Intent(this,MessengerService.class);
        //绑定service
        bindService(intent,mConnection,1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
