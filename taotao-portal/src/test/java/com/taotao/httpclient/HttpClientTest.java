package com.taotao.httpclient;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {
	
	@Test
	public void getTest() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet get = new HttpGet("http://muxiaocao.cn");
		CloseableHttpResponse response = httpClient.execute(get);
		HttpEntity entity = response.getEntity();
		String html = EntityUtils.toString(entity);
		System.out.println(html);
		response.close();
		httpClient.close();
	}
	
	@Test
	public void postTest() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://muxiaocoa.cn");
		List<NameValuePair> formList = new ArrayList<>();
		formList.add(new BasicNameValuePair("name", "张三"));
		formList.add(new BasicNameValuePair("pass", "1234"));
		StringEntity entity = new UrlEncodedFormEntity(formList,"utf-8");
		post.setEntity(entity);
		CloseableHttpResponse response = httpClient.execute(post);
		HttpEntity httpEntity = response.getEntity();
		String result = EntityUtils.toString(httpEntity);
		System.out.println(result);
		response.close();
		httpClient.close();
	}
	
}
