package com.erp.demo.controller.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.demo.model.physical.PostalCode;
import com.erp.demo.service.internal.EnumMgmt;

@RestController
@RequestMapping(value = "/api/in/v1/enum")
public class EnumMgmtController {

	@Autowired
	private EnumMgmt enumMgmt;
	
	@GetMapping(value = "/postal-code")
	public ResponseEntity<List<PostalCode>> getPostalCode () {
		return ResponseEntity.ok(enumMgmt.getAllPostalCodes());
	}
	
}
