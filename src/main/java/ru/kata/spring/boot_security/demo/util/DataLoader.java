package ru.kata.spring.boot_security.demo.util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserService userService;
    private final RoleService roleService;

    public DataLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        roleService.save(adminRole);
        roleService.save(userRole);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        User admin = new User("admin","admin",25,"admin@gmail.com","admin",adminRoles);
        System.out.println(admin.toString());
        User user = new User("user","user",27,"user@gmail.com","user",userRoles);
        userService.save(admin);
        userService.save(user);
    }


}
