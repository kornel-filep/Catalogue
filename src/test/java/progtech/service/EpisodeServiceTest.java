package progtech.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import progtech.dao.EpisodeDao;
import progtech.domain.Episode;
import progtech.domain.Series;

public class EpisodeServiceTest {

    private static EpisodeService episodeService;
    private static EpisodeDao episodeDao;

    @BeforeAll
    static void init() {
        episodeDao = Mockito.mock(EpisodeDao.class);
        episodeService = new EpisodeService(episodeDao);
    }

    @Test
    void testCreateEpisodeShouldCreateEpisode() {
        Series series = new Series();
        Episode actual = episodeService.createEpisode(series, "Name", "1", "1", "description");
        Episode expected = new Episode(series, "Name", 1, 1, "description");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPresistShouldCallDaoToPersist() {
        Series series = new Series();
        Episode ep = new Episode(series, "Name", 1, 1, "description");
        episodeService.persist(ep);
        Mockito.verify(episodeDao).persist(ep);
    }

}
