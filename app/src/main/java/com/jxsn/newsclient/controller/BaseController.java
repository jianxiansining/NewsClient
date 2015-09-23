package com.jxsn.newsclient.controller;

import android.content.Context;
import android.view.View;

import com.jxsn.newsclient.ui.HomeUi;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.controller
 * @作者:djn
 * @创建日期:2015/9/23 20:17
 * @描述:TODO
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public abstract class BaseController
{
    protected Context mContext;
    private View mView;
    //构造函数
    public BaseController(Context context){
        mContext=context;
        mView=initView(context);
    }

    //提供显示的View
    protected abstract View initView(Context context);

    //提供一个方法在其他类中调用可以获得View
    public View getView(){
        return mView;
    }

    //提供一个空方法，如果子类要去初始化数据，就可以复写该方法
    public void initData(){}
}
