package com.taotao.search.solrj;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * <p>
 * Description:SolrJ的测试类，测试与solr服务器的数据导入
 * </p>
 * <p>
 * SolrTest.java
 * </P>
 * <p>
 * 个人主页：www.muxiaocao.cn/me
 * </p>
 * 
 * @author 木小草 @date： 2016年6月6日 下午12:38:35
 * @version: 1.0
 */
public class SolrTest {
	
	@Test
	public void testSorlJ() {
		// 获得连接
		HttpSolrClient solrServer = new HttpSolrClient("http://192.168.3.111:8080/solr/my_solr");
		solrServer.setAllowCompression(true);
		// 创建文档对象
		SolrInputDocument document = new SolrInputDocument();
		// 添加域
		document.addField("id", "testid");
		document.addField("item_title", "testtitile");
		document.addField("item_sell_point", "testpoing");
		try {
			// 添加到索引库
			solrServer.add(document);
			// 提交
			solrServer.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Test
	public void testAddItem() {
		
	}
}
