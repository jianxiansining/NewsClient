package com.jxsn.newsclient.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.fragment
 * @作者:djn
 * @创建日期:2015/9/23 18:15
 * @描述:定义一个抽象的基类，提取出子类的相同点，提供一个方法去让子类实现自己的不同
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public abstract class BaseFragment extends Fragment
{

    private Context mContext;
    /**
     * 相同点由福利
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    //初始化View,
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mContext=getActivity();
        return initView();
    }

    //加载数据时候调用


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {

        super.onActivityCreated(savedInstanceState);
        initData();
    }


    //提供一个方法出去供子类不同布局的实现
    protected abstract View initView();

    /**
     * 提供一个空方法，子类需要加载数据就调用该方法
     */
    protected  void initData(){}
}
