package progtech.dao;

import org.springframework.stereotype.Component;
import progtech.domain.user.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 * The Dao to interact with the User domain object.
 */
@Component
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    public User find(long id) {
        return entityManager.find(User.class, id);
    }

    public User findByUsername(String username) {
        try {
            Query q = entityManager.createQuery("SELECT e FROM User e WHERE name = :username");
            q.setParameter("username", username);
            return (User) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public void refresh(User user) {
        entityManager.refresh(user);
    }
}
