package com.example.wz1.myapplication.BookBinderTest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.example.wz1.myapplication.Book;
import com.example.wz1.myapplication.IBookManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-06-14.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.Book
 */

public class BookService11 extends Service {

    private List<Book> books=new ArrayList<>();

    private final IBookManager.Stub binder=new IBookManager.Stub() {
        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (books){
                if (!books.contains(book))
                  books.add(book);
            }
        }

        @Override
        public void getBookList() throws RemoteException {
            synchronized (books){

            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return binder;
    }
}
