package progtech.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import progtech.Main;
import progtech.service.UserService;

import java.io.IOException;

@Controller
public class LoginPageController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    private UserService userService;

    public LoginPageController(UserService userService) {
        this.userService = userService;
    }

    public void login() throws IOException {
        userService.loginUser(loginField.getText(), passwordField.getText());
        //Dont forget this may not work!
        loginField.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/mainlist.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Catalogue");
        primaryStage.setScene(new Scene(root, 500, 475));
        primaryStage.show();
    }

    public void register() {
        userService.registerUser(loginField.getText(), passwordField.getText());
    }

}
