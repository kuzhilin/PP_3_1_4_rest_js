package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/index";
    }

    @GetMapping("/new")
    public String newUser(Model model,@ModelAttribute("user") User user) {
       model.addAttribute("roles",roleService.getAllRoles());
       return "admin/new";
    }

    @GetMapping("/edit")
    public String newUser(Model model, @RequestParam("id") long id) {

        model.addAttribute("roles",roleService.getAllRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping
    public String delete(@RequestParam("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
