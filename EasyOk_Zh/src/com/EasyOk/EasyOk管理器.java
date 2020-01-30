package com.EasyOk;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.CertificatePinner;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.CookieJar;
import okhttp3.Dns;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

public class EasyOk管理器 {

	public OkHttpClient Client对象;

	OkHttpClient.Builder builder;

	Builder requestbuilder;

	// Header_List
	Map<String, String> Header;

	public EasyOk管理器() {
		Client对象 = new OkHttpClient();
		builder = Client对象.newBuilder();
	}

	// Header添加方法
	public void 添加头(String 头名称, String 头内容) {
		Header = new HashMap<>();
		Header.put(头名称 + "", 头内容 + "");
	}

	/*
	 * 下面是其他信息的添加方法
	 */
	public void 设置连接超时时间(long 时间数字, String 时间尺度) {
		switch (时间尺度) {

		case "天": {
			builder.connectTimeout(时间数字, TimeUnit.DAYS);
			break;
		}
		case "小时": {
			builder.connectTimeout(时间数字, TimeUnit.HOURS);
			break;
		}
		case "分钟": {
			builder.connectTimeout(时间数字, TimeUnit.MINUTES);
			break;
		}
		case "秒": {
			builder.connectTimeout(时间数字, TimeUnit.SECONDS);
			break;
		}
		case "毫秒": {
			builder.connectTimeout(时间数字, TimeUnit.MILLISECONDS);
			break;
		}
		default: {
			builder.connectTimeout(时间数字, TimeUnit.SECONDS);
		}
		}
	}

	public void 设置默认读时间(long 时间数字, String 时间尺度) {
		switch (时间尺度) {

		case "天": {
			builder.readTimeout(时间数字, TimeUnit.DAYS);
			break;
		}
		case "小时": {
			builder.readTimeout(时间数字, TimeUnit.HOURS);
			break;
		}
		case "分钟": {
			builder.readTimeout(时间数字, TimeUnit.MINUTES);
			break;
		}
		case "秒": {
			builder.readTimeout(时间数字, TimeUnit.SECONDS);
			break;
		}
		case "毫秒": {
			builder.readTimeout(时间数字, TimeUnit.MILLISECONDS);
			break;
		}
		default: {
			builder.readTimeout(时间数字, TimeUnit.SECONDS);
		}
		}

	}

	public void 设置默认写时间(long 时间数字, String 时间尺度) {
		switch (时间尺度) {

		case "天": {
			builder.writeTimeout(时间数字, TimeUnit.DAYS);
			break;
		}
		case "小时": {
			builder.writeTimeout(时间数字, TimeUnit.HOURS);
			break;
		}
		case "分钟": {
			builder.writeTimeout(时间数字, TimeUnit.MINUTES);
			break;
		}
		case "秒": {
			builder.writeTimeout(时间数字, TimeUnit.SECONDS);
			break;
		}
		case "毫秒": {
			builder.writeTimeout(时间数字, TimeUnit.MILLISECONDS);
			break;
		}
		default: {
			builder.writeTimeout(时间数字, TimeUnit.SECONDS);
		}
		}

	}

	public void 设置代理(Proxy proxy) {
		builder.proxy(proxy);

	}

	public void 设置代理选择器(ProxySelector proxySelector) {
		builder.proxySelector(proxySelector);

	}

	public void 设置处理cookies的处理者_cookieJar(CookieJar cookieJar) {
		builder.cookieJar(cookieJar);

	}

	public void 设置缓存(Cache cache) {
		builder.cache(cache);

	}

	public void 设置DNS服务(Dns dns) {
		builder.dns(dns);

	}

	public void socketFactory(SocketFactory socketFactory) {
		builder.socketFactory(socketFactory);

	}

	public void sslSocketFactory(SSLSocketFactory sslSocketFactory) {
		builder.sslSocketFactory(sslSocketFactory);

	}

	public void hostnameVerifier(HostnameVerifier hostnameVerifier) {
		builder.hostnameVerifier(hostnameVerifier);

	}

	public void certificatePinner(CertificatePinner certificatePinner) {
		builder.certificatePinner(certificatePinner);

	}

	public void authenticator(Authenticator authenticator) {
		builder.authenticator(authenticator);

	}

	public void proxyAuthenticator(Authenticator proxyAuthenticator) {
		builder.proxyAuthenticator(proxyAuthenticator);

	}

	public void 设置连接池(ConnectionPool connectionPool) {
		builder.connectionPool(connectionPool);

	}

	public void followSslRedirects(boolean followProtocolRedirects) {
		builder.followSslRedirects(followProtocolRedirects);

	}

	public void followRedirects(boolean followRedirects) {
		builder.followRedirects(followRedirects);

	}

	public void 连接失败是否重连(boolean retryOnConnectionFailure) {
		builder.retryOnConnectionFailure(retryOnConnectionFailure);

	}

	public void 设置协议(List<Protocol> protocols) {
		builder.protocols(protocols);

	}

	public void connectionSpecs(List<ConnectionSpec> connectionSpecs) {
		builder.connectionSpecs(connectionSpecs);

	}

	public void 创建() {
		// 判断是否加入了请求头
		if (Header != null) {
			// 加入了请求头
			builder.addInterceptor(new Interceptor() {

				@Override
				public Response intercept(Chain chain) throws IOException {
					requestbuilder = chain.request().newBuilder();
					for (Map.Entry<String, String> entry : Header.entrySet()) {
						String mapKey = entry.getKey();
						String mapValue = entry.getValue();
						requestbuilder.addHeader(mapKey, mapValue);
					}
					Request request = requestbuilder.build();
					return chain.proceed(request);
				}
			});
		}
		Client对象 = builder.build();

	}
}
