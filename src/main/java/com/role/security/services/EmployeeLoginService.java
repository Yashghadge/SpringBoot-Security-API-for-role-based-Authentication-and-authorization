package com.role.security.services;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.role.security.models.Employee;
import com.role.security.models.EmployeeLogin;
import com.role.security.repositories.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeLoginService implements UserDetailsService {
   
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Employee userFound= employeeRepository.findByUsername(username).orElseThrow(
				   ()->{
					   throw new UsernameNotFoundException(username+" doesn't exist");
				   }
				   );
		   String userName= userFound.getUsername();
		   String password = userFound.getPassword();
		   List<String> roles = userFound.getRoles();
		   
		   Collection<GrantedAuthority> authorities = new ArrayList<>();
		   for( String role:roles) {
			  authorities.add(new SimpleGrantedAuthority(role));
		   }
		   
		  EmployeeLogin employee = new EmployeeLogin(userName, password, authorities) ;
		  return  employee;
	}
	
	
}
