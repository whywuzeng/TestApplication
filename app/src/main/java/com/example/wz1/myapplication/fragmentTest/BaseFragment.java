package com.example.wz1.myapplication.fragmentTest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019-01-15.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.fragmentTest
 */
public abstract class BaseFragment extends Fragment{

    private static final String TAG = "BaseFragment";
    private static final String STATE_SAVE_ISHIDDED = "state_save_ishidded";
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach: " );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: " );
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: " );
        /**
         * 防止chlid Fragment重叠
         */
        if (savedInstanceState!=null)
        {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            boolean supportHidde = savedInstanceState.getBoolean(STATE_SAVE_ISHIDDED);
            if (supportHidde){
                ft.hide(this);
            }else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    protected abstract Object setLayout();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView =null;

        if (setLayout() instanceof Integer)
        {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        }else if (setLayout() instanceof View)
        {
            rootView = (View) setLayout();
        }else {
            throw new ClassCastException("setLayout must be int or View");
        }
        unbinder = ButterKnife.bind(this, rootView);

        onBindView(rootView,savedInstanceState);
        return rootView;
    }

    protected abstract void onBindView(View rootView, Bundle savedInstanceState);

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_ISHIDDED,isHidden());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder!=null)
        {
            unbinder.unbind();
        }
    }
}
