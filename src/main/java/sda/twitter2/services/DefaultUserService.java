package sda.twitter2.services;

import sda.twitter2.models.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class DefaultUserService implements DatabaseService<User> {

    private EntityManager em;

    public DefaultUserService() {
        this.em = Persistence.createEntityManagerFactory("persistence").createEntityManager();
    }

    @Override
    public boolean create(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return isExist(user);
    }

    public boolean create(){

        return false;
    }

    @Override
    public boolean update(User... users){
         User updated = find(users[0]);
         updated.setUsername(users[1].getUsername());
         updated.setPassword(users[1].getPassword());

        return isExist(users[1]);
    }

    @Override
    public boolean isExist(User user) {
        user = find(user);
        return user != null;
    }

    @Override
    public User find(User user) {
        User found = null;

        em.getTransaction().begin();
        try {
            found = em.createQuery("FROM User u WHERE u.username=:username AND u.password=:password", User.class)
                    .setParameter("username", user.getUsername())
                    .setParameter("password", user.getPassword())
                    .getSingleResult();
        } catch (NoResultException e) {
        }
        em.getTransaction().commit();

        return found;
    }

    @Override
    public User findById(Long id) {
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.getTransaction().commit();
        return user;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }
}
