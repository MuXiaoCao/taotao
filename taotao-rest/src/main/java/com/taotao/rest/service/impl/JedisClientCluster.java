package com.taotao.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.rest.service.JedisClientService;

import redis.clients.jedis.JedisCluster;

/**
 * <p>Description:Dubbo集群版缓存实现类</p>
 * <p>JedisClientCluster.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月4日 下午8:27:32
 * @version: 1.0
 */
public class JedisClientCluster implements JedisClientService{

	@Autowired
	private JedisCluster jedisCluster;
	
	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}

	@Override
	public Long hset(String key, String item, String value) {
		return jedisCluster.hset(key, item, value);
	}

	@Override
	public String hget(String key, String item) {
		return jedisCluster.hget(key, item);
	}

	@Override
	public Long incr(String key) {
		return jedisCluster.incr(key);
	}

	@Override
	public Long decr(String key) {
		return jedisCluster.decr(key);
	}

	@Override
	public Long expire(String key, int seconds) {
		return jedisCluster.expire(key, seconds);
	}

	@Override
	public Long ttl(String key) {
		return jedisCluster.ttl(key);
	}

	@Override
	public void hdel(String key, String item) {
		jedisCluster.hdel(key, item);
	}

	@Override
	public Long delete(String key) {
		return jedisCluster.del(key);
	}
}
