package progtech.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;
import progtech.service.SeriesService;

@Controller
public class AddNewSeriesController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField episodesField;
    @FXML
    private TextArea descriptionField;
    private SeriesService seriesService;

    public AddNewSeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    public void save() {
        seriesService.saveSeries(nameField.getText(), episodesField.getText(), descriptionField.getText());
        nameField.getScene().getWindow().hide();
    }

}
