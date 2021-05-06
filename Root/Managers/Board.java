package Root.Managers;

import Root.Pages.ReversiBoard;

import java.util.ArrayList;

public class Board {

    public int[][] board;
    private boolean runner = true;

    /**
     * @param x horisontal size of the board
     * @param y vertical size of the board
     */
    public Board(int x, int y) {
        this.board = new int[x][y];
    }

    /**
     * @return the current game board
     */
    public int[][] getBoard() {
        return this.board;
    }

    /**
     * @param x horisontal coördinate
     * @param y vertical coöridinate
     * @param value player setting the stone
     * 1 = black
     * 2 = white
     */
    public void setStone(int x, int y, int value) {
        this.board[x][y] = value;
    }

    /**
     * @param newBord gives a new game board
     */
    public void boardChange(int[][] newBord) {
        this.board = newBord;
    }


    public boolean isEmpty(int x, int y) {
        return this.board[x][y] == 0;
    }

    /**
     * @return shows the number of stones for both players
     */
    public ArrayList<Integer> countStones(){
        ArrayList<Integer> numberOfStones = new ArrayList<Integer>();
        int player1 = 0;
        int player2 = 0;
        for(int i = 0; i< this.board.length; i++){
            for(int j =0; j < this.board[i].length; j++){
                if(this.board[i][j] == 1){
                    player1++;
                }else if(this.board[i][j] == 2){
                    player2++;
                }
            }
        }
        numberOfStones.add(player1);
        numberOfStones.add(player2);
        return numberOfStones;
    }
    /*
    Convert integer send from server to two dimensional Array
     */
    public int[] convertToBoardPosition(int p) {
        int[] position = new int[2];
        int counter = 0;
        for (int x = 0; x < this.board.length; x++) {
            for (int y = 0; y < this.board[x].length; y++) {
                if (p == counter) {
                    position[0] = x;
                    position[1] = y;
                }
                counter++;
            }
        }
        return position;
    }

    /*
    Convert two dimensional array to integer to send to server
     */
    public int LocationToInt(int[] set){
        int x = set[0];
        int y = set[1];
        return x*8+y;
    }

    public void drawBoard() {
        System.out.println("------------Dit is de methode drawBoard() in Board------------");
        System.out.println("  0 1 2 3 4 5 6 7 ");
        for (int i = 0; i < this.board.length; i++) {
            System.out.print((i) + " ");
            for (int j = 0; j < this.board[i].length; j++) {
                System.out.print(this.board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public boolean fullBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[1].length; j++) {
                if(board[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public void emptyBoard(int x, int y){
        new Board(x,y);
    }

    public boolean isRunner() {
        return runner;
    }
    public void setRunner(Boolean b){
        runner=b;
    }
}
