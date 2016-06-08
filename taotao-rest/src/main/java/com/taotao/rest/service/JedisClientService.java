package com.taotao.rest.service;

/**
 * <p>Description:Dubbo缓存服务提供者接口</p>
 * <p>JedisClient.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月4日 下午8:15:07
 * @version: 1.0
 */
public interface JedisClientService {
	
	public String set(String key,String value);
	public String get(String key);
	public Long hset(String key,String item,String value);
	public String hget(String key,String item);
	public Long incr(String key);
	public Long decr(String key);
	// 设置有效时间
	public Long expire(String key,int second);
	// 返回有效时间：-1：永久保存 -2：已经失效 大于0：有效时间（单位s）
	public Long ttl(String key);
	// 删除hash缓存
	public void hdel(String key,String item);
	// 删除缓存
	public Long delete(String key); 
}
