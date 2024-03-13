package com.erp.demo.configuration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	/**
	 * TODO: DISABLE FOR LIVE VERSIONS; FOR TESTING PURPOSES ONLY!
	 * @return
	 */
	@Bean
	CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    // Don't do this in production, use a proper list  of allowed origins
	    config.setAllowedOrigins(Collections.singletonList("*"));
	    config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
	    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
	
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
			.authorizeHttpRequests(authorize 
					-> authorize.requestMatchers("/api/ex/v1/members").hasRole("MEMBER")).csrf((csrf) -> csrf.disable())
			.formLogin(Customizer.withDefaults()).csrf((csrf) -> csrf.disable())			
			.authorizeHttpRequests(
					authorizationManagerRequestMatcherRegistry 
					// TODO: IMPORTANT! METHODS .anyRequest().permitAll() IS FOR TESTING PURPOSES ONLY!
					-> authorizationManagerRequestMatcherRegistry.anyRequest().permitAll()).csrf((csrf) -> csrf.disable());		
		return httpSecurity.build();		
		
	}
	
//	@Configuration
//	@Order(1)
	public static class ExternalApplicationConfigurationAdapter {
		
		@Bean
		SecurityFilterChain ExternalApplicationfilterChain(
				HttpSecurity httpSecurity, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {
			
			httpSecurity.csrf((csrf) -> csrf.disable())
				.securityMatcher("/api/ex/**")
				.authorizeHttpRequests(registry -> registry.requestMatchers("api/ex/**").hasRole("MEMBER"))
				.formLogin(Customizer.withDefaults())
				.authorizeHttpRequests(registry -> registry.requestMatchers("/api/ex/*/members/register").permitAll());
			return httpSecurity.build();
		}
		
	}
	
//	@Configuration
//	@Order(2)
	public static class InternalApplicationConfigurationAdapter {
		
		@Bean
		SecurityFilterChain ExternalApplicationfilterChain(
				HttpSecurity httpSecurity, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {

		httpSecurity.csrf((csrf) -> csrf.disable())
			.securityMatcher("/api/in/**")
			.authorizeHttpRequests(registry -> registry.requestMatchers("api/in/**").hasAnyRole("STAFF", "ADMIN"))
			.formLogin(configurer 
					-> configurer						
						.loginPage("/admin/login.html")
						.loginProcessingUrl("/admin/login")
						.defaultSuccessUrl("/admin/index.html"))
			.authorizeHttpRequests(registry -> registry.requestMatchers("/api/in/*/members/register").permitAll());
		return httpSecurity.build();
		}
	
	}
	
//	@Configuration
//	@Order(3)
	public static class GlobalApplicationConfigurationAdapter {
		
		@Bean
		SecurityFilterChain ExternalApplicationfilterChain(
				HttpSecurity httpSecurity, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {
			httpSecurity
			.csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests(
					registry 
					// TODO: IMPORTANT! METHODS .anyRequest().permitAll() IS FOR TESTING PURPOSES ONLY!
					-> registry.anyRequest().permitAll());
			return httpSecurity.build();
		}
	}
	
	@Bean 
	PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}

}
