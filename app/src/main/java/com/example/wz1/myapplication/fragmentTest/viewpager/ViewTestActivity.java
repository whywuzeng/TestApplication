package com.example.wz1.myapplication.fragmentTest.viewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.wz1.myapplication.R;
import com.example.wz1.myapplication.systembar.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019-01-17.
 * <p>
 * by author wz
 * PagerView view 测试
 * <p>
 * com.example.wz1.myapplication.fragmentTest
 */
public class ViewTestActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_del)
    Button btnDel;
    @BindView(R.id.btn_replace)
    Button btnReplace;
    @BindView(R.id.btn_clear)
    Button btnClear;

    private ArrayList<String> mDataList;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        mDataList = new ArrayList<>(5);
        mDataList.add("Java");
        mDataList.add("Android");
        mDataList.add("C&C++");
        mDataList.add("OC");
        mDataList.add("Swift");

         pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return mDataList.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = LayoutInflater.from(ViewTestActivity.this).inflate(R.layout.item_viewtest_layout, container, false);
                TextView tvContext = view.findViewById(R.id.tv_context);
                tvContext.setText(mDataList.get(position) + "第" + position + "个");
                view.setTag(position);
                container.addView(view);
                return view;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

             @Override
             public int getItemPosition(@NonNull Object object) {
                 if (object instanceof View)
                 {
                    ((View) object).getTag()
                 }
                 return super.getItemPosition(object);
             }
         };

        viewpager.setAdapter(pagerAdapter);
    }

    @Override
    protected int parentContentView() {
        return R.layout.activity_pager_adapter;
    }

    @OnClick(R.id.btn_add)
    public void onBtnAddClicked() {
        mDataList.add("添加的数据");
        pagerAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_del)
    public void onBtnDelClicked() {
        if (check()){
            mDataList.remove(0);
        }
        pagerAdapter.notifyDataSetChanged();
    }

    private boolean check() {
        if (mDataList.size()>0)
        {
            return true;
        }
        return false;
    }

    @OnClick(R.id.btn_replace)
    public void onBtnReplaceClicked() {

    }

    @OnClick(R.id.btn_clear)
    public void onBtnClearClicked() {
        mDataList.clear();
        pagerAdapter.notifyDataSetChanged();
    }


}
