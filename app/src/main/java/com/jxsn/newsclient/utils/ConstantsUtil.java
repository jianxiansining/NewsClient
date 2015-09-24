package com.jxsn.newsclient.utils;

/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.utils
 * @作者:djn
 * @创建日期:2015/9/24 13:27
 * @描述:存储常量
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public interface ConstantsUtil
{
    //定义一个常量值，赋值为常用的url
    String BASE_URL="http://188.188.7.6:8080/news";

    //新闻中心的url
    String NEWSCENTER_URL=BASE_URL+"/categories.json";
}
