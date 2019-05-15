package progtech.dao;

import org.springframework.stereotype.Component;
import progtech.model.Series;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class SeriesDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(Series series){
        entityManager.persist(series);
    }

    public List<Series> findAll(){
        Query q = entityManager.createQuery("SELECT e FROM Series e");
        return q.getResultList();
    }

    @Transactional
    public void update(Series series) {
        entityManager.merge(series);
    }
}
