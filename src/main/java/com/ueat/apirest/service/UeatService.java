package com.ueat.apirest.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ueat.apirest.model.ApplicationForm;

public interface UeatService {

	public ApplicationForm saveApplicationForm(ApplicationForm applicationForm);
	
	public List<ApplicationForm> getAllApplicationForm();
	
	public Page<ApplicationForm> getAllApplicationFormPagination(Pageable pageable);
}
