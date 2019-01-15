package com.example.wz1.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.example.wz1.myapplication.fragmentTest.OneFragment;
import com.example.wz1.myapplication.systembar.BaseActivity;

/**
 * Created by Administrator on 2019-01-15.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication
 */
public class FragmentTestActivity extends BaseActivity implements View.OnClickListener{


    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button viewById = (Button) findViewById(R.id.removefragment);
        viewById.setOnClickListener(this);

        //根fragment 需要判断tag 得到 fragment 是否存在
        fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag("oneFragment") == null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            OneFragment oneFragment = OneFragment.newInstance();
            ft.add(R.id.container, oneFragment, "oneFragment");
            ft.addToBackStack("stackoneFragment");
            ft.commit();
        }
    }

    @Override
    protected int parentContentView() {
        return R.layout.activity_fragmenttest;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.removefragment:
                //处理fragment remove  从stack删除  或者 remove
                fragmentManager.popBackStackImmediate("stackoneFragment",FragmentManager.POP_BACK_STACK_INCLUSIVE);
                fragmentManager.popBackStack();

//                FragmentTransaction ft = fragmentManager.beginTransaction();
//                ft.remove();
//                ft.commit();
                break;
                default:
                    break;
        }
    }
}
