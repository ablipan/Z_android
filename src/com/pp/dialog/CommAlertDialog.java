/**
 * author :  lipan
 * filename :  CommDialog.java
 * create_time : 2014年4月14日 下午3:55:33
 */
package com.pp.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.pp.R;

/**
 * @author : lipan
 * @create_time : 2014年4月14日 下午3:55:33
 * @desc : 通用提示框...
 * @update_time :
 * @update_desc :
 *
 */
public class CommAlertDialog
{
    
    public static AlertDialog alertDialog ;
    
    /**
     * 显示信息框
     * @param context 上下文对象
     * @param message 要显示的文本信息
     * @return
     */
    public static AlertDialog showInfoDialog(Context context , String message)
    {
        //自定义 alertdialog 视图
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.comm_alert_dialog_info, null);
        TextView dialogText = (TextView)dialogView.findViewById(R.id.dialogInfoText);
        dialogText.setText(message);
        
        //确定按钮点击事件
        Button confirmBtn = (Button) dialogView.findViewById(R.id.alert_dialog_confirm);
        confirmBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });
        
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setContentView(dialogView);
        return alertDialog;
    }
    
    /**
     * 
     * 确定对话框
     * @param context 上下文对象
     * @param message 要显示的文本信息
     * @param onConfirmClickListener 确定按钮点击事件
     * @return
     */
    public static AlertDialog showConfirmDialog(Context context , String message , final OnConfirmClickListener onConfirmClickListener)
    {
        //自定义 alertdialog 视图
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.comm_alert_dialog_confirm, null);
        TextView dialogText = (TextView)dialogView.findViewById(R.id.dialogInfoText);
        dialogText.setText(message);
        
        //取消按钮点击事件
        Button cancelBtn = (Button) dialogView.findViewById(R.id.alert_dialog_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });
        
        //确定按钮点击事件
        Button confirmBtn = (Button) dialogView.findViewById(R.id.alert_dialog_confirm_btn);
        confirmBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });
//        confirmBtn.seton
        confirmBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dismiss();
                onConfirmClickListener.onClick(v);
            }
        });
        
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setContentView(dialogView);
        return alertDialog;
    }

    public interface TTT extends View.OnClickListener
    {
        
    }
    
    /**
     * 隐藏对话框
     */
    public static void dismiss()
    {
        if(null != alertDialog && alertDialog.isShowing())
        {
            alertDialog.dismiss();
        }
    }
    
}
