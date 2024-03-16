package com.erp.demo.service.external;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.model.physical.PostalCode;
import com.erp.demo.repo.PostalCodeRepo;

@Service
public class EnumSvc {

	@Autowired
	private PostalCodeRepo postalCodeRepo;

	public List<PostalCode> getAllPostalCodes() {
		return postalCodeRepo.findAll();
	}

	public Optional<PostalCode> getPostalCodeById(Integer id) {
		return postalCodeRepo.findById(id);
	}

	public Optional<PostalCode> createPostalCode(PostalCode postalCode) {
		return (!postalCodeRepo.existsById(postalCode.getPostalCode()))
				? Optional.of(postalCodeRepo.save(postalCode))
				: Optional.empty();
	}

	public Optional<PostalCode> updatePostalCode(PostalCode postalCode) {
		return  (postalCodeRepo.existsById(postalCode.getPostalCode()))
				? Optional.of(postalCodeRepo.save(postalCode))
				: Optional.empty();
	}
	
	public Optional<PostalCode> deletePostalCode(Integer id) {
		postalCodeRepo.deleteById(id);
		return postalCodeRepo.findById(id);		
	}
	
}
