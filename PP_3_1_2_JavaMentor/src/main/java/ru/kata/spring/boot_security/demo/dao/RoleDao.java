package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;
import java.util.Set;

public interface RoleDao {
    public List<Role> getRoles();
    Set<Role> getSpecificRoles(Integer[] role_id);
    public void saveRole(Role role);
    Role getRole(String name);
}
