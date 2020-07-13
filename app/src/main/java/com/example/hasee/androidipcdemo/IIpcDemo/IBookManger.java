package com.example.hasee.androidipcdemo.IIpcDemo;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import com.example.hasee.androidipcdemo.Book;

import java.util.List;

/**
 * 自己写的Binder  AIDL性质的接口
 */
public interface IBookManger extends IInterface{
    static final  String DESCRIPTOR = "com.example.hasee.androidipcdemo.IBookManager";

    static final int TRANSACTION_getBookList = IBinder.FIRST_CALL_TRANSACTION+0;
    static final int TRANSACTION_addBook = IBinder.FIRST_CALL_TRANSACTION+1;

    public List<Book> getList() throws RemoteException;
    public  void addBook(Book book) throws RemoteException;
}
