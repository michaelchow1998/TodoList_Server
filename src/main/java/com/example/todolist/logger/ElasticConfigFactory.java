package com.example.todolist.logger;

import com.michow.elasticcommonframework.ElasticConfig;

public class ElasticConfigFactory {

    public static ElasticConfig getElasticConfig(ElasticCustomConfig elasticCustomConfig){
        ElasticConfig elasticConfig = new ElasticConfig(
                elasticCustomConfig.getUrl(),
                elasticCustomConfig.getPort(),
                elasticCustomConfig.getType(),
                elasticCustomConfig.getIndex()
                );

        return elasticConfig;
    }
}
