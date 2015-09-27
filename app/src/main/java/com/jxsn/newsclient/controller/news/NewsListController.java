package com.jxsn.newsclient.controller.news;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jxsn.newsclient.R;
import com.jxsn.newsclient.bean.NewsCenterBean;
import com.jxsn.newsclient.bean.NewsDataBean;
import com.jxsn.newsclient.controller.BaseController;
import com.jxsn.newsclient.utils.ConstantsUtil;
import com.jxsn.newsclient.utils.ScreenCodeUtil;
import com.jxsn.newsclient.view.FocusViewPager;
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
public class NewsListController extends BaseController implements ViewPager.OnPageChangeListener
{

    private static final String TAG = "NewsListController";

    @ViewInject(R.id.ui_menu_news_categoriesdata_pager)
    private FocusViewPager mViewPager;

    @ViewInject(R.id.ui_menu_news_categoriesdata_pictitle)
    private TextView mPicTitle;

    @ViewInject(R.id.ui_menu_news_categoriesdata_pager_points)
    private LinearLayout mPointContainer;

    //定义集合，用于存放图片
    private List<NewsDataBean.DataEntity.TopnewsEntity> mTopnews;

    //定义菜单条目中的新闻条目中的对象
    private NewsCenterBean.Category mCategoryData;

    private BitmapUtils mBitmapUtils;


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

        //获得图片加载器
        mBitmapUtils = new BitmapUtils(context);
        return view;
    }

    //初始化数据

    @Override
    public void initData()
    {
        //获得相应条目数据，设置数据
        //tv.setText(mCategoryData.title);
        String url = ConstantsUtil.BASE_URL + mCategoryData.url;
        //加载网络的方法
        loadingNet(url);
    }


    //
    //加载网络url的方法
    private void loadingNet(String url)
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

        //设置点的个数
        for (int i = 0; i <mTopnews.size(); i++)
        {
            View v=new View(mContext);
            v.setBackgroundResource(R.drawable.dot_normal);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ScreenCodeUtil.dpToPx(mContext,15),ScreenCodeUtil.dpToPx(mContext,15));
            if(i!=0){
                params.leftMargin=ScreenCodeUtil.dpToPx(mContext,6);
            }else{
                v.setBackgroundResource(R.drawable.dot_focus);
                mPicTitle.setText(mTopnews.get(i).getTitle());
            }
            mPointContainer.addView(v,params);
        }
        //获得bean中的图片，并设置轮播图
        mViewPager.setAdapter(new PicViewPager());

        //设置界面监听
        mViewPager.setOnPageChangeListener(this);
    }


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
        for (int i = 0; i <mTopnews.size(); i++)
        {
            View v = mPointContainer.getChildAt(i);
            if(i==position){
                v.setBackgroundResource(R.drawable.dot_focus);
            }else{
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
}
