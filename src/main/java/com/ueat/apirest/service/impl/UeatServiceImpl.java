package com.ueat.apirest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ueat.apirest.dao.ApplicationFormDao;
import com.ueat.apirest.model.ApplicationForm;
import com.ueat.apirest.service.UeatService;
import org.springframework.data.domain.Pageable;


@Service
public class UeatServiceImpl implements UeatService {

	@Autowired
	ApplicationFormDao applicationFormDao;
	
	@Override
	public ApplicationForm saveApplicationForm(ApplicationForm applicationForm) {
		// TODO Auto-generated method stub
		
		ApplicationForm applicationFormNew = applicationFormDao.save(applicationForm);
		return applicationFormNew;
	}

	@Override
	public List<ApplicationForm> getAllApplicationForm() {
		// TODO Auto-generated method stub
		return ((List<ApplicationForm>)applicationFormDao.findAll());
	}


	@Override
	public Page<ApplicationForm> getAllApplicationFormPagination(Pageable pageable) {
		// TODO Auto-generated method stub
		return  applicationFormDao.findAll(pageable);
	}
	
	@Override
	public Page<ApplicationForm> getAllApplicationFormByPositionPagination(String  position,Pageable pageable) {
		// TODO Auto-generated method stub
		return  applicationFormDao.getAllApplicationFormByPosition(position,pageable);
	}
	
	
	@Override
	public ApplicationForm findById(long id) {
		return  applicationFormDao.findById(id).orElse(null);
	}
	
	
	
	@Override
	public boolean deleteApplicationForm(Long id) {
		
		try {
			applicationFormDao.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	

}
