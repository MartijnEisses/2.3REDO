package Pages;
import Managers.Main;
import Managers.UIManager;
import Server.Interpreter;
import Pages.Alerthelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Onlinelobby implements Initializable {
    private Interpreter interpreter;
    private static Timer playerTimer;
    private List<String> playerList;
    private boolean viewRunner = true;

    @FXML private TextField opponent;
    @FXML private Button challengeButton;
    @FXML protected Label incomingChallenge;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        interpreter = new Interpreter();
        playerTimer = new Timer();
        gameChallenges();

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

    public void gameChallenges(){
        incomingChallenge.setText("Accept challenges");
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
        Alerthelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "The Online reversi board is currently not working");
        UIManager.createScene("Reversi.fxml");
        Main.connection.challengePlayer(opponent.getText() , "Reversi");
    }

    @FXML
    protected void logoutButton(ActionEvent event) throws IOException {
        UIManager.createScene("Onlinelogin.fxml");
        Main.connection.logout();
    }


}

