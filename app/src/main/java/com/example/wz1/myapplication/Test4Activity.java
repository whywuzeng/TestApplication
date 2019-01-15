package com.example.wz1.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.wz1.myapplication.glideround.GlideRoundTransform;
import com.example.wz1.myapplication.glideround.RoundedCornersTransformation;
import com.example.wz1.myapplication.systembar.BaseActivity;
import com.example.wz1.myapplication.systembar.StatusBarUtil;

/**
 * Created by Administrator on 2018-05-30.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication
 */

public class Test4Activity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setRootViewFitsSystemWindows(this,false);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar!=null)
        supportActionBar.hide();

        ImageView icon1 = (ImageView) findViewById(R.id.icon1);
        ImageView icon2 = (ImageView) findViewById(R.id.icon2);
        ImageView icon3 = (ImageView) findViewById(R.id.icon3);

        RequestOptions options =new RequestOptions()
                .transform(new MultiTransformation<Bitmap>(new CenterCrop(),new RoundedCornersTransformation(8,5)));

        RequestOptions options1 = new RequestOptions().bitmapTransform(new MultiTransformation<Bitmap>(new CenterCrop(), new GlideRoundTransform()));


        Glide.with(this).load(R.drawable.demo_org).apply(options1).into(icon1);
        Glide.with(this).load(R.drawable.demo_org).apply(options1).into(icon2);
        Glide.with(this).load(R.drawable.demo_org).apply(options1).into(icon3);
    }

    @Override
    protected int parentContentView() {
        return R.layout.activity_test5;
    }
}
