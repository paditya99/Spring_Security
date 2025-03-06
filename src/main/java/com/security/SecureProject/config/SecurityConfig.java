package com.security.SecureProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.security.SecureProject.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
		@Autowired
		private MyUserDetailsService myUserDetailsService;
	
		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
			httpSec.csrf(customizer -> customizer.disable());
			httpSec.authorizeHttpRequests(request-> request.anyRequest().authenticated());
			httpSec.formLogin(Customizer.withDefaults());
			httpSec.httpBasic(Customizer.withDefaults());
			return httpSec.build();
		}
		
//		@Bean
//		public UserDetailsManager userDetailsManager() {
//			
//			UserDetails user1 = User
//					.withDefaultPasswordEncoder()
//					.username("admin")
//					.password("pass123")
//					.roles("USER")
//					.build();
//			
//			return new InMemoryUserDetailsManager(user1);
//		}
		
		@Bean
		public AuthenticationProvider authenticationProvider() {
			
			DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
			provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
			provider.setUserDetailsService(myUserDetailsService);

			return provider;
		}
	
}
