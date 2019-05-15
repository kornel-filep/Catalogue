package progtech;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Entry point of the Spring Boot Application.
 */
@SpringBootApplication
@Slf4j
public class Main extends Application {

    @Getter
    private static ConfigurableApplicationContext springContext;
    private Parent root;
    @Getter
    private static FXMLLoader fxmlLoader;

    /**
     * Initializes the application with custom spring context.
     * @throws Exception if the fxml cannnot be found
     */
    @Override
    public void init() throws Exception {
        log.debug("Initializing application with custom spring context");
        springContext = SpringApplication.run(Main.class);
        fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        root = fxmlLoader.load();
    }

    /**
     * Starts the application.
     * @param primaryStage The window that needs to be opened when the application starts
     * @throws Exception if the fxml cannot be found
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Catalogue");
        primaryStage.setScene(new Scene(root, 472, 291));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }

    public static void main(String[] args) {
        launch(Main.class, args);
    }

}
