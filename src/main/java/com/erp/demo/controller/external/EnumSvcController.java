package com.erp.demo.controller.external;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.demo.model.physical.PostalCode;
import com.erp.demo.service.external.EnumSvc;

@RestController
@RequestMapping(value = "/api/ex/v1/enum")
public class EnumSvcController {

	@Autowired
	private EnumSvc enumSvc;
	
	@GetMapping(value = "/postal-code")
	public ResponseEntity<List<PostalCode>> getPostalCode () {
		return ResponseEntity.ok(enumSvc.getAllPostalCodes());
	}
	
}
