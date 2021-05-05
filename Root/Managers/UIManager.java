package Root.Managers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import Root.Main;

public class UIManager implements Manager {
    private static UIManager uiManager;
    private static Parent root;

    @Override
    public void Start() throws IOException {

    }

    @Override
    public void Update() {
        // TODO Auto-generated method stub

    }

    public static void createScene(String path) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();

        FileInputStream fileInputStream = new FileInputStream(new File("Root/Views/" + path));
        Parent root = fxmlLoader.load(fileInputStream);
        Main.primaryStage.getScene().setRoot(root);
        Main.primaryStage.getScene().getStylesheets().add("Root/Views/Style.css");
        Main.primaryStage.show();


    }

}
