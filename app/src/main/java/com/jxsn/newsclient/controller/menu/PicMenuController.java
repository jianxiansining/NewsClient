package com.jxsn.newsclient.controller.menu;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jxsn.newsclient.controller.BaseController;
import com.jxsn.newsclient.controller.TabController;


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
public class PicMenuController extends BaseController
{

    public PicMenuController(Context context)
    {

        super(context);
    }

    @Override
    protected View initView(Context context)
    {

        TextView tv=new TextView(context);
        tv.setText("组图界面");
        return tv;
    }
}
