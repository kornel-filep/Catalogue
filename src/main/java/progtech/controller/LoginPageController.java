package progtech.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;
import progtech.Main;
import progtech.dao.ShowDao;
import progtech.model.user.User;
import progtech.service.UserService;

import java.io.IOException;

@Controller
public class LoginPageController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    private UserService userService;
    private ShowDao showDao;
    private MainlistPageController mainlistPageController;

    public LoginPageController(UserService userService, ShowDao showDao, MainlistPageController mainlistPageController) {
        this.userService = userService;
        this.showDao = showDao;
        this.mainlistPageController = mainlistPageController;
    }

    public void login() throws IOException {
        User user = userService.loginAndGetUser(loginField.getText(), passwordField.getText());
        loginField.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainlist.fxml"));
        fxmlLoader.setControllerFactory(Main.getSpringContext()::getBean);
        Parent root = fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Catalogue");
        primaryStage.setScene(new Scene(root, 700, 400));
        mainlistPageController.setUser(user);
        mainlistPageController.getUserName().setText("Logged in as: " + user.getName());
        mainlistPageController
                .getTable()
                .getScene()
                .getWindow()
                .focusedProperty().addListener(o -> {
            mainlistPageController.getTable().getItems().clear();
            mainlistPageController.getTable().getItems().addAll(showDao.findAll());
        });
        if (user.isAdmin()) {
            mainlistPageController.getAdminAddNewEpisode().visibleProperty().setValue(true);
            mainlistPageController.getAdminAddNewSeries().visibleProperty().setValue(true);
        } else {
            mainlistPageController.getAdminAddNewSeries().visibleProperty().setValue(false);
            mainlistPageController.getAdminAddNewSeries().visibleProperty().setValue(false);
        }
        primaryStage.show();
    }

    public void register() {
        userService.registerUser(loginField.getText(), passwordField.getText());
    }

}
