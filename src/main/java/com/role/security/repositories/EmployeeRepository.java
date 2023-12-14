package com.role.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.role.security.models.Employee;
import com.role.security.projecttion.EmployeeProjection;

@RepositoryRestResource(excerptProjection = EmployeeProjection.class)
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
	@RestResource(exported = false)
	<S extends Employee> S save(S entity);
	
	@RestResource(exported = false)
	Optional<Employee> findByUsername(String username);
	
	@RestResource(exported = false)
	Optional<Employee> findByEmail(String email);
}
