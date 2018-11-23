package com.itgorillaz.lnk2pwn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itgorillaz.lnk2pwn.model.Shortcut;

@Configuration
public class ModelConfig {
    
    @Bean
    public Shortcut shortchut() {
        return new Shortcut();	
    }
    
}
