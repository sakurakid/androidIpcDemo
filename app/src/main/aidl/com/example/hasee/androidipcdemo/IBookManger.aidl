// IBookManger.aidl
package com.example.hasee.androidipcdemo;
import com.example.hasee.androidipcdemo.Book;
// Declare any non-default types here with import statements
//方法中参数修饰符可以是in、out、inout，也有自定义的类，该类需要实现Parcelable接口
//in：客户端的参数输入；
// out：服务端的参数输入；
//inout：这个可以叫输入输出参数，客户端可输入、服务端也可输入。
interface IBookManger {
     List<Book>getBookList();
     void addBook(in Book book);


}
