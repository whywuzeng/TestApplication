package com.example.wz1.myapplication.fragmentTest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wz1.myapplication.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019-01-10.
 * <p>
 * by author wz
 * <p>
 * com.scoket.wz1.scokettest
 */
public class OneAdapterFragment extends BaseFragment {

    private static final String TAG = "OneFragment";
    private static final String BTNTEXT_KEY = "btntext_key";
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.btn_text)
    Button btnText;
    private View rootView;
    public static final int BACKCODE = 5642;
    private static final String STATE_SAVE_ISHIDDED = "state_save_ishidded";
    private String btnTextString=null;

    public static OneAdapterFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString(BTNTEXT_KEY,text);
        OneAdapterFragment fragment = new OneAdapterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG, "onAttach: ");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG, "onActivityCreated: ");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        Bundle arguments = getArguments();
        if (arguments != null) {
            btnTextString = arguments.getString(BTNTEXT_KEY, "");
        }
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_oneadapter;
    }


    @Override
    protected void onBindView(View rootView, Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated: ");
        content.setText(btnTextString);
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "fragment onActivityResult: ");
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == BACKCODE) {
                //事物提交
            }
        }
    }


    @OnClick(R.id.btn_text)
    public void onViewClicked() {
        PagerAdapterTestActivity activity = (PagerAdapterTestActivity)getActivity();
        if (activity!=null) {
            activity.refreshFragment(2, "btn_text");
        }
    }
}
