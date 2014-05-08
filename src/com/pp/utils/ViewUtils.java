/**
 * author :  lipan
 * filename :  ViewUtils.java
 * create_time : 2014年4月19日 下午12:04:09
 */
package com.pp.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


/**
 * 视图工具类
 * 
 * @author : lipan
 * @create_time : 2014年4月19日 下午12:04:09
 * @desc : 视图工具类
 * @update_time :
 * @update_desc :
 * 
 */
public class ViewUtils
{
    
    private static final int ANIM_DURATION_LONG = 800;// 3D滚动持续时间
    
    /**
     * 让文本输入框获得焦点并且弹出软键盘
     */
    public static void setEditTextFocus( final EditText editText)
    {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                InputMethodManager inputManager = (InputMethodManager) editText
                        .getContext().getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(editText, 0);
            }
        }, 200);
    }
    
    /**
     * 让文本输入框失去焦点并且隐藏软键盘
     */
    public static void removeEditTextFocus( final EditText editText)
    {
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        InputMethodManager inputManager = (InputMethodManager) editText.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
    
    /**
     * 添加动画背景并且开始动画
     * @param animId
     */
    public static void addWaterAnim(View view , int animId)
    {
        view.setBackgroundResource(animId);
        ((AnimationDrawable)view.getBackground()).start(); 
    }
    
    
    /**
     * 获得屏幕显示的标量
     * @param context
     * @return
     */
    public static DisplayMetrics getDeviceMetrics(Context context)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics;
    }
    
    /**
     * 获得屏幕显示的宽度
     * @param context
     * @return
     */
    public static int getDeviceWidth(Context context)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }
    
    /**
     * 显示view
     * 
     * @param v
     */
    public static void show(View v)
    {
        v.setVisibility(View.VISIBLE);
    }
    
    /**
     * 视图是否显示？
     * @param v
     * @return
     */
    public static boolean isShow(View v)
    {
        return (v.getVisibility() == View.VISIBLE) ? true : false;
    }
    
    /**
     * 隐藏view
     * 
     * @param v
     */
    public static  void hide(View v)
    {
        v.setVisibility(View.GONE);
    }
    
    /**
     * 给view添加触摸透明事件
     * @param viewToListener 需要添加监听的view
     * @param viewToAlpha   需要改变背景透明值的view
     */
    public static void addViewTouchAlpha(View viewToListener , final View viewToAlpha)
    {
        final int alphaPress = 100;
        final int alphaNormal = 255;
        viewToListener.setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:// PRESSED DOWN
                        Drawable backgroundTrans = viewToAlpha.getBackground();
                        backgroundTrans.setAlpha(alphaPress);
                        return false;
                    case MotionEvent.ACTION_UP: // PRESSED UP
                        Drawable backgroundNormal = viewToAlpha.getBackground();
                        backgroundNormal.setAlpha(alphaNormal);
                        return false;
                    default:
                        return false;
                }
            }
        });
    }
}
