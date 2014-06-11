package com.pp.application;

import com.pp.webview.WebviewManager;

import android.app.Application;

/**
 * Application
 * 
 * @author : lipan
 * @create_time : 2014年4月18日 下午5:03:16
 * @desc : 
 * @update_time :
 * @update_desc :
 *
 */
public class ZApplication extends Application{

	public static final String ACTION_MISSED_CHANGED = "com.pp.missed.changed";

	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	private void init() {
	    
	    WebviewManager.init(getApplicationContext());
	    
		mainRelatedInit();
	}

	/**
	 * 初始化操作...
	 */
	private boolean mainRelatedInited = false;

	public synchronized void mainRelatedInit() {
		if (mainRelatedInited)
			return;
		mainRelatedInited = true;
	}
	        

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

}
