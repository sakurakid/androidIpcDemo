package com.example.hasee.androidipcdemo;

import android.os.RemoteException;

/**
 * 实现的类
 */
public class ComputeImpl extends ICompute.Stub {
    @Override
    public int add(int a, int b) throws RemoteException {
        return a+b;
    }
}
