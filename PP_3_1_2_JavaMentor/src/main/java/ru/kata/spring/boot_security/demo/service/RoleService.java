package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.*;
import java.util.List;
import java.util.Set;

public interface RoleService {

    List<Role> getAllRoles();

    Set getSpecificRoles(Integer[] role_id);

    void setRole(Role role);

    Role getRole(String name);
}
