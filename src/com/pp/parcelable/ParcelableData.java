/**
 * author :  lipan
 * filename :  ParcelableData.java
 * create_time : 2014年4月10日 下午12:42:30
 */
package com.pp.parcelable;

import java.util.HashMap;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author : lipan
 * @create_time : 2014年4月10日 下午12:42:30
 * @desc : 用于Intent和IPC传递复杂对象
 * @update_time :
 * @update_desc :
 *
 */
public class ParcelableData implements Parcelable
{

    //传递的对象
    public Map data = new HashMap();
    
    @Override
    public int describeContents()
    {
        return 0;
    }

    //实现Parcelable的方法writeToParcel，将ParcelableDate序列化为一个Parcel对象   
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeMap(data);
    }
    
    //实例化静态内部对象CREATOR实现接口Parcelable.Creator
    public static final Parcelable.Creator<ParcelableData> CREATOR = new Parcelable.Creator<ParcelableData>() 
    {

        @Override
        public ParcelableData createFromParcel(Parcel source)
        {
            ParcelableData p = new ParcelableData();
            p.data = source.readHashMap(HashMap.class.getClassLoader());
            return p;
        }

        @Override
        public ParcelableData[] newArray(int arg0)
        {
            return null;
        }
        
    };
}
