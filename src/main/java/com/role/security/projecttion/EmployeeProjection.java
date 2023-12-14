package com.role.security.projecttion;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.role.security.models.Employee;

@Projection(types = {Employee.class})
public interface EmployeeProjection {
    
	int getId();
	String getName();
	String getUsername();
	String getEmail();
//	List<Employee> getRoles();
}
