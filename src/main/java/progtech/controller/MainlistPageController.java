package progtech.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import progtech.dao.ShowDao;
import progtech.model.Episode;
import progtech.model.Series;
import progtech.model.user.User;
import progtech.service.UserService;

@Controller
public class MainlistPageController {

    @FXML
    @Getter
    private TableView<Series> table;
    @FXML
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
    @Getter
    private Label userName;
    @FXML
    private Button listButton;
    @Setter
    private User user;

    private ShowDao showDao;
    private UserService userService;

    public MainlistPageController(ShowDao showDao, UserService userService) {
        this.showDao = showDao;
        this.userService = userService;
    }

    public void addSeriesToUser() {
        if (addButton.getText().equals("Add")) {
            Series series = table.getSelectionModel().getSelectedItem();
            user.getSeries().add(series);
            userService.update(user);
        } else if (addButton.getText().equals("Show Episodes")) {
            Series series = table.getSelectionModel().getSelectedItem();
            episodeNameColumn.setCellValueFactory(new PropertyValueFactory<Episode, String>("name"));
            episodeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Episode, String>("description"));
            episodeNumberColumn.setCellValueFactory(new PropertyValueFactory<Episode, Integer>("episodeCount"));
            episodeSeasonColumn.setCellValueFactory(new PropertyValueFactory<Episode, Integer>("season"));
            episodeTable.getItems().addAll(series.getEpisodeList());
            table.visibleProperty().setValue(false);
            addButton.setText("Back");
        } else if (addButton.getText().equals("Back")) {
            table.visibleProperty().setValue(true);
        }
    }

    public void showSeries() {
        if (listButton.getText().equals("Show Own")) {
            table.getItems().clear();
            table.getItems().addAll(user.getSeries());
            listButton.setText("Show All");
            addButton.setText("Show Episodes");
        } else if (listButton.getText().equals("Show All")) {
            table.getItems().clear();
            table.getItems().addAll(showDao.findAll());
            listButton.setText("Show Own");
        }
    }

    public void watchedClick() {

    }

}
