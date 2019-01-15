package com.example.wz1.myapplication.fragmentTest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.wz1.myapplication.R;

/**
 * Created by Administrator on 2019-01-10.
 * <p>
 * by author wz
 * <p>
 * com.scoket.wz1.scokettest
 */
public class TwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Button viewById = (Button) findViewById(R.id.btn_backactivity);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_OK);
               onBackPressed();
            }
        });
    }
}
