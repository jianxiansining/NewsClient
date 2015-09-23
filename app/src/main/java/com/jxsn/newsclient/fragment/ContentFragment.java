package com.jxsn.newsclient.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jxsn.newsclient.R;
import com.jxsn.newsclient.controller.BaseController;
import com.jxsn.newsclient.controller.tab.GovController;
import com.jxsn.newsclient.controller.tab.HomeController;
import com.jxsn.newsclient.controller.tab.NewsCenterController;
import com.jxsn.newsclient.controller.tab.SettingController;
import com.jxsn.newsclient.controller.tab.SmartServiceController;
import com.jxsn.newsclient.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.fragment
 * @作者:djn
 * @创建日期:2015/9/23 13:57
 * @描述:内容部分Fragment
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class ContentFragment extends BaseFragment
{

    private NoScrollViewPager mViewPager;

    private RadioGroup mRadioGroup;

    List<BaseController> mListController;

    private int mCurrentPosition;
    //实现父类提供的方法，为自己布局
    @Override
    protected View initView()
    {
        //加载内容底端按钮选项组
        View view = View.inflate(getActivity(), R.layout.ui_content_bottomradio, null);
        //加载viewPager
        mViewPager = (NoScrollViewPager) view.findViewById(R.id.ui_home_content_pager);
        //加载单选按钮组
        mRadioGroup = (RadioGroup) view.findViewById(R.id.ui_home_content_bottom_radio);
        return view;
    }


    //需要加载数据
    protected void initData()
    {
        //初始化list对象
        mListController = new ArrayList<BaseController>();

        mListController.add(new HomeController(getActivity()));
        mListController.add(new NewsCenterController(getActivity()));
        mListController.add(new SmartServiceController(getActivity()));
        mListController.add(new GovController(getActivity()));
        mListController.add(new SettingController(getActivity()));
        //关联适配器
        mViewPager.setAdapter(new ContentAdapter());

        //默认选中内容界面底端的第一个按钮
        mRadioGroup.check(R.id.ui_home_content_bottom_home);

        //设置选中按钮按钮的监听
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {

                switch (checkedId)
                {
                    case R.id.ui_home_content_bottom_home:
                        mCurrentPosition=0;
                        break;
                    case R.id.ui_home_content_bottom_news:
                        mCurrentPosition=1;
                        break;
                    case R.id.ui_home_content_bottom_smart:
                        mCurrentPosition=2;
                        break;
                    case R.id.ui_home_content_bottom_gov:
                        mCurrentPosition=3;
                        break;
                    case R.id.ui_home_content_bottom_set:
                        mCurrentPosition=4;
                        break;
                    default:
                        break;
                }
                mViewPager.setCurrentItem(mCurrentPosition);
            }
        });
    }


    //自定义一个类继承PagerAdapter
    private class ContentAdapter extends PagerAdapter
    {

        //用于计算数据大小
        @Override
        public int getCount()
        {

            if (mListController != null)
            {
                return mListController.size();
            }
            return 0;
        }


        //用于判断是否加载数据
        @Override
        public boolean isViewFromObject(View view, Object object)
        {

            return view == object;
        }


        //加载数据
        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            //获得当前位置在集合中对应的对象
            BaseController baseController = mListController.get(position);
            //加载数据
            baseController.initData();
            //获得当前显示的view
            View view = baseController.getView();
            //添加当前view到该容器中
            container.addView(view);
            //显示view
            return view;
        }


        //销毁数据
        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {

            container.removeView((View) object);
        }
    }
}
