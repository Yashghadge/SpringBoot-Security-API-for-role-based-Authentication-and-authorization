package com.role.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.role.security.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
