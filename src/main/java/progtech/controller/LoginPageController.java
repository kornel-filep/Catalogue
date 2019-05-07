package progtech.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import progtech.dao.UserDao;
import progtech.model.user.User;
import progtech.spring.Config;

public class LoginPageController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;


    public void login(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        User user = new User(loginField.getText(), passwordField.getText());
        applicationContext.getBean(UserDao.class).persist(user);
    }

    public void register(){
        //implement registration logic
    }

}
