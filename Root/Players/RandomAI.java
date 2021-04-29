package Root.Players;

import java.util.ArrayList;
import java.util.Random;

public class RandomAI {
    private Random random = new Random();

    public int[][] calculateRandomMove(ArrayList<String> legalMoves, int[][] board, int cp) {
        if(legalMoves.size() == 0) {
            System.out.println("no move for player: " + cp);
            return board;
        }
        int randomZet = random.nextInt(legalMoves.size());
        System.out.println(legalMoves.get(randomZet));
        String coordinates = legalMoves.get(randomZet);
        String[] move = coordinates.split("-");
        int x = Integer.parseInt(move[1]);
        int y = Integer.parseInt(move[0]);
        //int[][] newBoard =  doMove(board, cp, x, y);
        System.out.print("Random AI choose: " + x + "  " + y);
        return null;
       // return newBoard;
    }




}
