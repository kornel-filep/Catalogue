package progtech.service;

import org.springframework.stereotype.Service;
import progtech.dao.SeriesDao;
import progtech.model.Series;

import java.util.HashSet;
import java.util.List;

@Service
public class SeriesService {
    private SeriesDao seriesDao;

    public SeriesService(SeriesDao seriesDao) {
        this.seriesDao = seriesDao;
    }

    public void saveSeries(String name, String episodeCount, String description) {
        Series series = new Series(name, Integer.parseInt(episodeCount), description, new HashSet<>());
        seriesDao.persist(series);
    }

    public void persist(Series series) {
        seriesDao.persist(series);
    }

    public void update(Series series) {
        seriesDao.update(series);
    }

    public List<Series> getAllSeries() {
        return seriesDao.findAll();
    }
}
