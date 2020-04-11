package cn.tedu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.joran.GenericConfigurator;
import cn.order.pojo.User;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisTest {
	
	
	
	@Test
	public void test() throws Exception{
		Jedis jedis=new Jedis("114.55.92.112",8000);
		
		User user=new User();
		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(user);
		jedis.set("user02", userJson);
		System.out.println(jedis.get("user02"));
	}
	
	
	@Test
	public void test04(){
		List<JedisShardInfo> info=new ArrayList<JedisShardInfo>();
		info.add(new JedisShardInfo("114.55.92.112",8000));
		
		GenericObjectPoolConfig config=new GenericObjectPoolConfig();
		
		
		ShardedJedisPool pool=new ShardedJedisPool(config,info);
		
		ShardedJedis jedis = pool.getResource();
		jedis.set("name", "wanglaoshi");
		System.out.println(jedis.get("name"));
	}
	
	
	
	@Test
	public void test05(){
		Set<HostAndPort> set=new HashSet<HostAndPort>();
		
		set.add(new HostAndPort("114.55.92.112", 8000));
		
		GenericObjectPoolConfig config=new GenericObjectPoolConfig();
		config.setMaxTotal(200);
		JedisCluster cluster=new JedisCluster(set,config);
		
		cluster.set("name", "wanglaoshi");
		System.out.println(cluster.get("name"));
	}
	
}
