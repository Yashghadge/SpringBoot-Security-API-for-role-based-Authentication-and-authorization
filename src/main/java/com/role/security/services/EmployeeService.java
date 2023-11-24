package com.role.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.role.security.models.Employee;
import com.role.security.repositories.EmployeeRepository;
import com.role.security.responserwrappers.EmployeeResponseWrapper;

@Service
public class EmployeeService {
    
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public ResponseEntity<?> registerEmployee( Employee employee) {
		String name = employee.getName();
		String username = employee.getUsername();
		String email = employee.getEmail();	
		String password = employee.getPassword();
		String confpassword = employee.getConfpassword();
		
		boolean is_username_exist = employeeRepository.findByUsername(username).isPresent();
		boolean is_email_exist = employeeRepository.findByEmail(email).isPresent();
		if (is_username_exist) {
			throw new ResponseStatusException(HttpStatus.FOUND,username+" already exists");
		} else if(is_email_exist) {
			throw new ResponseStatusException(HttpStatus.FOUND,email+" already exists");
		}else if (password.equals(confpassword)) {
			employee.setPassword(passwordEncoder.encode(password));
			Employee  register_emp =employeeRepository.save(employee);
			EmployeeResponseWrapper erw= new EmployeeResponseWrapper();
			erw.setMessage("Employee Registered Successfully!");
			erw.setObject(register_emp);
			return new ResponseEntity<>(erw,HttpStatus.CREATED);
		}else {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,password+"PASSWORD doesn't match");
		}
	}
}
