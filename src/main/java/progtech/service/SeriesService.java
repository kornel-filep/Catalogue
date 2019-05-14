package progtech.service;

import org.springframework.stereotype.Service;
import progtech.dao.ShowDao;
import progtech.model.Series;

import java.util.HashSet;

@Service
public class SeriesService {
    private ShowDao showDao;

    public SeriesService(ShowDao showDao) {
        this.showDao = showDao;
    }

    public void saveSeries(String name, String episodeCount, String description) {
        Series series = new Series(name, Integer.parseInt(episodeCount), description, new HashSet<>());
        showDao.persist(series);
    }
}
