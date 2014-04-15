/**
 * author :  lipan
 * filename :  TestThreadPool.java
 * create_time : 2014-3-13 下午2:41:43
 */
package com.pp.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.util.Log;

/**
 * @author : lipan
 * @create_time : 2014-3-13 下午2:41:43
 * @desc : 线程池
 * @update_time :
 * @update_desc :
 *
 */
public class TestThreadPool
{
    private static int produceTaskSleepTime = 2;  
    
    private static int produceTaskMaxNumber = 10;  

    public static void main(String[] args)
    {
        // 构造一个线程池  
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 4, 3,  
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),  
                new ThreadPoolExecutor.DiscardOldestPolicy());  
        for (int i = 1; i <= produceTaskMaxNumber; i++) {  
            try {  
                String task = "task@ " + i;  
//                System.out.println("创建任务并提交到线程池中：" + task);  
                Log.i("ThreadPool","创建任务并提交到线程池中：" + task);  
                threadPool.execute(new ThreadPoolTask(task));  
  
                Thread.sleep(produceTaskSleepTime);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  

    }
}
