/**
 * author :  lipan
 * filename :  LoadingActivity.java
 * create_time : 2014年4月12日 上午11:34:48
 */
package com.pp.net;

import java.lang.ref.WeakReference;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pp.R;
import com.pp.net.service.HtmlService;

/**
 * @author : lipan
 * @create_time : 2014年5月10日14:06:48
 * @desc : 网络网页资源访问
 * @update_time :
 * @update_desc :
 *
 */
public class HtmlViewerActivity extends Activity
{

    private EditText urlText;
    private TextView netTextView;
    
    private static class MyHandler extends Handler {
        private WeakReference<HtmlViewerActivity> activity;

        public MyHandler(HtmlViewerActivity activity) {
            this.activity = new WeakReference<HtmlViewerActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HtmlViewerActivity _this = activity.get();
            if (_this == null)
                return;

            _this.handleMessage(msg);
        }
    }
    
    /**
     * 
     */
    public void handleMessage(Message msg)
    {
        switch (msg.what)
        {
            case 0:
                netTextView.setText(new String((byte[])msg.obj));
                break;

            default:
                break;
        }
    }
    
    private Handler myHandler = new MyHandler(this);
    
    public void BtnClick(View v)
    {
        new AsyncTask<Object, Object, Object>(){

            @Override
            protected Object doInBackground(Object... params)
            {
                try
                {
                    byte[] data = HtmlService.getNetHtml(urlText.getText().toString());
                    Message message = myHandler.obtainMessage(0);
                    message.obj = data;
                    myHandler.sendMessage(message);
                } catch (Exception e)
                {
//                    CommAlertDialog.showInfoDialog(this, e.toString());
                    e.printStackTrace();
                }
                return null;
            }}.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_html);
        
        netTextView = (TextView) findViewById(R.id.netHtml);
        urlText = (EditText) findViewById(R.id.inputPath);
    }
    
}
