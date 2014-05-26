/**
 * author :  lipan
 * filename :  ImageService.java
 * create_time : 2014年5月9日 下午5:27:02
 */
package com.pp.net.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.pp.utils.HttpUtils;

/**
 * @author : lipan
 * @create_time : 2014年5月9日 下午5:27:02
 * @desc : 获取图片资源
 * @update_person:
 * @update_time :
 * @update_desc :
 *
 */
public class ImageService
{

    /**
     * 获取网路图片的数据
     * @param string 图片url
     * @return
     * @throws MalformedURLException 
     */
    public static byte[] getNetImage(String path) throws Exception
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
