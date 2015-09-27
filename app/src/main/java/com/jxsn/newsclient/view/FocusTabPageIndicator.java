package com.jxsn.newsclient.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.viewpagerindicator.TabPageIndicator;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.view
 * @作者:djn
 * @创建日期:2015/9/27 13:39
 * @描述:TODO
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class FocusTabPageIndicator extends TabPageIndicator
{

    public FocusTabPageIndicator(Context context)
    {

        super(context);
    }


    public FocusTabPageIndicator(Context context, AttributeSet attrs)
    {

        super(context, attrs);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        // 请求父容器不要拦截touch
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
