package progtech.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import progtech.Main;
import progtech.dao.SeriesDao;
import progtech.model.Series;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
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

    public void initializeAddNewSeriesScreen() throws IOException {
        log.debug("Initializing new series screen");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addSeries.fxml"));
        fxmlLoader.setControllerFactory(Main.getSpringContext()::getBean);
        Parent root = fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add Series");
        primaryStage.setScene(new Scene(root, 510, 300));
        primaryStage.show();
    }
}
