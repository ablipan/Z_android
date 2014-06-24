/**
 * author :  lipan
 * filename :  ViewAnimation.java
 * create_time : 2014年6月10日 下午5:10:48
 */
package com.pp.chart;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
public class PieChartActivity extends Activity
{
    protected SparseArray<Float> datas;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        init();
        
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(ViewUtils.getLinearLayoutParam(LayoutParamsType.MATCH_MATCH));
        layout.setOrientation(LinearLayout.VERTICAL);
        
        final PieChart pie = new PieChart(this);
        pie.setLayoutParams(new LinearLayout.LayoutParams(ViewUtils.getDeviceWidth(PieChartActivity.this), ViewUtils.getDeviceWidth(PieChartActivity.this)));
        pie.setData(null, datas); // 绑定数据
        pie.setColorTemplate(new ColorTemplate(ColorTemplate.PIE_COLORS_1, this)); // 颜色模板
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
        datas = new SparseArray<Float>();
        datas.put(0, 30f);
        datas.put(1, 100f);
        datas.put(2, 30f);
        datas.put(3, 40f);
        datas.put(4, 160f);
    }
}
