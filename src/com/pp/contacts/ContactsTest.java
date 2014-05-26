/**
 * author :  lipan
 * filename :  PersonServiceTest.java
 * create_time : 2014-3-17 上午11:35:28
 */
package com.pp.contacts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.test.AndroidTestCase;
import android.util.Log;

import com.pp.sqlite.domain.Person;
import com.pp.sqlite.service.DBOpenHelper;
import com.pp.sqlite.service.PersonService;

/**
 * @author : lipan
 * @create_time : 2014年5月8日21:51:01
 * @desc : 通讯录测试
 * @update_time :
 * @update_desc :
 *
 */
public class ContactsTest extends AndroidTestCase
{
    
    //获取联系人信息
    public void getContacts() throws Exception
    {
       Uri uri = Uri.parse("content://com.android.contacts/contacts");
       
       ContentResolver contentResolver = getContext().getContentResolver();
       Cursor cursor = contentResolver.query(uri, new String[]{"_id"}, null, null, null);
       while (cursor.moveToNext())
       {
           int contactId = cursor.getInt(0);
           
           StringBuffer sb = new StringBuffer();
           sb.append("联系人Id:"+contactId);
           
           uri = Uri.parse("content://com.android.contacts/contacts/" +contactId+ "/data");
           Cursor dataCursor = contentResolver.query(uri, new String[]{"mimetype","data1","data2"}, null, null, null);
           while (dataCursor.moveToNext())
           {
               String data = dataCursor.getString(dataCursor.getColumnIndex("data1"));
               String type = dataCursor.getString(dataCursor.getColumnIndex("mimetype"));
               if("vnd.android.cursor.item/name".equals(type)) //姓名
               {
                   sb.append("，姓名:"+data);
               }else if("vnd.android.cursor.item/email_v2".equals(type)) //邮箱
               {
                   sb.append("，邮箱:"+data);
               }else if("vnd.android.cursor.item/phone_v2".equals(type)) //电话
               {
                   sb.append("，电话:"+data);
               }
           }
           Log.i("Contacts_", sb.toString());
       }
    }
    
    //根据号码获取联系人的姓名
    public void getNameByNo()
    {
        String number = "17003313271";
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/"+number);
        
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursor = contentResolver.query(uri, new String[]{"display_name"}, null, null, null);
        while (cursor.moveToNext())
        {
            String name = cursor.getString(0);
            Log.i("Contacts_name", name);
        }
    }
    
    //添加联系人
    public void addContact()
    {
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver contentResolver = getContext().getContentResolver();
        ContentValues values = new ContentValues();
        
        //获得新增记录的id
        long newContactId = ContentUris.parseId(contentResolver.insert(uri, values));
        
        //姓名
        uri = Uri.parse("content://com.android.contacts/data");
        values.put("raw_contact_id", newContactId);
        values.put("mimetype", "vnd.android.cursor.item/name");
        values.put("data2", "攀攀攀"); //data1为data2和名字的相加
        contentResolver.insert(uri, values);
        
        //电话
        values.clear();
        uri = Uri.parse("content://com.android.contacts/data");
        values.put("raw_contact_id", newContactId);
        values.put("mimetype", "vnd.android.cursor.item/phone_v2");
        values.put("data1", "18801137101");
        values.put("data2", "2");
        contentResolver.insert(uri, values);
        
        //邮箱
        values.clear();
        uri = Uri.parse("content://com.android.contacts/data");
        values.put("raw_contact_id", newContactId);
        values.put("mimetype", "vnd.android.cursor.item/email_v2");
        values.put("data1", "ablipan@163.com");
        values.put("data2", "2");
        contentResolver.insert(uri, values);
    }

    //添加联系人(所有信息都在一个事务中添加)
    public void addContact2()
    {
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver contentResolver = getContext().getContentResolver();
        
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
        
        ContentProviderOperation opt1 = ContentProviderOperation.newInsert(uri)
            .withValue("account_name", null)
            .build();
        operations.add(opt1);
        
        //姓名
        uri = Uri.parse("content://com.android.contacts/data");
        ContentProviderOperation opt2 = ContentProviderOperation.newInsert(uri)
                .withValueBackReference("raw_contact_id", 0)    //使用第一个操作的返回值
                .withValue("mimetype", "vnd.android.cursor.item/name")
                .withValue("data2", "你哥")
                .build();
        operations.add(opt2);
        
        //电话
        ContentProviderOperation opt3 = ContentProviderOperation.newInsert(uri)
                .withValueBackReference("raw_contact_id", 0)    //使用第一个操作的返回值
                .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                .withValue("data1", "18801137101")
                .withValue("data2", "2")
                .build();
        operations.add(opt3);
        
        //邮箱
        ContentProviderOperation opt4 = ContentProviderOperation.newInsert(uri)
                .withValueBackReference("raw_contact_id", 0)    //使用第一个操作的返回值
                .withValue("mimetype", "vnd.android.cursor.item/email_v2")
                .withValue("data1", "ablipan@163.com")
                .withValue("data2", "2")
                .build();
        operations.add(opt4);
        
        try
        {
            contentResolver.applyBatch(uri.getAuthority(), operations);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        
    } 
}
