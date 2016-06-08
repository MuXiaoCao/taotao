package com.taotao.test.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.rest.service.JedisClientService;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * <p>
 * Description:Redis缓存测试
 * </p>
 * <p>
 * JedisTest.java
 * </P>
 * <p>
 * 个人主页：www.muxiaocao.cn/me
 * </p>
 * 
 * @author 木小草 @date： 2016年6月4日 下午7:58:45
 * @version: 1.0
 */
public class JedisTest {

	// 单机版测试
	@Test
	public void testJedisSingle() throws Exception {
		Jedis jedis = new Jedis("192.168.3.111", 6379);
		jedis.set("test", "hello jedis");
		String result = jedis.get("test");
		System.out.println(result);
		jedis.close();
	}
	
	// 使用连接池
	@Test
	public void testJedisPool() throws Exception {
		// 创建一个连接池对象
		// 在系统中应该是单例的
		JedisPool jedisPool = new JedisPool("192.168.3.111", 6379);
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get("test");
		System.out.println(string);
		// 使用完jedis必须关闭
		jedis.close();
		// 当系统关闭时，关闭连接池
		jedisPool.close();
	}

	// 集群版测试
	@Test
	public void testJedisCluster() throws Exception {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.3.111", 7001));
		nodes.add(new HostAndPort("192.168.3.111", 7002));
		nodes.add(new HostAndPort("192.168.3.111", 7003));
		nodes.add(new HostAndPort("192.168.3.111", 7004));
		nodes.add(new HostAndPort("192.168.3.111", 7005));
		nodes.add(new HostAndPort("192.168.3.111", 7006));
		// 在系统中是单例的
		JedisCluster jedisCluster = new JedisCluster(nodes);
		jedisCluster.set("name", "张三");
		jedisCluster.set("value", "1000");
		String name = jedisCluster.get("name");
		String value = jedisCluster.get("value");
		System.out.println(name + "," + value);

		// 在系统关闭时关闭
		jedisCluster.close();
	}
	
	@Test
	public void testJedisClientSpring() throws Exception {
		// 创建一个spring容器
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext-*.xml");
		// 从容器中获得JedisClient对象
		JedisClientService jedisClient = applicationContext.getBean(JedisClientService.class);
		// jedisClient操作redis
		jedisClient.set("cliet1", "1000");
		String string = jedisClient.get("cliet1");
		System.out.println(string);
	}
}
