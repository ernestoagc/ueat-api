package com.ueat.apirest.service.impl;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ueat.apirest.dao.UserDao;
import com.ueat.apirest.model.*;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService {

	@Autowired 
	UserDao userDao;

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.ueat.apirest.model.User user=null;
		
		user= userDao.findByEmail(username);
		if(user==null) {
			//logger.error("User does not exits " + usuario.getCodigo());
			throw new UsernameNotFoundException("User does not exits " + user.getEmail());
		}
		
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getCode()))
				.peek(aut ->logger.info("Rol: " + aut.getAuthority()))
				.collect(Collectors.toList());
		
		logger.info("usuario autenticado: " + username);
		
		return new User(user.getEmail(), user.getPassword(), true, 
				true, true, true, authorities);
	}
	
	

}
