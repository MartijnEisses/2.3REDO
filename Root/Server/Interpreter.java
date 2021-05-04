package Root.Server;

import Root.Main;
import Root.Managers.UIManager;
import Root.Pages.ReversiBoard;
import Root.Pages.ReversiController;
import Root.Pages.ReversiTemp;
import Root.Players.RandomAI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interpreter {
    private String GAMETYPE;
    private String PLAYERTOMOVE;
    private String OPPONENT;
    private static List<String> playerList;
    private static int gameID;
    private static List<String> legalmovesAI;
    private static String gameChallenge;
    private int playerAI;
    private int playerOpponent;
    private ReversiController reversiController;
    private RandomAI randomAI;
    private ReversiBoard reversiBoard;

    /*
        Note: zie protocol.txt op blackboard om de input te zien van de server
     */

    public Interpreter() {
        playerList = new ArrayList<>();
        legalmovesAI = new ArrayList<>();
        reversiController = new ReversiController();
        randomAI = new RandomAI();
    }

    public void inputInterpreter(String inputCommand) throws InterruptedException, IOException {
        inputCommand = inputCommand.replaceAll("[^A-Za-z0-9 ]", "");
        String[] commands = inputCommand.split(" ");
        //System.out.println("Volgende line is van inputInterpreter:");
        System.out.println(Arrays.toString(commands));
        switch (commands[0]) {
            case "OK":
                System.out.println("Confim message from server, command is accepted!");
                break;
            case "ERR":
                System.out.println("Command is not accept, there is an error!");
                break;
            case "SVR":
                switch (commands[1]) {
                    case "GAMELIST":
                        System.out.println("Gamelist of games");
                        break;
                    case "PLAYERLIST":
                        System.out.println("Playerlist of all players");

                        //for (int i = 2; i < commands.length; i++) {
                          //  String temp = commands[i];
                         //   playerList.add(temp);
                            //System.out.println(commands[i]);
                       // }
                        break;
                    case "HELP":
                        break;
                    case "GAME":
                        switch (commands[2]) {
                            case "MATCH":
                                switch (commands[3]) {
                                    case "PLAYERTOMOVE":
                                        PLAYERTOMOVE = commands[4];
                                        GAMETYPE = commands[6];
                                        OPPONENT = commands[8];
                                        //System.out.println("start van PLAYER TO MOVE TEST");
                                        if (PLAYERTOMOVE.equals(OPPONENT)) {
                                            System.out.println("Opponent is color black");
                                            setPlayerAI(2);
                                            setPlayerOpponent(1);
                                            reversiController.gameController(getPlayerOpponent(), getPlayerAI());
                                        } else {
                                            System.out.println("ai has color black");
                                            setPlayerAI(1);
                                            setPlayerOpponent(2);
                                            reversiController.gameController(getPlayerAI(),getPlayerOpponent());
                                        }
                                        System.out.println("Player to move: " + PLAYERTOMOVE);
                                        break;
                                }
                                break;
                            case "MOVE":
                                try{
                                    int zet = Integer.parseInt(commands[6]);
                                    int[] position;
                                    System.out.println(commands[6]);
                                    if (commands[4].equals(OPPONENT)) {
                                        System.out.println("Received move from opponent");
                                        System.out.println("Opponent made move on: " + zet);
                                        position = reversiController.convertToBoardPosition(zet);
                                        //Verwerk de move van de opponent.
                                        System.out.println("position is:  " + position[0] + " " + position[1]);
                                        reversiController.doMove(getPlayerOpponent(), position);
                                    } else {
                                        //position = reversiController.convertToBoardPosition(zet);
                                        //System.out.println("Received move from ai");
                                        //System.out.println("ai made move on: " + zet);
                                    }
                                }catch (Exception e){
                                    System.out.println("Move is illegal");
                                    e.printStackTrace();

                                }
                                break;

                            case "YOURTURN":
                                //ai moet weten dat het zijn beurt is.
                                System.out.println("Its your turn ai make a good move...");
                                int[] aiSET;
                                aiSET = randomAI.setRandomMove(reversiController.legalMoves(getPlayerAI()), reversiController.getBoard(), getPlayerAI());
                                //reversiController.doMove(getPlayerAI(),aiSET);
                                System.out.println("AI has these legal moves: " + reversiController.legalMoves(getPlayerAI()));
                                System.out.println("X: " + aiSET[0] + " and y: " + aiSET[1]);
                                int sendINT = reversiController.LocationToInt(aiSET);
                                System.out.println("Sending to opponent: " + sendINT + " for player: " + getPlayerAI());
                                reversiController.doMove(getPlayerAI(),aiSET);
                                Main.connection.setMove(sendINT);
                                break;
                            case "CHALLENGE":
                                switch (commands[3]) {
                                    case "CHALLENGER":
                                        //stuur challenge accept terug.
                                        gameID = Integer.parseInt(commands[6]);
                                        System.out.println("Challenged by " + commands[4] + " for game: " + commands[8] + " gameID :" + commands[6]);
                                        gameChallenge = commands[4];
                                        break;
                                    case "CANCELLED":
                                        //challenge is door de uitdager gecanceld
                                        System.out.println("Match has been cancelled");
                                        break;
                                }
                                break;
                            case "WIN":
                                //info over win
                                //alle stenen moeten worden gereset.
                                //Display that user has won.Terug naar online screen en display user has won.
                                System.out.println("You have won nice job!");
                                //UIManager.createScene("Onlinelobby.fxml");
                                reversiController.emptyBoard();
                                reversiController.drawBoard();
                                break;
                            case "LOSS":
                                //info over loss.
                                //alle stenen moeten worden gereset.
                                //Display that user has lost. Terug naar online screen en display user has lost
                                System.out.println("You have lost you suck!");
                                //UIManager.createScene("Onlinelobby.fxml");
                                reversiController.emptyBoard();
                                reversiController.drawBoard();
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + commands[2]);
                        }
                        break;
                }
                break;
        }
    }


    public int getGameID() {
        return gameID;
    }

    public List<String> getPlayerList() {
        return playerList;
    }

    public String getGameChallenge() {
        return gameChallenge;
    }

    public void setGameChallenge(String name){
        gameChallenge = name;
    }

    public int getPlayerAI(){
        return playerAI;
    }

    public int getPlayerOpponent(){
        return playerOpponent;
    }

    public void setPlayerAI(int number){
        this.playerAI = number;
    }

    public void setPlayerOpponent(int playerOpponent) {
        this.playerOpponent = playerOpponent;
    }
}
/*
class AlertHelpers {

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}*/