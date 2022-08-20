package com.salman.srping.pro;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.salman.srping.pro.io.entity.UserEntity;
import com.salman.srping.pro.shared.dto.UserDto;

//takes in data from user entity and persist data into the db
//CrudRepository contains inbuilt repos of the crud functionalities
//PagingAndSortingRepository halps us attain pagination . contains CrudRepository within it 

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity,Long> //<Class,dtype of id>
{
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);  

}
