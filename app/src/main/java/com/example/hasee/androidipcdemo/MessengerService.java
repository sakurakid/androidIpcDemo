package com.example.hasee.androidipcdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * 采用message方式
 */
public class MessengerService extends Service {
    public static final String TAG = "MoonMessenger";
    public static final int MSG_FROMLIENT = 1000;
    public MessengerService() {
    }
   private static class MessengerHandle extends Handler{
       @Override
       public void handleMessage(Message msg) {
           switch (msg.what){
               case 1:
                   //接收到客户端的消息
                   Log.d(TAG,"接收到客户端的消息是"+msg.getData().getString("msg"));
                   //收到之后的回复
                   Messenger client = msg.replyTo;
                   Message relpyMessage = Message.obtain(null,2);
                   Bundle bundle = new Bundle();
                   bundle.putString("reply","俺收到了，稍后回复你");
                   relpyMessage.setData(bundle);
                   try {
                       client.send(relpyMessage);
                   } catch (RemoteException e) {
                       e.printStackTrace();
                   }
                   break;
                default:
                    super.handleMessage(msg);
           }
       }
   }
   private final Messenger mMessenger = new Messenger(new MessengerHandle());

    @Override
    public IBinder onBind(Intent intent) {
       return mMessenger.getBinder();
    }
}
