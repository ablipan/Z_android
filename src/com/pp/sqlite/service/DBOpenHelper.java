/**
 * author :  lipan
 * filename :  DBOpenHelper.java
 * create_time : 2014-3-17 上午10:52:51
 */
package com.pp.sqlite.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author : lipan
 * @create_time : 2014-3-17 上午10:52:51
 * @desc : DB工具类
 * @update_time :
 * @update_desc :
 *
 */
public class DBOpenHelper extends SQLiteOpenHelper
{

    //建表
    private static final String CREATE_PERSON = "CREATE TABLE person(personid integer primary key autoincrement ,name varchar(20))";
    
    //修改表
    private static final String ALTER_PERSON_ADD_PHONE = "ALTER TABLE person ADD phone varchar(12) NULL";  //添加金额字段
    
    private static final String ALTER_PERSON_ADD_AMONT = "ALTER TABLE person ADD amount integer NULL";  //添加金额字段
    
    /**
     * @param context
     * @param name
     * @param factory
     * @param version
     * 
     */
    public DBOpenHelper(Context context)
    {
        //lipan.db是数据库名称(默认保存在 <包>/databases/ 目录)，   null为使用系统默认的游标工厂，   1为版本号(大于等于1)。
        super(context, "lipan.db", null, 2);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) // 数据库第一次被创建的时候调用
    {
        //创建person表
        db.execSQL(CREATE_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) // 数据库版本号升级时时被调用
    {
        db.execSQL(ALTER_PERSON_ADD_PHONE);
        db.execSQL(ALTER_PERSON_ADD_AMONT);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)// 数据库版本号降级时被调用
    {
    }

}
