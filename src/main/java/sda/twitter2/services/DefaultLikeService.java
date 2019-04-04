package sda.twitter2.services;

import org.joda.time.DateTime;
import sda.twitter2.models.Like;
import sda.twitter2.models.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class DefaultLikeService implements DatabaseService<Like> {

    private EntityManager em;

    public DefaultLikeService() {
        this.em = Persistence.createEntityManagerFactory("persistence").createEntityManager();
    }

    @Override
    public boolean create(Like like) {
        em.getTransaction().begin();
        em.persist(like);
        em.getTransaction().commit();
        return isExist(like);
    }

    public boolean toggleLike (Like like){
        Like updated = find(like);
        boolean active = updated.isActive();

        if(active == true)
            updated.setActive(false);
        else
            updated.setActive(true);

        return update(like, updated);
    }

    @Override
    public boolean update(Like... likes) {
        Like updated = find(likes[0]);
        updated.setActive(likes[1].isActive());
        updated.setDate(DateTime.now());
        return isExist(likes[1]);
    }

    @Override
    public boolean isExist(Like like) {
        return find(like) != null;
    }

    @Override
    public Like find(Like like) {
        Like found = null;

        em.getTransaction().begin();
        try {
            found = em.createQuery("FROM Like li WHERE li.user=:user AND li.date=:date", Like.class)
                    .setParameter("user", like.getUser())
                    .setParameter("date", like.getDate())
                    .getSingleResult();
        } catch (NoResultException e) {
        }
        em.getTransaction().commit();
        return found;
    }

    @Override
    public Like findById(Long id) {
        em.getTransaction().begin();
        Like like = em.find(Like.class, id);
        em.getTransaction().commit();
        return like;
    }

    @Override
    public boolean delete(Like like) {
        return false;
    }
}
