/**
 * author :  lipan
 * filename :  XmlParser.java
 * create_time : 2014-2-24 上午11:36:00
 */
package com.pp.pull;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

/**
 * @author : lipan
 * @create_time : 2014-2-24 上午11:36:00
 * @desc : 
 * @update_time :
 * @update_desc :
 *
 */
public class PersonService
{

    /**
     * 解析xml
     * @param xml
     * @return
     * @throws Exception
     */
    public static List<Person> getPersons(InputStream xml) throws Exception
    {
        
        List<Person> persons = null;
        Person person = null;
        
        //可以使用XmlPullParserFactory.newInstance().newPullParser()方式获取
        //这种方式是android提供的获取解析器的方式
        XmlPullParser pullParser = Xml.newPullParser();
        
        //设置输入
        pullParser.setInput(xml, "UTF-8");
        
        //第一个事件...
        int eventType = pullParser.getEventType();
        
        while(eventType != XmlPullParser.END_DOCUMENT)
        {
            switch (eventType)
            {
                case XmlPullParser.START_DOCUMENT:
                    persons =  new ArrayList<Person>();
                    break;
                case XmlPullParser.START_TAG:
                    
                    if("person".equals(pullParser.getName()))
                    {
                        int id = Integer.parseInt(pullParser.getAttributeValue(0));
                        person = new Person();
                        person.setId(id);
                    }
                    if("name".equals(pullParser.getName()))
                    {
                        String name = pullParser.nextText();
                        person.setName(name);
                    }
                    if("age".equals(pullParser.getName()))
                    {
                        int age = Integer.parseInt(pullParser.nextText());
                        person.setAge(age);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    
                    //当解析到person的结束时，将person添加到结果list
                    if("person".equals(pullParser.getName()))
                    {
                        persons.add(person);
                        person = null;
                    }
                    break;
                default:
                    break;
            }
            eventType = pullParser.next();
        }
        return persons;
    }
    
    
    /**
     * 保存数据到输出流
     * @param persons
     * @param outStream
     * @throws Exception
     */
    public static void save(List<Person> persons , OutputStream outStream) throws Exception
    {
        //实例化
        XmlSerializer xmlSerializer = Xml.newSerializer();
        
        //设置输出对象 和 输出字符集
        xmlSerializer.setOutput(outStream, "UTF-8");
        
        //设置xml文档开始 ,设置字符集,standalone指定xml文件是否可以单独存在，不依赖其他的文件
        xmlSerializer.startDocument("UTF-8", null);
        xmlSerializer.startTag(null, "persons");
        
        for (Person person : persons)
        {
            xmlSerializer.startTag(null, "person");
            
            //添加标签属性
            xmlSerializer.attribute(null, "id", String.valueOf(person.getId()));
            
            xmlSerializer.startTag(null, "name");
            xmlSerializer.text(person.getName());
            xmlSerializer.endTag(null, "name");
            
            xmlSerializer.startTag(null, "age");
            xmlSerializer.text(String.valueOf(person.getAge()));
            xmlSerializer.endTag(null, "age");
            
            xmlSerializer.endTag(null, "person");
        }
        
        xmlSerializer.endTag(null, "persons");
        xmlSerializer.endDocument();
        
        outStream.flush();
        outStream.close();
    }
}
