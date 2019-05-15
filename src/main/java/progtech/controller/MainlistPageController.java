package progtech.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import progtech.domain.Episode;
import progtech.domain.Series;
import progtech.domain.user.User;
import progtech.service.EpisodeService;
import progtech.service.SeriesService;
import progtech.service.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlls the main page of the application
 */
@Controller
public class MainlistPageController implements Initializable {

    @FXML
    @Getter
    private TableView<Series> table;
    @FXML
    @Getter
    private TableView<Episode> episodeTable;
    @FXML
    private TableColumn<Episode, Integer> episodeNumberColumn;
    @FXML
    private TableColumn<Episode, String> episodeNameColumn;
    @FXML
    private TableColumn<Episode, Integer> episodeSeasonColumn;
    @FXML
    private TableColumn<Episode, String> episodeDescriptionColumn;
    @FXML
    private TableColumn<Episode, String> episodeWatchedColumn;
    @FXML
    @Getter
    private TableColumn<Series, String> nameColumn;
    @FXML
    @Getter
    private TableColumn<Series, Integer> seasonColumn;
    @FXML
    @Getter
    private TableColumn<Series, String> descriptionColumn;
    @FXML
    private Button addButton;
    @FXML
    private Button watchedButton;
    @FXML
    private Button listButton;
    @FXML
    @Getter
    private Button adminAddNewEpisode;
    @FXML
    @Getter
    private Button adminAddNewSeries;

    @Setter
    private User user;
    private Series currentlySelectedSeries;
    private SeriesService seriesService;

    private UserService userService;
    private AddNewEpisodeController addNewEpisodeController;
    private EpisodeService episodeService;

    public MainlistPageController(SeriesService seriesService, UserService userService,
            AddNewEpisodeController addNewEpisodeController, EpisodeService episodeService) {
        this.seriesService = seriesService;
        this.userService = userService;
        this.addNewEpisodeController = addNewEpisodeController;
        this.episodeService = episodeService;
    }

    /**
     * Initializes the basic functionality of the table by setting the columns cell value factory
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Series, String>("name"));
        seasonColumn.setCellValueFactory(new PropertyValueFactory<Series, Integer>("episodes"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Series, String>("description"));
        watchedButton.visibleProperty().setValue(false);
    }

    /**
     * The functionality depends on the text of the button.
     * If the text is "Add" then it adds series to the user.
     * If the text is "Show Episodes" then it shows the Episodes of the selected series.
     * If the text is "Back" then it goes back to the previous page.
     */
    public void handleMultipurposeButton() {
        if (addButton.getText().equals("Add")) {
            Series series = table.getSelectionModel().getSelectedItem();
            userService.addSeriesToUser(series, user);
        } else if (addButton.getText().equals("Show Episodes")) {
            episodeTable.getItems().clear();
            currentlySelectedSeries = table.getSelectionModel().getSelectedItem();
            episodeNameColumn.setCellValueFactory(new PropertyValueFactory<Episode, String>("name"));
            episodeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Episode, String>("description"));
            episodeNumberColumn.setCellValueFactory(new PropertyValueFactory<Episode, Integer>("episodeCount"));
            episodeSeasonColumn.setCellValueFactory(new PropertyValueFactory<Episode, Integer>("season"));
            System.out.println(user.getWatchedEpisodes());
            episodeWatchedColumn.setCellValueFactory(param ->
                    Bindings.createStringBinding(() -> {
                        if (user.getWatchedEpisodes().contains(param.getValue())) {
                            return "Yes";
                        }
                        return "No";
                    }));

            episodeTable.getItems().addAll(currentlySelectedSeries.getEpisodeList());
            table.visibleProperty().setValue(false);
            watchedButton.visibleProperty().setValue(true);
            addButton.setText("Back");
        } else if (addButton.getText().equals("Back")) {
            table.visibleProperty().setValue(true);
            watchedButton.visibleProperty().setValue(false);
            addButton.setText("Show Episodes");
        }
    }

    /**
     * Shows the series depending on the text shown on the button.
     */
    public void showSeries() {
        if (listButton.getText().equals("Show Own")) {
            user = userService.findById(user.getId());
            table.getItems().clear();
            table.getItems().addAll(user.getSeries());
            listButton.setText("Show All");
            addButton.setText("Show Episodes");
        } else if (listButton.getText().equals("Show All")) {
            table.getItems().clear();
            table.getItems().addAll(seriesService.getAllSeries());
            listButton.setText("Show Own");
            addButton.setText("Add");
        }
    }

    /**
     * Adds the selected episode to the watched list.
     */
    public void watchedClick() {
        Episode episode = episodeTable.getSelectionModel().getSelectedItem();
        user.getWatchedEpisodes().add(episode);
        userService.update(user);
        episodeTable.getItems().clear();
        episodeTable.getItems().addAll(currentlySelectedSeries.getEpisodeList());
    }

    /**
     * Admin operation, opens up a new screen to add a new Series.
     * @throws IOException when the fxml cannot be found
     */
    public void addNewSeries() throws IOException {
        seriesService.initializeAddNewSeriesScreen();
    }

    /**
     * Admin operation, opens up a new screen to add a new Episode to the selected Series.
     * @throws IOException when the fxml cannot be found
     */
    public void addNewEpisode() throws IOException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            episodeService.initializeEpisodeScreen(addNewEpisodeController, table);
        }
    }

}
