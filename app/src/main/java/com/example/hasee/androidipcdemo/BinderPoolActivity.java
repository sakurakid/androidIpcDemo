package com.example.hasee.androidipcdemo;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class BinderPoolActivity extends AppCompatActivity {
    private static final String TAG = " BinderPoolActivity";
    private SecurityCenterImpl mSecourityCenter;
    private ComputeImpl compute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);
    }
    private void work() throws RemoteException {
        BinderPool binderPool = BinderPool.getInstance(BinderPoolActivity.this);
        IBinder securityBinder = binderPool.queryBinder(BinderPool.BINDER_SECURITY_CENTER);

        mSecourityCenter = (SecurityCenterImpl) SecurityCenterImpl.asInterface(securityBinder);

        Log.d(TAG,"visit ISecurityCenter");
        String msg = "hello lallalal";
        System.out.println("content"+msg);
        String password = mSecourityCenter.encrypt(msg);
        System.out.println("encrypt:"+password);

        IBinder computeBinder = binderPool.queryBinder(BinderPool.BINDER_COMPUTE);
        compute = (ComputeImpl) ComputeImpl.asInterface(computeBinder);
        System.out.println(compute.add(3,5));


    }
}
