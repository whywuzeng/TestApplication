package com.example.wz1.myapplication.fragmentTest.viewpager;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

import com.example.wz1.myapplication.fragmentTest.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2019-01-18.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.fragmentTest.viewpager
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private final FragmentManager mFragmentManager;
    //记录sparArray
    private SparseArray<String> mFragmetsMap;
    //更新后sparArray
    private SparseArray<String> getmFragmetsUpdateMap;

    private List<BaseFragment> fragments;

    public BaseFragmentPagerAdapter(FragmentManager fm,List<BaseFragment> fragments) {
        super(fm);
        this.mFragmentManager=fm;
        this.fragments=fragments;
        mFragmetsMap=new SparseArray<>(fragments.size());
        getmFragmetsUpdateMap=new SparseArray<>(fragments.size());
        setFragmentsMap();
        setFragmetsUpdateMap();
    }

    private void setFragmentsMap(){
        mFragmetsMap.clear();
        for (int i = 0; i < fragments.size(); i++) {
            mFragmetsMap.put(Long.valueOf(getItemId(i)).intValue(),String.valueOf(i));
        }
    }

    private void setFragmetsUpdateMap(){
        getmFragmetsUpdateMap.clear();
        for (int i = 0; i < fragments.size(); i++) {
            getmFragmetsUpdateMap.put(Long.valueOf(getItemId(i)).intValue(),String.valueOf(i));
        }
    }

    @Override
    public long getItemId(int position) {
        return fragments.get(position).hashCode();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        int hashCode = object.hashCode();
        String value = getmFragmetsUpdateMap.get(hashCode);
        if (value==null)
        {
            return POSITION_NONE;
        }

        for (int i = 0; i < mFragmetsMap.size(); i++)
        {
            int key = mFragmetsMap.keyAt(i);
            if (key==hashCode)
            {
                String oldVal = mFragmetsMap.get(key);
                if (value .equalsIgnoreCase(oldVal))
                {
                    return POSITION_UNCHANGED;
                }else {
                    return POSITION_NONE;
                }
            }
            //多增加的 要不要 none
//            else {
//                return POSITION_NONE;
//            }
        }
        return POSITION_UNCHANGED;
    }

    /**
     * 更新记录
     */
    private void notifyItem(){
        setFragmetsUpdateMap();
        this.notifyDataSetChanged();
        //notify data 会调用 getItemPosition
        setFragmentsMap();
        //重新记录一遍
    }

    private void removeFragmentInternal(BaseFragment fragment){
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.remove(fragment);
        ft.commitNow();
    }

    public void addFragment(BaseFragment addFragment){
        fragments.add(addFragment);
        notifyItem();
    }

    public void replaceFragment(int oldPosition,BaseFragment fragment){
        BaseFragment oldFragment = fragments.get(oldPosition);
        removeFragmentInternal(oldFragment);
        fragments.set(oldPosition,fragment);
        notifyItem();
    }

    public void removeFragment(int position)
    {
        if (position>=0&&fragments.size()>0&&position<fragments.size()) {
            BaseFragment baseFragment = fragments.get(position);
            fragments.remove(position);
            removeFragmentInternal(baseFragment);
            notifyItem();
        }
    }

    public void clearFragment(){
        fragments.clear();
        notifyItem();
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public List<BaseFragment> getFragments(){
        return fragments;
    }
}
