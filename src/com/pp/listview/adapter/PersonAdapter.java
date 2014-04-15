/**
 * author :  lipan
 * filename :  PersonAdapter.java
 * create_time : 2014-3-21 上午11:03:48
 */
package com.pp.listview.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pp.R;
import com.pp.sqlite.domain.Person;

/**
 * @author : lipan
 * @create_time : 2014-3-21 上午11:03:48
 * @desc : 自定义Adapter
 * @update_time :
 * @update_desc :
 *
 */
public class PersonAdapter extends BaseAdapter
{

    private List<Person> persons;   //绑定的数据
    private int resource;           //绑定条目的界面
    private LayoutInflater inflater; //布局填充器
    /**
     * 
     */
    public PersonAdapter(Context context , List<Person> persons , int resource)
    {
        this.persons = persons;
        this.resource = resource;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return persons.size();
    }

    @Override
    public Object getItem(int position)
    {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    //Listview翻页时会重用已经new的view对象
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        //目前显示的是第一页
        if(null == convertView)
        {
            convertView = inflater.inflate(resource, null); //生成条目界面对象
            
            //第一次加载时将视图对象放在ViewHolder中，然后缓存到convertView中。
            holder = new ViewHolder();
            holder.nameView = (TextView)convertView.findViewById(R.id.name);
            holder.phoneView = (TextView)convertView.findViewById(R.id.phone);
            holder.amountView = (TextView)convertView.findViewById(R.id.amount);
            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        
        Person person = persons.get(position);
        
        //数据绑定
        holder.nameView.setText(person.getName());
        holder.phoneView.setText(person.getPhone());
        holder.amountView.setText(person.getAmount().toString());
        return convertView;
    }

    /**
     * 为了防止重复调用findViewById会降低性能，将视图缓存起来
     */
    private final class ViewHolder
    {
        public TextView nameView;
        public TextView phoneView;
        public TextView amountView;
    }
}
