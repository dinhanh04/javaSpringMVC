package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name) ;
    }
}
