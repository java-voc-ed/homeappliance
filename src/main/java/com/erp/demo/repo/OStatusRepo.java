package com.erp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.CartItem;
import com.erp.demo.model.physical.OStatus;

public interface OStatusRepo extends JpaRepository<OStatus, Integer> {

}
