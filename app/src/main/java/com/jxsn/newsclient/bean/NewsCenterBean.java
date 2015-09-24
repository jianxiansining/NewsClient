package com.jxsn.newsclient.bean;

import java.util.List;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.bean
 * @作者:djn
 * @创建日期:2015/9/24 13:46
 * @描述:新闻中心数据的bean
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class NewsCenterBean
{
/*    data	Array
    extend	Array
    retcode	200*/
    /**
     * 第一层目录
     */
    public List<MenuBean> data;
    public List<Long> extend;
    public Integer retcode;

    /**
     * data下的层级
     */
    public class MenuBean{
/*        children	Array
        id	10000
        title	新闻
        type	1*/
        public List<Category> children;
        public long id;
        public String title;
        public Integer type;

/*        id	10002
        title	专题
        type	10
        url	/10006/list_1.json
        url1	/10007/list1_1.json*/
        public String url;
        public String url1;

/*        id	10003
        title	组图
        type	2
        url	/10008/list_1.json*/

/*      dayurl
        excurl
        id	10004
        title	互动
        type	3
        weekurl*/
        public String dayurl;
        public String excurl;
        public String weekurl;
    }
    /**
     * data数据目录下某个分支下的目录
     */
    public class Category{
/*        id	10007
        title	北京
        type	1
        url	/10007/list_1.json*/
        public long id;
        public String title;
        public Integer type;
        public String url;
    }
}
