package com.jxsn.newsclient.ui;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.jxsn.newsclient.R;
import com.jxsn.newsclient.fragment.ContentFragment;
import com.jxsn.newsclient.fragment.MenuFragment;
import com.jxsn.newsclient.utils.ScreenCodeUtil;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.com.jxsn.newsclient.ui
 * @作者:djn
 * @创建日期:2015/9/21 20:43
 * @描述:设置向导界面
 *
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Date$$
 * @修改时间:TODO
 */
public class HomeUi extends SlidingFragmentActivity
{

    private SlidingMenu slidingMenu;

    @Override
    public  void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //设置当前界面内容布局
        setContentView(R.layout.ui_home_content);

        //设置当前界面的菜单布局
        setBehindContentView(R.layout.ui_home_menu);

        //获得SlidingMunu对象
        SlidingMenu slidingMenu =getSlidingMenu();

        //设置左侧可以侧滑
        slidingMenu.setMode(SlidingMenu.LEFT);

        //设置菜单显示出的宽度
        slidingMenu.setBehindWidth(ScreenCodeUtil.dpToPx(this, 140));

        //设置可以全局触摸滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //设置渐变精度为0.35f;
        slidingMenu.setFadeDegree(0.35f);

        //使用FrageMent分流两个界面
        initFragment();
    }

    private void initFragment()
    {
        //获得Fragment支持包的管理者
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        //开始一个事务
        FragmentTransaction trans = fm.beginTransaction();

        //加载菜单Fragment
        trans.replace(R.id.ui_home_menu_fragment,new MenuFragment());
        //加载内容Fragment
        trans.replace(R.id.ui_home_content_fragment,new ContentFragment());
        //提交事务
        trans.commit();
    }
}
