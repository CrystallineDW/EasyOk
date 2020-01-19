# EasyOk

**EasyOk是基于Okhttp一个轻量级的HttpClient**





EasyOk可以用不到Okhttp50%的代码量实现通常的 GET , POST , 下载请求
并且EasyOk可以把添加请求头，设置代理等繁琐复杂的工作通过几行代码解决
由于基于Okhttp，所以性能强大(站在巨人的肩膀上)





EasyOk源于Square公司的Okhttp开源项目，[👉开源地址👈](https://github.com/square/okhttp")，并在MIT开源协议的许可范围内将代码进行包装，将实现代码进行精简，以适应广大用户群体的需求。
再一次向优秀的HttpClient原作者Square以及Okhttp致敬。


![Pandao editor.md](https://avatars0.githubusercontent.com/u/82592?s=100&v=1")



## EasyOk的功能演示

#### 设置EasyOkManager
	EasyOkManager manager = new EasyOkManager();

#### EasyOkManager设置方法
	manager.connectTimeout(long 时间数字, String 时间尺度),尺度：DAYS,HOURS,MINUTES,SECONDS,MILLISECONDS
	manager.readTimeout(long 时间数字, String 时间尺度)
	manager.writeTimeout(long 时间数字, String 时间尺度)
	manager.proxy(Proxy proxy)
	manager.proxySelector(ProxySelector proxySelector)
	manager.cookieJar(CookieJar cookieJar)
	manager.cache(Cache cache)
	manager.dns(Dns dns)
	manager.socketFactory(SocketFactory socketFactory)
	manager.sslSocketFactory(SSLSocketFactory sslSocketFactory)
	manager.hostnameVerifier(HostnameVerifier hostnameVerifier)
	manager.certificatePinner(CertificatePinner certificatePinner)
	manager.authenticator(Authenticator authenticator)
	manager.proxyAuthenticator(Authenticator proxyAuthenticator)
	manager.connectionPool(ConnectionPool connectionPool)
	manager.followSslRedirects(boolean followProtocolRedirects)
	manager.followRedirects(boolean followRedirects)
	manager.retryOnConnectionFailure(boolean retryOnConnectionFailure)
	manager.protocols(List<Protocol> protocols)
	manager.connectionSpecs(List<ConnectionSpec> connectionSpecs)

#### EasyOkManager设置请求头
	manager.addHeader(Key, Value);

#### EasyOkManager完成构建
	manager.ClientBuild();

## 发送请求注意事项
无论是GET请求还是POST还是下载
如果不需要设置请求头或者超时时间等，则可以直接传入null
如果有需要设置请求头或者超时时间等，则需要传入Manager对象中的"manager.Client"
######示例:
```java
	EasyOkManager manager = new EasyOkManager();
		manager.addHeader("", "");
		manager.connectTimeout(10, "SECONDS");
		manager.ClientBuild();
		EasyOk.GET(manager.Client, "", new OnGetListener() {
			@Override
			public void onGet_Success(String requestdata) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGet_Failed(Exception e) {
				// TODO Auto-generated method stub

			}
		});
```

#### 发送Get请求
```java
    EasyOk.GET(null, "url", new OnGetListener() {
			
			@Override
			public void onGet_Success(String requestdata) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onGet_Failed(Exception e) {
				// TODO Auto-generated method stub
				
			}
		});
```

#### 发送Post请求
```java
Map data = new HashMap<>();
		data.put("key1", "value1");
		data.put("key2", "value2");
		EasyOk.POST(null, "", data, new OnPostListener() {
			
			@Override
			public void onPost_Success(String requestdata) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPost_Failed(Exception e) {
				// TODO Auto-generated method stub
				
			}
		});
```

#### 下载文件
```java
EasyOk.Download(null, "", "保存的路径", "保存名称", new OnDownloadListener() {
			
			@Override
			public void onDownloading(int progress) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDownload_Success(File file) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onDownload_Failed(Exception e) {
				// TODO Auto-generated method stub
				
			}
		});
```


### 如果本项目对您有帮助，请star一下哦♪(^∇^*)
