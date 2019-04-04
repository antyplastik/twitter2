package sda.twitter2.services;

import org.joda.time.DateTime;
import sda.twitter2.models.Tweet;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class DefaultTweetService implements DatabaseService<Tweet> {

    private EntityManager em;

    public DefaultTweetService() {
        this.em = Persistence.createEntityManagerFactory("persistence").createEntityManager();
    }

    @Override
    public boolean create(Tweet tweet) {
        em.getTransaction().begin();
        em.persist(tweet);
        em.getTransaction().commit();
        return isExist(tweet);
    }

    @Override
    public boolean update(Tweet... tweets) {
        Tweet updated = find(tweets[0]);
        updated.setMessage(tweets[1].getMessage());
        updated.setResponses(tweets[1].getResponses());
        updated.setDate(DateTime.now());
        return isExist(tweets[1]);
    }

    @Override
    public boolean isExist(Tweet tweet) {
        return find(tweet) != null;
    }

    @Override
    public Tweet find(Tweet tweet) {
        Tweet found = null;

        em.getTransaction().begin();
        try {
            found = em.createQuery("FROM Tweet tw WHERE tw.account=:account AND tw.date=:date", Tweet.class)
                    .setParameter("account", tweet.getAccount())
                    .setParameter("date", tweet.getDate())
                    .getSingleResult();
        } catch (NoResultException e) {
        }
        em.getTransaction().commit();

        return found;
    }

    @Override
    public Tweet findById(Long id) {
        em.getTransaction().begin();
        Tweet tweet = em.find(Tweet.class, id);
        em.getTransaction().commit();
        return tweet;
    }

    @Override
    public boolean delete(Tweet tweet) {
        return false;
    }
}
