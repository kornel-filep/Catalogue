package progtech.dao;

import org.springframework.stereotype.Component;
import progtech.model.user.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void persist(User user){
        entityManager.persist(user);
    }
}
