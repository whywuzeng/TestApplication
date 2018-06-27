package com.example.wz1.myapplication.BookBinderTest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wz1.myapplication.IBookManager;

/**
 * Created by Administrator on 2018-06-14.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.Book
 */

public class BookActivity extends AppCompatActivity {

    private IBookManager manager;

    private ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            manager=  IBookManager.Stub.asInterface(service);
            try {
                service.linkToDeath(deathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        //重新绑定
        @Override
        public void binderDied() {
            if (manager == null)
                return;
            manager.asBinder().unlinkToDeath(deathRecipient, 0);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, BookService11.class);
        startService(intent);
    }
}
