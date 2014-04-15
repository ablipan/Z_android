/**
 * author :  lipan
 * filename :  ActivityA.java
 * create_time : 2014年4月10日 下午1:55:43
 */
package com.pp.parcelable;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.pp.R;

/**
 * @author : lipan
 * @create_time : 2014年4月10日 下午1:55:43
 * @desc : AcivityA发起意图，传递复杂对象给ActivityB
 * @update_time :
 * @update_desc :
 *
 */
public class ActivityA extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parcelable);
    }
    
    public void OnClickBtn(View v)
    {
        Intent i = new Intent(this,ActivityB.class);
        ParcelableData parcelableData = new ParcelableData();
        Data data = new Data("1","www.baidu.com");
        Map o = new HashMap();
        o.put("data", "测试");
        parcelableData.data = o;
        Bundle extras = new Bundle();
        extras.putParcelable("DATA", parcelableData);
        i.putExtras(extras);
        startActivity(i);
    }
}
