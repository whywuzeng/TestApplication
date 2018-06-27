package com.example.wz1.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wz1.myapplication.button.HorizontalSrollview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-05-24.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication
 */

public class Test2Activity extends AppCompatActivity {

    private HorizontalSrollview horizaon1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        horizaon1=(HorizontalSrollview) findViewById(R.id.horizaon1);
        for (int i=0;i<3;i++)
        {
            View inflate = LayoutInflater.from(this).inflate(R.layout.child_layout, null, false);
            TextView viewById = (TextView)inflate.findViewById(R.id.tv_topcontent);
            viewById.setText("page"+i+1);
            horizaon1.addView(inflate);

            initData(inflate);
        }

    }

    private void initData(View inflate) {
        ListView viewById = (ListView)inflate.findViewById(R.id.listview);
        List<String> arrayList=new ArrayList<>();
        for (int i=1;i<30;i++)
        {
            arrayList.add("内容"+i);
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, R.layout.item_childlayout, R.id.item_tvcontent, arrayList);
        viewById.setAdapter(stringArrayAdapter);
        viewById.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Test2Activity.this,view.getTextAlignment()+"position: "+position,Toast.LENGTH_SHORT);
            }
        });
    }
}
