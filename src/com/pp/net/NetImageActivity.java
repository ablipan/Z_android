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

import com.pp.R;
import com.pp.net.service.ImageService;

/**
 * @author : lipan
 * @create_time : 2014年5月9日16:31:02
 * @desc : 网络图片资源访问
 * @update_time :
 * @update_desc :
 *
 */
public class NetImageActivity extends Activity
{

    private EditText urlText;
    private ImageView netImgView;
    
    private static class MyHandler extends Handler {
        private WeakReference<NetImageActivity> activity;

        public MyHandler(NetImageActivity activity) {
            this.activity = new WeakReference<NetImageActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            NetImageActivity _this = activity.get();
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
                netImgView.setImageBitmap((Bitmap)msg.obj);
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
                    byte[] data = ImageService.getNetImage(urlText.getText().toString());
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    Message message = myHandler.obtainMessage(0);
                    message.obj = bitmap;
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
        setContentView(R.layout.net_image);
        
        netImgView = (ImageView) findViewById(R.id.netImg);
        urlText = (EditText) findViewById(R.id.inputPath);
    }
    
}
