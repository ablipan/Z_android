/**
 * author :  lipan
 * filename :  CommDialogActivity.java
 * create_time : 2014年4月14日 下午4:31:10
 */
package com.pp.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import com.pp.R;
import com.pp.toast.CommToast;

/**
 * @author : lipan
 * @create_time : 2014年4月14日 下午4:31:10
 * @desc : CommDialogActivity
 * @update_time :
 * @update_desc :
 *
 */
public class CommDialogActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_dialog_show);
    }
    
    public void OpenInfoDialog(View v)
    {
//        AlertDialog showInfoDialog = CommAlertDialog.showInfoDialog(this, "这是一个测试信息");
        
        AlertDialog showInfoDialog = CommAlertDialog.showConfirmDialog(this, "这是一个测试信息" ,new OnConfirmClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                   CommToast.showInfo(CommDialogActivity.this, "确定!!!!");
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
//                    CommAlertDialog.dismiss();
//                } catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
//                return null;
//            }};
//        asyncTask.execute();
        
    }
    
}
