package Root.Managers;

import Root.Players.Player;
import Root.Players.playertype;

public class Game {

    public void offlineMode(String player1, String player2, String game){

        Player playerOne = new Player(player1, playertype.LOCAL);
        Player playerTwo = new Player(player2, playertype.AI);
    }

    public void OnlineMode(){

        Player playerOne = new Player("AI", playertype.AI);
        Player playerTwo = new Player("Tegenstander", playertype.ONLINE);

    }

}
