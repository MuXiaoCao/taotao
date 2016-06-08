package com.taotao.fastdfs;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class FastDFSTest {

	@Test
	public void testUpload() throws Exception {
		// 1.初始化全局配置
		ClientGlobal.init(
				"G:\\muxiaocao\\javaEE\\淘淘商城项目\\workspace\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
		// 2.创建一个TrackerClient对象
		TrackerClient trackerClient = new TrackerClient();
		// 3.创建一个TrackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		// 4. 声明一个StorageServer对象
		StorageServer storageServer = null;
		// 5. 获得StorageClient对象
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		// 6.直接调用对象方法上传文件即可
		String[] strings = storageClient.upload_file("C:\\Users\\Administrator\\Desktop\\test.png", "png", null);
		for (String string : strings) {
			System.out.println(string);
		}
		System.out.println(ClientGlobal.getG_tracker_group().tracker_servers[0].getHostName());
	}

	@Test
	public void testFastDFSClient() throws Exception {
		FastDFSClient client = new FastDFSClient(
				"G:\\muxiaocao\\javaEE\\淘淘商城项目\\workspace\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
		String url = client.uploadFile("C:\\Users\\Administrator\\Desktop\\图片3.png");
		System.out.println(url);
	}
}
