package com.ueat.apirest.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.ueat.apirest.model.ApplicationForm;
public interface ApplicationFormDao  extends PagingAndSortingRepository<ApplicationForm, Long>{

	@Query("select r from ApplicationForm r")
	public Page<ApplicationForm> getAllApplicationForm(Pageable pageable);
}
