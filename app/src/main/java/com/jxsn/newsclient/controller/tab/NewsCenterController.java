package com.jxsn.newsclient.controller.tab;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

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
import com.jxsn.newsclient.utils.PreferenceUtil;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

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

    private List<NewsCenterBean.MenuBean> listMenuBean;

    private List<NewsCenterBean.MenuBean> mListMenuBean;


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

        Log.d(TAG,"开始11111111111111111111");

        mIvMenu.setVisibility(View.VISIBLE);
        mTvTitle.setText("新闻中心");

        Log.d(TAG, "加载新闻");

        //请求的路径url
        String url = ConstantsUtil.NEWSCENTER_URL;

        /**
         * 判断是否本地有存储缓存的
         * 如果有，表示是第二次进入，加载缓存
         * 如果没有，表示第一次进入，加载网络
         */
        //得到本地缓存的数据
        String urlData = PreferenceUtil.getString(mContext, url);
        //获得本地缓存数据的时间
        long urlDataTime = PreferenceUtil.getLong(mContext, url + "-time");
        if(!TextUtils.isEmpty(urlData)){
            //设置时间
            long delayTime=1000*60;
            //缓存时间+设置时间间隔小于当前时间，加载缓存
            if(urlDataTime+delayTime<System.currentTimeMillis()){
                Log.d(TAG,"过时了,加载过时缓存，更新缓存");
                //加载缓存
                analyzeJson(urlData);
                //更新缓存
                loadingNet(url);
            }else{
                Log.d(TAG,"没有过时,加载缓存");
                //只加载缓存
                analyzeJson(urlData);
            }
        }else{
            Log.d(TAG,"加载网络");
            //加载网络
            loadingNet(url);
        }
    }


    private void loadingNet(final String url)
    {

        /**
         * 加载数据
         * 1，url
         * 2，请求方式：GET,POST
         * 2，请求参数
         * 3，请求内容
         */
        //获取Http请求对象
        HttpUtils util = new HttpUtils();

        //发送get请求
        util.send(HttpRequest.HttpMethod.GET, url, null, new RequestCallBack<String>()
                {

                    //访问成功的情况
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo)
                    {
                        //获得响应内容，因为响应内容是json格式，所以这里定义字符串名为json
                        String json = responseInfo.result;

                        //把urlf访问数据存储，用于缓存
                        PreferenceUtil.setString(mContext,url,json);

                        //存储当前进入的时间，用于判断是否下一次需要重新加载访问
                        PreferenceUtil.setLong(mContext,url+"-time",System.currentTimeMillis());
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
        mListMenuBean = bean.data;

        //获得MenuFragment对象为它设置界面
        HomeUi ui = (HomeUi) mContext;
        //获得MenuFragment对象
        MenuFragment menuFragment = ui.getMenuFragment();
        //设置菜单数据
        menuFragment.setData(mListMenuBean);

        //添加数据到当前显示的容器中
        for (int i = 0; i < mListMenuBean.size(); i++)
        {
            switch (mListMenuBean.get(i).type)
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
        //设置对应位置的标题
        String title = mListMenuBean.get(postion).title;
        mTvTitle.setText(title);
        mContainer.addView(view);
        //加载数据
        controller.initData();
    }
}
