package Root.Pages;

import Root.Managers.Board;
import Root.Players.RandomAI;
import Root.Server.Interpreter;
import Root.Server.Serversocket;
import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ReversiController extends Board implements Initializable,Runnable {

    private int currentPlayer;
    private int playerBlack;
    private int playerWhite;
    private RandomAI randomAI;
    private int[][] curBoard;
    private ReversiBoard reversiBoard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        randomAI = new RandomAI();
        curBoard = getBoard();
        reversiBoard = new ReversiBoard();
        //syncReversiBoard.setBoard(curBoard);
    }

    public ReversiController() {
        super(8, 8);
        setStone(3,3,2);
        setStone(4,4,2);
        setStone(3,4,1);
        setStone(4,3,1);
    }

    public void gameController(int player1, int player2){
        this.playerBlack = player1;
        this.playerWhite = player2;
    }

    public ArrayList<String> legalMoves(int cp) {
        curBoard = getBoard();
        List<String> allMoves = new ArrayList<String>();

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
                if (curBoard[x][y] == 0) {
                    try {
                        if (curBoard[x + 1][y] == nCurp) {
                            checker1 = x + 1;
                            while (curBoard[checker1][y] == nCurp) {
                                checker1++;
                            }
                            if (curBoard[checker1][y] == curp) {
                                allMoves.add(y + "-" + x);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    try {
                        if (curBoard[x - 1][y] == nCurp) {
                            checker1 = x - 1;
                            while (curBoard[checker1][y] == nCurp) {
                                checker1--;
                            }
                            if (curBoard[checker1][y] == curp) {
                                allMoves.add(y + "-" + x);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //e.printStackTrace();
                    }
                    try {
                        if (curBoard[x][y + 1] == nCurp) {
                            checker2 = y + 1;
                            while (curBoard[x][checker2] == nCurp) {
                                checker2++;
                            }
                            if (curBoard[x][checker2] == curp) {
                                allMoves.add(y + "-" + x);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    try {
                        if (curBoard[x][y - 1] == nCurp) {
                            checker2 = y - 1;
                            while (curBoard[x][checker2] == nCurp) {
                                checker2--;
                            }
                            if (curBoard[x][checker2] == curp) {
                                allMoves.add(y + "-" + x);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    try {
                        if (curBoard[x + 1][y + 1] == nCurp) {
                            checker1 = x + 1;
                            checker2 = y + 1;
                            while (curBoard[checker1][checker2] == nCurp) {
                                checker1++;
                                checker2++;
                            }
                            if (curBoard[checker1][checker2] == curp) {
                                allMoves.add(y + "-" + x);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }

                    try {
                        if (curBoard[x - 1][y - 1] == nCurp) {
                            checker1 = x - 1;
                            checker2 = y - 1;
                            while (curBoard[checker1][checker2] == nCurp) {
                                checker1--;
                                checker2--;
                            }
                            if (curBoard[checker1][checker2] == curp) {
                                allMoves.add(y + "-" + x);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //e.printStackTrace();
                    }
                    try {
                        if (curBoard[x + 1][y - 1] == nCurp) {
                            checker1 = x + 1;
                            checker2 = y - 1;
                            while (curBoard[checker1][checker2] == nCurp) {
                                checker1++;
                                checker2--;
                            }
                            if (curBoard[checker1][checker2] == curp) {
                                allMoves.add(y + "-" + x);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    try {
                        if (curBoard[x - 1][y + 1] == nCurp) {
                            checker1 = x - 1;
                            checker2 = y + 1;
                            while (curBoard[checker1][checker2] == nCurp) {
                                //tempChanges++;
                                checker1--;
                                checker2++;
                            }
                            if (curBoard[checker1][checker2] == curp) {
                                allMoves.add(y + "-" + x);
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //e.printStackTrace();
                    }
                }
            }
        }
        return (ArrayList<String>) allMoves;
    }
    public void doMove(int cp, int[] move) {
        curBoard = getBoard();
        int x = move[1];
        int y = move [0];
        int[][] tempBoard = Arrays.stream(curBoard).map(int[]::clone).toArray(int[][]::new);
        int[][] newBoard = Arrays.stream(curBoard).map(int[]::clone).toArray(int[][]::new);
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
        if (curBoard[x][y] == 0) {
            try {
                if (curBoard[x + 1][y] == nCurp) {
                    xC = x + 1;
                    while (curBoard[xC][y] == nCurp) {
                        tempBoard[xC][y] = curp;
                        xC++;
                    }
                    if (curBoard[xC][y] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
                tempBoard = Arrays.stream(curBoard).map(int[]::clone).toArray(int[][]::new);
            try {
                if (curBoard[x - 1][y] == nCurp) {
                    xC = x - 1;
                    while (curBoard[xC][y] == nCurp) {
                        tempBoard[xC][y] = curp;
                        xC--;
                    }
                    if (curBoard[xC][y] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            tempBoard = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
            try {
                if (curBoard[x][y + 1] == nCurp) {
                    yC = y + 1;
                    while (curBoard[x][yC] == nCurp) {
                        tempBoard[x][yC] = curp;
                        yC++;
                    }
                    if (curBoard[x][yC] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }

            } catch (ArrayIndexOutOfBoundsException e) {
            }
            tempBoard = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
            try {
                if (curBoard[x][y - 1] == nCurp) {
                    yC = y - 1;
                    while (curBoard[x][yC] == nCurp) {
                        tempBoard[x][yC] = curp;
                        yC--;
                    }
                    if (curBoard[x][yC] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            tempBoard = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
            try {

                if (curBoard[x + 1][y + 1] == nCurp) {
                    xC = x + 1;
                    yC = y + 1;
                    while (curBoard[xC][yC] == nCurp) {
                        tempBoard[xC][yC] = curp;
                        xC++;
                        yC++;
                    }
                    if (curBoard[xC][yC] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }

            } catch (ArrayIndexOutOfBoundsException e) {
            }
            tempBoard = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
            try {
                if (curBoard[x - 1][y - 1] == nCurp) {
                    xC = x - 1;
                    yC = y - 1;
                    while (curBoard[xC][yC] == nCurp) {
                        tempBoard[xC][yC] = curp;
                        xC--;
                        yC--;
                    }
                    if (curBoard[xC][yC] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }

            } catch (ArrayIndexOutOfBoundsException e) {
            }
            tempBoard = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
            try {
                if (curBoard[x + 1][y - 1] == nCurp) {
                    xC = x + 1;
                    yC = y - 1;
                    while (curBoard[xC][yC] == nCurp) {
                        tempBoard[xC][yC] = curp;
                        xC++;
                        yC--;
                    }
                    if (curBoard[xC][yC] == curp) {
                        newBoard = Arrays.stream(tempBoard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                }

            } catch (ArrayIndexOutOfBoundsException e) {
            }
            tempBoard = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
            try {

                if (curBoard[x - 1][y + 1] == nCurp) {
                    xC = x - 1;
                    yC = y + 1;
                    while (curBoard[xC][yC] == nCurp) {
                        tempBoard[xC][yC] = curp;
                        xC--;
                        yC++;
                    }
                    if (curBoard[xC][yC] == curp) {
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
                        System.out.print(" " + newBoard[i][p]);
                    }
                    System.out.println();
                }
                curBoard = newBoard;
                boardChange(curBoard);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            syncBoards(curBoard);
                        }catch(NullPointerException e){
                            //e.printStackTrace();
                        }
                    }
                });
                //syncBoards(curBoard);
            }
        }
    }

    public int[][] getCurBoard(){
        return curBoard;
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


    public void run() {

    }
}
