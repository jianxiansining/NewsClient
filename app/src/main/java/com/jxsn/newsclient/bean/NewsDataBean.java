package com.jxsn.newsclient.bean;

import java.util.List;


/**
 * @项目名称:NewsClient
 * @包名:com.jxsn.newsclient.bean
 * @作者:djn
 * @创建日期:2015/9/27 11:07
 * @描述:TODO
 * @SVN版本号:$$Rev$$
 * @修改人:$$Author$$
 * @修改时间:$$Data$$
 * @修改内容:TODO
 */
public class NewsDataBean
{

    /*    data	Object
        retcode	200*/
    //第一级目录
    public Data data;

    public int retcode;


    /*    countcommenturl	http://zhbj.qianlong.com/client/content/countComment/
        more	/10007/list_2.json
        news	Array
        title	北京
        topic	Array
        topnews	Array*/
    public class Data
    {
        public String countcommenturl;
        public String more;
        public List<News> news;
        public String title;
        public List<Topic> topic;
        public List<Topnews> topnews;
    }

    public class News{
/*        comment	true
        commentlist	http://10.0.2.2:8080/zhbj/10007/comment_1.json
        commenturl	http://zhbj.qianlong.com/client/user/newComment/35319
        id	35311
        listimage	http://10.0.2.2:8080/zhbj/10007/2078369924F9UO.jpg
        pubdate	2014-10-1113:18
        title	网上大讲堂第368期预告：义务环保人人有责
        type	news
        url	http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html*/

        public boolean comment;
        public String commentlist;
        public String commenturl;
        public long id;
        public String listimage;
        public String pubdate;
        public String title;
        public String type;
        public String url;
    }

    public class Topic{
/*        description	11111111
        id	10101
        listimage	http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg
        sort	1
        title	北京
        url	http://10.0.2.2:8080/zhbj/10007/list_1.json*/

        public String description;
        public long id;
        public String listimage;
        public int sort;
        public String title;
        public String url;
    }

    public class Topnews{
/*        comment	true
        commentlist	http://10.0.2.2:8080/zhbj/10007/comment_1.json
        commenturl	http://zhbj.qianlong.com/client/user/newComment/35301
        id	35301
        pubdate	2014-04-0814:24
        title	蜗居生活
        topimage	http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg
        type	news
        url	http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html*/

        public boolean comment;
        public String commentlist;
        public String commenturl;
        public long id;
        public String pubdate;
        public String title;
        public String topimage;
        public String type;
        public String url;
    }
}
