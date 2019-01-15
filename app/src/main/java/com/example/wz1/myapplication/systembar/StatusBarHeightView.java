package com.example.wz1.myapplication.systembar;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.wz1.myapplication.R;

/**
 * Created by Administrator on 2019-01-15.
 * <p>
 * by author wz
 * 功能:状态栏高度View,用于沉浸占位
 * <p>
 * com.example.wz1.myapplication.systembar
 */
public class StatusBarHeightView extends LinearLayout {

    private int statusBarHeight;
    private int type;


    public StatusBarHeightView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StatusBarHeightView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        //使用getResources().getIdentifier()方法来获取该id。然后再使用该id进行相关的操作。
        int resId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            if (resId > 0)
            {
              statusBarHeight=  getResources().getDimensionPixelSize(resId);
            }
        }else {
            statusBarHeight =0 ;
        }

        if (attrs!=null)
        {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.StatusBarHeightView);
             type = typedArray.getInt(R.styleable.StatusBarHeightView_use_type, 0);
            typedArray.recycle();
        }

        if (type == 1)
        {
            setPadding(getPaddingLeft(),statusBarHeight,getPaddingRight(),getPaddingBottom());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (type ==0 )
        {
            setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec),statusBarHeight);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
