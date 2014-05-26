/**
 * author :  lipan
 * filename :  WebviewActivity.java
 * create_time : 2014年5月21日 下午1:30:13
 */
package com.pp.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * @author : lipan
 * @create_time : 2014年5月21日 下午1:30:13
 * @desc : CommWebActivity
 * @update_person:
 * @update_time :
 * @update_desc :
 *
 */
public class WebviewActivity extends Activity
{

    private WebView webView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
//        WebView view = WebviewManager.getInstance().getWebView("http://10.1.38.71:8080/MAPP/search/initRealTimeTelFarePage?telPhone=BmcyLdwJKvA1rcYM3CkV5w%3D%3D");
        WebView view = WebviewManager.getInstance().getWebView("http://192.168.1.104:8080/GOME_Web/adv/te_adv_index.html");
        setContentView(view);
//        setContentView(R.layout.webview);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
    
    
}
