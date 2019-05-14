package progtech.service;

import org.springframework.stereotype.Service;
import progtech.dao.EpisodeDao;
import progtech.model.Episode;
import progtech.model.Series;

@Service
public class EpisodeService {
    private EpisodeDao episodeDao;

    public EpisodeService(EpisodeDao episodeDao) {
        this.episodeDao = episodeDao;
    }

    public Episode createEpisode(Series series, String name, String episodeCount, String season, String description) {
        return new Episode(series, name, Integer.parseInt(episodeCount), Integer.parseInt(season), description);
    }

    public void persist(Episode episode) {
        episodeDao.persist(episode);
    }

}
