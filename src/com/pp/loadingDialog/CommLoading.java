/**
 * author :  lipan
 * filename :  LoadingDialog.java
 * create_time : 2014年4月11日 下午8:43:53
 */
package com.pp.loadingDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.pp.R;
import com.pp.utils.ViewUtils;

/**
 * @author : lipan
 * 
 * @create_time : 2014年4月11日 下午8:43:53
 * 
 * @desc : Loading时显示的dialog...
 * 
 * @update_time :
 * @update_desc :
 * 
 *  使用方法： 
 *  
 *  //显示 loading
 *  CommLoading.show(params); 
 *  
 *  //显示loading，监听laoding的隐藏事件
 *  CommLoading.show(context, new OnDismissListener(){ //....  });
 *  
 *  //隐藏loading
 *  CommLoading.dismiss();
 * 
 * 
 */
public class CommLoading
{

    // loading Dialog
    public static Dialog loadingDialog;

    /**
     * 显示loading框
     * 
     * @param context
     *            上下文对象
     */
    public static Dialog show(Activity context)
    {
        return show(context, null, null);
    }

    /**
     * 显示loading框
     * 
     * @param context
     *            上下文对象
     * @param loadingText
     *            loading文字，默认为 “加载中...”
     */
    public static Dialog show(Activity context, Integer loadingText)
    {
        return show(context, loadingText, null);
    }

    /**
     * 显示loading框
     * 
     * @param context
     *            上下文对象
     * @param dismissListener
     *            dialog消失时触发的事件...如dialog消失时,取消当前Http请求
     */
    public static Dialog show(Activity context,
            OnDismissListener onDismissListener)
    {
        return show(context, null, onDismissListener);
    }

    /**
     * 显示loading框
     * 
     * @param context
     *            上下文对象
     * @param loadingText
     *            loading文字，默认为 “加载中...”
     * @param dismissListener
     *            dialog消失时触发的事件...如dialog消失时,取消当前Http请求
     */
    public static Dialog show(Activity context, Integer loadingText,
            OnDismissListener onDismissListener)
    {
        Animation loadingAnimation = AnimationUtils.loadAnimation(context,
                R.anim.loading_animation);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // loading布局视图
        View layout = inflater.inflate(R.layout.comm_loading, null);

        // loading的圆圈...
        ImageView loadImage = (ImageView) layout
                .findViewById(R.id.loading_circle);

        // 添加旋转动画
        loadImage.setAnimation(loadingAnimation);

        // 如果自定义了loading的文字
        if (null != loadingText)
        {
            TextView loadingTextView = (TextView) layout
                    .findViewById(R.id.loading_text);
            loadingTextView.setText(context.getString(loadingText));
        }

        // 取消加载按钮
         ImageView loadingCancelImg = (ImageView) layout
         .findViewById(R.id.loading_cancel);
         
        View loadingCancelView = (View) layout
                .findViewById(R.id.loading_cancel_view);
        loadingCancelView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });

        //
        ViewUtils.addViewTouchAlpha(loadingCancelView, loadingCancelImg);
        
        loadingDialog = new Dialog(context, R.style.loading_dialog)
        {
            // 返回按钮事件——隐藏laodingdialog
            @Override
            public void onBackPressed()
            {
                dismiss();
            }
        };

        loadingDialog.setContentView(layout);
        // LayoutParams lay = loadingDialog.getWindow().getAttributes();
        // setParams(context, lay);

        if (null != onDismissListener)
        {
            loadingDialog.setOnDismissListener(onDismissListener);
        }

        loadingDialog.show();

        return loadingDialog;
    }

    /**
     * 隐藏loading框
     */
    public static void dismiss()
    {
        if (null != loadingDialog && loadingDialog.isShowing())
        {
            loadingDialog.dismiss();
        }
    }

    /**
     * 设置dialog大小
     * 
     * @param context
     * @param lay
     */
    @SuppressWarnings("unused")
    private static void setParams(Activity context, LayoutParams lay)
    {

        DisplayMetrics dm = new DisplayMetrics();

        context.getWindowManager().getDefaultDisplay().getMetrics(dm);

        Rect rect = new Rect();

        View view = context.getWindow().getDecorView();

        view.getWindowVisibleDisplayFrame(rect);

        lay.height = dm.heightPixels - rect.top;
        lay.width = dm.widthPixels;
    }
}
