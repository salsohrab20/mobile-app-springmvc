package com.salman.srping.pro.security;

import com.salman.srping.pro.SpringApplicationContext;

//Post login authentication the header token given to the users 

public class SecurityConstants {
	public static final long EXPIRATION_TIME = 864000000;  //in millsecs
	public static final String TOKEN_PREFIX = "Bearer";  //http post request will have authorization header foloowed by Bearer
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
	//public static final String TOKEN_SECRET = "jf9i4jgu83nfl0";
	public static String getToken()
	{
		AppProperties appProp = (AppProperties)SpringApplicationContext.getBean("AppProperties");
		return appProp.getToken();
	}
}
