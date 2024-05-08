package com.ecommerce.UserService.service;

import com.ecommerce.UserService.DTO.CreateRoleRequest;
import com.ecommerce.UserService.Repository.RoleRepository;
import com.ecommerce.UserService.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }



    public Role createRole(CreateRoleRequest createRoleRequest) {
        Role role = new Role();
        role.setRoleName(createRoleRequest.getRoleName());
        return roleRepository.save(role);
    }

}
