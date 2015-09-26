package com.jxsn.newsclient.controller.menu;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.jxsn.newsclient.R;
import com.jxsn.newsclient.bean.NewsCenterBean;
import com.jxsn.newsclient.controller.BaseController;
import com.jxsn.newsclient.controller.news.NewsListController;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.viewpagerindicator.TabPageIndicator;

import java.util.List;


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
    @ViewInject(R.id.ui_menu_news_viewpager)
    private ViewPager mViewPager;

    @ViewInject(R.id.ui_menu_news_indicator)
    private TabPageIndicator indicator;

    private List<NewsCenterBean.Category> mListChildren;

    private NewsMenuAdapter mPagerAdapter;

    public NewsMenuController(Context context,List<NewsCenterBean.Category> listData)
    {
        super(context);
        this.mListChildren=listData;
    }

    //初始化View
    @Override
    protected View initView(Context context)
    {
        //加载view
        View view= View.inflate(context, R.layout.ui_menu_news,null);

        //加载所有的类的方法和属性
        ViewUtils.inject(this,view);
        return view;
    }

    //加载数据
    @Override
    public void initData()
    {
        //初始化适配器对象
        mPagerAdapter=new NewsMenuAdapter();
        //关联适配器
        mViewPager.setAdapter(mPagerAdapter);
        //设置indicator绑定ViewPager
        indicator.setViewPager(mViewPager);
    }

    //创建一个类继承BaseAdapter
    private class NewsMenuAdapter extends PagerAdapter{

        //获得加载的条目数量
        @Override
        public int getCount()
        {
            if(mListChildren!=null){
                return mListChildren.size();
            }
            return 0;
        }

        //判断是否已经加载过了
        @Override
        public boolean isViewFromObject(View view, Object object)
        {

            return view==object;
        }

        //加载数据
        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            //获得当前位置的bean
            NewsCenterBean.Category bean = mListChildren.get(position);
            //获得对应构造函数对象
            NewsListController controller=new NewsListController(mContext,bean);
            //获得相应的view
            View view = controller.getView();
            //添加view到容器中
            container.addView(view);
            //加载数据
            controller.initData();
            return view;
        }

        //销毁数据
        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View) object);
        }

        //获得title的数目
        @Override
        public CharSequence getPageTitle(int position) {
            if(mListChildren!=null){
                return mListChildren.get(position).title;
            }
            return null;
        }
    }
}
