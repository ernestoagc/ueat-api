package com.ueat.apirest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ueat.apirest.model.ApplicationForm;
import com.ueat.apirest.service.UeatService;
import com.ueat.apirest.service.impl.UserServiceImpl;
 

@Import({
	UserServiceImpl.class
})

@WebMvcTest(HomeController.class)
class HomeControllerTest { 
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@MockBean
	UeatService ueatService; 
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserDetailsService usuarioServiceImpl;
	 
	
	@Test
	@WithMockUser(username="alonso",roles= {"ADMIN"})
	void testNewApplicationForm() throws Exception {
		
		ObjectMapper objectMapper = getObjectMapper();	
		
		
		ApplicationForm applicationFormDb=	objectMapper.readValue("{\r\n" + 
					"    \"id\": 2,\r\n" + 
					"    \"position\": \"java developer\",\r\n" + 
					"    \"comment\": \"testing\"\r\n" + 
					"}",ApplicationForm.class); 
			
			Mockito.when(ueatService.saveApplicationForm(new ApplicationForm())).thenReturn( applicationFormDb );		
			String uri="/api/home/";
			mockMvc.perform(post(uri) .contentType(APPLICATION_JSON_UTF8).
					content("{\"position\":\"java developer\"}")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username="alonso",roles= {"ADMIN"})
	void testNewApplicationForm_ValidateMandatoryParameters() throws Exception {
		
		ObjectMapper objectMapper = getObjectMapper();	
		
		
		ApplicationForm applicationFormDb=	objectMapper.readValue("{\r\n" + 
					"    \"id\": 2,\r\n" + 
					"    \"position\": \"java developer\",\r\n" + 
					"    \"comment\": \"testing\"\r\n" + 
					"}",ApplicationForm.class); 
			
			Mockito.when(ueatService.saveApplicationForm(new ApplicationForm())).thenReturn( applicationFormDb );		
			String uri="/api/home/";
			mockMvc.perform(post(uri) .contentType(APPLICATION_JSON_UTF8).
					content("{\"comment\":\"testing\"}")).andExpect(status().isBadRequest());
	}

	
	@Test
	@WithMockUser(username="alonso",roles= {"ADMIN"})
	void testSearchingByPosition() throws Exception {		
		String uri="/api/home/page/0"; 
		
		
		
		 Pageable pageable;
		 List<ApplicationForm> applicationFormDummy= new ArrayList<ApplicationForm>();
		 ApplicationForm applicationForm = new ApplicationForm();
		 applicationForm.setComment("testing");
		 applicationForm.setPosition("java developer");
		 applicationForm.setId(1L);
		 
		 
		 
		 applicationFormDummy.add(applicationForm);
		 Page<ApplicationForm> paginaRecomendaciones =  new PageImpl<ApplicationForm>(applicationFormDummy);				
		String positionParameter =""; 
		Mockito.when(ueatService.getAllApplicationFormByPositionPagination(positionParameter,PageRequest.of(1, 15)))
		.thenReturn(paginaRecomendaciones);
		 
		mockMvc.perform(get(uri)).andExpect(status().isOk());
		
		
	}
	
	
	@Test
	@WithMockUser(username="alonso",roles= {"ADMIN"})
	void testGetApplicationForm_WrongId() throws Exception {
		
		Mockito.when(ueatService.findById(1L)).thenReturn(null);		
		String uri="/api/home/5";
		mockMvc.perform(get(uri)).andExpect(status().isNoContent());
	}
	
	@Test
	@WithMockUser(username="alonso",roles= {"ADMIN"})
	void testDeleteApplicationForm_WrongId() throws Exception {
		
		Mockito.when(ueatService.findById(1L)).thenReturn(null);		
		String uri="/api/home/5";
		mockMvc.perform(delete(uri)).andExpect(status().isBadRequest());
	}

	
	protected ObjectMapper getObjectMapper()
	  {

		ObjectMapper objectMapper = new ObjectMapper();
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	    objectMapper.setDateFormat(df);
	    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    objectMapper.setSerializationInclusion(Include.NON_NULL);
	    return objectMapper;
	  }
	
}
