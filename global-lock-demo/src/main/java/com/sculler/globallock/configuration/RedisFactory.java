package com.sculler.globallock.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisFactory {
	@Autowired
	RedisClusterConfig rdsClusterConfig;
	
	@Bean
	public RedisConnectionFactory rdsConnectionFactory() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(rdsClusterConfig.getMaxtotal());
		jedisPoolConfig.setMaxIdle(rdsClusterConfig.getMaxidle());
		jedisPoolConfig.setMinIdle(rdsClusterConfig.getMinidle());
		return new JedisConnectionFactory(new RedisClusterConfiguration(rdsClusterConfig.getNodes()), jedisPoolConfig);
	}
	
	@Bean
	public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
		return new RedisLockRegistry(redisConnectionFactory, "spring-cloud");
	}
	
}
