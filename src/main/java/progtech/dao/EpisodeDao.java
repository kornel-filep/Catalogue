package progtech.dao;

import org.springframework.stereotype.Component;
import progtech.model.Episode;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;

@Component
public class EpisodeDao {
    @PersistenceContext
    EntityManager entityManager;

    public void persist(Episode episode){
        entityManager.persist(episode);
    }

}
