package com.EasyOk;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import com.oracle.tools.packager.Log;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CertificatePinner;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.CookieJar;
import okhttp3.Dispatcher;
import okhttp3.Dns;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.internal.InternalCache;
import okhttp3.Interceptor.Chain;

public class EasyOkManager{
	
	OkHttpClient Client;
	
	OkHttpClient.Builder builder;
	
	Builder requestbuilder;
	
	//Header_List
	Map<String,String> Header;
	
	public EasyOkManager()
	{
		Client = new OkHttpClient();
		builder = Client.newBuilder();
	}
	
	//Header添加方法
	public void addHeader(String Key,String Value)
	{
		Header =new HashMap<>();
		Header.put(Key+"", Value+"");
	}
	
	/*
	 * 下面是其他信息的添加方法
	 */
	public void connectTimeout(long timeout,String timesize)
	{
		switch(timesize){
		
			case "DAYS":
			{
				builder.connectTimeout(timeout, TimeUnit.DAYS);
				break;
			}
			case "HOURS":
			{
				builder.connectTimeout(timeout, TimeUnit.HOURS);
				break;
			}
			case "MINUTES":
			{
				builder.connectTimeout(timeout, TimeUnit.MINUTES);
				break;
			}
			case "SECONDS":
			{
				builder.connectTimeout(timeout, TimeUnit.SECONDS);
				break;
			}
			case "MILLISECONDS":
			{
				builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
				break;
			}
			default:
			{
				builder.connectTimeout(timeout, TimeUnit.SECONDS);
			}
		}
	}
	
	public void readTimeout(long timeout,String timesize)
	{
		switch(timesize){
		
		case "DAYS":
		{
			builder.readTimeout(timeout, TimeUnit.DAYS);
			break;
		}
		case "HOURS":
		{
			builder.readTimeout(timeout, TimeUnit.HOURS);
			break;
		}
		case "MINUTES":
		{
			builder.readTimeout(timeout, TimeUnit.MINUTES);
			break;
		}
		case "SECONDS":
		{
			builder.readTimeout(timeout, TimeUnit.SECONDS);
			break;
		}
		case "MILLISECONDS":
		{
			builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
			break;
		}
		default:
		{
			builder.readTimeout(timeout, TimeUnit.SECONDS);
		}
	}
		
	}
	
	public void writeTimeout(long timeout,String timesize)
	{
		switch(timesize){
		
		case "DAYS":
		{
			builder.writeTimeout(timeout, TimeUnit.DAYS);
			break;
		}
		case "HOURS":
		{
			builder.writeTimeout(timeout, TimeUnit.HOURS);
			break;
		}
		case "MINUTES":
		{
			builder.writeTimeout(timeout, TimeUnit.MINUTES);
			break;
		}
		case "SECONDS":
		{
			builder.writeTimeout(timeout, TimeUnit.SECONDS);
			break;
		}
		case "MILLISECONDS":
		{
			builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
			break;
		}
		default:
		{
			builder.writeTimeout(timeout, TimeUnit.SECONDS);
		}
	}
		
	}
	
	public void proxy(Proxy proxy)
	{
		builder.proxy(proxy);
		
	}
	
	public void proxySelector(ProxySelector proxySelector)
	{
		builder.proxySelector(proxySelector);
		
	}
	
	public void cookieJar(CookieJar cookieJar)
	{
		builder.cookieJar(cookieJar);
		
	}
	
	public void cache(Cache cache)
	{
		builder.cache(cache);
		
	}
	
	public void dns(Dns dns)
	{
		builder.dns(dns);
		
	}
	
	public void socketFactory(SocketFactory socketFactory)
	{
		builder.socketFactory(socketFactory);
		
	}
	
	public void sslSocketFactory(SSLSocketFactory sslSocketFactory)
	{
		builder.sslSocketFactory(sslSocketFactory);
		
	}
	
	
	public void hostnameVerifier(HostnameVerifier hostnameVerifier)
	{
		builder.hostnameVerifier(hostnameVerifier);
		
	}
	
	public void certificatePinner(CertificatePinner certificatePinner)
	{
		builder.certificatePinner(certificatePinner);
		
	}
	
	public void authenticator(Authenticator authenticator)
	{
		builder.authenticator(authenticator);
		
	}
	
	public void proxyAuthenticator(Authenticator proxyAuthenticator)
	{
		builder.proxyAuthenticator(proxyAuthenticator);
		
	}
	
	public void connectionPool(ConnectionPool connectionPool)
	{
		builder.connectionPool(connectionPool);
		
	}
	
	public void followSslRedirects(boolean followProtocolRedirects)
	{
		builder.followSslRedirects(followProtocolRedirects);
		
	}
	
	public void followRedirects(boolean followRedirects)
	{
		builder.followRedirects(followRedirects);
		
	}
	
	public void retryOnConnectionFailure(boolean retryOnConnectionFailure)
	{
		builder.retryOnConnectionFailure(retryOnConnectionFailure);
		
	}
	
	public void protocols(List<Protocol> protocols)
	{
		builder.protocols(protocols);
		
	}
	
	public void connectionSpecs(List<ConnectionSpec> connectionSpecs)
	{
		builder.connectionSpecs(connectionSpecs);

	}
	
	
	public void ClientBuild()
	{
		//判断是否加入了请求头
		if(Header!=null)
		{
			//加入了请求头
			for(Map.Entry<String, String> entry : Header.entrySet()){
        	    String mapKey = entry.getKey();
        	    String mapValue = entry.getValue();
			}
        	    builder.addInterceptor(new Interceptor() {

					@Override
					public Response intercept(Chain chain) throws IOException {
						requestbuilder = chain.request().newBuilder();
						for(Map.Entry<String, String> entry : Header.entrySet()){
			        	    String mapKey = entry.getKey();
			        	    String mapValue = entry.getValue();
			        	    requestbuilder.addHeader(mapKey, mapValue);
						}
						Request request  = requestbuilder.build();
                        return chain.proceed(request);
					}
        	    });
		}
		Client = builder.build();
		
	}
}
