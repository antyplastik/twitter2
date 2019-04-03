package sda.twitter2.services;

import sda.twitter2.models.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public enum UserService {
    INSTANCE;

    private EntityManager em;

    private UserService() {
        em = Persistence.createEntityManagerFactory("persistence").createEntityManager();
    }

    public void registerUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public User findUser(int id) {
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.getTransaction().commit();
        return user;
    }
}


