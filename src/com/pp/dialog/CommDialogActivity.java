/**
 * author :  lipan
 * filename :  CommDialogActivity.java
 * create_time : 2014年4月14日 下午4:31:10
 */
package com.pp.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.pp.R;

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
        TextView view = new TextView(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        view.setText("复仇擦擦擦擦");
        CommAlertDialog.showInfoDialog(this, view, null, true, null);
    }
    
    public void OpenConfirmDialog(View v)
    {
        
    }
    
    public void ShowPop(View v)
    {
    }
    
}
