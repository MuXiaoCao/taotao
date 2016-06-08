package com.taotao.common.utils;

import java.util.Random;

/**
 * <p>Description:id生成工具类</p>
 * <p>IDUtil.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年5月24日 下午6:39:46
 * @version: 1.0
 */
public class IDUtil {
	
	/**
	 * 商品id生成
	 * @return
	 */
	public static long getItemId() {
		long millis = System.currentTimeMillis();
		Random random = new Random();
		int end2 = random.nextInt(99);
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}
	public static void main(String[] args) {
		System.out.println(getItemId());
	}
}
