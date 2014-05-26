
package com.pp.datePicker;

import java.util.Calendar;
import java.util.Locale;

import wheelview.adapters.NumericWheelAdapter;
import wheelview.spinnerwheel.AbstractWheel;
import wheelview.spinnerwheel.OnWheelChangedListener;
import wheelview.spinnerwheel.WheelVerticalView;
import android.app.Dialog;
import android.content.Context;
import android.util.SparseIntArray;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.pp.R;
import com.pp.utils.DateUtils;
import com.pp.utils.StringB;
import com.pp.utils.ViewUtils;
import com.pp.utils.ViewUtils.LayoutParamsType;


/**
 * @author : lipan
 * @create_time : 2014年5月23日18:47:55
 * @desc : 时间选择dialog,使用dateformat来表示需要显示的内容。
 * @update_time :
 * @update_desc :
 *
 */
public class CommDatePicker
{
    
    // TODO 将下面的值设置为变量进行调用
    private static Dialog dateDialog;
    
    public static final char YEAR_EXP = 'y';
    public static final int YEAR_VAL_MIN = 1900;
    public static final int YEAR_VAL_MAX = 2100;
    public static boolean YEAR_SCROLL = true; //年是否滚动
    
    public static final char MONTH_EXP = 'M';
    public static final int MONTH_VAL_MIN = 1;
    public static final int MONTH_VAL_MAX = 12;
    public static boolean MONTH_SCROLL = true; //月是否滚动
    
    public static final char DAY_EXP = 'd';
    public static int DAY_VAL_MIN = 1;
    public static int DAY_VAL_MAX;
    public static boolean DAY_SCROLL = true; //日是否滚动
    
    //每个月的天数
    private static SparseIntArray monthDays;
    
//    private static final String EXP_HOUR = "HH";
//    private static final String EXP_MINUTE = "mm";
//    private static final String EXP_SECOND = "ss";
    
    //单位..
    private static final String UNIT_YEAR = "%1$d年";
    private static final String UNIT_MONTH = "%1$d月";
    private static final String UNIT_DAY = "%1$d日";
//    private static final String UNIT_HOUR = "时";
//    private static final String UNIT_MINUTE = "分";
//    private static final String UNIT_SECOND = "秒";
    
    private static final boolean WHEEL_CYCLIC = true; //循环
    private static final boolean WHEEL_NO_CYCLIC = false; //不循环
    
    private static AbstractWheel dayWheel;
    private static AbstractWheel monthWheel;
    private static AbstractWheel yearWheel;
    
    private static Context context; //
    private static Calendar calendar; // 日期
    private static String dateExp;
    
    private static Window dialogWindow;
    private static LayoutParams dialogLayoutParams;
    /**
     * 显示日期选择dialog
     * @param ctx 上下文
     * @param cal Calendar对象
     * @param exp 时间表达式：yyyy表示年 、MM表示月、dd表示日，可以任意组合
     * @return
     */
    public static Dialog show(Context ctx , Calendar cal , String exp)
    {
        context = ctx;
        calendar = cal;
        dateExp = exp;
        
        if(null == cal)
        {
            calendar = Calendar.getInstance(Locale.CHINA);
        }else
        {
            calendar = cal;
        }
        
        //指定日期的最大天数
        monthDays = DateUtils.getMonthMaxDays(calendar);
        
        //当月最大天数
        DAY_VAL_MAX = monthDays.get(DateUtils.getCalVal(calendar, Calendar.MONTH));
        
        //解析时间表达式,得到表达式代表的日期滚轮
        LinearLayout dateWheels = parseExp(context, dateExp);
        
        //指定样式的dialog
        dateDialog = new Dialog(context, R.style.dateWheel);
        
        //位置设置为屏幕底部
        dateDialog.getWindow().setGravity(Gravity.BOTTOM);
        
        //宽度为全屏，高度为200dp
        dialogLayoutParams = new LayoutParams(ViewUtils.getDeviceWidth(context), 
                context.getResources().getDimensionPixelOffset(R.dimen.gap_200));
        dateDialog.setContentView(dateWheels , dialogLayoutParams);
        dateDialog.show();
        return dateDialog;
    }
    
    public static void dismiss()
    {
        if(null != dateDialog && dateDialog.isShowing())
        {
            dateDialog.dismiss();
        }
    }
    
