package com.myicellar.digitalmenu.configuration;

import com.myicellar.digitalmenu.configuration.properties.SnowflakeProperties;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicWineShopConfig {

    @Bean
    public SnowflakeIdWorker snowflakeIdWorker(SnowflakeProperties properties) {
        return new SnowflakeIdWorker(properties.getWorkerId(), properties.getDataCenterId());
    }
}
