package com.role.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.role.security.models.Employee;
import com.role.security.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerEmployee(@RequestBody @Valid Employee employee) {
	 return	employeeService.registerEmployee(employee);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable int id,@RequestBody @Valid Employee employee){
		return employeeService.updateEmployee(id, employee);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id){
		return employeeService.deleteEmployee(id);
	}

}
