package progtech.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import progtech.model.Episode;
import progtech.model.Series;
import progtech.service.EpisodeService;
import progtech.service.SeriesService;

@Controller
public class AddNewEpisodeController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField numberField;
    @FXML
    private TextField seasonField;
    @FXML
    private TextArea descriptionField;
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
}
