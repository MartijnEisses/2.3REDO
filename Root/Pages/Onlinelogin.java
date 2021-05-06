package Root.Pages;

import Root.Main;
import Root.Managers.UIManager;
import Root.Server.Connection;
import Root.Views.Alerthelper;
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

    /**
     * Input velden om in te loggen op de server. Errors als er een fout is.
     * @param event mouse-click event
     * @throws IOException Als er een fout is bij het laden van een pagina.
     */
    @FXML
    protected void handleSubmitButton(ActionEvent event) throws IOException {
        Window ErrorMessage = submitButton.getScene().getWindow();
        //Check if the textfields are empty
        if (ignField.getText().isEmpty()) {
            Alerthelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Please enter a valid ign");
            return;
        }

        if (ipField.getText().isEmpty()) {
            Alerthelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Please enter a valid IP");
            return;
        }

        if (!portField.getText().matches("^[0-9]*$")) {
            Alerthelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Please enter a valid port");
            return;
        }

        if (portField.getText().isEmpty()) {
            Alerthelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Please enter a valid Port");
            return;
        }
        Main.connection.connectToServer(ipField.getText(), Integer.parseInt(portField.getText()));
        Main.connection.login(ignField.getText());
        UIManager.createScene("Onlinelobby.fxml");
    }


    @FXML
    protected void homePageButton(ActionEvent event) throws IOException {
        UIManager.createScene("Homepage.fxml");
    }
    public TextField getIgnField() {
        return ignField;
    }
}
