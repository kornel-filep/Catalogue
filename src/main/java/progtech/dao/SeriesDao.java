package progtech.dao;

import org.springframework.stereotype.Component;
import progtech.domain.Series;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * The DAO to interact with the Series domain object.
 */
@Component
public class SeriesDao {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Persists the Series domain object.
     * @param series the object to be persisted
     */
    @Transactional
    public void persist(Series series){
        entityManager.persist(series);
    }

    /**
     * Finds all Series currently stored in the database.
     * @return a list with all the series in the database
     */
    public List<Series> findAll(){
        Query q = entityManager.createQuery("SELECT e FROM Series e");
        return q.getResultList();
    }

    /**
     * Merges an entity with its database equivalent.
     * @param series the series to be merged
     */
    @Transactional
    public void update(Series series) {
        entityManager.merge(series);
    }
}
