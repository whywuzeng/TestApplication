package com.example.wz1.myapplication.fragmentTest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.wz1.myapplication.R;

import java.util.Objects;

/**
 * Created by Administrator on 2019-01-10.
 * <p>
 * by author wz
 * <p>
 * com.scoket.wz1.scokettest
 */
public class OneFragment extends Fragment {

    private static final String TAG = "OneFragment";
    private View rootView;
    public static final int BACKCODE = 5642;
    private static final String STATE_SAVE_ISHIDDED = "state_save_ishidded";

    public static OneFragment newInstance() {

        Bundle args = new Bundle();

        OneFragment fragment = new OneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private Handler handler =new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e(TAG, "handleMessage: OneRunable 延時 任务已经调用完" );
            FragmentActivity activity = getActivity();
            if (activity==null)
            {
                Log.e(TAG, "handleMessage: activity==null" );
            }else {
                Toast.makeText(activity, "dskjfhkj", Toast.LENGTH_SHORT).show();
            }
        }
    };


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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: " );
        Thread thread = new Thread(new OneRunable(handler));
        thread.start();
         rootView = inflater.inflate(R.layout.fragment_one, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated: " );
        final Button btnTo = rootView.findViewById(R.id.btn_toctivity);
        btnTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Objects.requireNonNull(getActivity()),TwoActivity.class);
                startActivityForResult(intent,BACKCODE);
            }
        });

        Button target = (Button) rootView.findViewById(R.id.btn_totarget);
        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                OneTargetFragment oneTargetFragment = OneTargetFragment.newInstance();
                ft.replace(R.id.container,oneTargetFragment,"OneTargetFragment");
                ft.addToBackStack("");
                ft.commit();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_ISHIDDED,isHidden());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: " );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: " );
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView: " );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG, "onDetach: " );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "fragment onActivityResult: " );
        if (resultCode== Activity.RESULT_OK)
        {
            if (requestCode==BACKCODE)
            {
                //事物提交
            }
        }
    }
}
