package com.example.hasee.androidipcdemo.IIpcDemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BookManagerService extends Service {

    public BookManagerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
