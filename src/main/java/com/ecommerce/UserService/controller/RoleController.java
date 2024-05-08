package com.ecommerce.UserService.controller;

import com.ecommerce.UserService.DTO.CreateRoleRequest;
import com.ecommerce.UserService.model.Role;
import com.ecommerce.UserService.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    private RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> addRole(@RequestBody CreateRoleRequest createRoleRequest) {
        Role role = roleService.createRole(createRoleRequest);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

}
