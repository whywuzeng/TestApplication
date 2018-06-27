package com.example.wz1.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018-06-14.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.Book
 */

public class Book implements Parcelable {
    private int BookId;
    private String name;
    private String autor;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.BookId);
        dest.writeString(this.name);
        dest.writeString(this.autor);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.BookId = in.readInt();
        this.name = in.readString();
        this.autor = in.readString();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
