package com.ecommerce.UserService.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role extends  Base{
    private String roleName;
}
