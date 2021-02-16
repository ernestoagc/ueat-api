package com.ueat.apirest.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.ueat.apirest.model.Role;

public interface RoleDao  extends PagingAndSortingRepository<Role, Long>{

}
