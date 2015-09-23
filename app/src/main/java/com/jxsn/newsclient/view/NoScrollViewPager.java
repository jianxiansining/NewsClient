package com.jxsn.newsclient.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.view
 * @作者:djn
 * @创建日期:2015/9/23 21:03
 * @描述:不可以滑动的ViewPager
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class NoScrollViewPager extends ViewPager
{

    public NoScrollViewPager(Context context)
    {

        this(context, null);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    /**
     *     复写分发和拦截的方法:父类不拦截，但还是要分发下去,设置当前view不消费
     */



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {

        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {

        return false;
    }
}
