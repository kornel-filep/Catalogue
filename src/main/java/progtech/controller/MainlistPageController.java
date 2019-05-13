package progtech.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import progtech.dao.ShowDao;
import progtech.model.Series;
import progtech.model.user.User;
import progtech.service.UserService;

@Controller
public class MainlistPageController {

    @FXML
    @Getter
    private TableView<Series> table;
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
        Series series = table.getSelectionModel().getSelectedItem();
        user.getSeries().add(series);
        userService.update(user);
    }

    public void showSeries() {
        if (listButton.getText().equals("Show Own")) {
            table.getItems().clear();
            table.getItems().addAll(user.getSeries());
            listButton.setText("Show All");
        } else {
            table.getItems().clear();
            table.getItems().addAll(showDao.findAll());
            listButton.setText("Show Own");
        }
    }





}
