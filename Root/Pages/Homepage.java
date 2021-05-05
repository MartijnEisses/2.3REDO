package Root.Pages;

import Root.Main;
import Root.Players.playertype;
import Root.Managers.GameType;
import Root.Managers.UIManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Homepage implements Initializable {
    private playertype playerType;
    private GameType gameType;
    private ReversiTemp reversiTemp;

    @FXML
    ComboBox<String> selectGame;

    public static GameType selectedGame;

    public Homepage()  {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectGame.getItems().addAll("TicTacToe", "Reversi");
        selectGame.getSelectionModel().select(String.valueOf(gameType.TicTacToe));
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws IOException, InterruptedException {
        selectedGame = getGameType();
        setPlayerType(playerType.LOCAL);
        switch(getGameType()) {
            case TicTacToe:
                UIManager.createScene("TicTacToeBoard.fxml");
                break;
            case Reversi:
                UIManager.createScene("Reversi.fxml");
                reversiTemp = new ReversiTemp();
                reversiTemp.startReversi(1,2);

                break;
        }
    }

    @FXML
    protected void handleOnlineButtonAction(ActionEvent event) throws IOException {
        UIManager.createScene("Onlinelogin.fxml");
    }

    public GameType getGameType(){
        GameType game  = GameType.valueOf(selectGame.getSelectionModel().getSelectedItem());
        return game;
    }

    public playertype getPlayerType() {
        return playerType;
    }

    public void setPlayerType(playertype playerType) {
        this.playerType = playerType;
    }
}
