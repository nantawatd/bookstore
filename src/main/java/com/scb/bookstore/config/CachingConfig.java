package com.scb.bookstore.config;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;


@EnableCaching
@Configuration
public class CachingConfig {

	public static final String BOOK_CACHE = "books";
	public static final String BOOK_CACHE_KEY = "scbBook";

	@Bean
	public Config hazelCastConfig() {
		Config config = new Config();

		// set policy
		config.setInstanceName("hazelcast-instance")
				.addMapConfig(new MapConfig().setName(BOOK_CACHE) // similar with region in hibernate
						.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
						.setEvictionPolicy(EvictionPolicy.NONE).setTimeToLiveSeconds(86400)); // 1 day
		return config;
	}

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache(BOOK_CACHE)));
		return cacheManager;
	}
}
