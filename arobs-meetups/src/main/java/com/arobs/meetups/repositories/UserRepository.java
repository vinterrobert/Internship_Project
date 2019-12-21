package com.arobs.meetups.repositories;

import com.arobs.meetups.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    public User findById(int userId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, userId);
    }

    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from User u where u.email = :email")
                .setParameter("email", email);
        User foundUser = (User)hql.getSingleResult();
        return foundUser;
    }

    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from User");
        return hql.getResultList();

    }

    public void create(User newUser) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newUser);
    }

    public void update(User updatedUser) {
        Session session = sessionFactory.getCurrentSession();
        session.update(updatedUser);
    }

    public void delete(User userToDelete) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(userToDelete);
    }
}
