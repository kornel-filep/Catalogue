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

    /**
     * Persists the Episode domain object.
     * @param episode the episode to be persisted
     */
    @Transactional
    public void persist(Episode episode){
        entityManager.persist(episode);
    }

    /**
     * Finds all Episodes stored in the database.
     * @return a list of episodes that are currently in the database
     */
    public List<Episode> findAll(){
        Query q = entityManager.createQuery("SELECT e FROM e Episode");
        return q.getResultList();
    }

}
