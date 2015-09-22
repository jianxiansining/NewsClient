package com.jxsn.newsclient.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jxsn.newsclient.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.com.jxsn.newsclient.ui
 * @作者:djn
 * @创建日期:2015/9/21 20:43
 * @描述:设置向导界面
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Date$$
 * @修改时间:TODO
 */
public class GuideUi extends Activity
{

    private ViewPager mViewPager;

    private List<ImageView> mListDatas;

    //定义一个数组，用于存储引导界面的id
    private int[] pages = {R.mipmap.guide1, R.mipmap.guide2, R.mipmap.guide3};

    private GuideAdapter mAdapter;

    private LinearLayout mPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_guide);

        //初始化VIew
        initView();
        //初始化数据
        initData();
        //事件动作
        initEvent();
    }

    private void initView()
    {
        //获得viewPager对象
        mViewPager = (ViewPager) findViewById(R.id.ui_guide_viewpager);
        //获得排列点的父容器
        mPoints = (LinearLayout) findViewById(R.id.ui_guide_point);
    }

    private void initData()
    {
        //初始化集合
        mListDatas = new ArrayList<ImageView>();
        //初始化数据
        for (int i = 0; i < pages.length; i++)
        {
            //定义一个Imageview对象，用来存储引导界面,并设置相关参数
            ImageView iv = new ImageView(this);
            iv.setImageResource(pages[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            //添加到集合中去，
            mListDatas.add(iv);
            //添加点
            View point = new View(this);
            point.setBackgroundResource(R.drawable.viewpager_point_select_shape);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    30, 30);
            if(i!=0){
                params.leftMargin=10;
            }
            mPoints.addView(point, params);
        }

        //获得adapter适配器对象
        mAdapter = new GuideAdapter();
        //ViewPager关联适配器
        mViewPager.setAdapter(mAdapter);
    }

    private void initEvent()
    {

    }

    //定义一个适配器关联数据
    public class GuideAdapter extends PagerAdapter
    {

        //设置数据的大小
        @Override
        public int getCount()
        {

            if (mListDatas != null)
            {
                return mListDatas.size();
            }
            return 0;
        }

        //判断是否是已经加载过的
        @Override
        public boolean isViewFromObject(View view, Object o)
        {

            return view == o;
        }

        //初始化加载数据
        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            //获得当前的数据对象
            ImageView iv = mListDatas.get(position);
            //加载数据并显示到界面上
            container.addView(iv);
            return iv;
        }

        //销毁数据
        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {

            container.removeView((View) object);
        }
    }
}
