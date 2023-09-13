package com.example.todolist.logger;

import com.michow.elasticcommonframework.ElasticCommonService;
import com.michow.elasticcommonframework.ElasticConfig;
import com.michow.elasticcommonframework.ElasticRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElasticLogger {


    private ElasticCommonService elasticCommonService;

    private ElasticCustomConfig elasticCustomConfig;

    private ElasticConfig elasticConfig;

    @Autowired
    public ElasticLogger(ElasticCustomConfig elasticCustomConfig) {
        this.elasticConfig = ElasticConfigFactory.getElasticConfig(elasticCustomConfig);
        this.elasticCommonService = new ElasticCommonService(elasticConfig);
    }

    public void sendElasticLog(String message){
        try{
            ElasticRequestDto dto = new ElasticRequestDto(message, elasticConfig);
            System.out.println(dto.getId());
            elasticCommonService.sendElasticLog(dto);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
