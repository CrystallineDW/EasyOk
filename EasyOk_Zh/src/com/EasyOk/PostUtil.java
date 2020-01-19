package com.EasyOk;


import java.io.IOException;
import java.util.Map;

import com.EasyOk.alltools.listenter.Post������;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

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
     * @param HttpClient   OkhttpClient�����������null����ʹ��Ĭ�϶���
     * @param url          ��������
     * @param post_map     ����Map
     * @param listener     �������
     */
    public void post(OkHttpClient HttpClient,final String url,final Map<String,String> post_map,final Post������ listener) {
 
    	RequestBody body = null;
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
        	        	listener.Postʧ��(e);
        	        }
        	 
        	        @Override
        	        public void onResponse(Call call, okhttp3.Response response) throws IOException {
        	        	listener.Post�ɹ�(response.body().string());
        	        }
        	    });
        }
        catch(Exception e)
        {
        	listener.Postʧ��(e);
        }
    }
}

