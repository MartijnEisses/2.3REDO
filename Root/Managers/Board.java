package Root.Managers;

public class Board {

    /*
     * Reversi Board Cheat Sheet
     *
     * 0,0|1,0|2,0|3,0|4,0|5,0|6,0|7,0
     * -------------------------------
     * 0,1|1,1|2,1|3,1|4,1|5,1|6,1|7,1
     * -------------------------------
     * 0,2|1,2|2,2|3,2|4,2|5,2|6,2|7,2
     * -------------------------------
     * 0,3|1,3|2,3|3,3|4,3|5,3|6,3|7,3
     * -------------------------------
     * 0,4|1,4|2,4|3,4|4,4|5,4|6,4|7,4
     * -------------------------------
     * 0,5|1,5|2,5|3,5|4,5|5,5|6,5|7,5
     * -------------------------------
     * 0,6|1,6|2,6|3,6|4,6|5,6|6,6|7,6
     * -------------------------------
     * 0,7|1,7|2,7|3,7|4,7|5,7|6,7|7,7
     *
     */

    protected int[][] board;

    public Board(int x, int y) {
        this.board = new int[x][y];
    }

    public int[][] getBoard() {
        return this.board;
    }

    public void setStone(int x, int y, int value) {
        board[x][y] = value;
        //System.out.println("Move is legal on position " + x + "-"  +y + " for player " + value);
    }

    public void boardChange(int[][] newBord) {
        board = newBord;
    }

    public int getStone(int x, int y) {
        return board[x][y];
    }

    public boolean isEmpty(int x, int y) {
        return board[x][y] == 0;
    }

    public void drawBoard() {
        System.out.println("------------Dit is de methode drawBoard() in Board------------");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[j][i]);
            }
            System.out.print("\n");
        }
    }

    public boolean fullBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[1].length; j++) {
                if(board[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }
}
