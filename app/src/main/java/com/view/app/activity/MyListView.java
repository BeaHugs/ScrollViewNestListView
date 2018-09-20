package com.view.app.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by wangyibo on 2018/9/19.
 */
public class MyListView extends ListView{

    private ScrollView mParent;

    private float mDownY;
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setParent(ScrollView view){
        mParent = view;
    }


    //重写该方法 在按下的时候让父容器不处理滑动事件
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setParentScrollAble(false);
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //
                if (isListViewTop() && ev.getY() - mDownY > 0) {
                    setParentScrollAble(true);
                } else if (isListViewBottom() && ev.getY() - mDownY < 0) {
                    setParentScrollAble(true);
                }
                break;
            case MotionEvent.ACTION_UP:

            case MotionEvent.ACTION_CANCEL:
                setParentScrollAble(true);
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }


    /**
     * @param flag
     */
    private void setParentScrollAble(boolean flag) {
        //requestDisallowInterceptTouchEvent是否禁用事件拦截的功能(默认是false)，

        if (mParent!=null){
            mParent.requestDisallowInterceptTouchEvent(!flag);
        }
    }


    public boolean isListViewTop() {
        boolean result=false;
        //代表是ListView的第一个item   即ListView的顶部
        if(getFirstVisiblePosition()==0){
            //第一个item
            View topChildView = getChildAt(0);
            if (topChildView != null) {
                //listview的顶部   即Y坐标==0
                result=topChildView.getTop()==0;
            }
        }
        return result ;
    }

    public boolean isListViewBottom() {
        boolean result=false;
        //listview最后可见的一个item
        if (getLastVisiblePosition() == (getCount() - 1)) {
            //getLastVisiblePosition() - getFirstVisiblePosition() 可见item的总数   即一个屏幕中的所有item
            View bottomChildView = getChildAt(getLastVisiblePosition() - getFirstVisiblePosition());
            if (bottomChildView != null) {
                //getHeight() listview的高度      最后一个item
                result= (getHeight() >= bottomChildView.getBottom());
            }
        }
        return  result;
    }
}
