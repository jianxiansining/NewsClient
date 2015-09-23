package com.jxsn.newsclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.fragment
 * @作者:djn
 * @创建日期:2015/9/23 13:57
 * @描述:菜单部分的Fragment
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class MenuFragment extends BaseFragment
{
    //实现父类提供的方法，为自己布局
    @Override
    protected View initView()
    {
        TextView tv=new TextView(getActivity());
        tv.setText("菜单界面");
        return tv;
    }
}
