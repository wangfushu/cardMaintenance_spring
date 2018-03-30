package gmms.unifiedPay.conn;

import gmms.util.MyX509TrustManager;
import gmms.util.WStringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;


/**
 * 连接类
 * 
 * @author Andy
 * 
 */
public class Connection {
	private int default_connTime = 5000;
	private int default_readTime = 5000;
	private int default_upload_readTime = 10 * 1000;
	protected String default_charset = "utf-8";


	/**
	 * http请求
	 * 
	 * @param method
	 *            请求方法GET/POST
	 * @param path
	 *            请求路径
	 * @param timeout
	 *            连接超时时间 默认为5000
	 * @param readTimeout
	 *            读取超时时间 默认为5000
	 * @param data
	 *            数据
	 * @return
	 */
	public String defaultConnection(String method, String path, int timeout,
			int readTimeout, String data) throws Exception {
		String result = "";
		URL url = new URL(path);
		if (url != null) {
			HttpURLConnection conn = getConnection(method, url);
			conn.setConnectTimeout(timeout == 0 ? default_connTime : timeout);
			conn.setReadTimeout(readTimeout == 0 ? default_readTime
					: readTimeout);
			if (data != null && !"".equals(data)) {
				OutputStream output = conn.getOutputStream();
				output.write(data.getBytes(default_charset));
				output.flush();
				output.close();
			}
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream input = conn.getInputStream();
				result = inputToStr(input);
				input.close();
				conn.disconnect();
			}
		}
		return result;
	}
	/**
	 * 根据url的协议选择对应的请求方式 例如 http://www.baidu.com 则使用http请求,https://www.baidu.com
	 * 则使用https请求
	 *
	 * @param method
	 *            请求的方法
	 * @return
	 * @throws java.io.IOException
	 */
	private HttpURLConnection getConnection(String method, URL url)
			throws IOException {
		HttpURLConnection conn = null;
		if ("https".equals(url.getProtocol())) {
			SSLContext context = null;
			try {
				context = SSLContext.getInstance("SSL", "SunJSSE");
				context.init(new KeyManager[0],
						new TrustManager[] { new MyX509TrustManager() },
						new java.security.SecureRandom());
			} catch (Exception e) {
				throw new IOException(e);
			}
			HttpsURLConnection connHttps = (HttpsURLConnection) url.openConnection();
			connHttps.setSSLSocketFactory(context.getSocketFactory());
			connHttps.setHostnameVerifier(new HostnameVerifier() {

				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			});
			conn = connHttps;
		} else {
			conn = (HttpURLConnection) url.openConnection();
		}
		conn.setRequestMethod(method);
		conn.setUseCaches(false);



		conn.setDoInput(true);
		conn.setDoOutput(true);
		return conn;
	}

	/**
	 * 将输入流转换为字符串
	 *
	 * @param input
	 *            输入流
	 * @return 转换后的字符串
	 */
	public String inputToStr(InputStream input) {
		String result = "";
		if (input != null) {
			byte[] array = new byte[1024];
			StringBuffer buffer = new StringBuffer();
			try {
				for (int index; (index = (input.read(array))) > -1;) {
					buffer.append(new String(array, 0, index, default_charset));
				}
				result = buffer.toString();
			} catch (IOException e) {
				e.printStackTrace();
				result = "";
			}
		}
		return result;
	}

	/**
	 * 默认的https执行方法,返回
	 *
	 * @param method
	 *            请求的方法 POST/GET
	 * @param path
	 *            请求path 路径
	 * @param data
	 *            输入的数据 允许为空
	 * @return
	 */
	public String HttpsDefaultExecute(String method, String path, String data) {
		String result = "";
		try {
			//String url = setParmas((TreeMap<String, String>) map, path, "");
			result = defaultConnection(method, path, default_connTime,default_readTime, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 设置参数
	 * @param map    参数map
	 * @param path      需要赋值的path
	 * @param charset  编码格式 默认编码为utf-8
	 * @return 已经赋值好的url 只需要访问即可
	 */
	public String setParmas(Map<String, String> map, String path, String charset)
			throws Exception {
		String result = "";
		boolean hasParams = false;
		if (path != null && !"".equals(path)) {
			if (map != null && map.size() > 0) {
				StringBuilder builder = new StringBuilder();
				Set<Map.Entry<String, String>> params = map.entrySet();
				for (Map.Entry<String, String> entry : params) {
					String key = entry.getKey().trim();
					String value = entry.getValue().trim();
					if (hasParams) {
						builder.append("&");
					} else {
						hasParams = true;
					}
					charset = (charset != null && !"".equals(charset) ? charset: default_charset);
					builder.append(key).append("=").append(URLDecoder.decode(value, charset));
				}
				result = builder.toString();
			}
		}
		// 此时的url待改进
		return doUrlPath(path, result).toString();
	}
	/**
	 * 设置连接参数
	 *
	 * @param path
	 *            路径
	 * @return
	 */
	private URL doUrlPath(String path, String query) throws Exception {
		URL url = new URL(path);
		if (WStringUtils.StringIsEmpty(path)) {
			return url;
		}
		if (WStringUtils.StringIsEmpty(url.getQuery())) {
			if (path.endsWith("?")) {
				path += query;
			} else {
				path = path + "?" + query;
			}
		} else {
			if (path.endsWith("&")) {
				path += query;
			} else {
				path = path + "&" + query;
			}
		}
		return new URL(path);
	}
}
