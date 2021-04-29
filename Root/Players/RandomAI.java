package Root.Players;

import Root.Pages.ReversiTemp;

import java.util.ArrayList;
import java.util.Random;

public class RandomAI {
    private Random random = new Random();
    private ReversiTemp reversiTemp;
    public int[][] calculateRandomMove(ArrayList<String> legalMoves, int[][] board, int cp) {
        reversiTemp = new ReversiTemp();
        if(legalMoves.size() == 0) {
            System.out.println("no move for player: " + cp);
            System.out.println("You Won, the AI lost");
            return board;
        }
        System.out.println(legalMoves);
        int randomZet = random.nextInt(legalMoves.size());
        //System.out.println(legalMoves.get(randomZet));
        String coordinates = legalMoves.get(randomZet);
        String[] move = coordinates.split("-");
        int x = Integer.parseInt(move[0]);
        int y = Integer.parseInt(move[1]);
        int[][] newBoard = reversiTemp.doMove(board, cp, x, y);
        System.out.print("Random AI choose position x: " + x + " and y:  " + y);

        return newBoard;
    }
}
