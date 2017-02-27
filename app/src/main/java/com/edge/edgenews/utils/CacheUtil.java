package com.edge.edgenews.utils;

import android.content.Context;

/**
 * 缓存json数据
 * 以URL+参数为key，json为value
 * Created by edge on 27/02/2017.
 */
public class CacheUtil {
    /**
     * 写缓存，
     */
    public static void setCache(String key, String value, Context ctx) {
        //将数据存在SharePreference中。也可以将缓存写在本地文件中，以MD5（url）为文件名，json为文件内容
        PrefUtils.putString(key,value,ctx);
    }

    /**
     * 读缓存
     */
    public static String getCache(String key,Context ctx) {
        // 如果缓存写在本地文件中，先找文件MD5(url)是否存在，存在则有缓存
        String result = PrefUtils.getString(key,null,ctx);
        return result;
    }

}
