package com.erp.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.demo.model.physical.EmRole;
import com.erp.demo.model.physical.Product;

public interface EmRoleRepo extends JpaRepository<EmRole, String>{

}
