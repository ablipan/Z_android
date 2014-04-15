/**
 * author :  lipan
 * filename :  ViewHolder.java
 * create_time : 2014-3-21 上午11:11:43
 */
package com.pp.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * @author : lipan
 * @create_time : 2014-3-21 上午11:11:43
 * @desc : ViewHolder
 * @update_time :
 * @update_desc :
 *
 */
public class ViewHolder {
    // I added a generic return type to reduce the casting noise in client code
    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
