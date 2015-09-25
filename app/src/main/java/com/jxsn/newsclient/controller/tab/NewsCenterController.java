package com.jxsn.newsclient.controller.tab;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jxsn.newsclient.bean.NewsCenterBean;
import com.jxsn.newsclient.controller.BaseController;
import com.jxsn.newsclient.controller.TabController;
import com.jxsn.newsclient.controller.menu.InteractMenuController;
import com.jxsn.newsclient.controller.menu.NewsMenuController;
import com.jxsn.newsclient.controller.menu.PicMenuController;
import com.jxsn.newsclient.controller.menu.SubjectMenuController;
import com.jxsn.newsclient.fragment.MenuFragment;
import com.jxsn.newsclient.ui.HomeUi;
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

import java.util.ArrayList;
import java.util.List;


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

    private static final String TAG = "NewsCenterController";

    private FrameLayout mContainer;
    private List<BaseController> mListMenuController;


    public NewsCenterController(Context context)
    {

        super(context);
    }


    @Override
    protected View initContent(Context context)
    {

        mContainer = new FrameLayout(context);
        return mContainer;
    }
    //加载数据


    @Override
    public void initData()
    {
        //初始化存储菜单view的list
        mListMenuController=new ArrayList<BaseController>();

        mIvMenu.setVisibility(View.VISIBLE);
        mTvTitle.setText("新闻中心");

        Log.d(TAG,"加载新闻");
        /**
         * 加载数据
         * 1，url
         * 2，请求方式：GET,POST
         * 2，请求参数
         * 3，请求内容
         */
        //获取Http请求对象
        HttpUtils util = new HttpUtils();

        //请求的路径url
        String url = ConstantsUtil.NEWSCENTER_URL;

        //发送get请求
        util.send(HttpRequest.HttpMethod.GET, url, null, new RequestCallBack<String>()
                {

                    //访问成功的情况
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo)
                    {
                        //获得响应内容，因为响应内容是json格式，所以这里定义字符串名为json
                        String json = responseInfo.result;

                        //解析json数据
                        analyzeJson(json);

/*                        Log.d(LOG,""+json);
                        //将json转换成数据对象
                        Gson gson=new Gson();
                        NewsCenterBean bean = gson.fromJson(json, NewsCenterBean.class);
                        Log.d(LOG,""+bean.extend.get(0));
                        Log.d(LOG,""+bean.data.get(0).children.get(0).title);*/
                    }


                    //访问失败的情况
                    @Override
                    public void onFailure(HttpException e, String s)
                    {

                        Log.d(TAG, "" + e + s);
                    }
                }
        );
    }


    //解析json的方法
    private void analyzeJson(String json)
    {
        //获得Gson的对象
        Gson gson = new Gson();
        //转换json封装到NewsCenterBean中
        NewsCenterBean bean = gson.fromJson(json, NewsCenterBean.class);
        List<NewsCenterBean.MenuBean> listMenuBean = bean.data;

        //获得MenuFragment对象为它设置界面
        HomeUi ui = (HomeUi) mContext;
        //获得MenuFragment对象
        MenuFragment menuFragment = ui.getMenuFragment();
        //设置菜单数据
        menuFragment.setData(listMenuBean);

        //添加数据到当前显示的容器中
        for (int i = 0; i < listMenuBean.size(); i++)
        {
            switch (listMenuBean.get(i).type)
            {
                case 1:
                    mListMenuController.add(new NewsMenuController(mContext));
                    break;
                case 2:
                    mListMenuController.add(new PicMenuController(mContext));
                    break;
                case 3:
                    mListMenuController.add(new InteractMenuController(mContext));
                    break;
                case 10:
                    mListMenuController.add(new SubjectMenuController(mContext));
                    break;
                default:
                    break;
            }
        }
        switchMenu(0);
    }
    //设置切换当前位置显示view的方法
    @Override
    public void switchMenu(int postion)
    {
        //进来之后先清除之前的显示
        mContainer.removeAllViews();
        //将显示的view的位置传输进来，并添加显示的view到当前显示的容器中
        BaseController controller = mListMenuController.get(postion);
        View view = controller.getView();
        mContainer.addView(view);
        //加载数据
        controller.initData();
    }
}
