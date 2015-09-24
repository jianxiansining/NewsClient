package com.jxsn.newsclient.controller.tab;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.jxsn.newsclient.controller.BaseController;
import com.jxsn.newsclient.controller.TabController;
import com.jxsn.newsclient.utils.ConstantsUtil;
import com.jxsn.newsclient.utils.OkHttpClientManager;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.controller
 * @作者:djn
 * @创建日期:2015/9/23 20:25
 * @描述:TODO
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class NewsCenterController extends TabController
{

    private static final String LOG ="NewsCenterController";


    public NewsCenterController(Context context)
    {
        super(context);
    }

    @Override
    protected View initContent(Context context)
    {
        TextView tv=new TextView(context);
        tv.setText("新闻中心");
        tv.setTextColor(Color.RED);
        tv.setTextSize(20);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
    //加载数据

    @Override
    public void initData()
    {
        mIvMenu.setVisibility(View.VISIBLE);
        mTvTitle.setText("新闻中心");


        /**
         * 加载数据
         * 1，url
         * 2，请求方式：GET,POST
         * 2，请求参数
         * 3，请求内容
         */
        //获取Http请求对象
        HttpUtils util=new HttpUtils();

        //请求的路径url
        String url= ConstantsUtil.NEWSCENTER_URL;

        //发送get请求
        util.send(HttpRequest.HttpMethod.GET, url, null, new RequestCallBack<String>(){

                    //访问成功的情况
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo)
                    {
                        //获得响应内容，因为响应内容是json格式，所以这里定义字符串名为json
                        String json = responseInfo.result;
                        Log.d(LOG,""+json);
                    }

                    //访问失败的情况
                    @Override
                    public void onFailure(HttpException e, String s)
                    {
                        Log.d(LOG,""+e+s);
                    }
                }
        );
    }
}
