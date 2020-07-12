package com.example.hasee.androidipcdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public int userId;
    public String userName;
    public  boolean isMale;

    //从序列化对象后创建原始对象
    protected User(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readByte() != 0;
    }
   //反序列化
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
    //序列化
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(userId);
        parcel.writeString(userName);
        parcel.writeByte((byte) (isMale ? 1 : 0));
    }
}
