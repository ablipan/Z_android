/**
 * author :  lipan
 * filename :  Pie.java
 * create_time : 2014年6月11日 下午12:15:30
 */
package com.pp.view;

import com.pp.utils.ViewUtils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;

/**
 * @author : lipan
 * @create_time : 2014年6月11日 上午10:07:09
 * @desc : PieView
 * @update_person:
 * @update_time :
 * @update_desc :
 * 
 */
public class Pie extends View
{

    private SparseIntArray angles;

    private SparseArray<Paint> paints;

    private RectF OVAL;
    
    int startAngle = 0;
    
    public Pie(Context context , SparseIntArray angles , SparseArray<Paint> paints)
    {
        super(context);
        
        this.angles = angles;
        this.paints = paints;
        OVAL = new RectF(10, 10,
                ViewUtils.getDeviceWidth(context) - 10,
                ViewUtils.getDeviceWidth(context) - 10);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        
        //当前角度所在区域的索引
        int maxIndex = getNowPart(startAngle);
        if(-1 == maxIndex)
        {
            return;
        }
        
        // 使用当前索引所在区域的颜色来画整个图形
        canvas.drawArc(OVAL, 0, startAngle, true, paints.get(maxIndex));
        
        // 再在整个图形区域上铺上各个部分
        int startArc = 0;
        for (int i = 0; i < maxIndex; i++)
        {
            canvas.drawArc(OVAL, startArc, angles.get(i), true, paints.get(i));
            startArc += angles.get(i);
        }
        
        // 如果小于360°刷新图形
        if(startAngle < 360)
        {
            invalidate();
        }
        
        //每次画10°
        startAngle += 10;
    }

    /**
     * 角度集合从第一个开始累加，当大于当前角度时候，即当前角度属于该数据块
     * @param i
     * @return
     */
    private int getNowPart(int currentDegree)
    {
        int degree = 0;
        for (int j = 0; j < angles.size(); j++)
        {
            degree += angles.get(j);
            if(degree >= currentDegree)
            {
               return j; 
            }
        }
        return -1;
    }
    
    /**
     * 刷新图形
     */
    public void refresh()
    {
        startAngle = 0;
        invalidate();
    }
}
