package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers() {
        List<User> users = entityManager.createQuery("from User", User.class).getResultList();
        users.forEach(this::passwordEncryption);
        return users;
    }

    @Override
    public void updateUser(User newUser) {
        Query query = entityManager.createQuery("update User set " +
                "name = :Name, " +
                "surname = :Surname, " +
                "username = :Username, " +
                "password = :Password where id = :Id");
        query.setParameter("Id", newUser.getId());
        query.setParameter("Name", newUser.getName());
        query.setParameter("Surname", newUser.getSurname());
        query.setParameter("Username", newUser.getUsername());
        passwordEncryption(newUser);
        query.setParameter("Password", newUser.getPassword());
        query.executeUpdate();
    }

    @Override
    public User getUser(int id) {
        User user = entityManager.find(User.class, id);
        passwordEncryption(user);
        return user;
    }

    @Override
    public void deleteUser(User user) {
        Query query = entityManager.createQuery("delete from User where id =:userID");
        query.setParameter("userID", user.getId());
        query.executeUpdate();
    }

    @Override
    public void setUser(User user) {
        passwordEncryption(user);
        entityManager.persist(user);
    }

    @Override
    public User getSpecificUsername(String username) {
        return entityManager.createQuery("select user from User user where user.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public User getSpecificUsernameOfUser(String username) {
        User user = entityManager.createQuery(
                "select user from User user where user.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        passwordEncryption(user);
        return user;
    }

    public void passwordEncryption(User user){
        user.setPassword(new BCryptPasswordEncoder(8).encode(user.getPassword()));
    }
}
