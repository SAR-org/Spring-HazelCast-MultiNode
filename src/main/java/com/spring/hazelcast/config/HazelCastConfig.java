package com.spring.hazelcast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.config.MaxSizeConfig.MaxSizePolicy;

@Configuration
public class HazelCastConfig {

	@Bean
	public Config hazelCastConfiguration() {
		return new Config()
				.setManagementCenterConfig(new ManagementCenterConfig().setEnabled(true)
						.setUrl("http://localhost:8080/hazelcast-mancenter"))
				.setInstanceName("hazelcast-instance")
				.addMapConfig(new MapConfig().setName("userCache").setTimeToLiveSeconds(2000).setMaxSizeConfig(
						new MaxSizeConfig().setSize(200).setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)));
	}

}
