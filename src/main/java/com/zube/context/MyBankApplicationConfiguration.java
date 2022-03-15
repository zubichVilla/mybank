package com.zube.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.zube.service.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBankApplicationConfiguration {

    @Bean
    public TransactionService transactionService(){
        return new TransactionService();
    }

    @Bean
    public ObjectMapper objectMapper(){
        return JsonMapper.builder()
                .findAndAddModules()
                .build();

    }

}
