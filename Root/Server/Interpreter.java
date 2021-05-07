package Server;


import Managers.GameType;
import Managers.ReversiManager;
import Managers.UIManager;

import Players.ReversiAI;
import Players.playertype;
import javafx.application.Platform;
import Managers.Main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Interpreter {
    private String GAMETYPE;
    private String PLAYERTOMOVE;
    private String OPPONENT;
    private static List<String> challengeList;
    private static int gameID;
    private static String gameChallenge;
    private int playerAI;
    private int playerOpponent;
    private ReversiManager reversiManager;
    private ReversiAI reversiAI;

    /*
        Note: zie protocol.txt op blackboard om de input te zien van de server
     */

    public Interpreter() {
        challengeList = new ArrayList<>();
        reversiManager = new ReversiManager();
        reversiAI = new ReversiAI();
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
                                            reversiManager.gameController(playertype.ONLINE,playertype.AI, GameType.ONLINE);
                                        } else {
                                            System.out.println("ai has color black");
                                            setPlayerAI(1);
                                            setPlayerOpponent(2);
                                            reversiManager.gameController(playertype.AI, playertype.ONLINE, GameType.ONLINE);
                                        }
                                        System.out.println("Player to move: " + PLAYERTOMOVE);
                                        Platform.runLater(new Runnable() {
                                            public void run() {
                                                try {
                                                    UIManager.createScene("Reversi.fxml");
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        break;
                                }
                                break;
                            case "MOVE":
                                try{
                                    int zet = Integer.parseInt(commands[6]);
                                    int[] position;
                                    System.out.println(commands[6]);
                                    if (commands[4].equals(OPPONENT)) {
                                        System.out.println("Opponent made move on: " + zet);
                                        position = reversiManager.convertToBoardPosition(zet);
                                        reversiManager.doMove(getPlayerOpponent(), position);
                                    } else {
                                    }
                                }catch (Exception e){
                                    System.out.println("Move is illegal");
                                    e.printStackTrace();
                                }
                                break;
                            case "YOURTURN":
                                System.out.println("Its your turn ai make a good move...");
                                int[] aiSET;
                                aiSET = reversiAI.getBestMove(reversiManager.legalMoves(getPlayerAI()), reversiManager.getBoard(), getPlayerAI());
                                System.out.println("AI has these legal moves: " + reversiManager.legalMoves(getPlayerAI()));
                                System.out.println("X: " + aiSET[0] + " and y: " + aiSET[1]);
                                int sendINT = reversiManager.LocationToInt(aiSET);
                                System.out.println("Sending to opponent: " + sendINT + " for player: " + getPlayerAI());
                                reversiManager.doMove(getPlayerAI(),aiSET);
                                Main.connection.setMove(sendINT);
                                break;
                            case "CHALLENGE":
                                switch (commands[3]) {
                                    case "CHALLENGER":
                                        gameID = Integer.parseInt(commands[6]);
                                        System.out.println("Challenged by " + commands[4] + " for game: " + commands[8] + " gameID :" + commands[6]);
                                        String addToChallengeList = commands[4] + " " + commands[6];
                                        gameChallenge = commands[4];
                                        challengeList.add(addToChallengeList);
                                        System.out.println(challengeList);
                                        break;
                                    case "CANCELLED":
                                        System.out.println("Match has been cancelled");
                                        break;
                                }
                                break;
                            case "WIN":
                                System.out.println("You have won nice job!");
                                Platform.runLater(new Runnable() {
                                    public void run() {
                                        try {
                                            UIManager.createScene("Onlinelobby.fxml");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                reversiManager = new ReversiManager();
                                reversiManager.drawBoard();
                                break;
                            case "LOSS":
                                System.out.println("You have lost you suck!");
                                Platform.runLater(new Runnable() {
                                    public void run() {
                                        try {
                                            UIManager.createScene("Onlinelobby.fxml");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                                reversiManager = new ReversiManager();
                                reversiManager.drawBoard();
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

    public List<String> getChallengeList() {
        return challengeList;
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
