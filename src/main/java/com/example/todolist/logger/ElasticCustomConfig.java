package com.example.todolist.logger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties(prefix = "elastic")
public class ElasticCustomConfig {

    private String url;
    private String port;
    private String type;
    private String index;

}
