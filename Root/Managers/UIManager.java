package Managers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
        //File file = new File()
        //URL url =
        //FileInputStream fileInputStream = new FileInputStream(new File("Views\\" +path));
        InputStream is = UIManager.class.getClassLoader().getResourceAsStream(path);
        Parent root = fxmlLoader.load(is);
        Main.primaryStage.getScene().setRoot(root);
        Main.primaryStage.getScene().getStylesheets().add("/Style.css");
        Main.primaryStage.show();
    }
}
