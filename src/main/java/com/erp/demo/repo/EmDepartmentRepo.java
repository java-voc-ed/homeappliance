package com.erp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.CartItem;
import com.erp.demo.model.physical.EmDepartment;

public interface EmDepartmentRepo extends JpaRepository<EmDepartment, String> {

}
