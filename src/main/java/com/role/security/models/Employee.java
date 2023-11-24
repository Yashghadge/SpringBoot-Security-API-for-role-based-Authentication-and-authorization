package com.role.security.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String username;
  @Column(nullable = false,unique = true)
  private String email;
  @Column(nullable = false)
  private String password;
  @Transient
  private String confpassword;
  @ElementCollection
  private List<String> roles;
}
