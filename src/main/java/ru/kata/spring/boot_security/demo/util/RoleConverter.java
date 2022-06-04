package ru.kata.spring.boot_security.demo.util;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.RoleService;
import java.util.Map;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RoleConverter implements Converter<String, Role> {

    private final RoleService roleService;
    private Map<String,Role> roles;

    public RoleConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role convert(String id) {
        if(roles == null){
            roles = roleService.getAllRoles().stream().collect(Collectors.toMap(el-> String.valueOf(el.getId()), Function.identity()));
        }
        return roles.get(id);
    }


}
