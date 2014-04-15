/**
 * author :  lipan
 * filename :  ActivityB.java
 * create_time : 2014年4月10日 下午1:56:39
 */
package com.pp.parcelable;

import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * @author : lipan
 * @create_time : 2014年4月10日 下午1:56:39
 * @desc : 接收Activity传递过来的复杂对象
 * @update_time :
 * @update_desc :
 *
 */
public class ActivityB extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ParcelableData parcelableData = getIntent().getParcelableExtra("DATA");
        Map data = parcelableData.data;
        Log.i("Parcelable", data.get("data").toString());
    }
    
}
