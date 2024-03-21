package com.erp.demo.configuration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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

import com.erp.demo.service.authentication.EmployeeUserDetailsService;
import com.erp.demo.service.authentication.MemberUserDetailsService;

@Configuration
@EnableWebSecurity(debug = true)
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
//	@Bean
//    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//		
//		/**
//		 * Note to self:
//		 * authorizeHttpRequests() accepts a function with an AuthorizationManagerRequestMatcherRegistry parameter.
//		 */
//		httpSecurity
//			.authorizeHttpRequests(authorize 
//					-> authorize.requestMatchers("/api/ex/v1/members").hasRole("MEMBER")).csrf((csrf) -> csrf.disable())
//			.formLogin(Customizer.withDefaults()).csrf((csrf) -> csrf.disable())			
//			.authorizeHttpRequests(
//					authorizationManagerRequestMatcherRegistry 
//					// TODO: IMPORTANT! METHODS .anyRequest().permitAll() IS FOR TESTING PURPOSES ONLY!
//					-> authorizationManagerRequestMatcherRegistry.anyRequest().permitAll()).csrf((csrf) -> csrf.disable());		
//		return httpSecurity.build();		
//		
//	}
	
// TODO:
// TODO:
// TODO:
// TODO:
// TODO:
// how to register authenticationprovider to manager???????????????????????????????????????
// TO READ:
// https://www.baeldung.com/spring-security-authentication-provider
// https://www.devxperiences.com/pzwp1/2023/01/31/spring-boot-security-configuration-practically-explained-part6-a-deep-intro-to-filter-token-based-security/
// https://stackoverflow.com/questions/75298001/how-to-use-multiple-login-pages-one-for-admin-and-the-other-one-for-user
// https://medium.com/@joenjenga/spring-boot-multiple-loginforms-9ed2dff946d
// https://www.codejava.net/frameworks/spring-boot/multiple-login-pages-examples
// https://blog.csdn.net/u012702547/article/details/107530246

	
	@Configuration
	@Order(1)
	public static class ExternalApplicationConfigurationAdapter {
		
		@Autowired
		MemberUserDetailsService memberUserdetailsService;
		@Autowired
		PasswordEncoder passwordEncoder;
		
		AuthenticationManager memberAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
			return authenticationConfiguration.getAuthenticationManager();			
		}
		
	    @Bean
	    DaoAuthenticationProvider memberAuthenticationProvider() {
	    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	    	authenticationProvider.setUserDetailsService(memberUserdetailsService);
	    	authenticationProvider.setPasswordEncoder(passwordEncoder);
	    	return authenticationProvider;
	    }
	    
		@Bean
		SecurityFilterChain ExternalApplicationfilterChain(
				HttpSecurity httpSecurity, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {
			
			httpSecurity.csrf((csrf) -> csrf.disable())
				.securityMatcher("/api/ex/**", "/login", "logout")
				.authorizeHttpRequests(registry 
						-> registry.requestMatchers("/api/ex/**").hasRole("MEMBER"))
				.formLogin(Customizer.withDefaults())
				.authenticationProvider(memberAuthenticationProvider());
			return httpSecurity.build();
		}
		
	}
	
//	@Configuration
//	@Order(2)
//	public static class InternalApplicationConfigurationAdapter {
//		
//		@Autowired
//		EmployeeUserDetailsService employeeUserDetailsService;
//		@Autowired
//		PasswordEncoder passwordEncoder;
//	    
//	    @Bean
//	    DaoAuthenticationProvider employeeAuthenticationProvider() {
//	    	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//	    	authenticationProvider.setUserDetailsService(employeeUserDetailsService);
//	    	authenticationProvider.setPasswordEncoder(passwordEncoder);;
//	    	return authenticationProvider;
//	    	
//	    }
//	    
//		@Bean
//		SecurityFilterChain InternalApplicationfilterChain(
//				HttpSecurity httpSecurity, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {
//
//			httpSecurity.csrf((csrf) -> csrf.disable())
//				.securityMatcher("/api/in/**", "/admin/**")
//				.authorizeHttpRequests(registry 
//						-> registry.requestMatchers("/api/in/**").hasAnyRole("STAFF", "ADMIN"))
//				.formLogin(configurer 
//						-> configurer
//							.loginPage("/admin/login.html")
//							.loginProcessingUrl("/admin/login")
//							.defaultSuccessUrl("/admin/index.html")
//							.failureForwardUrl("/admin/login.html?error")
//							.permitAll())
//				.authenticationProvider(employeeAuthenticationProvider());
//			return httpSecurity.build();
//			
//		}
//	
//	}
	
//	@Configuration
//	@Order(3)
//	public static class GlobalApplicationConfigurationAdapter {
//		
//		@Bean
//		SecurityFilterChain GlobalApplicationfilterChain(
//				HttpSecurity httpSecurity, HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {
//			httpSecurity
//			.csrf((csrf) -> csrf.disable())
//			.authorizeHttpRequests(
//					registry 
//					// TODO: IMPORTANT! METHODS .anyRequest().permitAll() IS FOR TESTING PURPOSES ONLY!
//					-> registry
//					.requestMatchers("/**").permitAll()
//					.anyRequest().authenticated());
//			return httpSecurity.build();
//		}
//	}
	
	@Bean 
	PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}

}
