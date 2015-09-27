package com.jxsn.newsclient.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.view
 * @作者:djn
 * @创建日期:2015/9/27 13:17
 * @描述:TODO
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class FocusViewPager extends ViewPager
{

    float mDownX;
    float mDownY;

    private int mViewPagerCount;
    public FocusViewPager(Context context)
    {

        super(context);
    }


    public FocusViewPager(Context context, AttributeSet attrs)
    {

        super(context, attrs);

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        mViewPagerCount=getAdapter().getCount();
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                //当按下去时候，不需要父容器拦截
                getParent().requestDisallowInterceptTouchEvent(true);

                mDownX = ev.getX();
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX=ev.getX();
                float moveY=ev.getY();
                //如果滑动的x距离大于y距离，
                if(Math.abs(moveX-mDownX)>Math.abs(moveY-mDownY)){
                    //如果当前是第一个界面，并且从左往右滑动，父容器拦截
                    if(moveX>mDownX && getCurrentItem()==0){
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    //如果当前是最后一个界面，并且从右往左滑动，父容器拦截
                    else if(moveX<mDownX && getCurrentItem()==mViewPagerCount-1){
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                    //其他情况不拦截
                    else{
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                else{
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
