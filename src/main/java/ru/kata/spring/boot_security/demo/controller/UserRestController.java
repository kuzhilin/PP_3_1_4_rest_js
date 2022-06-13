package ru.kata.spring.boot_security.demo.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public ResponseEntity<List<UserDto>> allUsers() {
        List<UserDto> users = userService.getAllUsers().stream().map(UserDto::fromUser).collect(Collectors.toList());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> readUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(UserDto.fromUser(user), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        User user = userDto.toUser();
        userService.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("UserId", String.valueOf(user.getId()));
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        userService.update(userDto.toUser());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto) {
        userService.delete(userDto.toUser());
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
