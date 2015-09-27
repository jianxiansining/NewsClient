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

    /**
     * data : {"countcommenturl":"http://zhbj.qianlong.com/client/content/countComment/","more":"/10007/list_2.json","news":[{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35311,"listimage":"http://10.0.2.2:8080/zhbj/10007/2078369924F9UO.jpg","pubdate":"2014-10-1113:18","title":"网上大讲堂第368期预告：义务环保人人有责","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35312,"listimage":"http://10.0.2.2:8080/zhbj/10007/1509585620ASS3.jpg","pubdate":"2014-10-1111:20","title":"马路改建为停车场车位年费高达3000元","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35313,"listimage":"http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg","pubdate":"2014-10-1110:34","title":"北京两年内将迁出1200家工业污染企业","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35314,"listimage":"http://10.0.2.2:8080/zhbj/10007/1505891536Z82T.jpg","pubdate":"2014-10-1110:08","title":"大雾再锁京城机场航班全部延误","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35315,"listimage":"http://10.0.2.2:8080/zhbj/10007/1483727032VMXT.jpg","pubdate":"2014-10-1110:03","title":"APEC会议期间调休企业员工盼同步放假","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35316,"listimage":"http://10.0.2.2:8080/zhbj/10007/1481879990BEMG.jpg","pubdate":"2014-10-1109:59","title":"机械\u201c龙马\u201d巡演17日亮相奥园","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35310,"listimage":"http://10.0.2.2:8080/zhbj/10007/14800329488K7F.jpg","pubdate":"2014-10-1109:54","title":"门头沟获批100套限价房","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35318,"listimage":"http://10.0.2.2:8080/zhbj/10007/14791094274LT9.jpg","pubdate":"2014-10-1109:52","title":"APEC期间净空区放带灯风筝可拘留","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35314","id":35314,"listimage":"http://10.0.2.2:8080/zhbj/10007/1478185906G9WQ.jpg","pubdate":"2014-10-1109:48","title":"今起两自住房摇号","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35117","id":35117,"listimage":"http://10.0.2.2:8080/zhbj/10007/1477262385PASS.jpg","pubdate":"2014-10-1109:45","title":"故宫神武门广场拟夜间开放停车","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"}],"title":"北京","topic":[{"description":"11111111","id":10101,"listimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg","sort":1,"title":"北京","url":"http://10.0.2.2:8080/zhbj/10007/list_1.json"},{"description":"22222222","id":10100,"listimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg","sort":2,"title":"222222222222","url":"http://10.0.2.2:8080/zhbj/10007/list_1.json"}],"topnews":[{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35301","id":35301,"pubdate":"2014-04-0814:24","title":"蜗居生活","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35125,"pubdate":"2014-04-0809:09","title":"中秋赏月","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU92.jpg","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35126,"pubdate":"2014-04-0809:09","title":"天空翱翔","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU93.jpg","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35127,"pubdate":"2014-04-0809:09","title":"感官设计","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU94.png","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"}]}
     * retcode : 200
     */

    private DataEntity data;

    private int retcode;


    public void setData(DataEntity data)
    {

        this.data = data;
    }


    public void setRetcode(int retcode)
    {

        this.retcode = retcode;
    }


    public DataEntity getData()
    {

        return data;
    }


    public int getRetcode()
    {

        return retcode;
    }


    public static class DataEntity
    {

        /**
         * countcommenturl : http://zhbj.qianlong.com/client/content/countComment/
         * more : /10007/list_2.json
         * news : [{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35311,"listimage":"http://10.0.2.2:8080/zhbj/10007/2078369924F9UO.jpg","pubdate":"2014-10-1113:18","title":"网上大讲堂第368期预告：义务环保人人有责","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35312,"listimage":"http://10.0.2.2:8080/zhbj/10007/1509585620ASS3.jpg","pubdate":"2014-10-1111:20","title":"马路改建为停车场车位年费高达3000元","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35313,"listimage":"http://10.0.2.2:8080/zhbj/10007/1506815057D99I.jpg","pubdate":"2014-10-1110:34","title":"北京两年内将迁出1200家工业污染企业","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35314,"listimage":"http://10.0.2.2:8080/zhbj/10007/1505891536Z82T.jpg","pubdate":"2014-10-1110:08","title":"大雾再锁京城机场航班全部延误","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35315,"listimage":"http://10.0.2.2:8080/zhbj/10007/1483727032VMXT.jpg","pubdate":"2014-10-1110:03","title":"APEC会议期间调休企业员工盼同步放假","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35316,"listimage":"http://10.0.2.2:8080/zhbj/10007/1481879990BEMG.jpg","pubdate":"2014-10-1109:59","title":"机械\u201c龙马\u201d巡演17日亮相奥园","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35310,"listimage":"http://10.0.2.2:8080/zhbj/10007/14800329488K7F.jpg","pubdate":"2014-10-1109:54","title":"门头沟获批100套限价房","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","id":35318,"listimage":"http://10.0.2.2:8080/zhbj/10007/14791094274LT9.jpg","pubdate":"2014-10-1109:52","title":"APEC期间净空区放带灯风筝可拘留","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35314","id":35314,"listimage":"http://10.0.2.2:8080/zhbj/10007/1478185906G9WQ.jpg","pubdate":"2014-10-1109:48","title":"今起两自住房摇号","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35117","id":35117,"listimage":"http://10.0.2.2:8080/zhbj/10007/1477262385PASS.jpg","pubdate":"2014-10-1109:45","title":"故宫神武门广场拟夜间开放停车","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"}]
         * title : 北京
         * topic : [{"description":"11111111","id":10101,"listimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg","sort":1,"title":"北京","url":"http://10.0.2.2:8080/zhbj/10007/list_1.json"},{"description":"22222222","id":10100,"listimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg","sort":2,"title":"222222222222","url":"http://10.0.2.2:8080/zhbj/10007/list_1.json"}]
         * topnews : [{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35301","id":35301,"pubdate":"2014-04-0814:24","title":"蜗居生活","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35125,"pubdate":"2014-04-0809:09","title":"中秋赏月","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU92.jpg","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35126,"pubdate":"2014-04-0809:09","title":"天空翱翔","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU93.jpg","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"},{"comment":true,"commentlist":"http://10.0.2.2:8080/zhbj/10007/comment_1.json","commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","id":35127,"pubdate":"2014-04-0809:09","title":"感官设计","topimage":"http://10.0.2.2:8080/zhbj/10007/1452327318UU94.png","type":"news","url":"http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html"}]
         */

        private String countcommenturl;

        private String more;

        private String title;

        private List<NewsEntity> news;

        private List<TopicEntity> topic;

        private List<TopnewsEntity> topnews;


        public void setCountcommenturl(String countcommenturl)
        {

            this.countcommenturl = countcommenturl;
        }


        public void setMore(String more)
        {

            this.more = more;
        }


        public void setTitle(String title)
        {

            this.title = title;
        }


        public void setNews(List<NewsEntity> news)
        {

            this.news = news;
        }


        public void setTopic(List<TopicEntity> topic)
        {

            this.topic = topic;
        }


        public void setTopnews(List<TopnewsEntity> topnews)
        {

            this.topnews = topnews;
        }


        public String getCountcommenturl()
        {

            return countcommenturl;
        }


        public String getMore()
        {

            return more;
        }


        public String getTitle()
        {

            return title;
        }


        public List<NewsEntity> getNews()
        {

            return news;
        }


        public List<TopicEntity> getTopic()
        {

            return topic;
        }


        public List<TopnewsEntity> getTopnews()
        {

            return topnews;
        }


        public static class NewsEntity
        {

            /**
             * comment : true
             * commentlist : http://10.0.2.2:8080/zhbj/10007/comment_1.json
             * commenturl : http://zhbj.qianlong.com/client/user/newComment/35319
             * id : 35311
             * listimage : http://10.0.2.2:8080/zhbj/10007/2078369924F9UO.jpg
             * pubdate : 2014-10-1113:18
             * title : 网上大讲堂第368期预告：义务环保人人有责
             * type : news
             * url : http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html
             */

            private boolean comment;

            private String commentlist;

            private String commenturl;

            private int id;

            private String listimage;

            private String pubdate;

            private String title;

            private String type;

            private String url;


            public void setComment(boolean comment)
            {

                this.comment = comment;
            }


            public void setCommentlist(String commentlist)
            {

                this.commentlist = commentlist;
            }


            public void setCommenturl(String commenturl)
            {

                this.commenturl = commenturl;
            }


            public void setId(int id)
            {

                this.id = id;
            }


            public void setListimage(String listimage)
            {

                this.listimage = listimage;
            }


            public void setPubdate(String pubdate)
            {

                this.pubdate = pubdate;
            }


            public void setTitle(String title)
            {

                this.title = title;
            }


            public void setType(String type)
            {

                this.type = type;
            }


            public void setUrl(String url)
            {

                this.url = url;
            }


            public boolean getComment()
            {

                return comment;
            }


            public String getCommentlist()
            {

                return commentlist;
            }


            public String getCommenturl()
            {

                return commenturl;
            }


            public int getId()
            {

                return id;
            }


            public String getListimage()
            {

                return listimage;
            }


            public String getPubdate()
            {

                return pubdate;
            }


            public String getTitle()
            {

                return title;
            }


            public String getType()
            {

                return type;
            }


            public String getUrl()
            {

                return url;
            }
        }


        public static class TopicEntity
        {

            /**
             * description : 11111111
             * id : 10101
             * listimage : http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg
             * sort : 1
             * title : 北京
             * url : http://10.0.2.2:8080/zhbj/10007/list_1.json
             */

            private String description;

            private int id;

            private String listimage;

            private int sort;

            private String title;

            private String url;


            public void setDescription(String description)
            {

                this.description = description;
            }


            public void setId(int id)
            {

                this.id = id;
            }


            public void setListimage(String listimage)
            {

                this.listimage = listimage;
            }


            public void setSort(int sort)
            {

                this.sort = sort;
            }


            public void setTitle(String title)
            {

                this.title = title;
            }


            public void setUrl(String url)
            {

                this.url = url;
            }


            public String getDescription()
            {

                return description;
            }


            public int getId()
            {

                return id;
            }


            public String getListimage()
            {

                return listimage;
            }


            public int getSort()
            {

                return sort;
            }


            public String getTitle()
            {

                return title;
            }


            public String getUrl()
            {

                return url;
            }
        }


        public static class TopnewsEntity
        {

            /**
             * comment : true
             * commentlist : http://10.0.2.2:8080/zhbj/10007/comment_1.json
             * commenturl : http://zhbj.qianlong.com/client/user/newComment/35301
             * id : 35301
             * pubdate : 2014-04-0814:24
             * title : 蜗居生活
             * topimage : http://10.0.2.2:8080/zhbj/10007/1452327318UU91.jpg
             * type : news
             * url : http://10.0.2.2:8080/zhbj/10007/724D6A55496A11726628.html
             */

            private boolean comment;

            private String commentlist;

            private String commenturl;

            private int id;

            private String pubdate;

            private String title;

            private String topimage;

            private String type;

            private String url;


            public void setComment(boolean comment)
            {

                this.comment = comment;
            }


            public void setCommentlist(String commentlist)
            {

                this.commentlist = commentlist;
            }


            public void setCommenturl(String commenturl)
            {

                this.commenturl = commenturl;
            }


            public void setId(int id)
            {

                this.id = id;
            }


            public void setPubdate(String pubdate)
            {

                this.pubdate = pubdate;
            }


            public void setTitle(String title)
            {

                this.title = title;
            }


            public void setTopimage(String topimage)
            {

                this.topimage = topimage;
            }


            public void setType(String type)
            {

                this.type = type;
            }


            public void setUrl(String url)
            {

                this.url = url;
            }


            public boolean getComment()
            {

                return comment;
            }


            public String getCommentlist()
            {

                return commentlist;
            }


            public String getCommenturl()
            {

                return commenturl;
            }


            public int getId()
            {

                return id;
            }


            public String getPubdate()
            {

                return pubdate;
            }


            public String getTitle()
            {

                return title;
            }


            public String getTopimage()
            {

                return topimage;
            }


            public String getType()
            {

                return type;
            }


            public String getUrl()
            {

                return url;
            }
        }
    }
}