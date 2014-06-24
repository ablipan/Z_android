/**
 * author :  lipan
 * filename :  Pie.java
 * create_time : 2014年6月11日 下午12:15:30
 */
package com.pp.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;

/**
 * @author : lipan
 * @create_time : 2014年6月11日 上午10:07:09
 * @desc : PieView
 * @update_person:
 * @update_time :
 * @update_desc :
 * 
 */
public class PieChart extends Chart
{

    private SparseArray<Float> angles; //相对度数
    private SparseArray<Float> absoluteAngles; //绝对度数
    private int maxAngle = 0;   // 绘制图形时当前的最大角度
    private float mChartAngle = 0f; // 当前图形已旋转的角度
    private float mStartAngle; // 开始旋转角度
    private PieTouchListener mListener; // touch监听
    private Context context;
    private float gap_10 = 10f; // 10dp
    private float mShift = 20f; // 选中图形区域，移动的距离
    private RectF OVAL;  //正方形
    private float diameter; //圆的直径
    public static final int ANIMATION_STATE_TODO = 1;
    public static final int ANIMATION_STATE_DOWN = 2;
    private int animState = ANIMATION_STATE_TODO; // 动画是否结束
    
    public PieChart(Context context)
    {
        super(context);
    }

    @Override
    protected void init()
    {
        super.init();
        
        this.context = getContext();
        mListener = new PieTouchListener(this);
        
        // 直径取高宽的最小值，保证外围是一个正方形
        diameter = Math.min(getWidth() , getHeight());
        
        // 圆的上下左右四点切线组成的正方形坐标
        OVAL = new RectF(width/2 - diameter/2 + mShift, // 左坐标
                height/2-diameter/2 + mShift,  //上坐标
                width/2 +diameter/2 - mShift,  //右坐标
                height/2+diameter/2 - mShift); //下坐标

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return mListener.onTouch(this, event);
    }
    
    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        // 
        if(animState == ANIMATION_STATE_DOWN)
        {
            drawData(canvas, mChartAngle , angles.size());
            return;
        }
        
        // 当前角度所在区域的索引
        int maxIndex = getNowPart(maxAngle);
        if (-1 == maxIndex)
        {
            // 整个部分
            drawData(canvas, 0 , angles.size());
            return;
        }

        // 使用当前索引所在区域的颜色来画整个图形
        canvas.drawArc(OVAL, 0, maxAngle, true, paints.get(maxIndex));

        // 再在整个图形区域上铺上各个部分
        drawData(canvas, 0 , maxIndex);

