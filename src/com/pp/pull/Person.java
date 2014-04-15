/**
 * author :  lipan
 * filename :  Person.java
 * create_time : 2014-2-24 上午11:36:55
 */
package com.pp.pull;

/**
 * @author : lipan
 * @create_time : 2014-2-24 上午11:36:55
 * @desc : 
 * @update_time :
 * @update_desc :
 *
 */
public class Person
{
    private Integer id;
    private String name;
    private Integer age;
    
    
    /**
     * 
     */
    public Person()
    {
    }
    
    /**
     * @param id
     * @param name
     * @param age
     */
    public Person(Integer id, String name, Integer age)
    {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Override
    public String toString()
    {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Integer getAge()
    {
        return age;
    }
    public void setAge(Integer age)
    {
        this.age = age;
    }
}
