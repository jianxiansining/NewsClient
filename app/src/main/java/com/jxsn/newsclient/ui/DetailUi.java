package com.jxsn.newsclient.ui;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.jxsn.newsclient.R;
import com.jxsn.newsclient.controller.news.NewsListController;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.ui
 * @作者:djn
 * @创建日期:2015/9/29 22:59
 * @描述:TODO
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class DetailUi extends Activity
{

    @ViewInject(R.id.ui_content_data_detail_back)
    private ImageView mBack;
    @ViewInject(R.id.ui_content_data_detail_size)
    private ImageView mSize;
    @ViewInject(R.id.ui_content_data_detail_share)
    private ImageView mShare;
    @ViewInject(R.id.ui_content_data_detail_web)
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_content_data_detail);

        //注入view信息。映射所有当前类的属性和方法
        ViewUtils.inject(this);

        //初始化数据
        initData();
    }


    private void initData()
    {
        //获得传输过来的数据
        String url = getIntent().getStringExtra(NewsListController.DETAIL_URL);

        //设置参数
        WebSettings set=mWebView.getSettings();
        set.setJavaScriptEnabled(true);//能显示JavaScript样式
        set.setUseWideViewPort(true);//设置双击可以放大缩小
        set.setBuiltInZoomControls(true);//设置显示放大缩小按钮
        //加载网页
        mWebView.loadUrl(url);
    }
}
