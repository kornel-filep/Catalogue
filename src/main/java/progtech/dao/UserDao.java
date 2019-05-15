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

    /**
     * Persists the User domain object.
     * @param user the object to be persisted.
     */
    @Transactional
    public void persist(User user) {
        entityManager.persist(user);
    }

    /**
     * Merges the User domain object with its database counterpart.
     * @param user the user to merge
     */
    @Transactional
    public void update(User user) {
        entityManager.merge(user);
    }

    /**
     * Finds the user by its id.
     * @param id the id to be seeked for
     * @return the user with the given id
     */
    public User find(long id) {
        return entityManager.find(User.class, id);
    }

    /**
     * Finds the user by username.
     * @param username the username
     * @return the user with the username
     */
    public User findByUsername(String username) {
        try {
            Query q = entityManager.createQuery("SELECT e FROM User e WHERE name = :username");
            q.setParameter("username", username);
            return (User) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
