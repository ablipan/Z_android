/**
 * author :  lipan
 * filename :  CommDialog.java
 * create_time : 2014年4月14日 下午3:55:33
 */
package com.pp.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.pp.R;
import com.pp.toast.CommToast;
import com.pp.utils.ViewUtils;

/**
 * @author : lipan
 * @create_time : 2014年4月14日 下午3:55:33
 * @desc : 通用提示框...
 * @update_time :
 * @update_desc : 使用方法：
 * 
 *              弹出提示信息Dialog：CommAlertDialog.showInfoDialog(params);
 * 
 *              弹出Confirm Dialog：showConfirmDialog.showConfirmDialog(params);
 * 
 */
public class CommAlertDialog
{

    private static final Boolean CANCEL_ABLE_DEFAULT = true;
    
    public static AlertDialog alertDialog;
    
    
    /**
     * 显示信息框
     * 
     * @param context
     *            上下文对象
     * @param message
     *            要显示的文本信息
     * @param btnText
     *            取消按钮显示文字
     * @param canceledOnTouchOutside
     *            点击Dialog周围隐藏dialog
     * @param cancelClickListener
     *            取消按钮监听
     * @return
     */
    public static AlertDialog showInfoDialog(Context context, String message,
            String btnText, Boolean canceledOnTouchOutside,
            final OnDialogClickListener dialogClickListener)
    {
        return showInfoDialog(context, message, btnText, canceledOnTouchOutside, CANCEL_ABLE_DEFAULT, dialogClickListener);
    }
    
   

    /**
     * 显示信息框
     * 
     * @param context
     *            上下文对象
     * @param message
     *            要显示的文本信息
     * @param btnText
     *            取消按钮显示文字
     * @param canceledOnTouchOutside
     *            点击Dialog周围隐藏dialog
     * @param canCancelAble
     *            点击返回按钮是否可退出
     * @param cancelClickListener
     *            取消按钮监听
     * @return
     */
    public static AlertDialog showInfoDialog(Context context, String message,
            String btnText, Boolean canceledOnTouchOutside, Boolean canCancelAble ,
            final OnDialogClickListener dialogClickListener)
    {
        // 自定义 alertdialog 视图
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.comm_alert_dialog_info,
                null);
        TextView dialogText = (TextView) dialogView
                .findViewById(R.id.dialogInfoText);
        dialogText.setText(message);
        // 确定按钮点击事件
        Button confirmBtn = (Button) dialogView
                .findViewById(R.id.alert_dialog_confirm);

        // 设置按钮文本，默认 “@string/alert_dialog_confirm”
        if (!"".equals(btnText) && null != btnText)
        {
            confirmBtn.setText(btnText);
        }
        confirmBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
                if (null != dialogClickListener)
                {
                    dialogClickListener.onClick(v);;
                }
            }
        });

        dismiss();

        alertDialog = new AlertDialog.Builder(context).create();
        
        //屏蔽返回键
        alertDialog.setCancelable(canCancelAble);
        // Window window = alertDialog.getWindow();
        // //设置显示动画
        // window.setWindowAnimations(R.style.comm_dialog);

        alertDialog.show();
        alertDialog.setContentView(dialogView);
        if (null != canceledOnTouchOutside)
        {
            alertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }
        
        //显示dialog的时候隐藏toast
//        CommToast.dismiss();
        return alertDialog;
    }
    
    

    
    /**
     * 
     * 显示对话框
     * 
     * @param context
     *            上下文对象
     * @param message
     *            要显示的文本信息
     * @param btnText
     *            取消按钮、确定按钮显示文字
     * @param canceledOnTouchOutside
     *            点击Dialog周围隐藏dialog
     * @param cancelClickListener
     *            取消按钮点击事件
     * @param onConfirmClickListener
     *            确定按钮点击事件
     * @return
     */
    public static AlertDialog showConfirmDialog(Context context,
            String message, String leftBtnText, String rightBtnText,
            Boolean canceledOnTouchOutside,
            final OnDialogClickListener dialogClickListener)
    {
        return showConfirmDialog(context, message, leftBtnText, rightBtnText, canceledOnTouchOutside, CANCEL_ABLE_DEFAULT , dialogClickListener);
    }

    /**
     * 
     * 显示对话框
     * 
     * @param context
     *            上下文对象
     * @param message
     *            要显示的文本信息
     * @param btnText
     *            取消按钮、确定按钮显示文字
     * @param canceledOnTouchOutside
     *            点击Dialog周围隐藏dialog
     * @param cancelClickListener
     *            取消按钮点击事件
     * @param cancelAble
     *            点击返回按钮是否可退出
     * @param onConfirmClickListener
     *            确定按钮点击事件
     * @return
     */
    public static AlertDialog showConfirmDialog(Context context,
            String message, String leftBtnText, String rightBtnText,
            Boolean canceledOnTouchOutside, Boolean cancelAble ,
            final OnDialogClickListener dialogClickListener)
    {
        // 自定义 alertdialog 视图
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.comm_alert_dialog_confirm,
                null);
        TextView dialogText = (TextView) dialogView
                .findViewById(R.id.dialogInfoText);
        dialogText.setText(message);

        // 取消按钮点击事件
        Button cancelBtn = (Button) dialogView
                .findViewById(R.id.alert_dialog_cancel);
        if (!"".equals(leftBtnText) && null != leftBtnText)
        {
            cancelBtn.setText(leftBtnText);
        }
        cancelBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
                if (null != dialogClickListener)
                {
                    dialogClickListener.onLeftClick(v);
                }
            }
        });

        // 确定按钮点击事件
        Button confirmBtn = (Button) dialogView
                .findViewById(R.id.alert_dialog_confirm_btn);
        if (!"".equals(rightBtnText) && null != rightBtnText)
        {
            confirmBtn.setText(rightBtnText);
        }
        confirmBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
                if (null != dialogClickListener)
                {
                    dialogClickListener.onRightClick(v);
                }
            }
        });

        dismiss();

        alertDialog = new AlertDialog.Builder(context).create();

        // Window window = alertDialog.getWindow();
        // //设置显示动画
        // window.setWindowAnimations(R.style.comm_dialog);

        alertDialog.show();
        alertDialog.setContentView(dialogView);
        if (null != canceledOnTouchOutside)
        {
            alertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }
        alertDialog.setCancelable(cancelAble);
        //显示dialog的时候隐藏toast
