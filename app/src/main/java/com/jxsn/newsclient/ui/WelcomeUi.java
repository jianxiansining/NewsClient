package com.jxsn.newsclient.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.jxsn.newsclient.R;
import com.jxsn.newsclient.utils.PreferenceUtil;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.com.jxsn.newsclient.ui
 * @作者:djn
 * @创建日期:2015/9/21 20:43
 * @描述:欢迎界面
 *
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Date$$
 * @修改时间:TODO
 */
public class WelcomeUi extends AppCompatActivity
{

    private static final String FIRST_LOADING="first_loading";
    private static final long DELAYANI=3000;

    private ImageView mIvWel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_welcome);

        //初始化View
        initView();
        //初始化数据
        initData();
        //初始化事件
        initEvent();
    }

    private void initView()
    {
        mIvWel = (ImageView) findViewById(R.id.ui_welcome_iv);
        //开始动画
        startAnimation();
    }

    private void initData()
    {

    }

    private void initEvent()
    {

    }
    //欢迎界面做动画的方法
    public void startAnimation(){

        //旋转动画

        RotateAnimation rotate=new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        //透明动画
        AlphaAnimation alpha=new AlphaAnimation(0,1);
        //缩放动画
        ScaleAnimation scale=new ScaleAnimation(0,1,0,1,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        //创建一个动画集合对象
        AnimationSet set=new AnimationSet(true);
        //添加动画
        set.addAnimation(rotate);
        set.addAnimation(alpha);
        set.addAnimation(scale);
        set.setDuration(DELAYANI);


        //设置动画监听
        set.setAnimationListener(new Animation.AnimationListener()
        {

            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {

                //休眠1000毫秒，让用户看着舒服
                new Thread()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(1500);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                        //获得本地记录数据，判断是否是第一次进入
                        boolean first_flag =true;// PreferenceUtil.getBoolean(WelcomeUi.this, FIRST_LOADING, true);
                        if (first_flag)
                        {
                            //向导界面
                            Intent intent = new Intent(WelcomeUi.this, GuideUi.class);
                            startActivity(intent);
                            PreferenceUtil.setBoolean(WelcomeUi.this, FIRST_LOADING, false);
                        } else
                        {
                            //主界面
                            Intent intent = new Intent(WelcomeUi.this, HomeUi.class);
                            startActivity(intent);
                        }
                        //销毁当前界面
                        finish();
                    }
                }.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });
        //开始动画
        mIvWel.startAnimation(set);
    }
}
