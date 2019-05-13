package progtech.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import progtech.Main;
import progtech.dao.ShowDao;
import progtech.model.Series;
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
        userService.loginUser(loginField.getText(), passwordField.getText());
        //Dont forget this may not work!
        loginField.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainlist.fxml"));
        fxmlLoader.setControllerFactory(Main.getSpringContext()::getBean);
        Parent root = fxmlLoader.load();
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Catalogue");
        primaryStage.setScene(new Scene(root, 900, 775));

        mainlistPageController.getNameColumn().setCellValueFactory(new PropertyValueFactory<Series, String>("name"));
        mainlistPageController.getSeasonColumn().setCellValueFactory(new PropertyValueFactory<Series, Integer>("episodes"));
        mainlistPageController.getDescriptionColumn().setCellValueFactory(new PropertyValueFactory<Series, String>("description"));
        mainlistPageController.getTable().getItems().addAll(showDao.findAll());
        primaryStage.show();
    }

    public void register() {
        userService.registerUser(loginField.getText(), passwordField.getText());
    }

}
