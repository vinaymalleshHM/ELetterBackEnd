package com.tyss.eletter.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.eletter.ELetterResponse.ELetterGenericResponse;
import com.tyss.eletter.ELetterResponse.ELetterHRResponse;
import com.tyss.eletter.dto.HRInfoBean;
import com.tyss.eletter.service.ELetterService;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*",allowCredentials = "true")
@RequestMapping("tyss")
public class ELetterRestController {
	
	@Autowired
	private ELetterService service;
	
	@PostMapping(path = "/register",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ELetterGenericResponse register(@Valid @RequestBody HRInfoBean hrInfoBean) {
		ELetterGenericResponse response = new ELetterGenericResponse();
		if (service.register(hrInfoBean)) {
			response.setStatus(201);
			response.setMessage("Succuss");
			response.setDescription("Account Created Succussfully");
		} else {
			response.setStatus(401);
			response.setMessage("Failure");
			response.setDescription("Account Couldn't Created");
		}
		return response;
		
	}

	
	@PostMapping(path = "/auth",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ELetterHRResponse auth(@RequestBody HRInfoBean hrInfoBean) {
		
		HRInfoBean infoBean = service.auth(hrInfoBean.getEmail(), hrInfoBean.getPassword());
		ELetterHRResponse response = new ELetterHRResponse();
		
		if (infoBean != null) {
			response.setStatus(201);
			response.setMessage("Succuss");
			response.setDescription("valid Credential Thanku You For Login");
			response.setHrInfoBeans(Arrays.asList(infoBean));
		} else {
			response.setStatus(401);
			response.setMessage("Failure");
			response.setDescription("Invalid Creadential");
		}
		return response;
		
	}
	
	@PutMapping(path = "/changepassword ",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ELetterGenericResponse changePassword(@RequestBody HRInfoBean hrInfoBean) {
		
		ELetterGenericResponse response = new ELetterGenericResponse();
		
		if (service.changePassword(hrInfoBean.getHId(), hrInfoBean.getPassword())) {
			response.setStatus(201);
			response.setMessage("Succuss");
			response.setDescription("password changed succusfully don't change it again");
		} else {
			response.setStatus(401);
			response.setMessage("Failure");
			response.setDescription("Unable to change password");
		}
		return response;
		
	}
	
	@GetMapping(path = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
	public ELetterHRResponse search(@RequestParam(name="name",required = true)String name) {
		
		List<HRInfoBean> hrInfoBeans = service.search(name);
		for (HRInfoBean hrInfoBean : hrInfoBeans) {
		} 
		ELetterHRResponse response = new ELetterHRResponse();
		
		if (hrInfoBeans!= null && !hrInfoBeans.isEmpty()) {
			response.setStatus(201);
			response.setMessage("Succuss");
			response.setDescription("Data Found");
			response.setHrInfoBeans(hrInfoBeans);
		} else {
			response.setStatus(401);
			response.setMessage("Failure");
			response.setDescription("Couldn't found the data");
		}
		return response;
		
	}
	
	@DeleteMapping(path = "/delete",produces = MediaType.APPLICATION_JSON_VALUE)
	public ELetterGenericResponse deleteHRInfoBean(@RequestParam(name="id",required = true)int id) {
		
		ELetterGenericResponse response = new ELetterGenericResponse();
		
		if (service.deleteHRInfoBean(id)) {
			response.setStatus(201);
			response.setMessage("Succuss");
			response.setDescription("deleted SccussFully");
		} else {
			response.setStatus(401);
			response.setMessage("Failure");
			response.setDescription("Couldn't deleted");
		}
		return response;
		
	}
	
}
