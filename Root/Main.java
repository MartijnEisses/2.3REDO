package Root;

import Root.Managers.GameManager;
import Root.Server.Connection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Root.Managers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main extends Application{

    public static GameManager gameManager;
    public static boolean running = true;
    public static Stage primaryStage;
    public static FXMLLoader loader;
    public static Connection connection;
    @FXML
    public static Parent root;

    public static void main(String[] args) throws IOException {
        gameManager = new GameManager();
        connection = new Connection();
        gameManager.Start();
        launch(args);
        // Gameloop
        while (running) {
            gameManager.Update();
        }
    }

    /**
     * Start van de JavaFX applicatie.
     * @param primaryStage gemaakte primaryStage.
     * @throws IOException Als er een fout is bestanden ophalen.
     */

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setMinHeight(720);
        primaryStage.setMinWidth(1240);
        primaryStage.setTitle("Project 2.3");
        Main.primaryStage = primaryStage;

        Main.primaryStage.setMinWidth(1240);
        Main.primaryStage.setMinHeight(720);

        //loader = new FXMLLoader();

        Scene scene = new Scene(new Pane());
        Main.primaryStage.setScene(scene);
        UIManager.createScene("Homepage.fxml");
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

    }

    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

}