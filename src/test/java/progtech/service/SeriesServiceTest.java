package progtech.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import progtech.dao.SeriesDao;
import progtech.model.Series;

import java.util.ArrayList;
import java.util.List;

public class SeriesServiceTest {

    private static SeriesService seriesService;
    private static SeriesDao seriesDao;

    @BeforeAll
    static void init() {
        seriesDao = Mockito.mock(SeriesDao.class);
        seriesService = new SeriesService(seriesDao);
    }

    @Test
    void testSaveSeriesShouldSaveSeriesThroughDao() {
        seriesService.saveSeries("Name", "1", "asd");
        Mockito.verify(seriesDao, Mockito.atLeastOnce()).persist(Mockito.any(Series.class));
    }

    @Test
    void testPersistShouldPersistSeriesThroughDao() {
        seriesService.persist(new Series());
        Mockito.verify(seriesDao).persist(Mockito.any(Series.class));
    }

    @Test
    void testUpdateShouldUpdateSeriesThroughDao() {
        seriesService.update(new Series());
        Mockito.verify(seriesDao).update(Mockito.any(Series.class));
    }

    @Test
    void testGetAllSeriesShouldReturnAllSeries() {
        ArrayList<Series> expected = new ArrayList<>();
        Mockito.when(seriesDao.findAll()).thenReturn(expected);
        List<Series> actual = seriesService.getAllSeries();
        Assertions.assertEquals(expected, actual);
    }

}
