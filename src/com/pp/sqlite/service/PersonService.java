/**
 * author :  lipan
 * filename :  PersonService.java
 * create_time : 2014-3-20 上午9:33:44
 */
package com.pp.sqlite.service;

import java.util.ArrayList;
import java.util.List;

import com.pp.sqlite.domain.Person;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * @author : lipan
 * @create_time : 2014-3-20 上午9:33:44
 * @desc : Service类
 * @update_time :
 * @update_desc :
 *
 */
public class PersonService
{
    private DBOpenHelper dbOpenHelper;
    
    private static final String SAVE_SQL = "INSERT INTO person(name,phone,amount) VALUES(?,?,?)";
    private static final String DELETE_SQL = "DELETE FROM person where personid = ?";
    private static final String UPDATE_SQL = "UPDATE person SET name=?,phone=?,amount=? WHERE personid=?";
    private static final String FIND_SQL = "SELECT * FROM person WHERE personid = ?";
    private static final String SCROLL_SQL = "SELECT * FROM person ORDER BY personid limit ?,?";
    private static final String COUNT_SQL = "SELECT count(*) FROM person";
    
    //转账操作
    private static final String ADD_AMOUNT = "UPDATE person set amount=amount+10 where personid=1";
    private static final String DECREASE_AMOUNT = "UPDATE person set amount=amount-10 where personid=2";
    
    
    /**
     * @param dbOpenHelper
     */
    public PersonService(Context context)
    {
        dbOpenHelper = new DBOpenHelper(context);
    }

    
    /**
     * 付款操作，需要在一个事务中进行
     */
    public void payment()
    {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.beginTransaction();
        try{
            db.execSQL(ADD_AMOUNT);
            db.execSQL(DECREASE_AMOUNT);
            //事务的提交或回滚是由是由事务的标识决定的，如果事务的标志为true，事务就会提交，否则回滚，默认情况下事务的标志为false
            db.setTransactionSuccessful();//设置事务标识为true
        }finally
        {
            //结束事务，有两种情况：commit，roolback，
            db.endTransaction();
        }
    }
    
    /**
     * 添加记录
     * @param person
     */
    public void save(Person person)
    {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        
        //execSQL方法
//        db.execSQL(SAVE_SQL, new Object[]{person.getName(),person.getPhone()});
        
        //insert方法
        ContentValues values = new ContentValues();
        values.put("name", person.getName());
        values.put("phone", person.getPhone());
        values.put("amount", person.getAmount());
        db.insert("person", null, values);  //
        
    }
    
    /**
     * 删除记录
     * @param person
     */
    public void delete(Integer id)
    {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        
        //execSQL方法
//        db.execSQL(DELETE_SQL, new Object[]{id});
        
        //delete方法
        db.delete("person", "personid=?", new String[]{id.toString()});
    }
    
    /**
     * 更新记录
     * @param person
     */
    public void update(Person person)
    {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        
        //execSQL方法
//        db.execSQL(UPDATE_SQL, new Object[]{person.getName(),person.getPhone(),person.getId()});
        
        //update方法
        ContentValues values = new ContentValues();
        values.put("name", person.getName());
        values.put("phone", person.getPhone());
        values.put("amount", person.getAmount());
        db.update("person", values, "personid=?", new String[]{person.getId().toString()});
    }
    
    /**
     * 查询记录
     * @param person
     * @return
     */
    public Person find(Integer id)
    {
       SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        
        //rawQuery 方法
//        Cursor cursor = db.rawQuery(FIND_SQL, new String[]{String.valueOf(id)});
        
       //query 方法
       Cursor cursor = db.query("person", null , "personid=?", new String[]{id.toString()}, null, null, null);
        
       if(cursor.moveToFirst())
       {
          int personid = cursor.getInt(cursor.getColumnIndex("personid"));
          String name = cursor.getString(cursor.getColumnIndex("name"));
          String phone = cursor.getString(cursor.getColumnIndex("phone"));
          int amount = cursor.getInt(cursor.getColumnIndex("amount"));
          return new Person(personid,name,phone,amount);
       }
       cursor.close();
       return null;
    }
    
    /**
     * 分页获取记录
     * @param offset 跳过前面多少条记录
     * @param maxResult 每页获取多少条记录
     * @return
     */
    public List<Person> getScrollData(int offset , int maxResult)
    {
        List<Person> persons = new ArrayList<Person>();
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        
        //rawQuery 方法
//        Cursor cursor = db.rawQuery(SCROLL_SQL, new String[]{String.valueOf(offset),String.valueOf(maxResult)});
        
       //query 方法
        Cursor cursor = db.query("person", null, null, null, null, null, "personid asc", offset +","+maxResult);
        
        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex("personid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            int amount = cursor.getInt(cursor.getColumnIndex("amount"));
            persons.add(new Person(id,name,phone,amount));
        }
        cursor.close();
        return persons;
    }
    
    /**
     * 分页获取记录  
     * @param offset 跳过前面多少条记录
     * @param maxResult 每页获取多少条记录
     * @return Cursor 类型
     */
    public Cursor getCursorScrollData(int offset , int maxResult)
    {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        
        //rawQuery 方法
//        Cursor cursor = db.rawQuery(SCROLL_SQL, new String[]{String.valueOf(offset),String.valueOf(maxResult)});
        
        //query 方法
        Cursor cursor = db.query("person", new String[]{"personid as _id","name","phone","amount"}, null, null, null, null, "personid asc", offset +","+maxResult);
        
        return cursor;
    }
    
    /**
     * 获取记录总数
     * @return
     */
    public long getCount()
    {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        
        //rawQuery 方法
//        Cursor cursor = db.rawQuery(COUNT_SQL, null);
        
        //query 方法
        Cursor cursor = db.query("person", new String[]{"count(*)"} , null, null, null, null, null);

        cursor.moveToFirst();
        long count = cursor.getLong(0);
        return count;
    }
}
