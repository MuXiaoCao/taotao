package com.taotao.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * <p>Description:FastDFS上传图片工具类</p>
 * <p>FastDFSClient.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年5月23日 下午4:22:35
 * @version: 1.0
 */
public class FastDFSClient {

	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageClient storageClient = null;
	private StorageServer storageServer = null;

	public FastDFSClient(String conf) throws FileNotFoundException, IOException, MyException {
		if (conf.contains("classpath:")) {
			conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
		}
		ClientGlobal.init(conf);
		trackerClient = new TrackerClient();
		trackerServer = trackerClient.getConnection();
		storageServer = null;
		storageClient = new StorageClient(trackerServer, storageServer);
	}
	/**
	 * 上传文件方法
	 * @param fileName
	 * @param extName
	 * @param metas
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
		String[] result = storageClient.upload_file(fileName, extName, metas);
		return getURL(result);
	}
	public String uploadFile(String fileName) throws Exception {
		String[] result = storageClient.upload_file(fileName, null, null);
		return getURL(result);
	}
	public String uploadFile(String fileName, String extName) throws Exception {
		String[] result = storageClient.upload_file(fileName, extName, null);
		return getURL(result);
	}
	
	public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
		String[] result = storageClient.upload_file(fileContent, extName, metas);
		return getURL(result);
	} 
	
	public String uploadFile(byte[] fileContent, String extName) throws Exception {
		String[] result = storageClient.upload_file(fileContent, extName, null);
		return getURL(result);
	} 
	
	public String uploadFile(byte[] fileContent ) throws Exception {
		String[] result = storageClient.upload_file(fileContent, null, null);
		return getURL(result);
	} 
	
	public String getURL(String[] result) {
		return result[0] + "/" + result[1];
	}
}
