package com.role.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.role.security.services.EmployeeLoginService;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {
    
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService()
	{
	  UserDetails user1=User.builder().username("harsh").password(passwordEncoder().encode("harsh@123")).roles("HR").build();
	  UserDetails user2=User.builder().username("sanskar").password(passwordEncoder().encode("sanskar@123")).roles("MANAGER").build();
      InMemoryUserDetailsManager manager =new InMemoryUserDetailsManager(user1,user2);
      return manager;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(http_request -> http_request
				.dispatcherTypeMatchers(DispatcherType.FORWARD,DispatcherType.ERROR).permitAll()
               .requestMatchers(HttpMethod.POST, "/employees/register").hasAnyAuthority("HR","MANAGER")
               .requestMatchers(HttpMethod.PUT, "/employees/update/{id}").hasAnyAuthority("HR","MANAGER")
               .requestMatchers(HttpMethod.GET, "/employees").hasAnyAuthority("HR","MANAGER")
               .requestMatchers(HttpMethod.GET, "/employees/**").hasAnyAuthority("HR","MANAGER")
              
               .requestMatchers(HttpMethod.DELETE, "/employees/{id}").hasAnyAuthority("HR","MANAGER")
               .requestMatchers(HttpMethod.GET, "/projects").permitAll()
               .requestMatchers(HttpMethod.GET, "/projects/{id}").permitAll()
               .requestMatchers(HttpMethod.POST, "/projects").hasAuthority("MANAGER")
               .requestMatchers(HttpMethod.PUT, "/projects/{id}").hasAuthority("MANAGER")
               .requestMatchers(HttpMethod.PATCH, "/projects").hasAuthority("MANAGER")
               .requestMatchers(HttpMethod.DELETE, "/projects/{id}").hasAuthority("MANAGER")
             .anyRequest().authenticated()).authenticationProvider(daoAuthenticationProvider())
		.httpBasic(Customizer.withDefaults())
		;
		http.csrf(csrf->csrf.disable());
		return http.build();
	}
	
	@Autowired
	EmployeeLoginService employeeLoginService;
    public DaoAuthenticationProvider daoAuthenticationProvider() {
	 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	 provider.setUserDetailsService(employeeLoginService);
	 provider.setPasswordEncoder(passwordEncoder());
	 return provider;
    }
}
