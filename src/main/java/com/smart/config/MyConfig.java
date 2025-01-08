package com.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MyConfig   {
	

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImple();
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider (){
	   
            DaoAuthenticationProvider DaoAuthenticationProvider = new DaoAuthenticationProvider();
            DaoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
            DaoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
            
            return DaoAuthenticationProvider;
            
	}        
   
	///configure method...
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
	}
        
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.requestMatchers("/admin/**").hasRole("ADMIN") //admin access
		.requestMatchers("/user/**").hasRole("USER")   //user access
		
		.requestMatchers("/**").permitAll()            //public access
		.and()
		.formLogin().loginPage("/signin")           //Enable form-based login
		 .loginProcessingUrl("/dologin")           
		.defaultSuccessUrl("/user/index",true)                              
	    .and()
	    . csrf().disable();                            //Disable CSRF

		return http.build();
		
		
	}
}
