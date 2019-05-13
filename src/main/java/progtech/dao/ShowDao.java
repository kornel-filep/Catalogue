package progtech.dao;

import org.springframework.stereotype.Component;
import progtech.model.Episode;
import progtech.model.Series;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class ShowDao {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void persist(Series series){
        entityManager.persist(series);
    }

    public List<Series> findAll(){
        Query q = entityManager.createQuery("SELECT e FROM Series e");
        return q.getResultList();
    }
}