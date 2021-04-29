package Root.Pages;

import Root.Main;
import Root.Managers.UIManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class Homepage {

    public Homepage()  {

    }


    @FXML
    protected void handleOnlineButtonAction(ActionEvent event) throws IOException {
        //setPlayerType(playerType.REMOTE);
        UIManager.createScene("Onlinelogin.fxml");
    }

    @FXML
    protected void handleOfflineButtonAction(ActionEvent event){
        //setPlayerType(playerType.REMOTE);
        //setScene("view/config.fxml");
    }



}
