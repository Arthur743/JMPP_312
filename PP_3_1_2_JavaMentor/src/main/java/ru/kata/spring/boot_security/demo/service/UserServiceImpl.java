package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.*;
import ru.kata.spring.boot_security.demo.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final RoleService roleService;

    public UserServiceImpl(UserDao userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(getUser(id));
    }

    @Override
    @Transactional
    public void setUser(User user) {
        userDao.setUser(user);
    }

    @Override
    @Transactional
    public UserDetails getSpecificUsername(String username) throws UsernameNotFoundException {
        return userDao.getSpecificUsername(username);
    }

    @Override
    @Transactional
    public User getSpecificUser(String username) throws UsernameNotFoundException {
        return userDao.getSpecificUsernameOfUser(username);
    }

    @Override
    @Transactional
    public void setUserTest(User user) {
        userDao.setUser(user);
    }
}
