package Root;

import Root.Managers.GameManager;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Root.Managers.*;
import java.io.IOException;

public class Main extends Application{

    public static GameManager gameManager;
    public static boolean running = true;
    public static Stage primaryStage;
    public static FXMLLoader loader;

    @FXML
    public static Parent root;

    public static void main(String[] args) throws IOException {
        launch(args);

        gameManager = new GameManager();

        gameManager.Start();
        // Gameloop
        while (running) {
            gameManager.Update();
        }

        //(args);
        // Start every Manager

    }

    @Override
    public void start(Stage stage) throws Exception {
        gameManager = new GameManager();

        gameManager.Start();
        // Gameloop
        while (running) {
            gameManager.Update();
        }

        stage.setMinHeight(720);
        stage.setMinWidth(1240);
        stage.setTitle("Project 2.3");
        Main.primaryStage = stage;

        Main.primaryStage.setMinWidth(1240);
        Main.primaryStage.setMinHeight(720);

        //loader = new FXMLLoader();

        Scene scene = new Scene(new Pane());
        Main.primaryStage.setScene(scene);
        UIManager.createScene("Homepage.fxml");

    }
}