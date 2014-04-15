/**
 * author :  lipan
 * filename :  Person.java
 * create_time : 2014-3-20 上午9:32:14
 */
package com.pp.sqlite.domain;

/**
 * @author : lipan
 * @create_time : 2014-3-20 上午9:32:14
 * @desc : DOMAIN
 * @update_time :
 * @update_desc :
 *
 */
public class Person
{
    private Integer id;
    private String name;
    private String phone;
    private Integer amount;
    
    /**
     * 
     */
    public Person()
    {
    }
    /**
     * @param id
     * @param name
     * @param phone
     */
    public Person(Integer id, String name, String phone,Integer amount)
    {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.amount = amount;
    }
    /**
     * @param name
     * @param phone
     */
    public Person(String name, String phone ,Integer amount)
    {
        this.name = name;
        this.phone = phone;
        this.amount = amount;
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
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public Integer getAmount()
    {
        return amount;
    }
    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }
    @Override
    public String toString()
    {
        return "Person [id=" + id + ", name=" + name + ", phone=" + phone
                + ", amount=" + amount + "]";
    }
}
