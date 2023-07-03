package com.digitalworlds.api1.configuration;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Getter
@Configuration
public class MapperConfiguration {
    @Bean
    @Scope(value="singleton")
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
