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

public class EasyOk������ {

	OkHttpClient Client����;

	OkHttpClient.Builder builder;

	Builder requestbuilder;

	// Header_List
	Map<String, String> Header;

	public EasyOk������() {
		Client���� = new OkHttpClient();
		builder = Client����.newBuilder();
	}

	// Header��ӷ���
	public void ���ͷ(String ͷ����, String ͷ����) {
		Header = new HashMap<>();
		Header.put(ͷ���� + "", ͷ���� + "");
	}

	/*
	 * ������������Ϣ����ӷ���
	 */
	public void �������ӳ�ʱʱ��(long ʱ������, String ʱ��߶�) {
		switch (ʱ��߶�) {

		case "��": {
			builder.connectTimeout(ʱ������, TimeUnit.DAYS);
			break;
		}
		case "Сʱ": {
			builder.connectTimeout(ʱ������, TimeUnit.HOURS);
			break;
		}
		case "����": {
			builder.connectTimeout(ʱ������, TimeUnit.MINUTES);
			break;
		}
		case "��": {
			builder.connectTimeout(ʱ������, TimeUnit.SECONDS);
			break;
		}
		case "����": {
			builder.connectTimeout(ʱ������, TimeUnit.MILLISECONDS);
			break;
		}
		default: {
			builder.connectTimeout(ʱ������, TimeUnit.SECONDS);
		}
		}
	}

	public void ����Ĭ�϶�ʱ��(long ʱ������, String ʱ��߶�) {
		switch (ʱ��߶�) {

		case "��": {
			builder.readTimeout(ʱ������, TimeUnit.DAYS);
			break;
		}
		case "Сʱ": {
			builder.readTimeout(ʱ������, TimeUnit.HOURS);
			break;
		}
		case "����": {
			builder.readTimeout(ʱ������, TimeUnit.MINUTES);
			break;
		}
		case "��": {
			builder.readTimeout(ʱ������, TimeUnit.SECONDS);
			break;
		}
		case "����": {
			builder.readTimeout(ʱ������, TimeUnit.MILLISECONDS);
			break;
		}
		default: {
			builder.readTimeout(ʱ������, TimeUnit.SECONDS);
		}
		}

	}

	public void ����Ĭ��дʱ��(long ʱ������, String ʱ��߶�) {
		switch (ʱ��߶�) {

		case "��": {
			builder.writeTimeout(ʱ������, TimeUnit.DAYS);
			break;
		}
		case "Сʱ": {
			builder.writeTimeout(ʱ������, TimeUnit.HOURS);
			break;
		}
		case "����": {
			builder.writeTimeout(ʱ������, TimeUnit.MINUTES);
			break;
		}
		case "��": {
			builder.writeTimeout(ʱ������, TimeUnit.SECONDS);
			break;
		}
		case "����": {
			builder.writeTimeout(ʱ������, TimeUnit.MILLISECONDS);
			break;
		}
		default: {
			builder.writeTimeout(ʱ������, TimeUnit.SECONDS);
		}
		}

	}

	public void ���ô���(Proxy proxy) {
		builder.proxy(proxy);

	}

	public void ���ô���ѡ����(ProxySelector proxySelector) {
		builder.proxySelector(proxySelector);

	}

	public void ���ô���cookies�Ĵ�����_cookieJar(CookieJar cookieJar) {
		builder.cookieJar(cookieJar);

	}

	public void ���û���(Cache cache) {
		builder.cache(cache);

	}

	public void ����DNS����(Dns dns) {
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

	public void �������ӳ�(ConnectionPool connectionPool) {
		builder.connectionPool(connectionPool);

	}

	public void followSslRedirects(boolean followProtocolRedirects) {
		builder.followSslRedirects(followProtocolRedirects);

	}

	public void followRedirects(boolean followRedirects) {
		builder.followRedirects(followRedirects);

	}

	public void ����ʧ���Ƿ�����(boolean retryOnConnectionFailure) {
		builder.retryOnConnectionFailure(retryOnConnectionFailure);

	}

	public void ����Э��(List<Protocol> protocols) {
		builder.protocols(protocols);

	}

	public void connectionSpecs(List<ConnectionSpec> connectionSpecs) {
		builder.connectionSpecs(connectionSpecs);

	}

	public void ����() {
		// �ж��Ƿ����������ͷ
		if (Header != null) {
			// ����������ͷ
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
		Client���� = builder.build();

	}
}
