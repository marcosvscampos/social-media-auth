package com.roguesoft.socialmedia.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = { "com.roguesoft" })
public class SocialMediaAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaAuthApplication.class, args);
	}

}
