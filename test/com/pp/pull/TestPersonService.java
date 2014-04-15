/**
 * author :  lipan
 * filename :  TestPersonService.java
 * create_time : 2014-2-25 上午11:49:09
 */
package com.pp.pull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * @author : lipan
 * @create_time : 2014-2-25 上午11:49:09
 * @desc : 测试
 * @update_time :
 * @update_desc :
 *
 */
public class TestPersonService extends AndroidTestCase
{
    private static final String TAG = "person";
    /**
     * xml解析
     * @throws Exception
     */
    public void testParsePersons() throws Exception
    {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("com/pp/pull/person.xml");
        List<Person> persons = PersonService.getPersons(inputStream);
        for (Person person : persons)
        {
            Log.i(TAG, person.toString());
        }
    }
    
    /**
     * xml生成
     * @throws Exception
     */
    public void testSerializerPersons() throws Exception
    {
        List<Person> persons = new ArrayList<Person>();
        Person person1 = new Person(1,"person1",10);
        Person person2 = new Person(1,"person2",20);
        Person person3 = new Person(1,"person3",25);
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        
        File outFile = new File(Environment.getExternalStorageDirectory(),"person.txt");
        PersonService.save(persons, new FileOutputStream(outFile));
    }
    
}
