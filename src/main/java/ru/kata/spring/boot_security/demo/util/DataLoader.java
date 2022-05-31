package ru.kata.spring.boot_security.demo.util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");

        List<Role> roles = new ArrayList<>();
        roles.add(adminRole);
        roles.add(userRole);

        User user = new User("admin","admin@gmail.com");
        user.setPassword("admin");
        user.setRoles(roles);

        userService.save(user);

    }
}
