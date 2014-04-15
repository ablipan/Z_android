/**
 * author :  lipan
 * filename :  CommonToast.java
 * create_time : 2014年4月14日 上午9:53:03
 */
package com.pp.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pp.R;

/**
 * @author : lipan
 * @create_time : 2014年4月14日 上午9:53:03
 * @desc : Toast
 * @update_time :
 * @update_desc :
 * 
 * 使用方法：
 * 
 *  //显示Toast
 *  CommToast.showInfo("xxxx"); 
 * 
 *  该方法返回值为toast对象，可以对toast对象进行其他设置
 */
public class CommToast
{

    private static Toast toast;

    // 默认显示时长
    private static final int DEF_DURATION = Toast.LENGTH_SHORT;

    /**
     * @param context
     *            上下文对象
     * @param info
     *            显示文本信息
     * @return
     */
    public static Toast showInfo(Context context, String info)
    {
        return showInfo(context, info, DEF_DURATION);
    }

    /**
     * @param context
     *            上下文对象
     * @param resId
     *            资源id
     * @return
     */
    public static Toast showInfo(Context context, int resId)
    {
        return showInfo(context, resId, DEF_DURATION);
    }

    /**
     * @param context
     *            上下文对象
     * @param resId
     *            资源id
     * @param duration
     *            显示时长
     * @return
     */
    public static Toast showInfo(Context context, int resId, int defDuration)
    {
        return showInfo(context, context.getString(resId), defDuration);
    }

    /**
     * 显示Toast
     * 
     * @param context
     *            上下文对象
     * @param info
     *            显示文本信息
     * @param duration
     *            显示时长
     * @return
     */
    public static Toast showInfo(Context context, String info, Integer duration)
    {
        if (null == duration)
        {
            duration = DEF_DURATION;
        }

        // 重新设置toast的view，duration，text，防止toast多次触发后连续显示隐藏的问题...
        if (null != toast)
        {
            toast.setView(toast.getView());
            toast.setDuration(duration);
            // toast.setText(info);
        } else
        {
            // 自定义toast显示的view
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View toastView = inflater.inflate(R.layout.toast, null);
            TextView text = (TextView) toastView.findViewById(R.id.toast_text);
            text.setText(info);

            toast = new Toast(context);
            toast.setDuration(duration);
            toast.setView(toastView);
        }

        // 默认居中显示
        toast.setGravity(Gravity.CENTER, 0, 0);

        toast.show();
        return toast;
    }
}
