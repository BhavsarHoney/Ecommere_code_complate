package com.einfo.Project.Ecommerce.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.einfo.Project.Ecommerce.Exception.UserAlaradyExiest;
@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST) 
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String ,String>handelInvalidArugument(MethodArgumentNotValidException ex){
    	Map<String,String>erromap=new HashMap<>();
    	ex.getBindingResult().getFieldErrors().forEach(error->{
    		erromap.put(error.getField(),error.getDefaultMessage());
    	});
    	return erromap;
    }
	
	
	@ResponseStatus(HttpStatus.FOUND) 
    @ExceptionHandler(UserAlaradyExiest.class)
	public Map<String ,String>handelInvalidArugument(UserAlaradyExiest ex){
    	Map<String,String>erroMap=new HashMap<>();
	    erroMap.put("errorMessage",ex.getMessage());
	    return erroMap;
	}
}
