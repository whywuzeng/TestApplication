package com.example.wz1.myapplication.fragmentTest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.example.wz1.myapplication.R;
import com.example.wz1.myapplication.systembar.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2019-01-17.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.fragmentTest
 */
public class PagerAdapterTestActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    boolean[] fragmentsUpdateFlag = { false, false, false, false };
    private Fragment[] fragments=new Fragment[4];
    private ParentViewPager parentViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i=0;i<4;i++)
        {
            OneAdapterFragment fragment = OneAdapterFragment.newInstance(i + "个页面");
            fragments[i]=(fragment);
        }
         parentViewPager = new ParentViewPager(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(parentViewPager);
    }

    public void refreshFragment(int position,String text){
        OneAdapterFragment fragment = OneAdapterFragment.newInstance(position + "个页面"+"改变"+text);
        fragments[position]=fragment;
        fragmentsUpdateFlag[position] = true;
        parentViewPager.notifyDataSetChanged();
    }

    @Override
    protected int parentContentView() {
        return R.layout.activity_pager_adapter;
    }

    class ParentViewPager extends FragmentPagerAdapter{

        private final FragmentManager PfragmentManager;
        Fragment[] fragments;

        public ParentViewPager(FragmentManager fm, @NonNull Fragment[] fragments) {
            super(fm);
            this.fragments=fragments;
            PfragmentManager =fm;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //得到缓存的fragment
            Object o = super.instantiateItem(container, position);
            if (o ==null)
            {
                return o;
            }
            Fragment fragment = (Fragment) o;
            if (fragment==null)
            {
                return fragment;
            }
            String fragmentTag = fragment.getTag();
            //如果这个fragment需要更新
            if (fragmentsUpdateFlag[position%fragmentsUpdateFlag.length])
            {
                FragmentTransaction ft = PfragmentManager.beginTransaction();
                ft.remove(fragment);
                fragment = fragments[position%fragmentsUpdateFlag.length];
                ft.add(container.getId(),fragment,fragmentTag);
                ft.attach(fragment);
                ft.commit();

                fragmentsUpdateFlag[position%fragmentsUpdateFlag.length]=false;
            }
            return fragment;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }
    }
}
