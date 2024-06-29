package com.redhat.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("app")
public class AppConfig {
    
    /** Config Parameter */
    private String inputTopicName;
    private String outputTopicName;
    
    // URL
    private String urlDepositEntryCheck;
    private String urlDepositCategory;
    private String urlCheckAvailableDepositAmount;
    private String urlDepositAllocation;
    private String urlDeposit;
}
