package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public ResponseEntity<List<UserDto>> allUsers() {
        return ResponseEntity.ok()
                .body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> readUser(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(userService.getUserById(id));

    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        Long id = userService.save(userDto);
        return ResponseEntity.ok()
                .header("UserID", String.valueOf(id))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return ResponseEntity.ok()
                .build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto) {
        userService.delete(userDto);
        return ResponseEntity.ok()
                .build();
    }


}
