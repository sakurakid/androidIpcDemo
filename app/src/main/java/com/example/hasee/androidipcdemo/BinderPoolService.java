package com.example.hasee.androidipcdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Binder的连接池的service
 */
public class BinderPoolService extends Service {
    private static final String TAG = "BinderPoolService";
    public BinderPoolService() {
    }

    private Binder mBinderPool = new BinderPool.BinderPoolImpl();


    @Override
    public IBinder onBind(Intent intent) {
       return mBinderPool;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