    /**
     * 解析exp得到包含date wheels的容器
     * @param context
     * @param exp
     * @return
     */
    private static LinearLayout parseExp(Context context , String exp)
    {
        //实例化wheel容器
        LinearLayout wheelParcel = new LinearLayout(context);
        wheelParcel.setLayoutParams(ViewUtils.getLinearLayoutParam(LayoutParamsType.WRAP_WRAP));
        wheelParcel.setOrientation(LinearLayout.HORIZONTAL);
        wheelParcel.setGravity(Gravity.CENTER);
        
        char[] temp;
        
        for (int i = 0; i < exp.length(); i++)
        {
            if(exp.charAt(i) == 'y')
            {
                if((i+4) <= exp.length())//年
                {
                    temp = new char[4];
                    
                    exp.getChars(i, i+4, temp, 0);
                    if(StringB.getStr(YEAR_EXP, 4).equals(StringB.getStr(temp)))
                    {
                        //添加年
                        yearWheel = getWheelView(context ,YEAR_VAL_MIN ,YEAR_VAL_MAX , UNIT_YEAR ,WHEEL_NO_CYCLIC);
                        
                        //选中当前年
                        yearWheel.setCurrentItem(DateUtils.getCalVal(calendar, Calendar.YEAR) - YEAR_VAL_MIN);
                        
                        wheelParcel.addView(yearWheel);
                    }
                }
            }else if(exp.charAt(i) == 'M')//月
            {
                if((i+2) <= exp.length())
                {
                    temp = new char[2];
                    exp.getChars(i, i+2, temp, 0);
                    if(StringB.getStr(MONTH_EXP, 2).equals(StringB.getStr(temp)))
                    {
                        //添加月份
                        monthWheel = getWheelView(context ,MONTH_VAL_MIN ,MONTH_VAL_MAX , UNIT_MONTH ,WHEEL_NO_CYCLIC);
                        
                        //选中当月
                        monthWheel.setCurrentItem(DateUtils.getCalVal(calendar, Calendar.MONTH)-1);
                        wheelParcel.addView(monthWheel);
                    }
                }
            }else if(exp.charAt(i) == 'd')//日
            {
                if((i+2) <= exp.length())
                {
                    temp = new char[2];
                    exp.getChars(i, i+2, temp, 0);
                    if(StringB.getStr(DAY_EXP, 2).equals(StringB.getStr(temp)))
                    {
                        //添加日
                        dayWheel = getWheelView(context ,DAY_VAL_MIN ,DAY_VAL_MAX , UNIT_DAY ,WHEEL_CYCLIC);
                        //选中当日
                        dayWheel.setCurrentItem(DateUtils.getCalVal(calendar, Calendar.DAY_OF_MONTH)-1);
                        wheelParcel.addView(dayWheel);
                    }
                }
            }
            
        }
        return wheelParcel;
    }
    
    /**
     * 生成 wheel view
     * @param context 
     * @param min 最小值
     * @param max 最大值
     * @param format 格式
     * @param cyclic 是否循环显示
     * @return
     */
    private static AbstractWheel getWheelView(Context context , int min , int max , String format , boolean cyclic)
    {
        //为了能让所有字符都能完整显示，需要根据文字的长度动态的调整宽度。
//        StringB.getStrLen();
        AbstractWheel item = new WheelVerticalView(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT ,
                                                        LayoutParams.WRAP_CONTENT);  
        item.setLayoutParams(layoutParams);
        
        NumericWheelAdapter itemAdapter = new NumericWheelAdapter(context ,min , max , format);
        itemAdapter.setItemResource(R.layout.wheel_item_layout);
        itemAdapter.setItemTextResource(R.id.wheel_item_text);
        
        item.setViewAdapter(itemAdapter);
        item.setCyclic(cyclic);
        item.addChangingListener(new MyWheelChangedListener());
        return item;
    }
    
    /**
     * <?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="50dp"
        android:layout_height="30dp">
    <TextView android:id="@+id/text"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="20dp"
            android:lines="1"
            android:textStyle="bold"
            android:textColor="#777"
            android:layout_gravity="center" />
     * 
     */
    private static FrameLayout getWheelItemLayout()
    {
        FrameLayout itemLayout = new FrameLayout(context);
//        itemLayout.setLayoutParams(new FrameLayout.LayoutParams(context.getResources().getDimensionPixelSize(R.dimen.), height));
        
        return itemLayout;
        
    }
    
    /**
     * 滚轮监听事件
     */
    private static class MyWheelChangedListener implements OnWheelChangedListener
    {
        @Override
        public void onChanged(AbstractWheel wheel, int oldValue, int newValue)
        {
            
        }
    }
    
}
