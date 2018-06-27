package com.example.wz1.myapplication.button;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Administrator on 2018-05-24.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.myapplication.button
 */

public class HorizontalSrollview extends ViewGroup {

    private int ChildWith;

    private Scroller mScroller;

    private VelocityTracker mVelocityTracker;

    private int  CurrentIndex;

    private int ChildCount;

    private int TouchSlop;

    public HorizontalSrollview(Context context) {
        this(context, null);
    }

    public HorizontalSrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HorizontalSrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        ChildWith = displayMetrics.widthPixels;
        mVelocityTracker= VelocityTracker.obtain();
        mScroller=new Scroller(context);
        TouchSlop= ViewConfiguration.get(context).getScaledTouchSlop();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        ChildCount= getChildCount();

        if (modeWidth == MeasureSpec.AT_MOST) {
            View childAt = getChildAt(0);
            int childCount = getChildCount();

            setMeasuredDimension(childAt.getMeasuredWidth() * childCount, sizeHeight);
        }
        else if (modeHeight == MeasureSpec.AT_MOST) {
            View childAt = getChildAt(0);
            setMeasuredDimension(sizeWidth, childAt.getMeasuredHeight());
        }
        else {
            View childAt = getChildAt(0);
            int childCount = getChildCount();
            int CountWidth = childAt.getMeasuredWidth() * childCount;
            setMeasuredDimension(CountWidth, childAt.getMeasuredHeight());
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            childAt.layout(left, 0, left + childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
            left += ChildWith;
        }
    }

    private float lastInterX;
    private float lastInterY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float rawX = ev.getRawX();
        float rawY = ev.getRawY();
        boolean isOnIntercept=false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isOnIntercept=false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    isOnIntercept = true;
                }
              break;

            case MotionEvent.ACTION_MOVE:
                float Vx = rawX - lastInterX;
                float VY = rawY - lastInterY;
                    if (Math.abs(Vx) > Math.abs(VY)) {
                        isOnIntercept=true;
                    }
                    else {
                        isOnIntercept=false;
                    }
                    break;

            case MotionEvent.ACTION_UP:
                isOnIntercept=false;
                break;

        }
        /**
         * 思考下 这个问题
         */
//        touchRawX = rawX;
//        touchRawY = rawY;
        lastInterX = rawX;
        lastInterY = rawY;
        return isOnIntercept;
    }

    private float touchRawX;
    private float touchRawY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        float rawX = event.getRawX();
        float rawY = event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                int deltX = (int) (rawX - touchRawX);
                float deltY = rawY - touchRawY;
                scrollBy(-deltX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if (Math.abs(xVelocity) > 50) {
                    if (xVelocity > 0) {
                        CurrentIndex--;
                        CurrentIndex = Math.max(0, CurrentIndex);
                    }
                    else {
                        CurrentIndex++;
                        CurrentIndex = Math.min(CurrentIndex, ChildCount-1);
                    }
                }else {
                    CurrentIndex= (scrollX+ChildWith/2)/ChildWith;
                }

                int dx = CurrentIndex * ChildWith-scrollX;
                mScroller.startScroll(getScrollX(),0,dx,0,500);
                invalidate();
                mVelocityTracker.clear();
                break;
        }
        touchRawX = rawX;
        touchRawY = rawY;
        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset())
        {
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mVelocityTracker.recycle();
    }
}
