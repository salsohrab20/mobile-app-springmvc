package com.salman.srping.pro.security;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salman.srping.pro.SpringApplicationContext;
import com.salman.srping.pro.service.UserService;
import com.salman.srping.pro.shared.dto.UserDto;
import com.salman.srping.pro.ui.model.request.UserLoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//UsernamePasswordAuthenticationFilter class provided by Spring package

//authenticate while login
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter 
{
	private final AuthenticationManager authenticationManager;
	
	private String contentType;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager)
	{
		this.authenticationManager=authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException{
		try
		{
			
        	
            UserLoginRequestModel creds = new ObjectMapper()
                    .readValue(req.getInputStream(), UserLoginRequestModel.class);
            
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					creds.getEmail(),
					creds.getPassword(),
					new ArrayList<>() ));
		
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		
	}
	
	@Override //if email pass correct then called or else not called
	protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res,
			FilterChain chain,
			Authentication auth) throws IOException,ServletException
	{
		String userName = ((User)auth.getPrincipal()).getUsername();
		
		 String token = Jwts.builder()
	                .setSubject(userName)
	                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
	                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getToken())
	                .compact();
	        UserService userService = (UserService)SpringApplicationContext.getBean("userServiceImpl");
	        UserDto userDto = userService.getUser(userName);
	        
	        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
	        res.addHeader("UserID", userDto.getUserId());
	}
	
}
