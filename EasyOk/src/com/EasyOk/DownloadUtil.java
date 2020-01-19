package com.EasyOk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.EasyOk.alltools.listenter.OnDownloadListener;

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
     * @param HttpClient   OkhttpClient�����������null����ʹ��Ĭ�϶���
     * @param url          ��������
     * @param destFileDir  ���ص��ļ�����Ŀ¼
     * @param destFileName �����ļ����ƣ�����ǵ�ƴ�Ӻ�׺������û��ʶ���ļ�����
     * @param listener     ���ؼ���
     */
 
    public void download(final OkHttpClient HttpClient,final String url, final String destFileDir, final String destFileName, final OnDownloadListener listener) {
 
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
                listener.onDownload_Failed(e);
            }
 
            @Override
            public void onResponse(Call call, Response response) throws IOException {
 
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
 
                //���������ļ���Ŀ¼
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
                        //�����и��½�����
                        listener.onDownloading(progress);
                    }
                    fos.flush();
                    //�������
                    listener.onDownload_Success(file);
                } catch (Exception e) {
                	System.out.println("error:"+e.getMessage());
                    listener.onDownload_Failed(e);
                }finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                    	 System.out.println("error:"+e.getMessage());
                    }
                }
            }
        });
    }
}
