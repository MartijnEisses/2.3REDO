package Pages;

import Managers.Board;
import Managers.ReversiManager;
import Managers.UIManager;
;
import Players.ReversiAI;
import Players.playertype;
import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ReversiTemp extends Board implements Initializable {
    Terminal term = new Terminal();
    private int currentPlayer; // wit is 2 - zwart is 1.
    private ReversiAI reversiAI;
    private Random random;
    private boolean finished = true;

    private ReversiManager reversiManager;
    private ReversiBoard reversiBoard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public ReversiTemp() {
        super(8, 8);
        setStone(3,3,2);
        setStone(4,4,2);
        setStone(3,4,1);
        setStone(4,3,1);
        currentPlayer = 1;
        reversiAI = new ReversiAI();
        reversiBoard = new ReversiBoard();
    }

    public void startReversi() throws InterruptedException, IOException{

        reversiManager = new ReversiManager();
        System.out.println("Reversi begint!!!");
        System.out.println("Het is jou beurt!!!!!");
        Platform.runLater(new Runnable() {
            public void run() {
                try {
                    UIManager.createScene("/Reversi.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        drawBoard();

        while(finished){
            String action = term.parce();
            doSome(action);
        }
    }

    public void doSome(String doSo) throws InterruptedException {
        String[] fullWord = doSo.split(" ");
        if(fullWord[0].equals("set")){
            String[] move = fullWord[1].split("-");
            int[] result = new int[2];
            for (int i = 0; i < move.length; i++) {
                result[i] = Integer.parseInt(move[i]);
            }

            reversiManager.doMove(currentPlayer, result);
            if(currentPlayer== 1){
                currentPlayer = 2;
            }

            if(currentPlayer == 2) {
                int[] aiSET;
                aiSET = reversiAI.getBestMove(reversiManager.legalMoves(currentPlayer), reversiManager.getBoard(), currentPlayer);
                System.out.println(aiSET);
                reversiManager.doMove(currentPlayer,aiSET);
                currentPlayer = 1;
            }

            if(currentPlayer == 1){
                System.out.println("\nHet is jou beurt!!");
            }else {
                System.out.println("\nHet is de beurt van de AI!!");
            }
            reversiManager.drawBoard();
            System.out.println("You currently have: " + reversiManager.countStones().get(0) + " stones");
            System.out.println("The AI currently has: " + reversiManager.countStones().get(1)+ " stones");
        }
        if(fullWord[0].equals("moves")){
            reversiManager.legalMoves(currentPlayer);
            reversiManager.legalMoves(currentPlayer).forEach((n) -> System.out.println(n));
        }
    }


}
