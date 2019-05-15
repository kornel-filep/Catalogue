package progtech.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import progtech.Main;
import progtech.dao.SeriesDao;
import progtech.domain.Series;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

/**
 * The Service to interact with Series and DAOs.
 */
@Service
@Slf4j
public class SeriesService {
    private SeriesDao seriesDao;

    public SeriesService(SeriesDao seriesDao) {
        this.seriesDao = seriesDao;
    }

    /**
     * Persists a service from its string values.
     * @param name The name of the series.
     * @param episodeCount How much episodes are in the series.
     * @param description The description of the series.
     */
    public void saveSeries(String name, String episodeCount, String description) {
        Series series = new Series(name, Integer.parseInt(episodeCount), description, new HashSet<>());
        seriesDao.persist(series);
    }

    /**
     * Persists the Series via the DAO.
     * @param series the Series to be persisted
     */
    public void persist(Series series) {
        seriesDao.persist(series);
    }

    /**
     * Updates the Series via the DAO.
     * @param series the Series to be updated
     */
    public void update(Series series) {
        seriesDao.update(series);
    }

    /**
     * Gets all the series currently in the database.
     * @return A List containing all the Series currently stored in the database
     */
    public List<Series> getAllSeries() {
        return seriesDao.findAll();
    }

    /**
     * Initializes the Add New Series Screen.
     * @throws IOException When the fxml cannot be found
     */
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
