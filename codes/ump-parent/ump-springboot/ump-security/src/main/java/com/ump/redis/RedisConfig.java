package com.ump.redis;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis 配置
 */
@Configuration
public class RedisConfig {
	@Bean
	public CacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
		// RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		// cacheManager.setDefaultExpiration(1800);
		//RedisCacheManager cacheManager = RedisCacheManager.RedisCacheManagerBuilder.build();
		return null;
	}

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}
}
