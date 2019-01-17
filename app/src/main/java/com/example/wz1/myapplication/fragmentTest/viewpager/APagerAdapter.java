package com.example.wz1.myapplication.fragmentTest.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2019-01-17.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.fragmentTest.viewpager
 */
public abstract class APagerAdapter<T> extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private List<T> mDatalist;
    private SparseArray<View> mViews;

    public APagerAdapter(Context context,List<T> mDatalist) {
        mLayoutInflater=LayoutInflater.from(context);
        this.mDatalist=mDatalist;
        mViews=new SparseArray<>(this.mDatalist.size());
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mViews.get(position);
        if (view==null)
        {
            view=getView(mLayoutInflater,position);
            view.setTag(position);
            mViews.put(position,view);
        }
        container.addView(view);
        return view;
    }

    protected abstract View getView(LayoutInflater mLayoutInflater, int position);

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public int getCount() {
        return mDatalist.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {

        return super.getItemPosition(object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
