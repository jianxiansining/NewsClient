package com.jxsn.newsclient.controller.news;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jxsn.newsclient.R;
import com.jxsn.newsclient.bean.NewsCenterBean;
import com.jxsn.newsclient.controller.BaseController;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


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
public class NewsListController extends BaseController
{
    @ViewInject(R.id.ui_menu_news_categoriesdata_pager)
    private ViewPager mViewPager;

    //定义菜单条目中的新闻条目中的对象
    private NewsCenterBean.Category mCategoryData;

    public NewsListController(Context context,NewsCenterBean.Category Data)
    {
        super(context);
        this.mCategoryData=Data;
    }

    //初始化视图view
    @Override
    protected View initView(Context context)
    {
        //加载view
        View view = View.inflate(context, R.layout.ui_menu_news_categoriesdata, null);

        //加载所有类的属性和方法
        ViewUtils.inject(this,view);
        return view;
    }

    //初始化数据


    @Override
    public void initData()
    {
        //获得相应条目数据，设置数据
        //tv.setText(mCategoryData.title);
    }


    //解析url数据的方法
    private void analyzeJson(){

    }
}
