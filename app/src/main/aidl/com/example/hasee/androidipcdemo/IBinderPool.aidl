// IBinderPool.aidl
package com.example.hasee.androidipcdemo;

// Declare any non-default types here with import statements
//Binder连接池的接口
interface IBinderPool {
  IBinder queryBinder(int binderCode);
}
