package com.EasyOk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.EasyOk.alltools.listenter.下载监听器;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadUtil {
	 
    private static DownloadUtil downloadUtil;
    private OkHttpClient Client;
 
    public static DownloadUtil get() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil();
        }
        return downloadUtil;
    }
 
    public DownloadUtil() {
    	Client = new OkHttpClient();
    }
 
 
    /**
     * @param HttpClient   OkhttpClient对象，如果传入null，则使用默认对象
     * @param url          下载连接
     * @param destFileDir  下载的文件储存目录
     * @param destFileName 下载文件名称，后面记得拼接后缀，否则没法识别文件类型
     * @param listener     下载监听
     */
 
    public void download(final OkHttpClient HttpClient,final String url, final String destFileDir, final String destFileName, final 下载监听器 listener) {
 
        Request request = new Request.Builder()
                .url(url)
                .build();
        
        if(HttpClient!=null)
        {
        	Client = HttpClient;
        }
        
        try {
            Response response = Client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println("发生错误，错误信息："+e.getMessage());
        }
 
        //异步请求
        Client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败监听回调
            	 System.out.println("下载失败，失败信息："+e.getMessage());
                listener.下载失败(e);
            }
 
            @Override
            public void onResponse(Call call, Response response) throws IOException {
 
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
 
                //储存下载文件的目录
                File dir = new File(destFileDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File file = new File(dir, destFileName);
 
                try {
 
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        //下载中更新进度条
                        listener.下载中(progress);
                    }
                    fos.flush();
                    //下载完成
                    listener.下载成功(file);
                } catch (Exception e) {
                	 System.out.println("成功接收到，但是发生运行时错误"+e.getMessage());
                    listener.下载失败(e);
                }finally {
 
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                    	 System.out.println("发生错误，错误信息："+e.getMessage());
                    }
                }
            }
        });
    }
}
