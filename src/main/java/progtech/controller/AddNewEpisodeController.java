package progtech.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import progtech.model.Episode;
import progtech.model.Series;
import progtech.service.EpisodeService;
import progtech.service.SeriesService;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class AddNewEpisodeController implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private TextField numberField;
    @FXML
    private TextField seasonField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button saveButton;
    @Setter
    private Series series;
    private SeriesService seriesService;
    private EpisodeService episodeService;

    public AddNewEpisodeController(SeriesService seriesService, EpisodeService episodeService) {
        this.seriesService = seriesService;
        this.episodeService = episodeService;
    }

    public void save() {
        Episode episode = episodeService.createEpisode(series, nameField.getText(), numberField.getText(),
                seasonField.getText(), descriptionField.getText());
        series.getEpisodeList().add(episode);
        episodeService.persist(episode);
        seriesService.update(series);
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
