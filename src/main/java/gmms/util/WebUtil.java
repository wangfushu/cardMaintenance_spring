package gmms.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gmms.unifiedPay.RBIPay;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: wangfs Date: 16-3-23
 */
public final class WebUtil {
	private Logger LOGGER = LoggerFactory.getLogger(WebUtil.class);
	/**
	 * 向指定URL发送GET方法的请求
	 *
	 * @param url
	 *            发送请求的URL
	 * @param params
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static JSONObject sendGet(String url, Map<String, String> params) {

		String result = "";
		BufferedReader in = null;
		StringBuffer buffer = new StringBuffer();
		JSONObject jsonResult = null;
		try {
			if (params != null && params.size() > 0) {
				for (String key : params.keySet()) {

					buffer.append(key).append("=").append(URLEncoder.encode(params.get(key), "utf-8"));
					buffer.append("&");
				}
			}
			if (buffer.toString().length() > 0) {
				buffer.deleteCharAt(buffer.length() - 1);
			}

			System.out.println(" url 的参数 ： " + buffer);
			// String urlNameString = null;
			String urlNameString = url + "?" + buffer;
			// get请求返回结果
			System.out.println(" url 的参数 ： " + urlNameString);
			CloseableHttpClient client = HttpClients.createDefault();
			;
			// 发送get请求
			HttpGet request = new HttpGet(urlNameString);
			HttpResponse response = client.execute(request);

			/** 请求发送成功，并得到响应 **/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/** 读取服务器返回过来的json字符串数据 **/

				String strResult = EntityUtils.toString(response.getEntity(), "UTF-8");
				// System.out.println("strResult = "+strResult);
				// URLDecoder.decode(response.getEntity(),"UTF-8");
				/** 把json字符串转换成json对象 **/
				jsonResult = JSONObject.fromObject(strResult);
				// 对url进行反编码，一般是服务器写的
				url = URLDecoder.decode(urlNameString, "UTF-8");
			} else {
				System.out.println("get请求提交失败:" + urlNameString);
			}
		} catch (IOException e) {
			e.printStackTrace();
			// logger.error("get请求提交失败:" + urlNameString, e);
		}
		return jsonResult;

	}

	/**
	 * 发送Post请求
	 *
	 * @param url
	 *            请求地址
	 * @param jsonParam
	 *            请求参数
	 * @return 请求结果
	 * @throws java.io.IOException
	 */
	public static String postJson(String url, String jsonParam) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		JSONObject jsonResult = null;
		HttpPost method = new HttpPost(url);
		String str = "";
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				method.setEntity(entity);
			}
			HttpResponse result = httpClient.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/

			if (result.getStatusLine().getStatusCode() == 200) {

				try {
					/** 读取服务器返回过来的json字符串数据 **/
					str = EntityUtils.toString(result.getEntity());
					/** 把json字符串转换成json对象 **/
					//jsonResult = JSONObject.fromObject(str);
				} catch (Exception e) {
					//LOGGER.info(" WebUtil error :"+e.getStackTrace());
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 通过Post方式请求webserviec方法
	 * @param postMethod 接口地址+"/"+请求的方法名
	 * @param host
	 * @param params 请求参数
	 * @return
	 * @throws org.apache.commons.httpclient.HttpException
	 * @throws java.io.IOException
	 */
/*
	public static String webServicePost(String postMethod, String host, Map<String, String> params)
			throws HttpException, IOException {
		InputStream is = null;
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(postMethod);
		method.setRequestHeader("Host", host);
		method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		for (String key : params.keySet()) {
			method.setParameter(key, params.get(key));
		}
		client.executeMethod(method);
		is = method.getResponseBodyAsStream();
		org.jsoup.nodes.Document document = Jsoup.parse(is, "UTF-8", "");
		if (is != null){

		}
			//is.close();
		return document.toString();
	}
*/


//    private static Log logger=LogFactory.getLog(TestHTTPClient.class);

	/**
	 * webService接口调用（post）
	 * @param url
	 * @param nameValuePairs
	 * @return
	 * @throws java.io.IOException
	 */
		public static String post(String url,List<NameValuePair> nameValuePairs) throws IOException {
			CloseableHttpClient httpClient= HttpClients.createDefault();
			HttpPost httpPost=new HttpPost(url);
			CloseableHttpResponse httpResponse=null;
			HttpEntity httpResponseEntity=null;
			UrlEncodedFormEntity urlEncodedFormEntity=new UrlEncodedFormEntity(nameValuePairs,"utf-8");
			httpPost.setEntity(urlEncodedFormEntity);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectionRequestTimeout(5000).build();
			httpPost.setConfig(requestConfig);
			httpResponse = httpClient.execute(httpPost);
			InputStream inputStream=null;
			System.out.println(httpResponse.getStatusLine());
			if (httpResponse.getStatusLine().getStatusCode()==200){
				httpResponseEntity = httpResponse.getEntity();
//            System.out.println("响应内容："+EntityUtils.toString(httpResponseEntity));//entity的流不可重复读，查看源码可以发现此处流已关闭
				inputStream = httpResponseEntity.getContent();
				BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
				String line=null;
				StringBuffer stringBuffer=new StringBuffer();
				while ((line=bufferedReader.readLine())!=null){
					stringBuffer.append(line);
				}
				//System.out.println("----------"+stringBuffer.toString()+"---------");
				return String.valueOf(stringBuffer);
			}else{
				System.out.println("请求出错!");
			}
			httpResponse.close();
			httpClient.close();
			inputStream.close();
			return null;
		}

	/**
	 *  webService接口调用（get）
	 * @param url
	 * @return
	 * @throws java.io.IOException
	 */
		public static String get(String url) throws IOException {
			CloseableHttpClient httpClient=HttpClients.createDefault();
			HttpGet httpGet=new HttpGet(url);
			CloseableHttpResponse httpResponse=null;
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectionRequestTimeout(2000).build();
			httpGet.setConfig(requestConfig);
			httpResponse=httpClient.execute(httpGet);
			InputStream inputStream=null;
			System.out.println(+httpResponse.getStatusLine().getStatusCode());
			if (httpResponse.getStatusLine().getStatusCode()==200){
				HttpEntity httpResponseEntity = httpResponse.getEntity();
//            System.out.println("响应内容："+EntityUtils.toString(httpResponseEntity));//entity的流不可重复读，查看源码可以发现此处流已关闭
				inputStream = httpResponseEntity.getContent();
				BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
				String line=null;
				StringBuffer stringBuffer=new StringBuffer();
				while ((line=bufferedReader.readLine())!=null){
					stringBuffer.append(line);
				}
				System.out.println("----------"+stringBuffer.toString()+"---------");
				return String.valueOf(stringBuffer);
			}else{
				System.out.println("请求出错!");
			}
			return null;

		}

		public static void main(String[] args) {

//        testHTTPClient.sendGetRequest();
//        testHTTPClient.sendPostRequest();
//        get("http://www.baidu.com");

		}

		private static CloseableHttpClient getHttpClient(){
			CloseableHttpClient httpClient=HttpClients.createDefault();
			return httpClient;
		}

		private static void closeHttpClient(CloseableHttpClient httpClient) {
			if (httpClient!=null){
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}







}
