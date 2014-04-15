/**
 * author :  lipan
 * filename :  LoadingActivity.java
 * create_time : 2014年4月12日 上午11:34:48
 */
package com.pp.loadingDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pp.R;

/**
 * @author : lipan
 * @create_time : 2014年4月12日 上午11:34:48
 * @desc : Loading dialog
 * @update_time :
 * @update_desc :
 *
 */
public class LoadingActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comm_loading_show);
    }
    
    Dialog dialog;
    
    public void ShowLoading(View v)
    {
        dialog = CommLoading.show(this,R.string.loading_text_custerm,new DialogInterface.OnDismissListener()
        {
            
            @Override
            public void onDismiss(DialogInterface dialog)
            {
                Toast.makeText(LoadingActivity.this, "yeah", Toast.LENGTH_LONG).show();
            }
        });
        
//        AsyncTask<Object, Object, Object> asyncTask = new AsyncTask<Object, Object, Object>()
//        {
//
//            @Override
//            protected Object doInBackground(Object... params)
//            {
//                try
//                {
//                    new Thread().sleep(2000);
//                    CommLoading.dismiss();
//                } catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//            
//        };
//        asyncTask.execute();
    }
    
}
