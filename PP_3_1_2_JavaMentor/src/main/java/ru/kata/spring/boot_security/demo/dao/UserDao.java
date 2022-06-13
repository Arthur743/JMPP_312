package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.*;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    void updateUser(User user);

    User getUser(int id);

    void deleteUser(User user);

    void setUser(User user);

    User getSpecificUsername(String username);

    User getSpecificUsernameOfUser(String username);
}
