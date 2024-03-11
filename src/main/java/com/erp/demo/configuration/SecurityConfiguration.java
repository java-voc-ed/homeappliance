package com.erp.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * TODO:IMPORTANT! FOR TESTING PURPOSES ONLY!
     * @param http
     * @return
     * @throws Exception
     */
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(
				authorizationManagerRequestMatcherRegistry 
				-> authorizationManagerRequestMatcherRegistry.anyRequest().permitAll())
			.csrf((csrf) -> csrf.disable());
		
		return http.build();
	}

}
