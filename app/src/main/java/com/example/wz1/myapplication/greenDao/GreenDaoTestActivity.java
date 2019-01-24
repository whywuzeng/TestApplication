package com.example.wz1.myapplication.greenDao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.example.wz1.myapplication.R;
import com.example.wz1.myapplication.systembar.BaseActivity;
import com.example.wz1.myapplication.tinker.TinkerApplicationLike;

import butterknife.BindView;

/**
 * Created by Administrator on 2019-01-24.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.greenDao
 */
public class GreenDaoTestActivity extends BaseActivity {


    @BindView(R.id.ll_head)
    LinearLayout llHead;
    @BindView(R.id.ll_content)
    LinearLayout llContent;

    @Override
    protected int parentContentView() {
        return R.layout.greendao_activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        DaoSession session = TinkerApplicationLike.getDaoSession();
        UserDao userDao = session.getUserDao();
        for (int i = 0; i < 10; i++) {
            userDao.insert(new User(i,"key"+i,"name"+i,i+1));
        }
    }
}
