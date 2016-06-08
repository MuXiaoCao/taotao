package com.taotao.dubbo;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbItem;
import com.taotao.rest.dubbo.RestContentService;
import com.taotao.rest.dubbo.RestItemService;

public class DubboConsumer {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring/applicationContext-consumer.xml" });
		context.start();
		RestContentService contentService = (RestContentService)context.getBean("contentService"); // 获取远程服务代理
	    List<TbContent> contentList = contentService.getContentList(89L);
	    for (TbContent tbContent : contentList) {
			System.out.println(tbContent.getTitle());
		}
	}
	
	
	@Test
	public void testRestItemService() {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring/applicationContext-consumer.xml" });
		context.start();
		RestItemService itemService = context.getBean(RestItemService.class);
		TbItem item = itemService.getItemById(146418656025717L);
		System.err.println("===================" + item.getTitle() + "," + item.getId());
	}
}
