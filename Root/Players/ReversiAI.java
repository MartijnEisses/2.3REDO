package Root.Players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ReversiAI {
    private String[] badToGoodMoves = new String[] {"1-1","1-2","1-3","1-4","1-5","1-6","2-1","2-6","3-1","3-6","4-1","4-6","5-1","5-6","6-1","6-6","2-2","2-3","2-4","2-5","3-2","3-3","3-4","3-5","4-2","4-3","4-4","4-5","5-2","5-3","5-4","5-5","6-2","6-3","6-4","6-5","0-1","0-2","0-3","0-4","0-5","0-6","1-0","2-0","3-0","4-0","5-0","6-0","1-7","2-7","3-7","4-7","5-7","6-7","0-0","0-7","7-0","7-7"};
    private String[] badMoves = new String[] {"0-6","1-0","0-1","1-1","1-2","1-3","1-4","1-5","1-7","7-1","1-6","2-1","2-6","3-1","3-6","4-1","4-6","5-1","5-6","6-1","6-2","6-3","6-4","6-5","6-6","6-7","7-6"};
    private String[] okMoves = new String[] {"2-2","2-3","2-4","2-5","3-2","3-3","3-4","3-5","4-2","4-3","4-4","4-5","5-2","5-3","5-4","5-5"};
    private String[] goodMoves = new String[] {"0-2","0-3","0-4","0-5","2-0","3-0","4-0","5-0","6-0","2-7","3-7","4-7","5-7","7-3"};
    private String[] bestMoves = new String[] {"0-0","0-7","7-0","7-7"};
    private String bestMove ="";
    //private Random random = new Random();
    private int[] result;


    /**
     * @param legalMoves give an array list with all legal moves
     * @param board give the current play board
     * @param cp give current player
     * @return the coordinates for the best move acording to our program
     * @throws InterruptedException
     */
    public int[] getBestMove (ArrayList<String> legalMoves, int[][] board, int cp) throws InterruptedException {
       int taken=0;
       int mostTaken=0;
        List<String> moveList = new ArrayList(Arrays.asList(legalMoves));
        mostTaken = 0;

        for (String pos: badMoves){
            for(String test : legalMoves){
                String[] parts = test.split(":");
                taken = Integer.parseInt(parts[1]);
                if(pos.equals(parts[0]) && taken > mostTaken) {
                    bestMove = pos;
                    mostTaken = taken;
                }
            }
        }

        mostTaken = 0;
        for (String pos: okMoves){
            for(String test : legalMoves){
                String[] parts = test.split(":");
                taken = Integer.parseInt(parts[1]);
                if(pos.equals(parts[0]) && taken > mostTaken) {
                    bestMove = pos;
                    mostTaken = taken;
                }
            }
        }

        mostTaken = 0;
        for (String pos: goodMoves){
            for(String test : legalMoves){
                String[] parts = test.split(":");
                taken = Integer.parseInt(parts[1]);
                if(pos.equals(parts[0]) && taken > mostTaken) {
                    bestMove = pos;
                    mostTaken = taken;
                }
            }
        }

        mostTaken = 0;
        for (String pos: bestMoves){
            for(String test : legalMoves){
                String[] parts = test.split(":");
                taken = Integer.parseInt(parts[1]);
                if(pos.equals(parts[0]) && taken > mostTaken) {
                    bestMove = pos;
                    mostTaken = taken;
                }
            }
        }

        try {
            System.out.println("The best move of the ai is: " + bestMove);
            Thread.sleep(500);
            String[] move = bestMove.split("-");
            result = new int[move.length];
            for (int i = 0; i < move.length; i++) {
                result[i] = Integer.parseInt(move[i]);
            }
            return result;
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return result;
    }
}
