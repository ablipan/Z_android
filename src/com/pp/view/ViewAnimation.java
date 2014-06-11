/**
 * author :  lipan
 * filename :  ViewAnimation.java
 * create_time : 2014年6月10日 下午5:10:48
 */
package com.pp.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pp.R;
import com.pp.utils.ViewUtils;
import com.pp.utils.ViewUtils.LayoutParamsType;

/**
 * @author : lipan
 * @create_time : 2014年6月10日 下午5:10:48
 * @desc : 图形动画
 * @update_person:
 * @update_time :
 * @update_desc :
 * 
 */
public class ViewAnimation extends Activity
{
    protected static final int ANIM_DURATION = 800;
    
    protected static final int ANIM_DOING = 1;
    protected static final int ANIM_DONE = 2;
    protected int animState = ANIM_DOING;
    
    protected SparseIntArray angles;

    protected SparseArray<Paint> paints;

    protected RectF OVAL;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        init();
        
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(ViewUtils.getLinearLayoutParam(LayoutParamsType.MATCH_MATCH));
        layout.setOrientation(LinearLayout.VERTICAL);
        
        final Pie pie = new Pie(this ,angles ,paints);
        pie.setLayoutParams(new LinearLayout.LayoutParams(ViewUtils.getDeviceWidth(ViewAnimation.this), ViewUtils.getDeviceWidth(ViewAnimation.this)));
        layout.addView(pie);
        
        Button button = new Button(this);
        button.setText("Refresh");
        button.setLayoutParams(ViewUtils.getLinearLayoutParam(LayoutParamsType.MATCH_WRAP));
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                pie.refresh();
            }
        });
        layout.addView(button);
        
        setContentView(layout);
    }

    private void init()
    {
        
        angles = new SparseIntArray();
        angles.put(0, 30);
        angles.put(1, 100);
        angles.put(2, 30);
        angles.put(3, 40);
        angles.put(4, 160);

        Paint paint0 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint0.setAntiAlias(true); // 反锯齿
        paint0.setStyle(Style.FILL);
        paint0.setColor(getResources().getColor(R.color.color0));
        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setAntiAlias(true); // 反锯齿
        paint1.setStyle(Style.FILL);
        paint1.setColor(getResources().getColor(R.color.color1));
        Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setAntiAlias(true); // 反锯齿
        paint2.setStyle(Style.FILL);
        paint2.setColor(getResources().getColor(R.color.color2));
        Paint paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint3.setAntiAlias(true); // 反锯齿
        paint3.setStyle(Style.FILL);
        paint3.setColor(getResources().getColor(R.color.color3));
        Paint paint4 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint4.setAntiAlias(true); // 反锯齿
        paint4.setStyle(Style.FILL);
        paint4.setColor(getResources().getColor(R.color.color4));
        Paint paint5 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint5.setAntiAlias(true); // 反锯齿
        paint5.setStyle(Style.FILL);
        paint5.setColor(getResources().getColor(R.color.color5));

        paints = new SparseArray<Paint>();
        paints.put(0, paint0);
        paints.put(1, paint1);
        paints.put(2, paint2);
        paints.put(3, paint3);
        paints.put(4, paint4);
        paints.put(5, paint5);

    }
}
