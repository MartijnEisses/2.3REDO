package Root.Players;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RandomAI {

    private Random random = new Random();
    private int[] result;

    public int[] setRandomMove (ArrayList<String> legalMoves, int[][] board, int cp) throws InterruptedException {
        try {
            TimeUnit.SECONDS.sleep(1);
            int randomZet = random.nextInt(legalMoves.size());
            String coordinates = legalMoves.get(randomZet);
            String[] move = coordinates.split("-");
            result = new int[move.length];
            for (int i = 0; i < move.length; i++) {
                result[i] = Integer.parseInt(move[i]);
            }
            return result;
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return result;
    }
}
