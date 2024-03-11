package com.erp.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * TODO: IMPORTANT! METHODS .anyRequest().permitAll() IS FOR TESTING PURPOSES ONLY!
     * @param httpSecurity
     * @return
     * @throws Exception
     */
	@Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		/**
		 * Note to self:
		 * authorizeHttpRequests() accepts a function with an AuthorizationManagerRequestMatcherRegistry parameter.
		 */
		httpSecurity
			.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/ex/v1/members").hasRole("MEMBER"))
			.formLogin(Customizer.withDefaults())
			.authorizeHttpRequests(
					authorizationManagerRequestMatcherRegistry 
					// TODO: IMPORTANT! METHODS .anyRequest().permitAll() IS FOR TESTING PURPOSES ONLY!
					-> authorizationManagerRequestMatcherRegistry.anyRequest().permitAll()).csrf((csrf) -> csrf.disable());		
		return httpSecurity.build();
	}
	
	@Bean 
	PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}

}
