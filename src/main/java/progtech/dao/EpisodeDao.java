package progtech.dao;

import org.springframework.stereotype.Component;
import progtech.domain.Episode;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * The Dao that interacts with the Episode domain object.
 */
@Component
public class EpisodeDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(Episode episode){
        entityManager.persist(episode);
    }

    public List<Episode> findAll(){
        Query q = entityManager.createQuery("SELECT e FROM e Episode");
        return q.getResultList();
    }

}
