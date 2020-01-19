package com.EasyOk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.EasyOk.alltools.listenter.���ؼ�����;
import com.EasyOk.alltools.listenter.Get���������;

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
     * @param HttpClient   OkhttpClient�����������null����ʹ��Ĭ�϶���
     * @param url          ��������
     * @param listener     �������
     */
    public void get(OkHttpClient HttpClient,final String url,final Get��������� listener) {
 
        Request request = new Request.Builder()
                .url(url)
                .build();
        if(HttpClient!=null)
        {
        	Client = HttpClient;
        }
       
 
        //�첽����
        Client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // ����ʧ�ܼ����ص�
                listener.Getʧ��(e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                	listener.Get�ɹ�(response.body().string());
                    }
                 catch (Exception e) {
                	System.out.println("�������󣬴�����Ϣ:"+e.getMessage());
                }
                
            }
        });
    }
}