//        CommToast.dismiss();
        return alertDialog;
    }
    
    /**
     * 
     * 显示对话框(自定义view)
     * 
     * @param context
     *            上下文对象
     * @param View
     *            要显示的view
     * @param btnText
     *            取消按钮、确定按钮显示文字
     * @param canceledOnTouchOutside
     *            点击Dialog周围隐藏dialog
     * @param cancelClickListener
     *            取消按钮点击事件
     * @param onConfirmClickListener
     *            确定按钮点击事件
     * @return
     */
    public static AlertDialog showConfirmDialog(Context context, View view,
            String leftBtnText, String rightBtnText,
            Boolean canceledOnTouchOutside,
            final OnDialogClickListener dialogClickListener)
    {
        LinearLayout dialogLayout = new LinearLayout(context);
        dialogLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        dialogLayout.setGravity(Gravity.CENTER);
        dialogLayout.setOrientation(LinearLayout.VERTICAL);
        dialogLayout.setBackgroundDrawable(context.getResources().getDrawable(
                R.drawable.comm_round_coner_white_bg));

        LayoutParams viewParams = new LayoutParams(context.getResources()
                .getDimensionPixelSize(R.dimen.gap_250),
                LayoutParams.WRAP_CONTENT);
        int viewMarginL = context.getResources().getDimensionPixelSize(
                R.dimen.gap_20);
        int viewMarginT = context.getResources().getDimensionPixelSize(
                R.dimen.gap_10);
        viewParams.setMargins(viewMarginL, viewMarginT, viewMarginL, viewMarginT);
        // 添加自定义view
        dialogLayout.addView(view, viewParams);

        // view与按钮分隔线
        View lineHori = new View(context);
        lineHori.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                context.getResources().getDimensionPixelSize(R.dimen.pix_1)));
        lineHori.setBackgroundColor(context.getResources().getColor(
                R.color.black));
        dialogLayout.addView(lineHori);

        // 按钮横向layout
        LinearLayout buttonLayout = new LinearLayout(context);
        buttonLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setGravity(Gravity.CENTER);
        buttonLayout.setWeightSum(0);

        // 取消按钮
        Button cancelBtn = new Button(context);
        cancelBtn.setLayoutParams(new LinearLayout.LayoutParams(0, context
                .getResources().getDimensionPixelSize(R.dimen.gap_45), 1));
        cancelBtn.setText(context.getString(R.string.alert_dialog_cancel));
        cancelBtn.setTextSize(ViewUtils.getXmlDef(context,
                R.dimen.text_size_medium));
        cancelBtn.setTextColor(context.getResources().getColor(
                R.color.text_blue));
        cancelBtn.setBackgroundDrawable(context.getResources().getDrawable(
                R.drawable.comm_button_bg));
        buttonLayout.addView(cancelBtn);

        // 取消按钮与确认按钮分隔线
        View lineVert = new View(context);
        lineVert.setLayoutParams(new LayoutParams(context.getResources()
                .getDimensionPixelSize(R.dimen.pix_1),
                LayoutParams.MATCH_PARENT));
        lineVert.setBackgroundColor(context.getResources().getColor(
                R.color.black));
        buttonLayout.addView(lineVert);

        // 确认按钮
        Button confirmBtn = new Button(context);
        confirmBtn.setLayoutParams(new LinearLayout.LayoutParams(0, context
                .getResources().getDimensionPixelSize(R.dimen.gap_45), 1));
        confirmBtn.setText(context.getString(R.string.alert_dialog_confirm));
        confirmBtn.setTextSize(ViewUtils.getXmlDef(context,
                R.dimen.text_size_medium));
        confirmBtn.setTextColor(context.getResources().getColor(
                R.color.text_blue));
        confirmBtn.setBackgroundDrawable(context.getResources().getDrawable(
                R.drawable.comm_button_bg));
        buttonLayout.addView(confirmBtn);

        dialogLayout.addView(buttonLayout);

        // 取消按钮点击事件
        if (!"".equals(leftBtnText) && null != leftBtnText)
        {
            cancelBtn.setText(leftBtnText);
        }
        cancelBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
                if (null != dialogClickListener)
                {
                    dialogClickListener.onLeftClick(v);
                }
            }
        });

        // 确定按钮点击事件
        if (!"".equals(rightBtnText) && null != rightBtnText)
        {
            confirmBtn.setText(rightBtnText);
        }
        confirmBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
                if (null != dialogClickListener)
                {
                    dialogClickListener.onRightClick(v);
                }
            }
        });

        dismiss();

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setContentView(dialogLayout);
        
        //只用下面这一行弹出对话框时需要点击输入框才能弹出软键盘  
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);  
        
