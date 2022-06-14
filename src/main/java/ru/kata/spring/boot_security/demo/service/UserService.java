package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.dto.UserDto;

import java.util.List;

public interface UserService extends UserDetailsService {
    Long save(UserDto userDto);

    void update(UserDto userDto);

    void delete(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();
}
