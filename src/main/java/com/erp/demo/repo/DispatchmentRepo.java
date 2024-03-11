package com.erp.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.erp.demo.model.physical.Dispatchment;

public interface DispatchmentRepo extends JpaRepository<Dispatchment, Integer>{
	
	public List<Dispatchment> findAllByOid(Integer oid);
	public Boolean existsByOid(Integer oid);

}