        // 如果小于360°刷新图形
        if (maxAngle < 360)
        {
            invalidate();
        }else
        {
            animState = ANIMATION_STATE_DOWN;
        }
        // 每次画10°
        maxAngle += 10;
    }

    /**
     * 画弧形
     * @param canvas
     * @param size 数据的最大index
     */
    private void drawData(Canvas canvas,float startAngle , int size)
    {
        float angle = startAngle;
        for (int i = 0; i < size; i++)
        {
            canvas.drawArc(OVAL, angle, angles.get(i), true, paints.get(i));
            angle += angles.get(i);
        }
    }

    /**
     * 给定角度所在部分
     * 累加角度，当和大于指定角度，则该角度属于该索引指向的数据块
     * @param i
     * @return
     */
    private int getNowPart(int currentDegree)
    {
        int sumDegree = 0;
        for (int j = 0; j < angles.size(); j++)
        {
            sumDegree += angles.get(j);
            if (sumDegree >= currentDegree)
            {
                return j;
            }
        }
        return -1;
    }

    /**
     * 刷新图形，重新开始画图
     */
    public void refresh()
    {
        maxAngle = 0;
        animState = ANIMATION_STATE_TODO;
        mChartAngle = 0f;
        invalidate();
    }

    /**
     * 使用勾股定律计算某一点距离圆心的距离
     * @param x
     * @param y
     * @return
     */
    public float distanceToCenter(float x, float y)
    {
        PointF center = getCenter();
        double xDis = Math.abs(x - center.x);
        double yDis = Math.abs(y - center.y);
        float distance = (float)Math.sqrt(Math.pow(xDis, 2) + Math.pow(yDis, 2));
        return distance;
    }

    /**
     * 获得圆心坐标
     * 
     * @return
     */
    private PointF getCenter()
    {
        return new PointF(getWidth() / 2, getHeight() / 2);
    }
    
    /**
     * 半径
     * @return
     */
    public float getRadius()
    {
        return diameter/2;
    }
    
    /**
     * 使用指定的坐标得到相对于视图中心的角度  0~360之间，0°是东边
     * 
     *  反正弦
     * @param x
     * @param y
     * @return
     */
    public float getAngleForPoint(float x , float y)
    {
        PointF center = getCenter();
        float tx = x - center.x; // 邻边
        float ty = y - center.y; // 对边
        double length = Math.sqrt(Math.pow(tx, 2) + Math.pow(ty, 2)); // 斜边
        double radian = Math.asin(ty / length); // 弧度
        float radius = (float) Math.abs(Math.toDegrees(radian)); // 角度
        //第一象限
        if(x > center.x && y< center.y)
        {
            radius = 360 - radius;
        }
        //第二象限
        if (x < center.x && y < center.y)
        {
            radius = 180 + radius;
        }
        
        //第三象限
        if (x < center.x && y > center.y)
        {
            radius = 180 - radius;
        }
        //第四象限
        if (x > center.x && y > center.y)
        {
        }
        Log.i("Pie",radius+"");
        return radius;
    }
    
    /**
     * 得到指定角度所在部分的索引值 
     * @param angle
     * @return
     */
    public int getIndexForAngle(float angle) {
        
        // 不考虑旋转角度，所以减掉
        float a = (angle - mChartAngle +360 ) % 360; 
        for (int i = 0; i < absoluteAngles.size(); i++)
        {
            if (absoluteAngles.get(i) > a) // 绝对角度刚好大于该角度时，则角度在此的弧形中
            {
                return i;
            }
        }
        return -1; // return -1 if no index found
    }

    /**
     * 准备画笔
     */
    @Override
    protected void prepareDataPaints(ColorTemplate ct)
    {
        paints = new SparseArray<Paint>();
        SparseIntArray colors = ct.getColors(); //
        Paint paint;
        for (int i = 0; i < colors.size(); i++)
        {
            paint = new Paint();
            paint.setColor(colors.get(i)); //颜色
            paint.setStyle(Style.FILL); // 使用颜色填充内容
            paint.setAntiAlias(true); // 防止锯齿
            paints.put(i, paint);
            paint = null;
        }
    }

    /**
     * 根据yVals计算相对、绝对角度
     */
    @Override
    protected void prepare()
    {
        angles = new SparseArray<Float>(); //相对度数
        absoluteAngles = new SparseArray<Float>(); //绝对度数
        
        for (int i = 0; i < yVals.size(); i++)
        {
            // 相对角度  =  数据值/总量 * 360
            angles.put(i, yVals.get(i)/ySum *360f);
            
            // i的据对角度为 i-1 的绝对角度 + i的相对角度
            if (i > 0)
            {
                absoluteAngles.put(i, absoluteAngles.get(i-1) + angles.get(i));
            } else
            {
                // 第一个绝对角度 = 相对角度
                absoluteAngles.put(i,angles.get(i));
            }
        }
    }
    
    /**
     * 根据触摸事件的按下事件，记录旋转的开始位置
     * @param x
     * @param y
     */
    public void setStartAngle(float x, float y) {
        mStartAngle = getAngleForPoint(x, y);
        // 扣除已旋转的角度
        mStartAngle -= mChartAngle;
    }
    
    /**
     * 更新图形
     * @param x
     * @param y
     */
    public void updateRotation(float x, float y) {
        mChartAngle = getAngleForPoint(x, y);
        mChartAngle -= mStartAngle;
        mChartAngle = (mChartAngle + 360f) % 360f;
    }
}
