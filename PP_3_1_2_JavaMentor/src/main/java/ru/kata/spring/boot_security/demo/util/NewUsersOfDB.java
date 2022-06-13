package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class NewUsersOfDB {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public NewUsersOfDB(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createUsersWithRoles() {

        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");

        Set<Role> adminRole = new HashSet<>();
        adminRole.add(role1);
        adminRole.add(role2);

        Set<Role> userRole = new HashSet<>();
        userRole.add(role2);

        User admin = new User("Faina", "Amandosova", "1","11", adminRole);
        User user = new User("Diana", "Aktuganova", "2", "22", userRole);

        try {
            Role checkRole = roleService.getRole(role1.getName());
        } catch (Exception e) {
            roleService.setRole(role1);
        }
        try {
            Role checkRole = roleService.getRole(role2.getName());
        } catch (Exception e) {
            roleService.setRole(role2);
        }
        try {
            userService.setUser(admin);
            userService.setUser(user);
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }


    }
}
