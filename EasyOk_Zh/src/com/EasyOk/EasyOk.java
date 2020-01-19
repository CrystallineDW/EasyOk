package com.EasyOk;

import java.io.File;
import java.util.Map;

import com.EasyOk.alltools.listenter.下载监听器;
import com.EasyOk.alltools.listenter.Get请求监听器;
import com.EasyOk.alltools.listenter.Post监听器;

import okhttp3.OkHttpClient;

public class EasyOk extends OkHttpClient{
	
	
	
	public static void 下载(OkHttpClient Client对象,String 网址,String 保存路径,String 文件名称,final 下载监听器 监听器)
	{
		
		new Thread() {
			public void run()
			{
				DownloadUtil.get().download(Client对象,网址, 保存路径, 文件名称,监听器);			
			}
		}.start();
		
		
	}
	
	public static void GET(OkHttpClient Client对象,String 网址,final Get请求监听器 监听器)
	{
		new Thread() {
			public void run()
			{
				GetUtil.get().get(Client对象,网址,监听器);			
			}
		}.start();
		
	}
	
	public static void POST(OkHttpClient Client对象,String 网址,Map post参数表,final Post监听器 监听器)
	{
		new Thread() {
			public void run()
			{
				PostUtil.get().post(Client对象,网址,post参数表,监听器);			
			}
		}.start();
	}
	
	
	
}
