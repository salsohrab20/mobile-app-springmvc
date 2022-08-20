package com.salman.srping.pro.shared;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

//utility class that will contain helpful functions that our project needs
@Component
public class Utils {
	
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789QWERTYUIOPASDFGHJKLZCXVBNMqwertyuioplkjhgfdsamnbvvxzxc";
	
	private String generateUserId(int length)
	{
		return generateRandomString(length);
	}
	
	
	public String generateRandomString(int length)
	{
		StringBuilder returnValue=new StringBuilder(length);
		for(int i=0;i<length;i++)
		{
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}

}
