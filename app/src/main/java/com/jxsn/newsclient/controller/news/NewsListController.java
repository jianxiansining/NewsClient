package com.jxsn.newsclient.controller.news;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jxsn.newsclient.R;
import com.jxsn.newsclient.bean.NewsCenterBean;
import com.jxsn.newsclient.bean.NewsDataBean;
import com.jxsn.newsclient.controller.BaseController;
import com.jxsn.newsclient.controller.menu.NewsMenuController;
import com.jxsn.newsclient.utils.ConstantsUtil;
import com.jxsn.newsclient.utils.PreferenceUtil;
import com.jxsn.newsclient.utils.ScreenCodeUtil;
import com.jxsn.newsclient.view.FocusViewPager;
import com.jxsn.newsclient.view.RefreshListView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.controller.news
 * @作者:djn
 * @创建日期:2015/9/26 0:47
 * @描述:TODO
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class NewsListController extends BaseController implements ViewPager.OnPageChangeListener, NewsMenuController.OnPageIdleListener
{

    private static final String TAG = "NewsListController";

    @ViewInject(R.id.ui_menu_news_categoriesdata_pager)
    private FocusViewPager mViewPager;

    @ViewInject(R.id.ui_menu_news_categoriesdata_pictitle)
    private TextView mPicTitle;

    @ViewInject(R.id.ui_menu_news_categoriesdata_pager_points)
    private LinearLayout mPointContainer;

    @ViewInject(R.id.ui_menu_news_category_listview)
    private RefreshListView mListView;

    //定义集合，用于存放图片
    private List<NewsDataBean.DataEntity.TopnewsEntity> mTopnews;

    //定义菜单条目中的新闻条目中的对象
    private NewsCenterBean.Category mCategoryData;

    private BitmapUtils mBitmapUtils;

    private AutoPlayHandler mHandler;

    List<NewsDataBean.DataEntity.NewsEntity> mNewsData;


    public NewsListController(Context context, NewsCenterBean.Category Data)
    {

        super(context);
        this.mCategoryData = Data;
    }


    //初始化视图view
    @Override
    protected View initView(Context context)
    {
        //加载view
        View view = View.inflate(context, R.layout.ui_menu_news_categoriesdata, null);

        //加载所有类的属性和方法
        ViewUtils.inject(this, view);

        View headerView = View.inflate(context, R.layout.menu_news_viewpager_pic, null);
        //注入
        ViewUtils.inject(this,headerView);

        //把V轮播图部分添加为listView的头部分
        mListView.addHeaderView(headerView);
        //获得图片加载器
        mBitmapUtils = new BitmapUtils(context);

        mHandler = new AutoPlayHandler();
        return view;
    }

    //初始化数据


    @Override
    public void initData()
    {
        //获得相应条目数据，设置数据
        //tv.setText(mCategoryData.title);
        String url = ConstantsUtil.BASE_URL + mCategoryData.url;
        //获得缓存数据
        String storeData = PreferenceUtil.getString(mContext, url);
        if (!TextUtils.isEmpty(storeData))
        {
            analyzeJson(storeData);
        }
        //加载网络的方法
        loadingNet(url);
    }


    //
    //加载网络url的方法
    private void loadingNet(final String url)
    {

        //获得httpUtil对象
        HttpUtils utils = new HttpUtils();
        //发送数据
        utils.send(HttpRequest.HttpMethod.GET, url, null, new RequestCallBack<String>()
        {
            //访问成功
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo)
            {
                //获得返回的结果内容
                String result = responseInfo.result;

                //缓存数据
                PreferenceUtil.setString(mContext, url, result);
                //解析json数据
                analyzeJson(result);
            }


            //访问失败
            @Override
            public void onFailure(HttpException e, String s)
            {

                Log.d(TAG, "访问网络失败");
                e.printStackTrace();
            }
        });
    }


    //解析json数据的方法
    private void analyzeJson(String json)
    {

        //获得Gson对象
        Gson gson = new Gson();
        //转换gson为数据，并封装在bean中
        NewsDataBean newsDataBean = gson.fromJson(json, NewsDataBean.class);
        //获得图片源数据
        mTopnews = newsDataBean.getData().getTopnews();

        //因为可能有缓存，需要清空一下
        mPointContainer.removeAllViews();
        //设置点的个数
        for (int i = 0; i < mTopnews.size(); i++)
        {
            View v = new View(mContext);
            v.setBackgroundResource(R.drawable.dot_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenCodeUtil.dpToPx(mContext, 15), ScreenCodeUtil.dpToPx(mContext, 15));
            if (i != 0)
            {
                params.leftMargin = ScreenCodeUtil.dpToPx(mContext, 6);
            } else
            {
                v.setBackgroundResource(R.drawable.dot_focus);
                mPicTitle.setText(mTopnews.get(i).getTitle());
            }
            mPointContainer.addView(v, params);
        }
        //获得bean中的图片，并设置轮播图
        mViewPager.setAdapter(new PicViewPager());

        //利用handler的原理设置轮播图
        mHandler.start();
        //设置界面监听
        mViewPager.setOnPageChangeListener(this);
        //设置按下和抬起的监听，用于按下时，停止轮播，抬起时候开始轮播
        mViewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {

                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        mHandler.stop();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.start();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        //获得图文数据
        mNewsData = newsDataBean.getData().getNews();
        //加载图文对应数据
        mListView.setAdapter(new newsDataAdapter());
    }


    //自定义类继承BaseAdapter
    public class newsDataAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {

            if (mNewsData != null)
            {
                return mNewsData.size();
            }
            return 0;
        }


        @Override
        public Object getItem(int position)
        {

            if (mNewsData != null)
            {
                return mNewsData.get(position);
            }
            return null;
        }


        @Override
        public long getItemId(int position)
        {

            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {

            ViewHolder holder = null;
            if (convertView == null)
            {
                //加载view
                convertView = View.inflate(mContext,R.layout.menu_news_data_show, null);
                //初始化holder
                holder = new ViewHolder();
                //设置标记
                convertView.setTag(holder);
                //找到相应控件，并缓存起来
                holder.ivIcon = (ImageView) convertView.findViewById(R.id.menu_news_data_show_icon);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.menu_news_data_show_title);
                holder.tvDate = (TextView) convertView.findViewById(R.id.menu_news_data_show_date);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            //找到相应数据对象
            NewsDataBean.DataEntity.NewsEntity entity = mNewsData.get(position);

            //设置默认的图，如果没有网络图，就也会有显示
            holder.ivIcon.setImageResource(R.drawable.pic_item_list_default);
            //获得网络图片，并设置
            mBitmapUtils.display(holder.ivIcon, entity.getListimage());

            //设置标题
            holder.tvTitle.setText(entity.getTitle());
            //设置日期
            holder.tvDate.setText(entity.getPubdate());
            return convertView;
        }
    }


    //定义ViewHolder,用于缓存view控件
    private class ViewHolder
    {

        ImageView ivIcon;

        TextView tvTitle;

        TextView tvDate;
    }


    //自定义类继承Handler并实现Runnable接口
    public class AutoPlayHandler extends Handler implements Runnable
    {

        @Override
        public void run()
        {

            int item = mViewPager.getCurrentItem();
            if (item == mTopnews.size() - 1)
            {
                item = 0;
            } else
            {
                item++;
            }
            mViewPager.setCurrentItem(item);
            //再次调用run方法，
            postDelayed(this, 2000);
        }


        public void start()
        {
            //因为有缓存，会调用多次，所以需要先移除之前的轮播
            stop();
            postDelayed(this, 2000);
        }


        public void stop()
        {

            removeCallbacks(this);
        }
    }
    //设置当按下去当前viewpager的监听


    //界面监听的方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }


    //当界面被选中时候
    @Override
    public void onPageSelected(int position)
    {
        //获得相应图片的title
        String title = mTopnews.get(position).getTitle();
        //设置title
        mPicTitle.setText(title);

        //设置填充点
        for (int i = 0; i < mTopnews.size(); i++)
        {
            View v = mPointContainer.getChildAt(i);
            if (i == position)
            {
                v.setBackgroundResource(R.drawable.dot_focus);
            } else
            {
                v.setBackgroundResource(R.drawable.dot_normal);
            }
        }
    }


    @Override
    public void onPageScrollStateChanged(int state)
    {

    }


    //定义类继承BaseAdapter
    private class PicViewPager extends PagerAdapter
    {

        //获得条目数量
        @Override
        public int getCount()
        {

            if (mTopnews != null)
            {
                return mTopnews.size();
            }
            return 0;
        }


        //判断是否加载
        @Override
        public boolean isViewFromObject(View view, Object object)
        {

            return view == object;
        }


        //加载数据
        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {

            ImageView iv = new ImageView(mContext);
            //获得当前条目的图片资源
            String imageUrl = mTopnews.get(position).getTopimage();

            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            mBitmapUtils.display(iv, imageUrl);
            container.addView(iv);
            return iv;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {

            container.removeView((View) object);
        }
    }


    //接收到上一层viewpager的闲置
    @Override
    public void OnIdle()
    {

        Log.d(TAG, "接收到闲置" + mCategoryData.title);
        if(mHandler!=null){
            //开始轮播
            mHandler.start();
        }
    }
}
