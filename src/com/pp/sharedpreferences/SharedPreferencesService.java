/**
 * author :  lipan
 * filename :  SharedPreferencesService.java
 * create_time : 2014-3-10 上午9:46:23
 */
package com.pp.sharedpreferences;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author : lipan
 * @create_time : 2014-3-10 上午9:46:23
 * @desc : service方法
 * @update_time :
 * @update_desc :
 *
 */
public class SharedPreferencesService
{

    private Context context;
    
    /**
     * @param context
     */
    public SharedPreferencesService(Context context)
    {
        this.context = context;
    }

    /**
     * 保存参数
     * @param name
     * @param parseInt
     */
    public void save(String name, int age)
    {
        //name参数为不带后缀的文件名
        SharedPreferences preferences = context.getSharedPreferences("sharedpf", Context.MODE_PRIVATE);
        //获得一个编辑器
        Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.putInt("age", age);
        //一定要进行提交 commit为由返回值的提交，apply为无返回值的提交
        editor.apply();
    }
    
    
    /**
     * 获取SharedPreferences中的参数
     * @return
     */
    public Map<String,String> getPreferences()
    {
        Map<String,String> map = new HashMap<String, String>();
        //name参数为不带后缀的文件名
        SharedPreferences preferences = context.getSharedPreferences("sharedpf", Context.MODE_PRIVATE);
        map.put("name", preferences.getString("name", ""));
        map.put("age", String.valueOf(preferences.getInt("age", 0)));
        return map;
    }
}
