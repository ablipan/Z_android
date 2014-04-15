/**
 * author :  lipan
 * filename :  ToastInfo.java
 * create_time : 2014-3-21 上午11:35:26
 */
package com.pp.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author : lipan
 * @create_time : 2014-3-21 上午11:35:26
 * @desc : TODO
 * @update_time :
 * @update_desc :
 *
 */
public class ToastInfo
{
    /**
     * toast
     * @param info
     */
    public static void showInfo(Context context, String info)
    {
        Toast toast = Toast.makeText(context, info,Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
