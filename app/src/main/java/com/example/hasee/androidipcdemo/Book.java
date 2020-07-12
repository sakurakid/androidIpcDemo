package com.example.hasee.androidipcdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    public String bookName;
    public int bookId;

    public Book(String name,int id){
        this.bookName = name;
        this.bookId = id;
    }

    protected Book(Parcel in) {
        bookName = in.readString();
        bookId = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bookName);
        parcel.writeInt(bookId);
    }
}
