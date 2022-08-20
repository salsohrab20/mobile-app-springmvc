package com.salman.srping.pro.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.salman.srping.pro.UserRepository;
import com.salman.srping.pro.io.entity.UserEntity;
import com.salman.srping.pro.service.UserService;
import com.salman.srping.pro.shared.Utils;
import com.salman.srping.pro.shared.dto.UserDto;
import com.salman.srping.pro.ui.model.response.ErrorMessages;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository; 
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		UserEntity storedEmailEntity=userRepository.findByEmail(userDto.getEmail());
		if(storedEmailEntity!=null) throw new RuntimeException("Record already exists");
		
		UserEntity userEntity=new UserEntity();
		BeanUtils.copyProperties(userDto, userEntity);
		
		String publicUserId=utils.generateRandomString(30);
		userEntity.setUserId(publicUserId);
		//userEntity.setEncryptedPassword("1234");
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword())); //encrypts password before storing in db
		UserEntity savedetails=userRepository.save(userEntity);
		UserDto returnValue=new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}
	
	@Override
	public UserDto getUser(String email) // to get the user id in authnticationfilter
	{
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity==null) throw new UsernameNotFoundException(email);
		UserDto returnValue=new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override  //for  login
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity userEntity=userRepository.findByEmail(email);
		if(userEntity==null) throw new UsernameNotFoundException(email);  //exception predefined in Spring
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
		  //predefined provided by Spring core
	}

	@Override
	public UserDto getUserbyUserId(String id) {
		// TODO Auto-generated method stub
		UserDto returnValue=new UserDto();
		UserEntity userEntity=userRepository.findByUserId(id);
		if(userEntity==null) throw new UsernameNotFoundException("User not found with " + id); 
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDto updateUser(String id,UserDto userDto) {
		UserDto returnValue=new UserDto();
		UserEntity userEntity=userRepository.findByUserId(id);
		if(userEntity==null) throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userEntity.setFirstName(userDto.getFirstName());//updates firstname
		userEntity.setLastName(userDto.getLastName()); //updates lastname
		UserEntity updatedName = userRepository.save(userEntity); //saves updated name in the db
		BeanUtils.copyProperties(updatedName, returnValue);
		return returnValue;
	}

	@Override
	public void deleteUser(String UserId) {
		// TODO Auto-generated method stub
		UserEntity userEntity=userRepository.findByUserId(UserId);
		if(userEntity==null) throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		userRepository.delete(userEntity);
		
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		// TODO Auto-generated method stub
		
		List<UserDto> returnValue= new ArrayList<>();
		Pageable pageableRequest = new PageRequest(page,limit); //helps attain pagenation
		Page<UserEntity> userEntity=userRepository.findAll(pageableRequest);  //finds the record that much required
		List<UserEntity> out=userEntity.getContent(); //converts page into userEntity
		for(UserEntity uentity : out)
		{
			UserDto udto=new UserDto();
			BeanUtils.copyProperties(uentity, udto);
			returnValue.add(udto);
			
		}
		return returnValue;
	}

}
