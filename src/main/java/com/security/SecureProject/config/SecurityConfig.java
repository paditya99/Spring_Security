package com.security.SecureProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
			httpSec.csrf(customizer -> customizer.disable());
			
			return httpSec.build();
		}
	
}
