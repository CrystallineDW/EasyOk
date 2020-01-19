package com.EasyOk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.EasyOk.alltools.listenter.OnDownloadListener;
import com.EasyOk.alltools.listenter.OnGetListener;
import com.EasyOk.alltools.listenter.OnPostListener;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.FormBody.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostUtil {
	 
    private static PostUtil GetUtil;
    private OkHttpClient Client;
 
    public static PostUtil get() {
        if (GetUtil == null) {
        	GetUtil = new PostUtil();
        }
        return GetUtil;
    }
 
    public PostUtil() {
    	Client = new OkHttpClient();
    }
 
 
    /**
     * @param HttpClient   OkhttpClient对象，如果传入null，则使用默认对象
     * @param url          请求连接
     * @param post_map     请求Map
     * @param listener     请求监听
     */
    public void post(OkHttpClient HttpClient,final String url,final Map<String,String> post_map,final OnPostListener listener) {
 
    	RequestBody body = null;
    	
    	
        Request request = new Request.Builder()
                .url(url)
                .build();
        if(HttpClient!=null)
        {
        	Client = HttpClient;
        }
        
        if(post_map!=null)
        {
        	FormBody.Builder post_data = new FormBody.Builder();
        	
        	for(Map.Entry<String, String> entry : post_map.entrySet()){
        	    String mapKey = entry.getKey();
        	    String mapValue = entry.getValue();
        	    post_data.add(mapKey,mapValue); 
        	}
        	body = post_data.build();  	
        }
        
        try {
        	 okhttp3.Request request1 = new okhttp3.Request.Builder()
                     .url(url)
                     .post(body)
                     .build();
        	 Call call = Client.newCall(request1);
        	    call.enqueue(new Callback() {
        	        @Override
        	        public void onFailure(Call call, IOException e) {
        	        	listener.onPost_Failed(e);
        	        }
        	 
        	        @Override
        	        public void onResponse(Call call, okhttp3.Response response) throws IOException {
        	        	listener.onPost_Success(response.body().string());
        	        }
        	    });
        }
        catch(Exception e)
        {
        	listener.onPost_Failed(e);
        }
    }
}


