/**
 * author :  lipan
 * filename :  SharedPreferencesActivity.java
 * create_time : 2014-3-10 上午9:37:20
 */
package com.pp.sharedpreferences;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pp.R;

/**
 * @author : lipan
 * @create_time : 2014-3-10 上午9:37:20
 * @desc : SharedPreferences使用
 * @update_time :
 * @update_desc :
 *
 */
public class SharedPreferencesActivity extends Activity
{

    private EditText nameText;
    private EditText ageText;
    private SharedPreferencesService service;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        
        nameText = (EditText) findViewById(R.id.name);
        ageText = (EditText) findViewById(R.id.age);
        service = new SharedPreferencesService(this);
        
        Map<String,String> params = service.getPreferences();
        nameText.setText(params.get("name"));
        ageText.setText(params.get("age"));
    }
    
    /**
     * 保存按钮点击事件
     * @param v
     */
    public void OnSaveBtnClick(View v)
    {
        String name = nameText.getText().toString();
        String age = ageText.getText().toString();
        service.save(name,Integer.parseInt(age));
        Toast.makeText(this, getString(R.string.save_success), Toast.LENGTH_SHORT).show();
    }
}
