package com.jxsn.newsclient.controller;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jxsn.newsclient.R;
import com.jxsn.newsclient.ui.HomeUi;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.controller
 * @作者:djn
 * @创建日期:2015/9/23 21:31
 * @描述:tab对应Controller的基类
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public abstract class TabController extends BaseController
{


    protected TextView mTvTitle;

    protected ImageView mIvMenu;

    private FrameLayout mMiddleContainer;


    public TabController(Context context)
    {
        super(context);
    }


    @Override
    protected View initView(Context context)
    {
        //加载view
       View view = View.inflate(context, R.layout.ui_content_title, null);
        //获得view控件
        mTvTitle = (TextView) view.findViewById(R.id.ui_content_top_title);
        mIvMenu = (ImageView) view.findViewById(R.id.ui_content_top_menu);
        mMiddleContainer = (FrameLayout) view.findViewById(R.id.ui_content_middle_content);

        //将不同显示的暴露出去,由子类去完成后，添加到FrameLayout容器中
        mMiddleContainer.addView(initContent(context));

        //点击top上menu的监听动作
        mIvMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /**
                 * 获得主界面的对象，并进而获得SlidingMenu对象，操作菜单的显示或隐藏
                 */
                HomeUi ui= (HomeUi) mContext;
                SlidingMenu slidingMenu = ui.getSlidingMenu();
                slidingMenu.toggle();
            }
        });
        return view;
    }

    //提供一个抽象方法，由子类去完成
    protected abstract View initContent(Context context);
}
