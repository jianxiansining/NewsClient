package com.jxsn.newsclient.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.utils
 * @作者:djn
 * @创建日期:2015/9/21 21:25
 * @描述:Key 用于本地存储的数据
 *
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Date$$
 * @修改时间:TODO
 */
public class PreferenceUtil
{
    private static final String LOCAL_DATA="local_data";
    private static SharedPreferences sp;

    //获得存储数据工具的对象
    public static SharedPreferences getPreferences(Context context){
        if(sp==null){
            sp=context.getSharedPreferences(LOCAL_DATA,context.MODE_PRIVATE);
        }
        return sp;
    }
    //设置数据
    public static void setBoolean(Context context,String name,boolean value){

        SharedPreferences sp = getPreferences(context);
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(name,value);
        edit.commit();
    }
    //得到boolean值方式一
    public static boolean getBoolean(Context context,String name,boolean defValue){

        SharedPreferences sp = getPreferences(context);
        return  sp.getBoolean(name, defValue);
    }
    //得到boolean值方式二
    public static boolean getBoolean(Context context,String name){

        return getBoolean(context, name, false);
    }


    //获得字符串数据方式一
    public String getString(Context context,String name,String defValue){

        SharedPreferences sp = getPreferences(context);
        return sp.getString(name, defValue);
    }
    //获得字符串数据方式二
    public String getString(Context context,String name){

        SharedPreferences sp = getPreferences(context);
        return sp.getString(name,null);
    }
    //设置字符串数据的键值存储
    public void setString(Context context,String name,String values){

        SharedPreferences sp = getPreferences(context);
        sp.edit().putString(name,values).commit();
    }
}
