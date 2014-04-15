/**
 * author :  lipan
 * filename :  ToastActivity.java
 * create_time : 2014年4月14日 上午9:56:37
 */
package com.pp.toast;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pp.R;

/**
 * @author : lipan
 * @create_time : 2014年4月14日 上午9:56:37
 * @desc : Toast
 * @update_time :
 * @update_desc :
 *
 */
public class ToastActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comm_toast_show);
    }
 
    
    public void ShowInfo(View v)
    {
        Toast showInfo = CommToast.showInfo(this, "测试啊啊啊....",null);
    }
}
