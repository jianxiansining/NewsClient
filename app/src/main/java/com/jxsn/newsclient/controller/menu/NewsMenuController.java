package com.jxsn.newsclient.controller.menu;

import android.content.Context;
import android.view.View;

import com.jxsn.newsclient.R;
import com.jxsn.newsclient.controller.BaseController;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.controller.menu
 * @作者:djn
 * @创建日期:2015/9/24 16:29
 * @描述:TODO
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class NewsMenuController extends BaseController
{

    public NewsMenuController(Context context)
    {

        super(context);
    }

    //初始化View
    @Override
    protected View initView(Context context)
    {
        //加载view
        View view= View.inflate(context, R.layout.ui_menu_news,null);

        //TODO viewPager控件的注入
        return view;
    }

    //加载数据
    @Override
    public void initData()
    {

    }
}
