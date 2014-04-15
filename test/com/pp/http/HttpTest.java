/**
 * author :  lipan
 * filename :  HttpTest.java
 * create_time : 2014年4月10日 下午4:26:50
 */
package com.pp.http;

import java.io.IOException;
import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.test.AndroidTestCase;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

/**
 * @author : lipan
 * @create_time : 2014年4月10日 下午4:26:50
 * @desc : 测试Http
 * @update_time :
 * @update_desc :
 *
 */
public class HttpTest extends AndroidTestCase
{
    
    private static final String TAG = "httputils";

    public void testHttpReq()
    {
     
//        final String url = "http://114.255.216.118:14141/mvno-mapp/login?userCode=BmcyLdwJKvDPlsrfsnsFUw==&password=5XKRL8zBZPw=";
//        final String url = "http://114.255.216.118:14141/mvno-mapp/login";
        final String url = "http://114.255.216.118:14141/mvno-mapp/search/initAccountSearchPage";
//        final RequestParams params = new RequestParams();
//        params.put("userCode", "BmcyLdwJKvDPlsrfsnsFUw==");
//        params.put("password", "5XKRL8zBZPw=");
        SyncHttpClient  client = new SyncHttpClient();
        
        client.get(url, new RequestParams("telPhone","XF/QUGbveCGrdstXJlyx2w=="),  new TextHttpResponseHandler("UTF-8"){

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                    String responseBody)
            {
                Log.i(TAG, responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                    String responseString, Throwable throwable)
            {
                Log.i(TAG, "fuck11");
            }
            
        });
        
        SyncHttpUtil.post(url, null, new TextHttpResponseHandler("UTF-8"){

            @Override
            public void onSuccess(int statusCode, Header[] headers,
                    String responseBody)
            {
                Log.i(TAG, responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                    String responseString, Throwable throwable)
            {
                Log.i(TAG, "fuck11");
            }
            
        });
        

        }
    
}
