/**
 * author :  lipan
 * filename :  CheckStatus.java
 * create_time : 2014年4月10日 上午11:26:46
 */
package com.pp.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @create_time : 2014年4月10日 上午11:26:46
 * @desc : 检查手机状态
 * @update_time :
 * @update_desc :
 *
 */
public class CheckPhoneStatus
{
    /**
     * 判断wifi是否连接
     * 
     * @param context
     * @return
     */
    public static boolean isWIFIConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (null != mobile && !mobile.isAvailable()) {
            return false;
        }
        if (null != wifi && !wifi.isAvailable()) {
            return false;
        }
        return true;
    }

    /**
     *  检查网络连接状态，Monitor network connections (Wi-Vi, GPRS, UMTS, etc.)
     * @param context
     * @return
     */
    public static boolean checkNetWorkStatus(Context context) {
        boolean result;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnected()) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * 检查URL
     * 
     * @param url
     * @return
     */
    public static boolean checkURL(String url) {
        boolean value = false;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url)
                    .openConnection();
            conn.setReadTimeout(0);
            int code = conn.getResponseCode();
            if (code != 200) {
                value = false;
            } else {
                value = true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }
}
