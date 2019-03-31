package sda.twitter2.dbServices;

import lombok.Getter;
import lombok.NoArgsConstructor;
//import org.apache.log4j.Logger;
import sda.twitter2.models.User;

import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

@NoArgsConstructor
@Getter
public class DefaultUserService {
    private static DefaultUserService ourInstance = new DefaultUserService();

//    static Logger log = Logger.getLogger(DefaultUserService.class);

    private List<User> resultList;

    public static DefaultUserService getInstance() {
        return ourInstance;
    }

    private static EntityManager initConnection() {
        return Persistence.createEntityManagerFactory("persistence")
                .createEntityManager();
    }

    public boolean checkUser(final String username, final String password) throws IOException {
        Query<User> query = null;

        EntityManager em = initConnection();

        query = (Query<User>) em.createQuery("FROM User user WHERE user.username=:username and user.password=:password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

//        User user = em.find(User.class, username);
        resultList = query.list();

        if (resultList.size() > 0)
            return true;

        return false;
    }


}
