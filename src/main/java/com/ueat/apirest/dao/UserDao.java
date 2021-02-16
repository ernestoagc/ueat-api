package com.ueat.apirest.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.ueat.apirest.model.User;

public interface UserDao extends PagingAndSortingRepository<User, Long>{

	
	public User findByEmail(String email);
	
}
