package com.einfo.Project.Ecommerce.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.einfo.Project.Ecommerce.Controller.ProjectController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {
	Logger log = LoggerFactory.getLogger(Interceptor.class);
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
            log.info("invoked prehandeler mathod ...." + request.getRequestURL(),request.getMethod());
		return true;
	}
	public  void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				@Nullable ModelAndView modelAndView) throws Exception {
		log.info("invoked postHandler mathod ...." + request.getRequestURL(),request.getMethod());
		}

	  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
				@Nullable Exception ex) throws Exception {
		  log.info("invoked Aftercompliation  mathod ...." + request.getRequestURL(),request.getMethod());
		}

    
	
}
