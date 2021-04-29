package Root.Pages;

import Root.Main;
import Root.Managers.UIManager;
import Root.Server.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Onlinelogin implements Initializable {

    @FXML
    private TextField ignField;

    @FXML
    private TextField ipField;

    @FXML
    private TextField portField;

    @FXML
    private Button submitButton;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        ignField.setText("B2");
        ipField.setText("127.0.0.1");
        portField.setText("7789");

    }

    @FXML
    protected void handleSubmitButton(ActionEvent event) throws IOException {
        Window ErrorMessage = submitButton.getScene().getWindow();
        //Check if the textfields are empty
        if(ignField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Please enter a valid ign");
            return;
        }

        if(ipField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Please enter a valid IP");
            return;
        }

        if(!portField.getText().matches("^[0-9]*$")){
            AlertHelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Please enter a valid port");
            return;
        }

        if(portField.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Please enter a valid Port");
            return;
        }

        //System.out.println("Connected to: " + ipField.getText() + " on port : " + portField.getText());

        Main.connection.connectToServer(ipField.getText(), Integer.parseInt(portField.getText()));
        Main.connection.login(ignField.getText());
        UIManager.createScene("Onlinelobby.fxml");

    }

    public TextField getIgnField() {
        return ignField;
    }
    //public Connection getConnection(){return connection;}

}

class AlertHelper {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}