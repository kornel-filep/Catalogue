package progtech.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import progtech.Main;
import progtech.controller.AddNewEpisodeController;
import progtech.dao.EpisodeDao;
import progtech.domain.Episode;
import progtech.domain.Series;

import java.io.IOException;

/**
 * The Service which works with Episodes and DAOs.
 */
@Service
@Slf4j
public class EpisodeService {
    private EpisodeDao episodeDao;

    public EpisodeService(EpisodeDao episodeDao) {
        this.episodeDao = episodeDao;
    }

    /**
     * Creates and Episode object from text values and a Series object
     * @param series The Series the episode belongs to.
     * @param name The name of the episode.
     * @param episodeCount The episode number in the season
     * @param season The season of the Series
     * @param description The description of the episode
     * @return The Episode object with the values of the string in their proper types
     */
    public Episode createEpisode(Series series, String name, String episodeCount, String season, String description) {
        return new Episode(series, name, Integer.parseInt(episodeCount), Integer.parseInt(season), description);
    }

    /**
     * Persists the episode to the database via the dao.
     * @param episode The Episode to be persisted
     */
    public void persist(Episode episode) {
        episodeDao.persist(episode);
    }

    /**
     * Initializes the episode Screen
     * @param addNewEpisodeController the controller used to set the series
     * @param table the table to get the selected series
     * @throws IOException then the fxml cannot be found
     */
    public void initializeEpisodeScreen(AddNewEpisodeController addNewEpisodeController, TableView<Series> table) throws IOException {
        log.debug("Initializing new episode screen");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addEpisode.fxml"));
        fxmlLoader.setControllerFactory(Main.getSpringContext()::getBean);
        Parent root = fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add Episode");
        primaryStage.setScene(new Scene(root, 525, 250));
        addNewEpisodeController.setSeries(table.getSelectionModel().getSelectedItem());
        primaryStage.show();
    }
}
