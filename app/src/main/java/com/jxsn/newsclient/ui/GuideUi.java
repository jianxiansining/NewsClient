package com.jxsn.newsclient.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jxsn.newsclient.R;
import com.jxsn.newsclient.utils.PreferenceUtil;
import com.jxsn.newsclient.utils.ScreenCodeUtil;

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

    private View mScrollPoint;

    private TextView mStart;

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
        //移动的点
        mScrollPoint = findViewById(R.id.ui_guide_scrollpoint);
        //开始进入阅读界面的按钮
        mStart = (TextView) findViewById(R.id.ui_guide_start);
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
            point.setBackgroundResource(R.drawable.viewpager_point_normal_shape);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenCodeUtil.dpToPx(this,15),ScreenCodeUtil.dpToPx(this,15));
            if(i!=0){
                params.leftMargin=ScreenCodeUtil.dpToPx(this,10);
            }
            mPoints.addView(point, params);
        }

        //获得adapter适配器对象
        mAdapter = new GuideAdapter();
        //ViewPager关联适配器
        mViewPager.setAdapter(mAdapter);

        //获得点与点之间的间距
        final int pointDistance = ScreenCodeUtil.dpToPx(this, 25);
        //设置监听ViewPager的事件
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
                //1,获得Viewpage当前图移动的比例  :positionOffset
                //2,实时获得点到另外一个点过程中移动的距离
                int  pointMove = (int) (pointDistance * positionOffset+position*pointDistance+0.5f);

                //获得父容器的layout给予的参数
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mScrollPoint.getLayoutParams();
                params.leftMargin=pointMove;
                //实时更新移动的点
                mScrollPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position)
            {
                if(position==mListDatas.size()-1){
                    mStart.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    private void initEvent()
    {
        //开始阅读按钮的监听事件
        mStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PreferenceUtil.setBoolean(GuideUi.this, WelcomeUi.FIRST_LOADING, false);
                //跳转到Home页面
                Intent intent=new Intent(GuideUi.this,HomeUi.class);
                startActivity(intent);
                //销毁当前界面
                finish();
            }
        });
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
