/**
 * author :  lipan
 * filename :  ViewGestureActivity.java
 * create_time : 2014年6月4日 上午9:22:55
 */
package com.pp.gesture;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.pp.utils.ViewUtils;
import com.pp.utils.ViewUtils.LayoutParamsType;

/**
 * @author : lipan
 * @create_time : 2014年6月4日 上午9:22:55
 * @desc : View的手势操作
 * @update_person:
 * @update_time :
 * @update_desc :
 *
 */
public class ViewGestureActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setLayoutParams(ViewUtils.getLinearLayoutParam(LayoutParamsType.MATCH_MATCH));

        MyView view = new MyView(this);
        view.setLayoutParams(ViewUtils.getLinearLayoutParam(LayoutParamsType.WRAP_WRAP));
        layout.addView(view);
        
        setContentView(layout);
    }
    
}
