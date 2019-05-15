package progtech.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.springframework.stereotype.Controller;
import progtech.service.SeriesService;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AddNewSeriesController implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private TextField episodesField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button saveButton;
    private SeriesService seriesService;

    public AddNewSeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    public void save() {
        seriesService.saveSeries(nameField.getText(), episodesField.getText(), descriptionField.getText());
        nameField.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorPane.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                saveButton.fire();
                ev.consume();
            }
        });
    }
}
