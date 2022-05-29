package ru.kata.spring.boot_security.demo.util;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;

@Component
public class UserSecurity {
    public boolean hasId(Authentication authentication, long id){
        User user = (User) authentication.getPrincipal();
        return user.getId() == id;
    }

}
