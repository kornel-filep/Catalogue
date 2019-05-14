package progtech.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import progtech.Main;
import progtech.dao.ShowDao;
import progtech.model.Episode;
import progtech.model.Series;
import progtech.model.user.User;
import progtech.service.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    @Getter
    private Label userName;
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

    private ShowDao showDao;
    private UserService userService;
    private AddNewEpisodeController addNewEpisodeController;

    public MainlistPageController(ShowDao showDao, UserService userService, AddNewEpisodeController addNewEpisodeController) {
        this.showDao = showDao;
        this.userService = userService;
        this.addNewEpisodeController = addNewEpisodeController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Series, String>("name"));
        seasonColumn.setCellValueFactory(new PropertyValueFactory<Series, Integer>("episodes"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Series, String>("description"));
        watchedButton.visibleProperty().setValue(false);
        //watchedButton.getScene().getWindow().focusedProperty().addListener(o -> table.getItems().addAll(showDao.findAll()));
    }

    /**
     * <pre>
     * Some text here.
     *
     * <code>
     *     public static void main(String[] args){
     *         MainlistPageController.start();
     *     }
     * </code>
     * </pre>
     */
    public void addSeriesToUser() {
        if (addButton.getText().equals("Add")) {
            Series series = table.getSelectionModel().getSelectedItem();
            user.getSeries().add(series);
            userService.update(user);
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

    public void showSeries() {
        if (listButton.getText().equals("Show Own")) {
            user = userService.findById(user.getId());
            table.getItems().clear();
            table.getItems().addAll(user.getSeries());
            listButton.setText("Show All");
            addButton.setText("Show Episodes");
        } else if (listButton.getText().equals("Show All")) {
            table.getItems().clear();
            table.getItems().addAll(showDao.findAll());
            listButton.setText("Show Own");
            addButton.setText("Add");
        }
    }

    public void watchedClick() {
        Episode episode = episodeTable.getSelectionModel().getSelectedItem();
        user.getWatchedEpisodes().add(episode);
        userService.update(user);
        episodeTable.getItems().clear();
        episodeTable.getItems().addAll(currentlySelectedSeries.getEpisodeList());
    }

    public void addNewSeries() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/addSeries.fxml"));
        fxmlLoader.setControllerFactory(Main.getSpringContext()::getBean);
        Parent root = fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Add Series");
        primaryStage.setScene(new Scene(root, 510, 300));
        primaryStage.show();
    }

    public void addNewEpisode() throws IOException {
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