//        //加上下面这一行弹出对话框时软键盘随之弹出  
//        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);  
        
        if (null != canceledOnTouchOutside)
        {
            alertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }
        
        //显示dialog的时候隐藏toast
//        CommToast.dismiss();
        return alertDialog;
    }
    
    /**
     * 
     * 显示对话框(自定义view)
     * 
     * @param context
     *            上下文对象
     * @param View
     *            要显示的view
     * @param btnText
     *            取消按钮、确定按钮显示文字
     * @param canceledOnTouchOutside
     *            点击Dialog周围隐藏dialog
     * @param cancelClickListener
     *            取消按钮点击事件
     * @param onConfirmClickListener
     *            确定按钮点击事件
     * @return
     */
    public static AlertDialog showInfoDialog(Context context, View view, String btnText,
            Boolean canceledOnTouchOutside,
            final OnDialogClickListener dialogClickListener)
    {
        LinearLayout dialogLayout = new LinearLayout(context);
        dialogLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        dialogLayout.setGravity(Gravity.CENTER);
        dialogLayout.setOrientation(LinearLayout.VERTICAL);
        dialogLayout.setBackgroundDrawable(context.getResources().getDrawable(
                R.drawable.comm_round_coner_white_bg));
        
        LayoutParams viewParams = new LayoutParams(context.getResources()
                .getDimensionPixelSize(R.dimen.gap_250),
                LayoutParams.WRAP_CONTENT);
        int viewMarginL = context.getResources().getDimensionPixelSize(
                R.dimen.gap_10);
        int viewMarginT = context.getResources().getDimensionPixelSize(
                R.dimen.gap_10);
        viewParams.setMargins(viewMarginL, 0, viewMarginL, viewMarginT);
        // 添加自定义view
        dialogLayout.addView(view, viewParams);
        
        // view与按钮分隔线
        View lineHori = new View(context);
        lineHori.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                context.getResources().getDimensionPixelSize(R.dimen.pix_1)));
        lineHori.setBackgroundColor(context.getResources().getColor(
                R.color.black));
        dialogLayout.addView(lineHori);
        
        // 确认按钮
        Button confirmBtn = new Button(context);
        confirmBtn.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                context.getResources().getDimensionPixelSize(R.dimen.gap_45)));
        confirmBtn.setText(context.getString(R.string.alert_dialog_confirm));
        confirmBtn.setTextSize(ViewUtils.getXmlDef(context,
                R.dimen.text_size_medium));
        confirmBtn.setTextColor(context.getResources().getColor(
                R.color.text_blue));
        confirmBtn.setBackgroundDrawable(context.getResources().getDrawable(
                R.drawable.comm_button_bg));
        
        dialogLayout.addView(confirmBtn);
        
        // 设置按钮文本，默认 “@string/alert_dialog_confirm”
        if (!"".equals(btnText) && null != btnText)
        {
            confirmBtn.setText(btnText);
        }
        confirmBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
                if (null != dialogClickListener)
                {
                    dialogClickListener.onClick(v);;
                }
            }
        });
        
        dismiss();
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setContentView(dialogLayout);
        
        if (null != canceledOnTouchOutside)
        {
            alertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }
        
        //显示dialog的时候隐藏toast
//        CommToast.dismiss();
        return alertDialog;
    }

    /**
     * 是否在显示...
     * @return
     */
    public static boolean isShowing()
    {
        if(null == alertDialog)
        {
            return false;
        }
        return alertDialog.isShowing();
    }
    
    
    /**
     * 隐藏Dialog
     */
    public static void dismiss()
    {
        if (null != alertDialog && alertDialog.isShowing())
        {
            alertDialog.dismiss();
            alertDialog = null;
        }
    }

}
