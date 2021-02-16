package com.ueat.apirest.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ueat.apirest.model.ApplicationForm;
import com.ueat.apirest.service.UeatService;
 

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="api/home")
public class HomeController {
	

	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	UeatService ueatService;
	
	@PostMapping("/")
	public ResponseEntity<?> saveApplicationForm(@RequestBody ApplicationForm applicationForm){
		
		logger.info("===NEW APPLICATION FORM==== BEGIN ====");	   		
		
		ApplicationForm newApplicationForm = ueatService.saveApplicationForm(applicationForm);
		logger.info("===NEW APPLICATION FORM==== END ====");
		return ResponseEntity.status(HttpStatus.OK).body(newApplicationForm);
	}
	
	@GetMapping("/findall")	
	@ResponseBody
	public ResponseEntity<?> findall(@RequestParam Map<String,String> parametros) throws FileNotFoundException  {

		logger.info("===LIST APPLICATION FORM==== BEGIN ====");   		
		List<ApplicationForm> applicationFormList =ueatService.getAllApplicationForm();
		
		
		logger.info("===LIST APPLICATION FORM==== END ====");
		return ResponseEntity.status(HttpStatus.OK).body(applicationFormList);
	}
	
	@GetMapping("/page/{page}")	
	@ResponseBody
	public ResponseEntity<?> findAllPage(@PathVariable int page,@RequestParam Map<String,String> parametros) throws FileNotFoundException  {

		logger.info("===LIST PAGE APPLICATION FORM==== BEGIN ====");    		
		List<ApplicationForm> applicationFormList =ueatService.getAllApplicationForm();
		

		Page<ApplicationForm> listPaginable= ueatService.getAllApplicationFormPagination( PageRequest.of(page, 15));
		logger.info("===LIST PAGE APPLICATION FORM==== END ====");   
		return ResponseEntity.status(HttpStatus.OK).body(listPaginable);
		


	}

}
