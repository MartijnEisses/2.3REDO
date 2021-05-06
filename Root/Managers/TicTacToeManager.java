package Root.Managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicTacToeManager extends Board implements Manager {

    @Override
    public void Start() {
        // TODO Auto-generated method stub

    }

    @Override
    public void Update() {
        // TODO Auto-generated method stub

    }

    Scanner in = new Scanner(System.in);

    public enum Color {
        // AI is BLACK player is WHITE
        EMPTY, BLACK, WHITE
    }

    private int[][] board = { { Color.EMPTY.ordinal(), Color.EMPTY.ordinal(), Color.EMPTY.ordinal() },
            { Color.EMPTY.ordinal(), Color.EMPTY.ordinal(), Color.EMPTY.ordinal() },
            { Color.EMPTY.ordinal(), Color.EMPTY.ordinal(), Color.EMPTY.ordinal() } };
    public int[][] boardPosition = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
    public int[][] oBoardPosition = { { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 }, { 2, 1 },
            { 2, 2 } };

    public Map<Integer, Integer> scores = new HashMap<Integer, Integer>();

    Board b;
    boolean endGame = false;
    int turn = 1;
    private static final int MAX_DEPTH = 6;

    public TicTacToeManager() {
        super(3, 3);
        b = new Board(3, 3);
        scores.put(Color.BLACK.ordinal(), 10);
        scores.put(Color.WHITE.ordinal(), -10);
        scores.put(Color.EMPTY.ordinal(), 0);
    }

    public int BestMove() {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                // is there an empty spot
                if (board[i][j] == Color.EMPTY.ordinal()) {
                    board[i][j] = Color.BLACK.ordinal();
                    turn++;
                    int score = Minimax(board, MAX_DEPTH, false);
                    turn--;
                    board[i][j] = Color.EMPTY.ordinal();
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = boardPosition[i][j];
                    }
                }
            }
        }
        return bestMove;
    }

    private int Minimax(int[][] board, int depth, boolean isMaximizing) {
        int boardVal = evaluateBoard(board, depth);
        if (Math.abs(boardVal) > 0 || depth == 0 || turn == 9) {
            return boardVal;
        }

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    // is there an empty spot
                    if (board[i][j] == Color.EMPTY.ordinal()) {
                        board[i][j] = Color.BLACK.ordinal();
                        turn++;
                        bestScore = Math.max(bestScore, Minimax(board, depth - 1, false));
                        board[i][j] = Color.EMPTY.ordinal();
                        turn--;
                    }
                }
            }
            return bestScore;
        } else {
            int lowestScore = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) { // is there an empty spot
                    if (board[i][j] == Color.EMPTY.ordinal()) {
                        board[i][j] = Color.WHITE.ordinal();
                        turn++;
                        lowestScore = Math.min(lowestScore, Minimax(board, depth - 1, true));
                        turn--;
                        board[i][j] = Color.EMPTY.ordinal();
                    }
                }
            }
            return lowestScore;
        }

    }

    public void Move(int position, int turn) {
        Color color = Color.EMPTY;
        if (turn % 2 == 0) {
            color = Color.BLACK;
        } else if (turn % 2 == 1) {
            color = Color.WHITE;
        }

        // System.out.println(color + " Made a move on position: " + position + " on
        // turn " + turn);

        switch (position) {
            case 0:
                if (ValidMove(0, 0)) {
                    board[0][0] = color.ordinal();
                    setStone(0, 0, turn);
                }

                break;
            case 1:
                if (ValidMove(0, 1)) {
                    board[0][1] = color.ordinal();
                    setStone(0, 1, turn);
                }

                break;
            case 2:
                if (ValidMove(0, 2)) {
                    board[0][2] = color.ordinal();
                    setStone(0, 2, turn);
                }

                break;
            case 3:
                if (ValidMove(1, 0)) {
                    board[1][0] = color.ordinal();
                    setStone(1, 0, turn);
                }

                break;
            case 4:
                if (ValidMove(1, 1)) {
                    board[1][1] = color.ordinal();
                    setStone(1, 1, turn);
                }

                break;
            case 5:
                if (ValidMove(1, 2)) {
                    board[1][2] = color.ordinal();
                    setStone(1, 2, turn);
                }

                break;
            case 6:
                if (ValidMove(2, 0)) {
                    board[2][0] = color.ordinal();
                    setStone(2, 0, turn);
                }

                break;
            case 7:
                if (ValidMove(2, 1)) {
                    board[2][1] = color.ordinal();
                    setStone(2, 1, turn);
                }

                break;
            case 8:
                if (ValidMove(2, 2)) {
                    board[2][2] = color.ordinal();
                    setStone(2, 2, turn);
                }
                break;
            default:
                break;
        }

        for (int i = 0; i < board.length; i++) {
            System.out.println(board[0][i] + "" + board[1][i] + "" + board[2][i]);
        }

    }

    public Boolean ValidMove(int x, int y) {
        if (board[x][y] == Color.EMPTY.ordinal()) {
            return true;
        } else
            System.out.println("INVALID MOVE");
        return false;
    }

    public Color CheckForWin() {
        if (board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] == Color.BLACK.ordinal()) {
            return Color.BLACK;
        }
        if (board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][0] == Color.WHITE.ordinal()) {
            return Color.WHITE;
        }

        if (board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] == Color.BLACK.ordinal()) {
            return Color.BLACK;
        }
        if (board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][0] == Color.WHITE.ordinal()) {
            return Color.WHITE;
        }

        if (board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] == Color.BLACK.ordinal()) {
            return Color.BLACK;
        }
        if (board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][0] == Color.WHITE.ordinal()) {
            return Color.WHITE;
        }

        if (board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] == Color.BLACK.ordinal()) {
            return Color.BLACK;
        }
        if (board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[0][0] == Color.WHITE.ordinal()) {
            return Color.WHITE;
        }

        if (board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] == Color.BLACK.ordinal()) {
            return Color.BLACK;
        }
        if (board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[0][1] == Color.WHITE.ordinal()) {
            return Color.WHITE;
        }

        if (board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] == Color.BLACK.ordinal()) {
            return Color.BLACK;
        }
        if (board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[0][2] == Color.WHITE.ordinal()) {
            return Color.WHITE;
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == Color.BLACK.ordinal()) {
            return Color.BLACK;
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] == Color.WHITE.ordinal()) {
            return Color.WHITE;
        }

        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] == Color.BLACK.ordinal()) {
            return Color.BLACK;
        }
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] == Color.WHITE.ordinal()) {
            return Color.WHITE;
        }

        // a tie
        if (turn == 9) {
            return Color.EMPTY;
        }
        return null; // no win/loss/tie
    }

    private int evaluateBoard(int[][] board, int depth) {
        int rowSum = 0;
        int bWidth = board.length;
        int blackWin = scores.get(Color.BLACK.ordinal()) * bWidth;
        int whiteWin = scores.get(Color.WHITE.ordinal()) * bWidth;

        // Check rows for winner.
        for (int row = 0; row < bWidth; row++) {
            for (int col = 0; col < bWidth; col++) {
                rowSum += scores.get(board[row][col]);
            }
            if (rowSum == blackWin) {
                return 10 + depth;
            } else if (rowSum == whiteWin) {
                return -10 - depth;
            }
            rowSum = 0;
        }

        // Check columns for winner.
        rowSum = 0;
        for (int col = 0; col < bWidth; col++) {
            for (int row = 0; row < bWidth; row++) {
                rowSum += scores.get(board[row][col]);
            }
            if (rowSum == blackWin) {
                return 10 + depth;
            } else if (rowSum == whiteWin) {
                return -10 - depth;
            }
            rowSum = 0;
        }

        // Check diagonals for winner.
        // Top-left to bottom-right diagonal.
        rowSum = 0;
        for (int i = 0; i < bWidth; i++) {
            rowSum += scores.get(board[i][i]);
        }
        if (rowSum == blackWin) {
            return 10 + depth;
        } else if (rowSum == whiteWin) {
            return -10 - depth;
        }

        // Top-right to bottom-left diagonal.
        rowSum = 0;
        int indexMax = bWidth - 1;
        for (int i = 0; i <= indexMax; i++) {
            rowSum += scores.get(board[i][indexMax - i]);
        }
        if (rowSum == blackWin) {
            return 10 + depth;
        } else if (rowSum == whiteWin) {
            return -10 - depth;
        }

        return 0;
    }

    public void Win(Color winner) {
        if (winner != Color.EMPTY) {
            System.out.println(winner + " HAS WON!!");
        } else {
            System.out.println("It's a TIE");
        }

    }
}
