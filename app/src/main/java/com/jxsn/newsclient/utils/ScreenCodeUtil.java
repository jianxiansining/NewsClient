package com.jxsn.newsclient.utils;

import android.content.Context;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.utils
 * @作者:djn
 * @创建日期:2015/9/22 18:54
 * @描述:屏幕大小单位的换算工具类
 *
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class ScreenCodeUtil
{

    /**
     * 单位px转dp
     * @param px
     * @param context
     * @return
     */
    public static int pxTodp(Context context,int px){
        int dpi=context.getResources().getDisplayMetrics().densityDpi;
        int dp = (int) (px* 160f/dpi+0.5f);
        return dp;
    }

    /**
     * 单位dp转px
     * @param dp
     * @param context
     * @return
     */
    public static int dpToPx(Context context,int dp){
        int dpi=context.getResources().getDisplayMetrics().densityDpi;
        int px = (int) (dp * (dpi / 160f)+0.5f);
        return px;
    }
}
