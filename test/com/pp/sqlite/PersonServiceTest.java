/**
 * author :  lipan
 * filename :  PersonServiceTest.java
 * create_time : 2014-3-17 上午11:35:28
 */
package com.pp.sqlite;

import java.util.List;

import android.test.AndroidTestCase;
import android.util.Log;

import com.pp.sqlite.domain.Person;
import com.pp.sqlite.service.DBOpenHelper;
import com.pp.sqlite.service.PersonService;

/**
 * @author : lipan
 * @create_time : 2014-3-17 上午11:35:28
 * @desc : sqlite测试类
 * @update_time :
 * @update_desc :
 *
 */
public class PersonServiceTest extends AndroidTestCase
{
    public static final String TAG = "com.pp.sqlite.PersonServiceTest";
    public void testCreateDB() throws Exception
    {
        DBOpenHelper db = new DBOpenHelper(getContext());
        db.getReadableDatabase();
    }
    
    public void testSave() throws Exception
    {
        PersonService p = new PersonService(getContext());
        for (int i = 0; i < 20; i++)
        {
            Person person = new Person("lipan"+i,"18801137101_"+i,100);
            p.save(person);
        }
    }
    
    public void testDelete() throws Exception
    {
        PersonService p = new PersonService(getContext());
        p.delete(20);
    }
    
    public void testUpdate() throws Exception
    {
        PersonService p = new PersonService(getContext());
        Person person = new Person(1,"lipan","18801137101",0);
        p.update(person);
    }
    
    public void testFind() throws Exception
    {
        PersonService p = new PersonService(getContext());
        Person person = p.find(1);
        Log.i(TAG,person.toString());
    }
    
    public void testGetScrollData() throws Exception
    {
        PersonService p = new PersonService(getContext());
        List<Person> persons = p.getScrollData(0, 50);
        for(Person person : persons)
        {
            Log.i(TAG,person.toString());
        }
    }
    
    public void testGetCount() throws Exception
    {
        PersonService p = new PersonService(getContext());
        Log.i(TAG,String.valueOf(p.getCount()));
    }
 
    public void testPayment() throws Exception
    {
        PersonService p = new PersonService(getContext());
        p.payment();
    }
}
