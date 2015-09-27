package com.jxsn.newsclient.controller.menu;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jxsn.newsclient.R;
import com.jxsn.newsclient.bean.NewsCenterBean;
import com.jxsn.newsclient.controller.BaseController;
import com.jxsn.newsclient.controller.news.NewsListController;
import com.jxsn.newsclient.ui.HomeUi;
import com.jxsn.newsclient.view.FocusTabPageIndicator;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.Iterator;
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
public class NewsMenuController extends BaseController implements ViewPager.OnPageChangeListener, SlidingMenu.OnOpenListener, SlidingMenu.OnOpenedListener, SlidingMenu.OnCloseListener, SlidingMenu.OnClosedListener
{
    @ViewInject(R.id.ui_menu_news_viewpager)
    private ViewPager mViewPager;

    @ViewInject(R.id.ui_menu_news_indicator)
    private FocusTabPageIndicator indicator;

    private List<NewsCenterBean.Category> mListChildren;

    private NewsMenuAdapter mPagerAdapter;

    private List<OnPageIdleListener> mListListener=new ArrayList<OnPageIdleListener>();


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

        //设置滑动监听,此时这里要的对indicator监听
        indicator.setOnPageChangeListener(this);

        //设置菜单栏的监听
        HomeUi ui= (HomeUi) mContext;
        SlidingMenu slidingMenu = ui.getSlidingMenu();
        slidingMenu.setOnOpenListener(this);
        slidingMenu.setOnOpenedListener(this);
        slidingMenu.setOnCloseListener(this);
        slidingMenu.setOnClosedListener(this);
    }


    /**
     * 监听的方法
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }


    @Override
    public void onPageSelected(int position)
    {
        //获得主页对象
        HomeUi ui= (HomeUi) mContext;
        SlidingMenu slidingMenu = ui.getSlidingMenu();
        //设置当处于第一个条目北京时候,才能像左滑动，显示出菜单
        slidingMenu.setTouchModeAbove(position==0?SlidingMenu.TOUCHMODE_FULLSCREEN:SlidingMenu.TOUCHMODE_NONE);
    }


    @Override
    public void onPageScrollStateChanged(int state)
    {
        //当页面闲置时候，返回给轮播图消息
        notifyListeners();
    }



    //点击箭头跳到下一个条目
    @OnClick(R.id.ui_menu_news_category_arr)
    public void nextArr(View v){

        Toast.makeText(mContext,"你点击了下一个条目",Toast.LENGTH_SHORT).show();
        int item = mViewPager.getCurrentItem();
        mViewPager.setCurrentItem(++item);
    }


    /**
     * 对菜单栏的舰艇1
     */
    @Override
    public void onOpen()
    {
        notifyListeners();
    }


    @Override
    public void onOpened()
    {
        notifyListeners();
    }


    @Override
    public void onClose()
    {
        notifyListeners();
    }


    @Override
    public void onClosed()
    {
        notifyListeners();
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
            //设置标记方便传输到销毁时候，用于该view在销毁时候移除
            view.setTag(controller);
            //为当前条目的轮播图设置监听整体条目的闲置
            addOnPageIdleListener(controller);
            //加载数据
            controller.initData();
            return view;
        }

        //销毁数据
        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            //获得当前销毁的view，并在监听中移除
            View view= (View) object;
            NewsListController controller = (NewsListController) view.getTag();
            removePageIdleListener(controller);
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

    //提供接口
    public interface OnPageIdleListener{
        void OnIdle();
    }

   /* //提供方法出去
    public void setOnPageIdleListener(OnPageIdleListener listener){
        this.mListener=listener;
    }*/
    //设置多个同时监听
    public void addOnPageIdleListener(OnPageIdleListener listener){
        mListListener.add(listener);
    }
    //移除监听
    public void removePageIdleListener(OnPageIdleListener listener){
        mListListener.remove(listener);
    }
    //调用传递的方法，传递当前ViewPager加载的条目的闲置
    public void notifyListeners(){
        Iterator<OnPageIdleListener> it = mListListener.iterator();
        while(it.hasNext()){
            OnPageIdleListener next = it.next();
            next.OnIdle();
        }
    }
}
