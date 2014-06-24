/**
 * author :  lipan
 * filename :  ViewUtils.java
 * create_time : 2014年4月19日 下午12:04:09
 */
package com.pp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.pp.R;
import com.pp.dialog.CommAlertDialog;
import com.pp.dialog.OnDialogClickListener;

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
    
    // 动画持续时间
    private static final int ANIM_DURATION_SHORT = 200;// 2D翻转持续时间

    //布局宽高参数
    public enum LayoutParamsType
    {
        MATCH_MATCH, MATCH_WRAP, WRAP_WRAP, WRAP_MATCH
    }

    private static TypedValue mTmpValue = new TypedValue();
    /**
     * 获得
     * @param context
     * @param id
     * @return
     */
    public static int getXmlDef(Context context, int id)
    {
         synchronized (mTmpValue) 
         {
             TypedValue value = mTmpValue;
             context.getResources().getValue(id, value, true);
             return (int)TypedValue.complexToFloat(value.data);
         }
    }

    
    /**
     * 让文本输入框获得焦点并且弹出软键盘
     */
    public static void setEditTextFocus(final EditText editText)
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
        }, 600);
    }

    /**
     * 让文本输入框失去焦点并且隐藏软键盘
     */
    public static void removeEditTextFocus(final EditText editText)
    {
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
        InputMethodManager inputManager = (InputMethodManager) editText
                .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 添加动画背景并且开始动画
     * 
     * @param animId
     */
    public static void addWaterAnim(View view, int animId)
    {
        view.setBackgroundResource(animId);
        ((AnimationDrawable) view.getBackground()).start();
    }

    /**
     * 获得屏幕显示的标量
     * 
     * @param context
     * @return
     */
    public static DisplayMetrics getDeviceMetrics(Context context)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics;
    }

    /**
     * 获得屏幕显示的宽度
     * 
     * @param context
     * @return
     */
    public static int getDeviceWidth(Context context)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    /**
     * 获得屏幕显示的高度
     * 
     * @param context
     * @return
     */
    public static int getDeviceHeight(Context context)
    {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
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
     * 
     * @param v
     * @return
     */
    public static boolean isShowing(View v)
    {
        return (v.getVisibility() == View.VISIBLE) ? true : false;
    }

    /**
     * 隐藏view
     * 
     * @param v
     */
    public static void hide(View v)
    {
        v.setVisibility(View.GONE);
    }

    /**
     * 隐藏view时加动画
     * 
     * @param v
     */
    public static void hideAnim(View v)
    {
        // 转入转出3d滚动动画
        Rotate3DCubeAnimation cubeAnim_0_90 = new Rotate3DCubeAnimation(0, 90);
        cubeAnim_0_90.setDuration(ANIM_DURATION_LONG);
        cubeAnim_0_90.setFillAfter(true);
        v.startAnimation(cubeAnim_0_90);
        hide(v);
        cubeAnim_0_90.setAnimationListener(new CleanAnimationListener(v));
    }
    
    /**
     * 显示view时加动画
     * 
     * @param v
     */
    public static void showAnim(View v)
    {
        // 转入转出3d滚动动画
        Rotate3DCubeAnimation cubeAnim_90_0 = new Rotate3DCubeAnimation(-90, 0);
        cubeAnim_90_0.setDuration(ANIM_DURATION_LONG);
        cubeAnim_90_0.setFillAfter(true);
        show(v);
        v.startAnimation(cubeAnim_90_0);
        cubeAnim_90_0.setAnimationListener(new CleanAnimationListener(v));
    }

    /**
     * 显示view时加动画
     * 
     * @param v
     */
    public static void showAnim(View v , CleanAnimationListener cleanAnimationListener)
    {
        // 转入转出3d滚动动画
        Rotate3DCubeAnimation cubeAnim_90_0 = new Rotate3DCubeAnimation(-90, 0);
        cubeAnim_90_0.setDuration(ANIM_DURATION_LONG);
        cubeAnim_90_0.setFillAfter(true);
        show(v);
        v.startAnimation(cubeAnim_90_0);
        cubeAnim_90_0.setAnimationListener(cleanAnimationListener);
    }

    /**
     * 给view添加触摸透明事件
     * 
     * @param viewToListener
     *            需要添加监听的view
     * @param viewToAlpha
     *            需要改变背景透明值的view
     * 
     *            使用场景：viewToListener为viewToAlpha的外一层layout，
     *            点击外层layout时改变里层Imageview的alpha，如果没有里外层，两个参数都传imageview即可
     *            Imageview包含background属性，而不是src!!!，触摸时改变图片的background透明值
     * 
     */
    public static void addViewTouchAlpha(View viewToListener,
            final View viewToAlpha)
    {
        final int alphaPress = 100;
        final int alphaNormal = 255;
        viewToListener.setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:// PRESSED DOWN
                        Drawable backgroundTrans = viewToAlpha.getBackground();
                        if(null == backgroundTrans)
                        {
                            backgroundTrans = ((ImageView)viewToAlpha).getDrawable();
                            backgroundTrans.setAlpha(alphaPress);
                            ((ImageView)viewToAlpha).setImageDrawable(backgroundTrans);
                        }else
                        {
                            backgroundTrans.setAlpha(alphaPress);
                            viewToAlpha.setBackgroundDrawable(backgroundTrans);
                        }
                        break;
                    case MotionEvent.ACTION_UP: // PRESSED UP
                    case MotionEvent.ACTION_CANCEL: // PRESSED UP
                        Drawable backgroundNormal = viewToAlpha.getBackground();
                        if(null == backgroundNormal)
                        {
                            backgroundNormal = ((ImageView)viewToAlpha).getDrawable();
                            backgroundNormal.setAlpha(alphaNormal);
                            ((ImageView)viewToAlpha).setImageDrawable(backgroundNormal);
                        }else
                        {
                            backgroundNormal.setAlpha(alphaNormal);
                            viewToAlpha.setBackgroundDrawable(backgroundNormal);
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 给gridview每个View添加触摸透明事件
     * 
     * @param view
     *            gridview
     * @param imgViewId
     *            需要改变背景的图片id
     *            使用场景，gridview的每个item中包含一个Imageview，Imageview包含background属性
     *            ，而不是src!!!，触摸时改变图片的background透明值
     * 
     */
    public static void addGridViewTouchAlpha(final GridView view,
            final int imgViewId)
    {
        final int alphaPress = 100;
        final int alphaNormal = 255;

        view.setOnTouchListener(new OnTouchListener()
        {
            // girdview的历史触摸图片
            private ImageView hisTouchView; 

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                int position = view.pointToPosition((int) event.getX(),
                        (int) event.getY());
                LinearLayout item = (LinearLayout) view.getChildAt(position);
                
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        if (null != item)
                        {
                            ImageView imageItem = (ImageView) item
                                    .findViewById(imgViewId);
                            Drawable background = imageItem.getBackground();
//                            if(null == background)
//                            {
//                                background = imageItem.getDrawable();
//                                background.setAlpha(alphaPress);
//                                imageItem.setImageDrawable(background);
//                            }else
//                            {
                                background.setAlpha(alphaPress);
                                imageItem.setBackgroundDrawable(background);
//                            }
                            hisTouchView = imageItem;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        if(null != hisTouchView)
                        {
                            Drawable background = hisTouchView.getBackground();
//                            if(null == background)
//                            {
//                                background = hisTouchView.getDrawable();
//                                background.setAlpha(alphaNormal);
//                                hisTouchView.setImageDrawable(background);
//                            }else
//                            {
                                background.setAlpha(alphaNormal);
                                hisTouchView.setBackgroundDrawable(background);
//                            }
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }


    /**
     * 设置输入框的值，并且光标移到末尾位置
     * @param v
     * @param s
     */
    public static void setTextSelectionEnd(EditText v , String s)
    {
        v.setText(s);
        if(s.length() > 0)
        {
            v.setSelection(s.length());
        }
    }
    
    /**
     * 开始3d横向翻转
     * 
     * @param v
     *            需要翻转的图片
     * @param check
     *            是否为选中动作
     */
    public static Rotate3dAnimation rotate3dHide(final View v , AnimationListener listener)
    {
        // 获取布局的中心点位置，作为翻转的中心点
        float centerX = v.getWidth() / 2f;
        float centerY = v.getHeight() / 2f;

        // 构建3D翻转动画对象，翻转角度为0到90度，这使得View将会从可见变为不可见
        Rotate3dAnimation checkAnim_0_90 = new Rotate3dAnimation(0, 90, centerX, centerY, 300f,
                true);

        // 动画持续时间500毫秒
        checkAnim_0_90.setDuration(ANIM_DURATION_SHORT);

        // 动画完成后保持完成的状态
        checkAnim_0_90.setFillAfter(true);
        checkAnim_0_90.setInterpolator(new AccelerateInterpolator());

        if(null != listener)
        {
            // 设置动画的监听器
            checkAnim_0_90.setAnimationListener(listener);
        }
        v.startAnimation(checkAnim_0_90);
        return checkAnim_0_90;
    }
    /**
     * 开始3d横向翻转
     * 
     * @param v
     *            需要翻转的图片
     * @param check
     *            是否为选中动作
     */
    public static void rotate3dShow(final View v , AnimationListener listener)
    {
        // 获取布局的中心点位置，作为翻转的中心点
        float centerX = v.getWidth() / 2f;
        float centerY = v.getHeight() / 2f;
        
        // 构建3D翻转动画对象，翻转角度为270到360度，这使得View将会从不可见变为可见
        final Rotate3dAnimation rotation = new Rotate3dAnimation(270,
                360, centerX, centerY, 300f, false);

        // 动画持续时间500毫秒
        rotation.setDuration(ANIM_DURATION_SHORT);

        // 动画完成后保持完成的状态
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        if (null != listener)
        {
            rotation.setAnimationListener(listener);
        }
        v.startAnimation(rotation);
    }
    
    /**
     * 使用Context结束activity
     * @param context
     */
    public static void finishActivity(Context context)
    {
        ((Activity)context).finish();
    }
    
    /**
     * 检查网络连接
     * @param context
     * @return
     */
    public static boolean checkNetwork(final Context context)
    {
        //判断网络环境
        if(!CheckPhoneStatus.checkNetWorkStatus(context))
        {
            CommAlertDialog.showConfirmDialog(
                    context, 
                    context.getString(R.string.network_disable),
                    context.getString(R.string.existApp),
                    context.getString(R.string.acessSetting),
                    false, false,
                    new OnDialogClickListener()
                    {
    
                        @Override
                        public void onLeftClick(View v)
                        {
                            ViewUtils.finishActivity(context);
                        }
                        
                        @Override
                        public void onRightClick(View v)
                        {
                            if(android.os.Build.VERSION.SDK_INT > 10 ){
                                //3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
                                context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                            } else {
                                context.startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                            }
                        }
                    });
            return false;
        }else
        {
            return true;
        }
    }
    
    
    /**
     * 获得线性布局的layout参数
     * @param type
     * @return
     */
    
    public static LayoutParams getLinearLayoutParam(LayoutParamsType type)
    {
        if(LayoutParamsType.MATCH_MATCH.equals(type))
        {
            return new LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.MATCH_PARENT);
        }else if(LayoutParamsType.MATCH_WRAP.equals(type))
        {
            return new LayoutParams(LayoutParams.MATCH_PARENT , LayoutParams.WRAP_CONTENT);
        }else if(LayoutParamsType.WRAP_WRAP.equals(type))
        {
            return new LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT);
        }else if(LayoutParamsType.WRAP_MATCH.equals(type))
        {
            return new LayoutParams(LayoutParams.WRAP_CONTENT , LayoutParams.MATCH_PARENT);
        }
        return null;
    }
    
    /**
     * 金额、有效数值输入框不能以0开头
     * @param editText
     */
    public static void setTVNotStartZero(EditText editText)
    {
        String outText = editText.getText().toString();
        if (outText.length()==1 && "0".equals(outText))
        {
            editText.setText("");
        }
    }
    
    /**
     * 实例一个Edittext对象
     * @param editText
     */
    public static EditText getEditText(Context context ,String content)
    {
       EditText et = new EditText(context);
       et.setLayoutParams(getLinearLayoutParam(LayoutParamsType.WRAP_WRAP));
       et.setText(content);
       return et;
    }
    
    /**
     * 为ImageView设置背景资源
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static void setImageViewDrawable(ImageView imageView , Drawable resource)
    {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
        {
            imageView.setBackground(resource);
        }else
        {
            imageView.setBackgroundDrawable(resource);
        }
    }
    
    /**
     * dp转pix
     * @param context
     * @param dp
     * @return
     */
    public static float convertDpToPixel(Context context , float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }
}
