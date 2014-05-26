/**
 * author :  lipan
 * filename :  UserManager.java
 * create_time : 2014年5月21日14:00:25
 */
package com.pp.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Webview管理
 * @author : lipan
 * @create_time : 2014年5月21日14:00:25
 * @desc : 用户管理
 * @update_time :
 * @update_desc :
 *
 */
public class WebviewManager
{
    private static WebviewManager instance;
    private WebView webView;
    private Context context;
    
    public static WebviewManager getInstance() {
        return instance;
    }

    public static void init(Context context) {
        
        instance = new WebviewManager(context);
        
        // 重置管理对象
        instance.reset();
    }
    
    private WebviewManager(Context context) {
        this.context = context;
    }
    
    private void reset() {
        
        webView = new WebView(context);
        
        WebSettings settings = webView.getSettings();
        
        //通知和请求处理类
        webView.setWebViewClient(new MyWebViewClient());
        
        //JavaScript dialogs, favicons, titles, and the progress 处理类
        webView.setWebChromeClient(new MyWebChromeClient());
        
        //支持Javascript
        settings.setJavaScriptEnabled(true);
        
        //是否显示缩放工具条
        settings.setBuiltInZoomControls(false);

        // 字符集
        settings.setDefaultTextEncodingName("UTF-8");
        
        /**
         * 设置是否使用html页面head中meta标签的viewport属性，如果为false，那么webview的宽度为WebView控件设置的的宽度。
         * 如果为true并且html页面包含viewport属性，那么webview的宽度将被设置为viewport属性中的宽度；如果html页面不包含
         * viewport属性 那么将使用android webview 的 Wide Viewport模式
         */
        settings.setUseWideViewPort(true);
        
        /**
         * 设置webview加载模式是否为overview模式，overview模式：放大内容到适合屏幕的宽度。
         * 如果设置了Wide Viewport模式为true，那么内容宽度的控制权将大于webview自身宽度的控制权
         * 一句话：放大
         */
        settings.setLoadWithOverviewMode(true);
        
    }

    /**
     * 获得webview实例
     * @param url 地址
     * @return
     */
    public WebView getWebView(String url)
    {
        webView.removeAllViews();
        webView.loadUrl(url);
        //给webview设置焦点 
//        webView.requestFocus();
        return webView;
    }
    
    /**
     * TODO
     * 打开webview activity
     * @param url
     */
    public void startWebViewActivity(String url)
    {
        
    }
    
    private class MyWebChromeClient extends WebChromeClient
    {

        @Override
        public void onProgressChanged(WebView view, int newProgress)
        {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title)
        {
            super.onReceivedTitle(view, title);
        }
        
    }
    
    private class MyWebViewClient extends WebViewClient
    {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            //点击url只在本webview跳转，不跳转到浏览器
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
            super.onPageFinished(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url) //加载每个资源都会调用一次
        {
            super.onLoadResource(view, url);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode,
                String description, String failingUrl)
        {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler,
                SslError error)
        {
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event)
        {
            return super.shouldOverrideKeyEvent(view, event);
        }

        @Override
        public void onUnhandledKeyEvent(WebView view, KeyEvent event)
        {
            super.onUnhandledKeyEvent(view, event);
        }

        @Override
        public void onScaleChanged(WebView view, float oldScale, float newScale)
        {
            super.onScaleChanged(view, oldScale, newScale);
        }
        
    }
}
