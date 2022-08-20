package com.salman.srping.pro.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

//to read tokenSecret from the properties file
@Component
public class AppProperties {
	
	@Autowired
	public Environment env;  //way to read data from properties files
	
	                                                                       
	
	public String getToken()
	{
		return env.getProperty("tokenSecret");
	}
	                                                                                                     
}
