package com.ftn.sbnz.service.config;

import com.ftn.sbnz.service.util.LongGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class GeneratorConfiguration {
    @Bean
    @Scope("prototype")
    public LongGenerator longGenerator() {
        return new LongGenerator();
    }
}
