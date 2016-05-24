package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;

public interface PictureService {
	
	public PictureResult uploadPicture(MultipartFile pictureFile);
	
}
