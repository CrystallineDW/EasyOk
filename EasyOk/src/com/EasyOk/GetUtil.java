package com.EasyOk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.EasyOk.alltools.listenter.OnDownloadListener;
import com.EasyOk.alltools.listenter.OnGetListener;

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
    public void get(OkHttpClient HttpClient,final String url,final OnGetListener listener) {
 
        Request request = new Request.Builder()
                .url(url)
                .build();
        if(HttpClient!=null)
        {
        	System.out.println("has");
        	Client = HttpClient;
        }
        try {
            Response response = Client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println("error! errorMessage is :"+e.getMessage());
        }
 
        //异步请求
        Client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败监听回调
                listener.onGet_Failed(e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                
                try {
                	listener.onGet_Success(response.body().string());
                    }
                 catch (Exception e) {
                	 System.out.println("error! errorMessage is :"+e.getMessage());
                }
                
            }
        });
    }
}

