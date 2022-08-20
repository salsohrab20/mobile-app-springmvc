package com.salman.srping.pro.ui.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.salman.srping.pro.exceptions.UserServiceException;
import com.salman.srping.pro.service.UserService;
import com.salman.srping.pro.shared.dto.UserDto;
import com.salman.srping.pro.ui.model.request.UserDetailsRequestModel;
import com.salman.srping.pro.ui.model.response.ErrorMessages;
import com.salman.srping.pro.ui.model.response.OperationStatusModel;
import com.salman.srping.pro.ui.model.response.RequestOperationName;
import com.salman.srping.pro.ui.model.response.RequestOperationStatus;
import com.salman.srping.pro.ui.model.response.UserRest;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("users")  //http:localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService; //data returned in postman is bydefault json .MediaType used to change it to XMl bydefault  .order of mediatype matters
	@GetMapping(path="/{id}", 
			produces= {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})   //path: /users/id    .binds to http get req 
	public UserRest getUser(@PathVariable String id)   //Pathvariable helps read the incoming input from id
	{
		UserRest returnValue= new UserRest();
		UserDto userDto = userService.getUserbyUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}
	
	//take input =consumes
	//give output = produces
	@PostMapping(consumes= {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE},
	produces= {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})  //binds to http post req
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails)
	{
		UserRest returnValue= new UserRest();  //data to be returned 
		if(userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		UserDto userdto=new UserDto();  
		
		//BeanUtils is a predefined class in Spring framework
		BeanUtils.copyProperties(userDetails, userdto);  //populating req body request into userdto
		
		UserDto createdUser=userService.createUser(userdto);  //service cls perform some additional logics
		BeanUtils.copyProperties(createdUser, returnValue);
		return returnValue;
	}
	@PutMapping(path="/{id}",consumes= {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})     //binds to http put req
	public UserRest updateUser(@PathVariable String id,@RequestBody UserDetailsRequestModel userDetails)
	{
		UserRest returnValue= new UserRest();  //data to be returned 
		if(userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		UserDto userdto=new UserDto();  
		
		//BeanUtils is a predefined class in Spring framework
		BeanUtils.copyProperties(userDetails, userdto);  //populating req body request into userdto
		
		UserDto createdUser=userService.updateUser(id,userdto);  //service cls perform some additional logics
		BeanUtils.copyProperties(createdUser, returnValue);
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}",
			produces= {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})  
	//binds to http delete req
	public OperationStatusModel deleteUser(@PathVariable String id)
	{
		OperationStatusModel returnValue= new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		userService.deleteUser(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		return returnValue;
	}
	
	
	@GetMapping(produces= {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE})
	public List<UserRest> getUsers(@RequestParam(value ="page" , defaultValue ="0") int page,
			@RequestParam(value ="limit" , defaultValue ="50") int limit)  // makes up query ..../users?page=1&limit=50
	{
		List<UserRest> returnValue = new ArrayList<>();
		
		List<UserDto> userdto = userService.getUsers(page,limit);
		for (UserDto udto : userdto)
		{
			UserRest userRest=new UserRest();
			BeanUtils.copyProperties(udto, userRest);
			returnValue.add(userRest);
		}
		return returnValue;
		
	}

}
