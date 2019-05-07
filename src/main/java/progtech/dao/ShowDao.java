package progtech.dao;

import org.springframework.stereotype.Component;
import progtech.model.Series;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class ShowDao {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void persist(Series series){
        entityManager.persist(series);
    }
}
