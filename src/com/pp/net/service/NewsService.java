/**
 * author :  lipan
 * filename :  HtmlService.java
 * create_time : 2014年5月10日 下午2:08:40
 */
package com.pp.net.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import com.pp.utils.HttpUtils;

import android.util.Xml;

/**
 * @author : lipan
 * @create_time : 2014年5月10日14:42:08
 * @desc : 获得服务器最新的视频
 * @update_person:
 * @update_time :
 * @update_desc :
 *
 */
public class NewsService
{
    
    /**
     * 
     * @param string
     * @return
     * @throws Exception 
     */
    public static List<Video> getJsonNews(String path) throws Exception
    {
        path += "?format=Json";
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200)
        {
            InputStream inputStream = conn.getInputStream();
            return parseJson(inputStream);
        }
        return null;
    }


    /**
     * @param string
     * @return
     * @throws Exception 
     */
    public static List<Video> getNews(String path) throws Exception
    {
        
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200)
        {
            InputStream inputStream = conn.getInputStream();
            return parseXml(inputStream);
        }
        return null;
    }

    /**
     * @param bytes
     * @return
     * @throws Exception 
     * <?xml version="1.0" encoding="UTF-8"?>
        <videonews>
            <news id="1">
                <title>舌尖上的中国第一集</title>
                <timelength>40</timelength>
            </news>
            <news id="2">
                <title>舌尖上的中国第二集</title>
                <timelength>50</timelength>
            </news>
        </videonews>
     */
    private static List<Video> parseXml(InputStream inputStream) throws Exception
    {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream,"UTF-8");
        int event = parser.getEventType();
        
        List<Video> videos = null;
        Video video = null;
        String tagName = "";
        
        while (event != XmlPullParser.END_DOCUMENT)
        {
            tagName = parser.getName();
            switch (event)
            {
                case XmlPullParser.START_TAG:
                    if("videonews".equals(tagName))
                    {
                        videos = new ArrayList<Video>();
                    }else if("news".equals(tagName))
                    {
                        video = new Video();
                        video.setId(parser.getAttributeValue(0));
                    }else if("title".equals(tagName))
                    {
                        video.setTitle(parser.nextText());
                    }else if("timelength".equals(tagName))
                    {
                        video.setTime(Long.parseLong(parser.nextText()));
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if("news".equals(tagName))
                    {
                        if(null != videos)
                        {
                            videos.add(video);
                            video = null;
                        }
                    }
                    break;
                default:
                    break;
            }
            event = parser.next();
        }
        inputStream.close();
        return videos;
    }

    /**
     * 解析json数据
     * @param inputStream
     * @return
     * @throws Exception 
     * 
     * {id:1,title:'舌尖上的中国第一集',timelength:40}
     */
    private static List<Video> parseJson(InputStream inputStream) throws Exception
    {
        byte[] data = HttpUtils.getBytes(inputStream);
        String json = new String(data);
        JSONArray jsonArray = new JSONArray(json);
        List<Video> videos = new ArrayList<Video>();
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            videos.add(new Video(jsonObject.getString("id") , jsonObject.getString("title") ,jsonObject.getLong("timelength")));
        }
        return videos;
    }
}
