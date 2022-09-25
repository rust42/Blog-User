package edu.miu.bloguser.config;

import eye2web.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
