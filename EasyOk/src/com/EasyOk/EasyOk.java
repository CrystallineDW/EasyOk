package com.EasyOk;

import java.io.File;
import java.util.Map;

import com.EasyOk.alltools.listenter.OnDownloadListener;
import com.EasyOk.alltools.listenter.OnGetListener;
import com.EasyOk.alltools.listenter.OnPostListener;

import okhttp3.OkHttpClient;

public class EasyOk extends OkHttpClient{
	
	private static boolean isnewClient = false;
	
	public void HeaderAdd()//ÃÌº”«Î«ÛÕ∑
	{
		
		
		
	}
	
	
	
	
	
	public static void Download(OkHttpClient HttpClient,String url,String savefilePath,String filename,final OnDownloadListener listener)
	{
		
		new Thread() {
			public void run()
			{
				DownloadUtil.get().download(HttpClient,url, savefilePath, filename,listener);			
			}
		}.start();
		
		
	}
	
	public static void GET(OkHttpClient HttpClient,String url,final OnGetListener listener)
	{
		new Thread() {
			public void run()
			{
				GetUtil.get().get(HttpClient,url,listener);			
			}
		}.start();
		
	}
	
	public static void POST(OkHttpClient HttpClient,String url,Map post_map,final OnPostListener listener)
	{
		new Thread() {
			public void run()
			{
				PostUtil.get().post(HttpClient,url,post_map,listener);			
			}
		}.start();
	}
	
	
	
}
