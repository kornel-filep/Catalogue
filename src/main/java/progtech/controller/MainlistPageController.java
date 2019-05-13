package progtech.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import progtech.dao.ShowDao;
import progtech.model.Series;

@Controller
public class MainlistPageController {

    @FXML
    @Getter
    private TableView table;
    @FXML
    @Getter
    private TableColumn<Series, String> nameColumn;
    @FXML
    @Getter
    private TableColumn<Series, Integer> seasonColumn;
    @FXML
    @Getter
    private TableColumn<Series, String> descriptionColumn;

    private ShowDao showDao;

    public MainlistPageController(ShowDao showDao) {
        this.showDao = showDao;
    }
}
