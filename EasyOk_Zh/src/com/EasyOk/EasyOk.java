package com.EasyOk;

import java.io.File;
import java.util.Map;

import com.EasyOk.alltools.listenter.���ؼ�����;
import com.EasyOk.alltools.listenter.Get���������;
import com.EasyOk.alltools.listenter.Post������;

import okhttp3.OkHttpClient;

public class EasyOk extends OkHttpClient{
	
	
	
	public static void ����(OkHttpClient Client����,String ��ַ,String ����·��,String �ļ�����,final ���ؼ����� ������)
	{
		
		new Thread() {
			public void run()
			{
				DownloadUtil.get().download(Client����,��ַ, ����·��, �ļ�����,������);			
			}
		}.start();
		
		
	}
	
	public static void GET(OkHttpClient Client����,String ��ַ,final Get��������� ������)
	{
		new Thread() {
			public void run()
			{
				GetUtil.get().get(Client����,��ַ,������);			
			}
		}.start();
		
	}
	
	public static void POST(OkHttpClient Client����,String ��ַ,Map post������,final Post������ ������)
	{
		new Thread() {
			public void run()
			{
				PostUtil.get().post(Client����,��ַ,post������,������);			
			}
		}.start();
	}
	
	
	
}
