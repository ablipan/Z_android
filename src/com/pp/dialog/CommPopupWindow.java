/**
 * author :  lipan
 * filename :  CommDialog.java
 * create_time : 2014年4月14日 下午3:55:33
 */
package com.pp.dialog;

import com.pp.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * @author : lipan
 * @create_time : 2014年4月14日 下午3:55:33
 * @desc : 通用提示框...
 * @update_time :
 * @update_desc :
 *
 */
public class CommPopupWindow
{
    
    public static PopupWindow popupWindow;
    
    /**
     * 显示信息框
     * @param context 上下文对象
     * @param message 要显示的文本信息
     * @return
     */
    public static PopupWindow show(Context context , View anchor , String message)
    {
        //实例
        popupWindow = new PopupWindow(400,500);
        popupWindow.setOutsideTouchable(true);
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        textView.setBackgroundColor(context.getResources().getColor(R.color.text_black));
        textView.setText("这是一个测试信息");
        popupWindow.setContentView(textView);
        popupWindow.showAsDropDown(anchor);
        return popupWindow;
    }
    
    /**
     * 隐藏对话框
     */
    public static void dismiss()
    {
        if(null != popupWindow && popupWindow.isShowing())
        {
            popupWindow.dismiss();
        }
    }
    
}
