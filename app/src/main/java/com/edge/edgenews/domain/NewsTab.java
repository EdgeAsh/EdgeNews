package com.edge.edgenews.domain;

import java.util.ArrayList;

/**
 * Created by edge on 01/03/2017.
 */
public class NewsTab {

    public int retcode;
    public NewsData data;

    @Override
    public String toString() {
        return "NewsTab{" +
                "data=" + data +
                '}';
    }

    public class NewsData{
        public ArrayList<News> news;
        public ArrayList<TopNews> topnews;
        public String title;
        public String more;

        @Override
        public String toString() {
            return "NewsData{" +
                    "news=" + news +
                    ", topnews=" + topnews +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public class News{
        public String id;
        public String pubDate;
        public String title;
        public String listimage;
        public String url;

        @Override
        public String toString() {
            return "News{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }

    public class TopNews {
        public String id;
        public String pubDate;
        public String title;
        public String topimage;
        public String url;

        @Override
        public String toString() {
            return "TopNews{" +
                    "title='" + title + '\'' +
                    '}';
        }
    }
}
