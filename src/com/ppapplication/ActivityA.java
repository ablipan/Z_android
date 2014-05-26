/**
 * author :  lipan
 * filename :  ActivityA.java
 * create_time : 2014年4月14日 下午2:54:02
 */
package com.ppapplication;

import com.pp.R;

import android.os.Bundle;
import android.view.View;

/**
 * @author : lipan
 * @create_time : 2014年4月14日 下午2:54:02
 * @desc : ActivityA
 * @update_time :
 * @update_desc :
 *
 */
public class ActivityA extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity_a);
    }
    
    /**
     * 跳转Activity
     * @param v
     */
    public void GoActvity(View v)
    {
        startActivity(ActivityB.class);
    }

}
