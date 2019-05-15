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
import progtech.model.Episode;
import progtech.model.Series;

import java.io.IOException;

@Service
@Slf4j
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
