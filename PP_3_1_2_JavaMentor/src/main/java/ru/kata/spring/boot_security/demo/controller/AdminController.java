package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.*;
import ru.kata.spring.boot_security.demo.service.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public String printAllUsers(ModelMap model, Principal principal) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("activeUser", userService.getSpecificUser(principal.getName()));
        return "users";
    }

    @GetMapping("/{id}")
    public String editeOldUser(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "user";
    }

    @GetMapping("/newUser")
    public String openPageNewUser(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("user", user);
        return "newUser";
    }

    @PostMapping("/users")
    public String createNewUser(@ModelAttribute("newUser") User user, @RequestParam(required = false) String role) {
        user.getRoles().add(new Role(role));
        userService.setUser(user);
        return "redirect:/users";
    }

    @PatchMapping("/{id}")
    public String updateOldUser(@PathVariable(name = "id") int id, @RequestParam(required = false) String role) {
        User user = userService.getUser(id);
        user.getRoles().add(new Role(role));
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteOldUser(@PathVariable(name = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}