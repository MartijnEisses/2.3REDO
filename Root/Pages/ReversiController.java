package Root.Pages;

import Root.Managers.Board;
import Root.Managers.GameType;
import Root.Managers.UIManager;
import Root.Players.ReversiAI;
import Root.Players.playertype;
import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ReversiController extends Board implements Initializable {

    private int currentPlayer =1;
    private int playerOne;
    private int playerTwo;
    private GameType gameType;
    private ReversiAI randomAI;
    //public int[][] board;
    private ReversiBoard reversiBoard;
    private ReversiTemp reversiTemp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         emptyBoard();
        //curBoard = getBoard();
        //reversiBoard = new ReversiBoard();
    }

    public ReversiController() {
        super(8, 8);
        setStone(3,3,2);
        setStone(4,4,2);
        setStone(3,4,1);
        setStone(4,3,1);
    }

    /**
     * GameController voor Reversi
     * @param player1 Vaststellen wie speler1 is.
     * @param player2 Vaststellen wie speler2 is.
     * @param gametype Controleren of het een Online game is of Offline
     * @throws IOException
     * @throws InterruptedException
     */

    public void gameController(playertype player1, playertype player2, GameType gametype) throws IOException, InterruptedException {
        if(gametype.equals(GameType.OFFLINE)){
            this.playerOne = 1;
            this.playerTwo = 2;
            this.gameType = GameType.OFFLINE;
            reversiTemp = new ReversiTemp();
            reversiTemp.startReversi();
            reversiBoard = new ReversiBoard();
        }
        else if(gametype.equals(GameType.ONLINE)){
            this.gameType = GameType.ONLINE;
            reversiBoard = new ReversiBoard();
            if(player1.equals(playertype.AI)){
                this.playerOne =1;
                this.playerTwo =2;
            }
            if(player1.equals(playertype.ONLINE)){
                this.playerOne =2;
                this.playerTwo =1;
            }
        }

    }

    /**
     * @param cp give current player so method know which moves to look for
     * @return returns an arraylist with all possible moves to make with an indicator for how many stones will be turned this move
     */
    public ArrayList<String> legalMoves(int cp) {
       // board = getBoard();
        List<String> allMoves = new ArrayList<String>();
        int tot = 0;
        int checker1;
        int checker2;
        int curp = 2;
        int nCurp = 1;
        if (cp == 1) {
            curp = 1;
            nCurp = 2;
        }
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == 0) {
                    try {
                        if (board[x + 1][y] == nCurp) {
                            checker1 = x + 1;
                            while (board[checker1][y] == nCurp) {
                                checker1++;
                                tot++;
                            }
                            if (board[checker1][y] == curp) {
                                allMoves.add(y + "-" + x + ":" + tot);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    tot=0;
                    try {
                        if (board[x - 1][y] == nCurp) {
                            checker1 = x - 1;
                            while (board[checker1][y] == nCurp) {
                                checker1--;
                                tot++;
                            }
                            if (board[checker1][y] == curp) {
                                allMoves.add(y + "-" + x + ":"+ tot);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //e.printStackTrace();
                    }
                    tot=0;
                    try {
                        if (board[x][y + 1] == nCurp) {
                            checker2 = y + 1;
                            while (board[x][checker2] == nCurp) {
                                checker2++;
                                tot++;
                            }
                            if (board[x][checker2] == curp) {
                                allMoves.add(y + "-" + x + ":"+ tot);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    tot=0;
                    try {
                        if (board[x][y - 1] == nCurp) {
                            checker2 = y - 1;
                            while (board[x][checker2] == nCurp) {
                                checker2--;
                                tot++;
                            }
                            if (board[x][checker2] == curp) {
                                allMoves.add(y + "-" + x + ":"+ tot);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    tot=0;
                    try {
                        if (board[x + 1][y + 1] == nCurp) {
                            checker1 = x + 1;
                            checker2 = y + 1;
                            while (board[checker1][checker2] == nCurp) {
                                checker1++;
                                checker2++;
                                tot++;
                            }
                            if (board[checker1][checker2] == curp) {
                                allMoves.add(y + "-" + x + ":"+ tot);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    tot=0;
                    try {
                        if (board[x - 1][y - 1] == nCurp) {
                            checker1 = x - 1;
                            checker2 = y - 1;
                            while (board[checker1][checker2] == nCurp) {
                                checker1--;
                                checker2--;
                                tot++;
                            }
                            if (board[checker1][checker2] == curp) {
                                allMoves.add(y + "-" + x + ":"+ tot);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //e.printStackTrace();
                    }
                    tot=0;
                    try {
                        if (board[x + 1][y - 1] == nCurp) {
                            checker1 = x + 1;
                            checker2 = y - 1;
                            while (board[checker1][checker2] == nCurp) {
                                checker1++;
                                checker2--;
                                tot++;
                            }
                            if (board[checker1][checker2] == curp) {
                                allMoves.add(y + "-" + x + ":"+ tot);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    tot=0;
                    try {
                        if (board[x - 1][y + 1] == nCurp) {
                            checker1 = x - 1;
                            checker2 = y + 1;
                            while (board[checker1][checker2] == nCurp) {
                                //tempChanges++;
                                checker1--;
                                checker2++;
                                tot++;
                            }
                            if (board[checker1][checker2] == curp) {
                                allMoves.add(y + "-" + x + ":"+ tot);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //e.printStackTrace();
                    }
                    tot=0;
                }
            }
        }
        return (ArrayList<String>) allMoves;
    }

    /**
     *
     * @param cp give current player so method knows who to set for
     * @param move give coordinates for the move
     *             this method changes the play board
     * @throws InterruptedException
     */
    public void doMove(int cp, int[] move) throws InterruptedException {
        //TimeUnit.SECONDS.sleep(1);
        //curBoard = getBoard();
        int x = move[1];
        int y = move [0];
        int[][] tempBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        int[][] newBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        int[][] newBoard2 = new int[8][8];
        boolean change = false;
        int xC;
        int yC;
        int curp = 2;
        int nCurp = 1;
        if (cp == 1) {
            curp = 1;
            nCurp = 2;
        }
        if (board[x][y] == 0) {
            try {
                if (board[x + 1][y] == nCurp) {
                    xC = x + 1;
                    while (board[xC][y] == nCurp) {
                        tempBoard[xC][y] = curp;
                        xC++;
                    }
                    if (board[xC][y] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
                tempBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
            try {
                if (board[x - 1][y] == nCurp) {
                    xC = x - 1;
                    while (board[xC][y] ==
                            nCurp) {
                        tempBoard[xC][y] = curp;
                        xC--;
                    }
                    if (board[xC][y] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            tempBoard = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
            try {
                if (board[x][y + 1] == nCurp) {
                    yC = y + 1;
                    while (board[x][yC] == nCurp) {
                        tempBoard[x][yC] = curp;
                        yC++;
                    }
                    if (board[x][yC] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }

            } catch (ArrayIndexOutOfBoundsException e) {
            }
            tempBoard = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
            try {
                if (board[x][y - 1] == nCurp) {
                    yC = y - 1;
                    while (board[x][yC] == nCurp) {
                        tempBoard[x][yC] = curp;
                        yC--;
                    }
                    if (board[x][yC] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            tempBoard = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
            try {
                if (board[x + 1][y + 1] == nCurp) {
                    xC = x + 1;
                    yC = y + 1;
                    while (board[xC][yC] == nCurp) {
                        tempBoard[xC][yC] = curp;
                        xC++;
                        yC++;
                    }
                    if (board[xC][yC] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            tempBoard = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
            try {
                if (board[x - 1][y - 1] == nCurp) {
                    xC = x - 1;
                    yC = y - 1;
                    while (board[xC][yC] == nCurp) {
                        tempBoard[xC][yC] = curp;
                        xC--;
                        yC--;
                    }
                    if (board[xC][yC] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            tempBoard = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
            try {
                if (board[x + 1][y - 1] == nCurp) {
                    xC = x + 1;
                    yC = y - 1;
                    while (board[xC][yC] == nCurp) {
                        tempBoard[xC][yC] = curp;
                        xC++;
                        yC--;
                    }
                    if (board[xC][yC] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            tempBoard = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
            try {

                if (board[x - 1][y + 1] == nCurp) {
                    xC = x - 1;
                    yC = y + 1;
                    while (board[xC][yC] == nCurp) {
                        tempBoard[xC][yC] = curp;
                        xC--;
                        yC++;
                    }
                    if (board[xC][yC] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            if (change == true) {
                newBoard[x][y] = curp;
                if (currentPlayer == 1) {
                    currentPlayer++;
                } else {
                    currentPlayer--;
                }
                System.out.println("     0 1 2 3 4 5 6 7");
                for (int i = 0; i < board.length; i++) {
                    System.out.print("  " + (i) + " ");
                    for (int p = 0; p < board[i].length; p++) {
                        System.out.print(" " + newBoard[p][i]);
                        if(newBoard[p][i] == 1) {
                            //reversiBoard.setStoneOnBoard(p,i,currentPlayer);
                            //setStone(p,i,1);
                        }
                        else if(newBoard[p][i] == 2){
                            //reversiBoard.setStoneOnBoard(p,i,currentPlayer);
                            //setStone(p,i,2);
                        }
                    }
                    System.out.println();
                }
                board = newBoard;
                boardChange(board);
                System.out.println("Black has:  " + countStones().get(0) + " stones");
                System.out.println("White has: " + countStones().get(1) + " stones");
                //syncBoards(board);
                //reversiBoard.updateBoard();
            }
        }
    }

    public int[][] getCurBoard(){
        return board;
    }

    public void syncBoards(int[][] newBoard){
        int[][] tempBoard = newBoard;
        for(int i =0; i< tempBoard.length; i++){
            for(int j=0; j< tempBoard[i].length; j++){
                if(tempBoard[i][j] == 1) {
                    reversiBoard.setStoneOnBoard(i,j, 1);
                }
                else if(tempBoard[i][j] == 2){
                    reversiBoard.setStoneOnBoard(i,j,2);
                }
            }
        }
    }

}
