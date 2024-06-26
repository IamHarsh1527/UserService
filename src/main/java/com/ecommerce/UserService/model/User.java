package com.ecommerce.UserService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User extends  Base{
private String email;
private String password;

@ManyToMany(fetch = FetchType.EAGER)
@JsonIgnore
private Set<Role> roles=new HashSet<>();
}
