/**
 * author :  lipan
 * filename :  SyncHttpUtils.java
 * create_time : 2014年4月10日 下午4:37:04
 */
package com.pp.http;

import android.content.Context;

import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;

/**
 * 同步的Http请求工具类
 * 
 * @create_time : 2014年4月10日 下午4:37:04
 * @desc : 用于同步的发送http请求，获得返回的信息
 * @update_time :
 * @update_desc :
 * 
 */
public class SyncHttpUtil
{

//    private static final String TAG = "com.ailk.zt4android.utils.SyncHttpUtil";

    // 请求超时时间
    private static final int REQUEST_TIMEOUT = 1000;

    //
    private static SyncHttpClient client;

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
     * 实例化SyncHttpClient对象
     */
    private static SyncHttpClient getClient()
    {
        if (null == client)
        {
            client = new SyncHttpClient();

            // 设置链接超时，如果不设置，默认为10s
            client.setTimeout(REQUEST_TIMEOUT);
        }
        return client;
    }

    /**
     * 发送get请求 
     * @param url
     * @param params
     * @return
     */
    public static RequestHandle get(String url, RequestParams params, ResponseHandlerInterface responseHandler)
    {
         return getClient().get(getAbsoluteUrl(url), params, responseHandler);
    }
    
    /**
     * 发送post请求 
     * @param url
     * @param params
     * @return
     */
    public static RequestHandle post(String url, RequestParams params, ResponseHandlerInterface responseHandler)
    {
        return getClient().post(getAbsoluteUrl(url), params, responseHandler);
    }
    
    /**
     * 拼接BASE_URL 和 相对url
     * @param relativeUrl
     * @return 
     */
    private static String getAbsoluteUrl(String relativeUrl) {
        return "" + relativeUrl;
    }
}
