package progtech.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import progtech.Main;
import progtech.domain.user.User;
import progtech.service.SeriesService;
import progtech.service.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the Login page.
 */
@Controller
@Slf4j
public class LoginPageController implements Initializable {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML

    private AnchorPane anchorPane;
    private UserService userService;
    private SeriesService seriesService;
    private MainlistPageController mainlistPageController;

    /**
     * Basic constructor used for autowiring.
     * @param userService autowired by Spring
     * @param seriesService autowired by Spring
     * @param mainlistPageController autowired by Spring
     */
    public LoginPageController(UserService userService, SeriesService seriesService, MainlistPageController mainlistPageController) {
        this.userService = userService;
        this.seriesService = seriesService;
        this.mainlistPageController = mainlistPageController;
    }

    /**
     * Initializes the login page so that the login button fires when Enter was hit.
     * @param location not used
     * @param resources not used
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.debug("initializing event handler for anchor pane");
        anchorPane.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                loginButton.fire();
                ev.consume();
            }
        });
    }

    /**
     * Logs in the user to the application from the database. Shows and sets up the new main page.
     *
     * @throws IOException if it cant find the fxml file
     */
    public void login() throws IOException {
        log.debug("Starting login for username: " + loginField.getText());
        User user = userService.loginAndGetUser(loginField.getText(), passwordField.getText());
        if (user != null) {
            log.info("Logged in user");
            loginField.getScene().getWindow().hide();
            Stage primaryStage = setUpMainScreen(user);
            primaryStage.show();
        }
    }

    /**
     * Registers the user to the database.
     */
    public void register() {
        userService.registerUser(loginField.getText(), passwordField.getText());
    }

    /**
     * Sets up the main screen and closes down the login screen.
     * @param user The logged in user
     * @return The Stage
     * @throws IOException Thrown when the fxml file cannot be found
     */
    private Stage setUpMainScreen(User user) throws IOException {
        log.debug("Setting up the main screen");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainlist.fxml"));
        fxmlLoader.setControllerFactory(Main.getSpringContext()::getBean);
        Parent root = fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Catalogue");
        primaryStage.setScene(new Scene(root, 700, 400));
        mainlistPageController.setUser(user);
        mainlistPageController
                .getTable()
                .getScene()
                .getWindow()
                .focusedProperty().addListener(o -> {
            mainlistPageController.getTable().getItems().clear();
            mainlistPageController.getTable().getItems().addAll(seriesService.getAllSeries());
        });
        if (user.isAdmin()) {
            log.debug(user + " is admin so displaying admin buttons");
            mainlistPageController.getAdminAddNewEpisode().visibleProperty().setValue(true);
            mainlistPageController.getAdminAddNewSeries().visibleProperty().setValue(true);
        } else {
            mainlistPageController.getAdminAddNewSeries().visibleProperty().setValue(false);
            mainlistPageController.getAdminAddNewSeries().visibleProperty().setValue(false);
        }
        return primaryStage;
    }
}
