/**
 * author :  lipan
 * filename :  News.java
 * create_time : 2014年5月10日 下午2:42:43
 */
package com.pp.net.service;

/**
 * @author : lipan
 * @create_time : 2014年5月10日 下午2:42:43
 * @desc : 视频资讯
 * @update_person:
 * @update_time :
 * @update_desc :
 *
 */
public class Video
{
    private String id;
    private String title;
    private long time;
    
    
    public Video()
    {
    }
    /**
     * @param id
     * @param title
     * @param time
     */
    public Video(String id, String title, long time)
    {
        this.id = id;
        this.title = title;
        this.time = time;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public long getTime()
    {
        return time;
    }
    public void setTime(long time)
    {
        this.time = time;
    }
    
}
