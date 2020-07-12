package com.example.hasee.androidipcdemo;

import android.app.Application;
import android.support.annotation.RequiresApi;
import android.util.Log;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    @RequiresApi(api = 28)
    @Override
    public void onCreate() {
        super.onCreate();
        int pid = android.os.Process.myPid();
        String name = getProcessName();
        Log.i(TAG, name+"  "+"MyApplication is oncreate====="+"pid="+pid);


    }
}
