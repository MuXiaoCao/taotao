package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FastDFSClient;
import com.taotao.service.PictureService;

/**
 * <p>Description:图片上传service</p>
 * <p>PictureServiceImpl.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年5月23日 下午5:12:36
 * @version: 1.0
 */
@Service
public class PictureServiceImpl implements PictureService{

	@Value("${IMAGE_SERVER_BASE_URL}")
	private String IMAGE_SERVER_BASE_URL;
	
	@Override
	public PictureResult uploadPicture(MultipartFile pictureFile) {
		PictureResult result = new PictureResult();
		if (pictureFile.isEmpty()) {
			result.setError(1);
			result.setMessage("图片为空");
			return result;
		}
		// 上传到图片服务器
		FastDFSClient client;
		try {
			client = new FastDFSClient("classpath:properties/client.conf");
			String url = client.uploadFile(pictureFile.getBytes());
			result.setUrl(url);
			result.equals(0);
		} catch (Exception e) {
			e.printStackTrace();
			result.setError(1);
			result.setMessage("图片上传失败");
		}
		return result;
	}
	
}
