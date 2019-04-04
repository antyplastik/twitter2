package sda.twitter2.services;

import sda.twitter2.models.Account;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class DefaultAccountService implements DatabaseService <Account> {

    private EntityManager em;

    public DefaultAccountService(){
        this.em = Persistence.createEntityManagerFactory("persistence").createEntityManager();
    }

    @Override
    public boolean create(Account account) {
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        return isExist(account);
    }

    @Override
    public boolean update(Account... accounts) {
        Account updated = find(accounts[0]);
        updated.setActive(accounts[1].isActive());
        updated.setDescription(accounts[1].getDescription());
        updated.setFollowers(accounts[1].getFollowers());
        updated.setFollows(accounts[1].getFollows());
        return isExist(accounts[1]);
    }

    @Override
    public boolean isExist(Account account) {
        return find(account) != null;
    }

    @Override
    public Account find(Account account) {
        Account found = null;

        em.getTransaction().begin();
        try {
            found = em.createQuery("FROM Account ac WHERE ac.userId=:userId AND ac.accountName=:accountName", Account.class)
                    .setParameter("userId", account.getAccountName())
                    .setParameter("accountName", account.getUserId())
                    .getSingleResult();
        } catch (NoResultException e) {
        }
        em.getTransaction().commit();

        return found;
    }

    @Override
    public Account findById(Long id) {
        em.getTransaction().begin();
        Account account = em.find(Account.class, id);
        em.getTransaction().commit();
        return account;
    }

    @Override
    public boolean delete(Account account) {
        return false;
    }
}
