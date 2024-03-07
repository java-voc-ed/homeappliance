package com.erp.demo.service.internal;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.demo.model.physical.Dispatchment;
import com.erp.demo.repo.DispatchmentRepo;

@Service
public class DispatchmentMgmt {
	@Autowired
	DispatchmentRepo dispatchmentRepo;
	
	/**
	 * CRUD Operation
	 */
	
	public List<Dispatchment> getAll() {
		return dispatchmentRepo.findAll();
	}
	
	public Optional<Dispatchment> getById(Integer id) {
		return dispatchmentRepo.findById(id);
	}
	
	public Optional<Dispatchment> getByOid(Integer oid) {
		return Optional.ofNullable(dispatchmentRepo.findByOid(oid));
	}

	public Optional<Dispatchment> create(Dispatchment dispatchment) {
		return (!dispatchmentRepo.existsByOid(dispatchment.getOid()))
				? Optional.of(dispatchmentRepo.save(dispatchment))
				: Optional.empty();
	}
	
	public Optional<Dispatchment> update(Dispatchment dispatchment) {
		return  (dispatchmentRepo.existsByOid(dispatchment.getOid()))
				? Optional.of(dispatchmentRepo.save(dispatchment))
				: Optional.empty();
	}
	
	public Optional<Dispatchment> delete(String id) {
		dispatchmentRepo.deleteById(Integer.valueOf(id));
		return dispatchmentRepo.findById(Integer.valueOf(id));		
	}
}
