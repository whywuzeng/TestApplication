package com.example.wz1.myapplication.okhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018-07-09.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.okhttp
 */

public class OkMainActivity extends AppCompatActivity{

    private static final String TAG = "OkMainActivity";

    private static final MediaType post=MediaType.parse("application/json;charset=utf-8");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                getNet();
            }
        }).start();
    }

    private void getNet() {

        OkHttpClient client = new OkHttpClient();

        Request build = new Request.Builder().url("http://www.baidu.com").build();

        Response execute = null;
        try {
            execute = client.newCall(build).execute();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        Log.e(TAG, execute.body().toString());
    }


    private void postNet() {

        OkHttpClient build1 = new OkHttpClient.Builder().addInterceptor(null).build();

        String json = "{ \"wuzeng\":1}";

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(post, json);

        Request build = new Request.Builder().url("http://ydjg.bhome.com.cn/forumapp/show/list").post(requestBody).build();

        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.e(TAG, "postNet: tag" + response.body().toString());
            }
        });

    }
}
