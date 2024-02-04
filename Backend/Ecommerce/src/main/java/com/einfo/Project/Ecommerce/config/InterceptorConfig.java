package com.einfo.Project.Ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.einfo.Project.Ecommerce.Interceptor.Interceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Autowired
   private Interceptor intre;
  
   public void addInterceptors(InterceptorRegistry registry) {
	   registry.addInterceptor(intre);
	}
  
  
}
