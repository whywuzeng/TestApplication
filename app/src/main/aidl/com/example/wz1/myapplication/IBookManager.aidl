// IBookManager.aidl
package com.example.wz1.myapplication;

// Declare any non-default types here with import statements
import com.example.wz1.myapplication.Book;
interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void addBook(in Book book);
     void getBookList();
}
