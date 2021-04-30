package Root.Players;

import Root.Pages.ReversiTemp;

import java.util.ArrayList;
import java.util.Random;

public class RandomAI {

    private Random random = new Random();
    private ReversiTemp reversiTemp;

    public int[] setRandomMove (ArrayList<String> legalMoves, int[][] board, int cp) {
        int randomZet = random.nextInt(legalMoves.size());
        String coordinates = legalMoves.get(randomZet);
        String[] move = coordinates.split("-");
        int[] result = new int[move.length];
        for (int i = 0; i < move.length; i++) {
            result[i] = Integer.parseInt(move[i]);
        }
        return result;
    }
}
