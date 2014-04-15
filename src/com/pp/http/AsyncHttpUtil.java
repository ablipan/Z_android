/**
 * author :  lipan
 * filename :  AsyncHttpUtils.java
 * create_time : 2014年4月10日 下午4:37:16
 */
package com.pp.http;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 异步的Http请求工具类
 * 
 * 可用于文件上传和下载，使用方法详见
 * http://loopj.com/android-async-http/
 * 
 * @create_time : 2014年4月10日 下午4:37:16
 * @desc : 用于异步的发送http请求
 * @update_time :
 * @update_desc :
 * 
 */
public class AsyncHttpUtil
{

    private static AsyncHttpClient client = new AsyncHttpClient();

    // 请求超时时间
    private static final int REQUEST_TIMEOUT = 1000;

    /**
     * 取消当前上下文发起的所有请求
     * 
     * @param context
     *            上下文对象
     */
    public static void cancelRequests(Context context)
    {
        // 如果已经创建了客户端，则取消所有进行中的客户端
        if (client != null)
        {
            client.cancelRequests(context, true);
        }
    }

    /**
     * 实例化AsyncHttpClient对象
     */
    private static AsyncHttpClient getClient()
    {
        if (null == client)
        {
            client = new AsyncHttpClient();

            // 设置链接超时，如果不设置，默认为10s
            client.setTimeout(REQUEST_TIMEOUT);
        }
        return client;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * 拼接BASE_URL 和 相对url
     * 
     * @param relativeUrl
     * @return
     */
    private static String getAbsoluteUrl(String relativeUrl)
    {
        return "" + relativeUrl;
    }
}
