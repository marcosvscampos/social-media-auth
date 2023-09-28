package com.roguesoft.socialmedia.auth.application.config;

import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfig() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "OPTIONS")
                        .allowedHeaders("Content-Type", "Access-Control-Allow-Origin", "Access-Control-Request-Headers")
                        .maxAge(3600);
            }
        };
    }

}
