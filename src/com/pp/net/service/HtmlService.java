/**
 * author :  lipan
 * filename :  HtmlService.java
 * create_time : 2014年5月10日 下午2:08:40
 */
package com.pp.net.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.pp.utils.HttpUtils;

/**
 * @author : lipan
 * @create_time : 2014年5月10日 下午2:08:40
 * @desc : 获得网络html代码
 * @update_person:
 * @update_time :
 * @update_desc :
 *
 */
public class HtmlService
{

    /**
     * @param string
     * @return
     * @throws Exception 
     */
    public static byte[] getNetHtml(String path) throws Exception
    {
        
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200)
        {
            InputStream inputStream = conn.getInputStream();
            byte[] bytes = HttpUtils.getBytes(inputStream);
            return bytes;
        }
        return null;
    }

}
