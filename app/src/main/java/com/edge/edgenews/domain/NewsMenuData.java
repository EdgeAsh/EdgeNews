package com.edge.edgenews.domain;

import java.util.ArrayList;

/**
 *  gson对象封装原则
 *  遇到{}创建对象
 *  遇到[]创建集合
 *  对象中所有属性命名必须和服务器返回字段一样
 *
 * Created by edge on 26/02/2017.
 */
public class NewsMenuData {
    public int retcode;
    public ArrayList<NewsData> data;
    public ArrayList<String> extend;

    @Override
    public String toString() {
        return "NewsMenuData{" +
                "retcode=" + retcode +
                ", data=" + data +
                ", extend=" + extend +
                '}';
    }

    public class NewsData {
        public String id;
        public String title;
        public int type;
        public ArrayList<NewsTabData> children;

        @Override
        public String toString() {
            return "NewsData{" +
//                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
//                    ", type=" + type +
                    ", children=" + children +
                    '}';
        }
    }
    // 页签信息封装
    public class NewsTabData {
        public String id;
        public String title;
        public int type;
        public String url;

        @Override
        public String toString() {
            return "NewsTabData{" +
//                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
//                    ", type=" + type +
//                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
