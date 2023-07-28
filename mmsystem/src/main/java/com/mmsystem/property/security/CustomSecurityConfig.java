package com.mmsystem.property.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Spring boot uses this custom configuration for custom security filter chain,
 * for setting/providing security bean like passwordEncoder, CORS configuration,spring auth managers
 * authorization to requests, etc... 
 *   
 * */

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;
    
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationManager authManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
   @Bean
    public CustomTokenFilter customFilter() throws Exception {
        return new CustomTokenFilter(authManager());
    }
   
   @Bean
   CorsConfigurationSource corsConfigurationSource() {
   	CorsConfiguration configuration = new CorsConfiguration();
   	configuration.setAllowCredentials(true);
   	
   	configuration.addAllowedOrigin("http://localhost:4200/");
   	configuration.addAllowedMethod("*");
   	configuration.addAllowedHeader("*");
   	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
   	source.registerCorsConfiguration("/**", configuration);
   	return source;
   }
   //testing
    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {

    	// Enable CORS and disable CSRF
    	
        http.cors();
        http.csrf().disable();

        // Set session management to stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
     // Set unauthorized requests exception handler
        http.exceptionHandling(
            (exceptions) ->
                exceptions
                    .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                    .accessDeniedHandler(new BearerTokenAccessDeniedHandler()));
       
        http   
        .authorizeHttpRequests(	authorize -> 
        						authorize.requestMatchers("api/auth/signin").authenticated()
        								 .requestMatchers("api/auth/signup").authenticated());
        
        
        http
            .authorizeHttpRequests(	authorize -> 
            						authorize.requestMatchers("api/issue/mmsissue-list").authenticated()
            								 .requestMatchers("api/issue/mmsissue-save").authenticated()
            								 .requestMatchers("api/issue/mmsissue-create").authenticated()
            								 .requestMatchers("api/issue/mmsissue-update").authenticated()
            								 .requestMatchers("api/issue/mmsissue/list/**").authenticated()
            								 .requestMatchers("api/issue/mmsissue-list-jpa").authenticated()
            								 .requestMatchers("api/issue/mmsissue-create-jpa").authenticated()
            								 .requestMatchers("api/issue/mmsissue-list-jpa-paged").authenticated()
            								 .requestMatchers("api/issue/mmsissue/list-paged/**").authenticated()
            								 .requestMatchers("api/staticdata").authenticated());
        http
        	.authorizeHttpRequests(	authorize -> 
								authorize.requestMatchers("api/propmgmt/**").authenticated());
        
        
        http   
        .authorizeHttpRequests(	authorize -> 
        						authorize.requestMatchers("api/task/mmstechtask-list").authenticated()
        								 .requestMatchers("api/task/mmstechtask-create").hasRole("MGMT")
        								 .requestMatchers("api/task/mmstechtask-update").authenticated()
        								 .requestMatchers("api/task/mmstechtask-delete").authenticated());
        
        http   
        .authorizeHttpRequests(	authorize -> 
        						authorize.requestMatchers("api/mmsuser/mmsuser-create").authenticated()
        								 .requestMatchers("api/mmsuser/mmsuser-list").authenticated()
        								 .requestMatchers("api/mmsuser/mmsuser-update").authenticated()
        								 .requestMatchers("api/mmsuser/mmsuser-list-paged").authenticated()
        								 .requestMatchers("api/mmsuser/mmsuser-activation").permitAll());
     
        
        http.authorizeHttpRequests().anyRequest().authenticated();

        

        http.addFilterBefore( customFilter(), UsernamePasswordAuthenticationFilter.class);    // OK ---

        return http.build();
    } 

}
