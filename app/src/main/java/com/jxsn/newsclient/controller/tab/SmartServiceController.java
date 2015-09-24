package com.jxsn.newsclient.controller.tab;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.jxsn.newsclient.controller.BaseController;
import com.jxsn.newsclient.controller.TabController;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.controller
 * @作者:djn
 * @创建日期:2015/9/23 20:25
 * @描述:TODO
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class SmartServiceController extends TabController
{

    public SmartServiceController(Context context)
    {
        super(context);
    }

    @Override
    protected View initContent(Context context)
    {
        TextView tv=new TextView(context);
        tv.setText("智慧服务");
        tv.setTextColor(Color.RED);
        tv.setTextSize(20);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
    //加载数据


    @Override
    public void initData()
    {
        mIvMenu.setVisibility(View.VISIBLE);
        mTvTitle.setText("智慧服务");
    }
}