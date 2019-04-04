package sda.twitter2.services.oldSolution;

import sda.twitter2.models.Tweet;
import sda.twitter2.models.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public enum TweetService {
    INSTANCE;

    private EntityManager em;

    TweetService(){
        em = Persistence.createEntityManagerFactory("persistence")
                .createEntityManager();
    }

    public void createTweet(Tweet tweet){
        em.getTransaction().begin();
        em.persist(tweet);
        em.getTransaction().commit();
    }

    public Tweet findTweet(int id){
        em.getTransaction().begin();
        Tweet tweet = em.find(Tweet.class, id);
        em.getTransaction().commit();
        return tweet;
    }
}