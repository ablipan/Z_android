/**
 * author :  lipan
 * filename :  ListViewActivity.java
 * create_time : 2014-3-10 上午9:37:20
 */
package com.pp.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.pp.R;
import com.pp.listview.adapter.PersonAdapter;
import com.pp.sqlite.domain.Person;
import com.pp.sqlite.service.PersonService;

/**
 * @author : lipan
 * @create_time : 2014-3-10 上午9:37:20
 * @desc : ListView使用
 * @update_time :
 * @update_desc :
 *
 */
public class ListViewActivity extends Activity
{

    private ListView listView;
    private PersonService personService;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        personService = new PersonService(this);
        
        listView = (ListView) findViewById(R.id.listview);
        
        //条目点击监听
        listView.setOnItemClickListener(new ItemClickListener());
        
        //显示数据
//        show();
//        show2();
        show3();
    }
    
    /**
     * 采用自定义Adapter显示数据
     */
    private void show3()
    {
        List<Person> persons = personService.getScrollData(0, 50);
        PersonAdapter adapter = new PersonAdapter(this, persons,R.layout.listview_item);
        listView.setAdapter(adapter);
    }

    /**
     * 采用SimpleCursorAdapter显示数据
     */
    private void show2()
    {
        Cursor c =  personService.getCursorScrollData(0, 20);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, 
                R.layout.listview_item, 
                c, 
                new String[]{"name","phone","amount"},  //from
                new int[]{R.id.name,R.id.phone,R.id.amount},1); // to
        listView.setAdapter(adapter);
    }

    /**
     * 采用SimpleAdapter显示数据
     */
    private void show()
    {
        List<Person> persons = personService.getScrollData(0, 20);
        List<HashMap<String,Object>> data = new ArrayList<HashMap<String,Object>>();
        
        //把persons中数据方式data集合中
        for(Person person : persons)
        {
            HashMap<String,Object> item = new HashMap<String, Object>();
            item.put("id", person.getId());
            item.put("name", person.getName());
            item.put("phone", person.getPhone());
            item.put("amount", person.getAmount());
            data.add(item);
        }
        
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this, 
                data, 
                R.layout.listview_item,     //显示的layout
                new String[]{"name","phone","amount"},  //from
                new int[]{R.id.name,R.id.phone,R.id.amount}); // to
        
        listView.setAdapter(simpleAdapter);
    }

    /**
     * 点击条目触发事件
     */
    private final class ItemClickListener implements OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id)
        {
            //将
            ListView listView = (ListView)parent;
            
            /**自定义适配器**/
            //调用的是adapter的getItem方法
            Person person = (Person)listView.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(), person.getId().toString(), Toast.LENGTH_SHORT).show();
            
            /**SimpleCursorAdapter**/
//            Cursor cursor = (Cursor)listView.getItemAtPosition(position);
//            Integer personid = cursor.getInt(cursor.getColumnIndex("_id"));
//            Toast.makeText(getApplicationContext(), personid.toString(), Toast.LENGTH_SHORT).show();
            
            /**SimpleAdapter**/
//            HashMap<String,Object> data = (HashMap<String,Object>)listView.getItemAtPosition(position);
//            Toast.makeText(getApplicationContext(), data.get("id")+"", Toast.LENGTH_SHORT).show();
        }
        
    }
    
}
