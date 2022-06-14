package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.Role;

import ru.kata.spring.boot_security.demo.service.RoleService;


import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
public class RolesRestController {

    private final RoleService roleService;

    public RolesRestController( RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> allRoles(){
        return ResponseEntity.ok()
                .body(roleService.getAllRoles());
    }

}