/**
 * author :  lipan
 * filename :  HttpUtils.java
 * create_time : 2014年5月9日 下午5:41:20
 */
package com.pp.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author : lipan
 * @create_time : 2014年5月9日 下午5:41:20
 * @desc : Http工具类
 * @update_person:
 * @update_time :
 * @update_desc :
 *
 */
public class HttpUtils
{

    /**
     * 把输入流转换成字节数组
     * @param inputStream
     */
    public static byte[] getBytes(InputStream in) throws Exception
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int n;
        while ((n = in.read(b)) != -1)
        {
            out.write(b, 0, n);
        }
        in.close();
        return out.toByteArray();
    }
}
