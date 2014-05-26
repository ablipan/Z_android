/**
 * author :  lipan
 * filename :  LoadingActivity.java
 * create_time : 2014年4月12日 上午11:34:48
 */
package com.pp.net;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.pp.R;
import com.pp.baseActivity.BaseActivity;
import com.pp.net.service.NewsService;
import com.pp.net.service.Video;

/**
 * @author : lipan
 * @create_time : 2014年5月10日14:57:50
 * @desc : 访问服务器的最新视频资讯
 * @update_time :
 * @update_desc :
 *
 */
public class NewsActivity extends BaseActivity
{

    private ListView newsListView;
    
    private static class MyHandler extends Handler {
        private WeakReference<NewsActivity> activity;

        public MyHandler(NewsActivity activity) {
            this.activity = new WeakReference<NewsActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            NewsActivity _this = activity.get();
            if (_this == null)
                return;

            _this.handleMessage(msg);
        }
    }
    
    /**
     * 
     */
    @SuppressWarnings("unchecked")
    public void handleMessage(Message msg)
    {
        switch (msg.what)
        {
            case 0:
                List<Video> videos = (List<Video>) msg.obj;
                List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
                for (Video video : videos)
                {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("id", video.getId());
                    map.put("title", video.getTitle());
                    map.put("time", video.getTime()+getString(R.string.video_length_unit));
                    data.add(map);
                }
                
                SimpleAdapter adapter = new SimpleAdapter(getContext(),
                        data, R.layout.net_news_item, new String[]{"id","title","time"}, new int[]{R.id.id,R.id.title,R.id.timeLength});
                newsListView.setAdapter(adapter);
                break;

            default:
                break;
        }
    }
    
    private Handler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_news);
        
        newsListView = (ListView) findViewById(R.id.newsListView);
        
        new AsyncTask<Object, Object, Object>(){

            @Override
            protected Object doInBackground(Object... params)
            {
                try
                {
                    //获取XML格式的资讯
//                    List<Video> news = NewsService.getNews(getString(R.string.video_url));
                    //获取Json格式的资讯
                    List<Video> news = NewsService.getJsonNews(getString(R.string.video_url));
                    Message message = myHandler.obtainMessage(0);
                    message.obj = news;
                    myHandler.sendMessage(message);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                return null;
            }}.execute();
    }
    
}
