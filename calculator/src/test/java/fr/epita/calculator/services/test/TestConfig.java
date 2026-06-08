package fr.epita.calculator.services.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public String version(){
        return "1.0.1";
    }

}
