package com.taotao.test.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProvider {
	
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring/applicationContext-provider.xml" });
		context.start();
		
		System.in.read(); // 按任意键退出
	}
}
