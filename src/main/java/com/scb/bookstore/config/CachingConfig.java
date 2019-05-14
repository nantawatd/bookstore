package com.scb.bookstore.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;


@EnableCaching
@Configuration
public class CachingConfig {

	@Bean
	public Config hazelCastConfig() {
		Config config = new Config();

		// set policy
		config.setInstanceName("hazelcast-instance")
				.addMapConfig(new MapConfig().setName("books") // similar with region in hibernate
						.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
						.setEvictionPolicy(EvictionPolicy.NONE).setTimeToLiveSeconds(30));
		return config;
	}
}
