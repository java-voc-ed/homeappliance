package com.erp.demo.service.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erp.demo.model.authentication.EmployeeUserDetails;
import com.erp.demo.model.authentication.MemberUserDetails;
import com.erp.demo.model.physical.Employee;
import com.erp.demo.repo.EmployeeRepo;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepo.findByUsername(username);
        if (employee == null) {
            throw new UsernameNotFoundException(username);
        }
		return new EmployeeUserDetails(employee);
	}

}