package com.example.wz1.myapplication.EventClick;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2018-05-23.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.EventClick
 */

public class ViewClick extends LinearLayout{
    public ViewClick(Context context) {
        super(context);
    }

    public ViewClick(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewClick(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    public boolean dispatchTouchEvent(MotionEvent ev)
//    {
//        boolean consume=false;
//        if (onInterceptTouchEvent(ev))
//        {
//              if(touchListener!=null)
    //    {
    //        touchListener.touch();
    //    }else{
    //        consume=onTouchEvent(ev);
    //    }else{
    //        clickListener();
    //    }
//
//        }else{
//            consume= child.dispatchtouchevent(ev);
//        }
//
//        return consume;
//    }

}
