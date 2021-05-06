package Root.Pages;

import Root.Main;
import Root.Managers.Board;
import Root.Managers.UIManager;
import Root.Server.Connection;
import Root.Server.Interpreter;
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
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Onlinelobby implements Initializable {
    private Interpreter interpreter;
    private static Timer playerTimer;
    private List<String> playerList;

    @FXML
    private TextField opponent;

    @FXML
    private Button challengeButton;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        interpreter = new Interpreter();
        playerTimer = new Timer();
    }

    @FXML
    protected void handleMainMenuButton(ActionEvent event){
       // setScene("view/main.fxml");
    }

    /**
     * Subscriben op reversi.
     * @param event
     */
    @FXML
    protected void subscribeMenuButton(ActionEvent event){
        System.out.println("Subscribe to Reversi is pressed!");
        Main.connection.subscribe("Reversi");
    }

    /**
     * Accepteren van een challenge.
     * @param event mouse-click event.
     * @throws IOException lLs er een fout is bij het laden van het bestand.
     */
    @FXML
    protected void acceptChallengeButton(ActionEvent event) throws IOException {
        Window ErrorMessage = challengeButton.getScene().getWindow();
        //System.out.println("accept is pressed!");
        if(interpreter.getGameChallenge() == null){
            Alerthelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "No challenge has been Send!");
            return;
        }
        UIManager.createScene("Reversi.fxml");
        Main.connection.acceptGameChallenge(interpreter.getGameID());

    }

    public void youLost(){
        Window ErrorMessage = challengeButton.getScene().getWindow();
        Alerthelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Lost game", "Whoops, looks Like you lost!");
        return;

    }

    /**
     * Het versturen van een uitdaging naar een opponent.
     * @param event mouse-click event.
     * @throws IOException Als er een fout is bij het laden van het bestand.
     */
    @FXML
    protected void handleChallengeButton(ActionEvent event) throws IOException {
        Window ErrorMessage = challengeButton.getScene().getWindow();

        if(opponent.getText().isEmpty()){
            Alerthelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Please enter a valid ign");
            return;
        }
        if(opponent.getText().equals(playerList)){
            Alerthelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Player is not online");
            return;
        }
        UIManager.createScene("Reversi.fxml");
        Main.connection.challengePlayer(opponent.getText() , "Reversi");
    }
}

