package com.erp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.CartItem;
import com.erp.demo.model.physical.PostalCode;

public interface PostalCodeRepo extends JpaRepository<PostalCode, Integer> {

}
