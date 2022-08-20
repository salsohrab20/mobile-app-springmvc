package com.salman.srping.pro.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.salman.srping.pro.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	
	UserDto createUser(UserDto userDto);
	UserDto updateUser(String id,UserDto userDto);
	UserDto getUser(String email);
	UserDto getUserbyUserId(String UserId);
	void deleteUser(String UserId);
	List<UserDto> getUsers(int page,int limit);

}
