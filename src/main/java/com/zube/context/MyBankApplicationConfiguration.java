package com.zube.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.zube.ApplicationLauncher;
import com.zube.service.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
public class MyBankApplicationConfiguration {


    @Bean
    public ObjectMapper objectMapper(){
        return JsonMapper.builder()
                .findAndAddModules()
                .build();

    }

}
