package com.salman.srping.pro.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}
	 @Override
	    protected void doFilterInternal(HttpServletRequest req,
	                                    HttpServletResponse res,
	                                    FilterChain chain) throws IOException, ServletException {
	        
	        String header = req.getHeader(SecurityConstants.HEADER_STRING);    //matches req with "Authorization" in postman	        
	        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) { //checks if header starts with "Bearer"
	            chain.doFilter(req, res);
	            return;
	        }
	        
	        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
	        SecurityContextHolder.getContext().setAuthentication(authentication); 
	        chain.doFilter(req, res);
	    }   
	    
	 //user defined method to get the authentication 
	    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
	        String token = request.getHeader(SecurityConstants.HEADER_STRING); //takes token from header begin with "Bearer"
	        
	        if (token != null) {
	            
	            token = token.replace(SecurityConstants.TOKEN_PREFIX, ""); //Remove "Bearer" from token
	            
	            String user = Jwts.parser()
	                    .setSigningKey( SecurityConstants.getToken())     //parses token 
	                    .parseClaimsJws( token )
	                    .getBody()
	                    .getSubject();
	            
	            if (user != null) {
	                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
	            }
	            
	            return null;
	        }
	        
	        return null;
	    }
	

}
