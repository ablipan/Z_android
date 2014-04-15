/**
 * author :  lipan
 * filename :  Data.java
 * create_time : 2014年4月10日 下午1:57:14
 */
package com.pp.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author : lipan
 * @create_time : 2014年4月10日 下午1:57:14
 * @desc : 复杂对象，通过Parcelable传递
 * @update_time :
 * @update_desc :
 *
 */
public class Data implements Parcelable
{
    private String id;
    private String src;
    /**
     * @param id
     * @param src
     */
    public Data(String id, String src)
    {
        this.id = id;
        this.src = src;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getSrc()
    {
        return src;
    }
    public void setSrc(String src)
    {
        this.src = src;
    }
    @Override
    public String toString()
    {
        return "Data [id=" + id + ", src=" + src + "]";
    }
    
    
    @Override
    public int describeContents()
    {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        
    }
    
    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>(){

        @Override
        public Data createFromParcel(Parcel source)
        {
            return null;
        }

        @Override
        public Data[] newArray(int size)
        {
            return null;
        }};
}
