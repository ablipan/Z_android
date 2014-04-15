/**
 * author :  lipan
 * filename :  ThreadPoolTask.java
 * create_time : 2014-3-13 下午2:45:00
 */
package com.pp.threadpool;

import java.io.Serializable;

/**
 * @author : lipan
 * @create_time : 2014-3-13 下午2:45:00
 * @desc : ThreadPoolTask
 * @update_time :
 * @update_desc :
 *
 */
public class ThreadPoolTask implements Runnable, Serializable {  
    
    private static final long serialVersionUID = 1L;
    private Object attachData;  
  
    ThreadPoolTask(Object tasks) {  
        this.attachData = tasks;  
    }  
  
    public void run() {  
          
        System.out.println("开始执行任务：" + attachData);  
          
        attachData = null;  
    }  
  
    public Object getTask() {  
        return this.attachData;  
    }  
}  

