/**
 * author :  lipan
 * filename :  MyView.java
 * create_time : 2014年6月4日 上午9:27:23
 */
package com.pp.gesture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.view.View;

import com.pp.R;
import com.pp.R.color;
import com.pp.R.dimen;
import com.pp.utils.Utils;
import com.pp.utils.ViewUtils;

/**
 * @author : lipan
 * @create_time : 2014年6月4日 上午9:27:23
 * @desc : 自定义视图
 * @update_person:
 * @update_time :
 * @update_desc :
 *
 */
public class MyView extends View
{

    private Paint paint;
    /**
     * @param context
     */
    public MyView(Context context)
    {
        super(context);
        init();
    }

    /**
     * 
     */
    private void init()
    {
        paint = new Paint();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color.text_blue);
        paint.setTextSize(ViewUtils.getXmlDef(getContext(), R.dimen.text_size_huge));
        paint.setTextAlign(Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawText("我是文本", 0, 0, paint);
    }
    
    
}
