/**
 * author :  lipan
 * filename :  PersonProvider.java
 * create_time : 2014-3-21 下午4:13:14
 */
package com.pp.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.pp.sqlite.service.DBOpenHelper;

/**
 * @author : lipan
 * @create_time : 2014-3-21 下午4:13:14
 * @desc : 内容提供者
 * @update_time :
 * @update_desc :
 *
 */
public class PersonProvider extends ContentProvider
{
    private DBOpenHelper dbOpenHelper;
    
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    
    private static final String SCHEMA= "content://";
    
    
    private static final String AUTHORITY= "com.pp.providers.personprovider";
    
    private static final String TABLE = "person";
    
    private static final String SPRIT= "/";
    
    
    private static final int PERSONS = 1;
    private static final int PERSON = 2;
    static
    {
        MATCHER.addURI(AUTHORITY, "person", PERSONS);
        MATCHER.addURI(AUTHORITY, "person/#", PERSON); //*代表所有字符/ #代表数据
    }
    
    @Override
    public boolean onCreate()
    {
        dbOpenHelper = new DBOpenHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder)
    {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (MATCHER.match(uri))
        {
            case PERSONS:
                return db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
            case PERSON:
                //从URI中得到/后面的数字
                long rowid = ContentUris.parseId(uri); // 得到/后面的#号数字..
               
                //id条件
                String where = "personid ="+rowid;
               
                //其他条件
                if(null!=selection && !"".equals(selection))
                {
                    where +=  " and "+selection;
                }
                return db.query("person", projection, where, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("this is Unknow Uri: "+ uri);
        }
    }

    @Override
    public String getType(Uri uri)
    {
        switch (MATCHER.match(uri))
        {
            case PERSONS:
                return "vnd.android.cursor.dir/person";//如果操作集合类型
            case PERSON:
                return "vnd.android.cursor.item/person";//非集合类型
            default:
                throw new IllegalArgumentException("this is Unknow Uri: "+ uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        Log.i("insert", uri.toString());
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        switch (MATCHER.match(uri))
        {
            case PERSONS:
                
                long insertId = db.insert("person", "name", values); // 主键
                //content://.../person/insertId
//                Uri insertUrl = Uri.parse(SCHEMA + AUTHORITY + TABLE + SPRIT + insertId);
                
                Uri insertUrl = ContentUris.withAppendedId(uri, insertId);
                
                //发出数据变化通知
                getContext().getContentResolver().notifyChange(uri, null);
                return insertUrl;

            default:
                throw new IllegalArgumentException("this is Unknow Uri: "+ uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int num = 0;
        switch (MATCHER.match(uri))
        {
            case PERSONS:
                num = db.delete("person", selection, selectionArgs);
                break;
            case PERSON:
                
                //从URI中得到/后面的数字
                long rowid = ContentUris.parseId(uri); // 得到/后面的#号数字..
               
                //id条件
                String where = "personid ="+rowid;
               
                //其他条件
                if(null!=selection && !"".equals(selection))
                {
                    where +=  " and "+selection;
                }
                
                num = db.delete("person", where, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("this is Unknow Uri: "+ uri);
        }
        return num;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs)
    {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int num = 0;
        switch (MATCHER.match(uri))
        {
            case PERSONS:
                num = db.update("person", values, selection , selectionArgs);
                break;
            case PERSON:
                
                //从URI中得到/后面的数字
                long rowid = ContentUris.parseId(uri); // 得到/后面的#号数字..
               
                //id条件
                String where = "personid ="+rowid;
               
                //其他条件
                if(null!=selection && !"".equals(selection))
                {
                    where +=  " and "+selection;
                }
                
                num = db.update("person", values, where , selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("this is Unknow Uri: "+ uri);
        }
        return num;
    }

    
}
