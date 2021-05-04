package Root.Pages;

import Root.Managers.Board;
import Root.Players.RandomAI;
import Root.Players.playertype;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.*;

public class ReversiTemp extends Board implements Initializable {
    Terminal term = new Terminal();
    private int currentPlayer; // wit is 2 - zwart is 1.
    private RandomAI randomAI;
    private Random random;
    private boolean finished = true;
    private ReversiController reversiController;
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
        randomAI = new RandomAI();
        reversiBoard = new ReversiBoard();
    }

    public void startReversi(int player1, int player2) throws InterruptedException {
        reversiController = new ReversiController();
        System.out.println("Reversi begint!!!");
        System.out.println("Het is jou beurt!!!!!");
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
            //int temp = result[0];
           // result[0] = result[1];
           // result[1] = temp;
            reversiController.doMove(currentPlayer, result);
            if(currentPlayer== 1){
                currentPlayer = 2;
            }

            if(currentPlayer == 2) {
                int[] aiSET;
                aiSET = randomAI.setRandomMove(reversiController.legalMoves(currentPlayer), reversiController.getBoard(), currentPlayer);
                //System.out.println(aiSET[1] + "   " + aiSET[0]);
                System.out.println(aiSET);
                reversiController.doMove(currentPlayer,aiSET);
                currentPlayer = 1;
            }

            if(currentPlayer == 1){
                System.out.println("\nHet is jou beurt!!");
            }else {
                System.out.println("\nHet is de beurt van de AI!!");
            }
            reversiController.drawBoard();
            System.out.println("You currently have: " + reversiController.countStones().get(0) + " stones");
            System.out.println("The AI currently has: " + reversiController.countStones().get(1)+ " stones");
        }
        if(fullWord[0].equals("moves")){
            reversiController.legalMoves(currentPlayer);
            reversiController.legalMoves(currentPlayer).forEach((n) -> System.out.println(n));
        }
    }


}
