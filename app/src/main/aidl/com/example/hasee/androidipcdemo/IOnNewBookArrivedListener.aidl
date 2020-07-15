// IOnNewBookArrivedListener.aidl
package com.example.hasee.androidipcdemo;
import com.example.hasee.androidipcdemo.Book;

// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
   void onNewBookArrived(in Book newBook);
}
