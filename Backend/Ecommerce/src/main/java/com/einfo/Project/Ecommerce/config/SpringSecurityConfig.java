package com.einfo.Project.Ecommerce.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.einfo.Project.Ecommerce.filter.Jwtfilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig   {
	
	  @Autowired
	    private Jwtfilter authFilter;
	 @Bean
	 public UserDetailsService userDetailsService() {
		 return new  UserUserDetailsService();
	    
	 }
	  
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	  @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        return http.cors().and().csrf().disable()
	                .authorizeHttpRequests()
	                .requestMatchers("/signup","/authenticate","image/**","/checkseller,/deleteuser","/createorder").permitAll()
//	                .requestMatchers("/addcategory","image/add").hasRole("ADMIN")
	                .and()
	                .authorizeHttpRequests().anyRequest()
	                .authenticated()
	                .and()
	                .sessionManagement()
	                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	                .and()
	                .authenticationProvider(authenticationProvider())
	                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
	                .build();
	                
	    }


	    @Bean
	    public AuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService());
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        authenticationProvider.setAuthoritiesMapper(authoritiesMapper());
	        return authenticationProvider;
	    }
	    @Bean
	    public GrantedAuthoritiesMapper authoritiesMapper() {
	        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
	        authorityMapper.setPrefix("ROLE_"); // Optional: Add a prefix to role names
	        authorityMapper.setConvertToUpperCase(true); // Optional: Convert role names to uppercase
	        return authorityMapper;
	    }

		@Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
//	    @Bean
//	    public CorsConfigurationSource corsConfigurationSource() {
//	        CorsConfiguration configuration = new CorsConfiguration();
//	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
//	        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
//
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", configuration);
//
//	        return source;
//	    }
}
