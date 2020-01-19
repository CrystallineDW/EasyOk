package com.EasyOk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.EasyOk.alltools.listenter.下载监听器;
import com.EasyOk.alltools.listenter.Get请求监听器;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetUtil {
	 
    private static GetUtil GetUtil;
    private OkHttpClient Client;
 
    public static GetUtil get() {
        if (GetUtil == null) {
        	GetUtil = new GetUtil();
        }
        return GetUtil;
    }
 
    public GetUtil() {
    	Client = new OkHttpClient();
    }
 
 
    /**
     * @param HttpClient   OkhttpClient对象，如果传入null，则使用默认对象
     * @param url          请求连接
     * @param listener     请求监听
     */
    public void get(OkHttpClient HttpClient,final String url,final Get请求监听器 listener) {
 
        Request request = new Request.Builder()
                .url(url)
                .build();
        if(HttpClient!=null)
        {
        	Client = HttpClient;
        }
       
 
        //异步请求
        Client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败监听回调
                listener.Get失败(e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                	listener.Get成功(response.body().string());
                    }
                 catch (Exception e) {
                	System.out.println("发生错误，错误信息:"+e.getMessage());
                }
                
            }
        });
    }
}

