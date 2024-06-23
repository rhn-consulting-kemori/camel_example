package com.redhat.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("app")
public class AppConfig {
    
    /** Config Parameter */
    private String urlCardCheckService;
    private String urlDepositCategoryService;
    private String inputTopicName;
    private String outputTopicName;
    
}
