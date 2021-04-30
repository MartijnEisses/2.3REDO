package Root.Pages;

import Root.Managers.Board;
import Root.Players.RandomAI;
import Root.Server.Interpreter;
import Root.Server.Serversocket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReversiController extends Board {
    private boolean reversiGamerunner = true;
    private int currentPlayer;
    private int playerBlack;
    private int playerWhite;
    private int AIplayer;
    private RandomAI randomAI;
    private int[][] curBoard;

    public ReversiController() {
        super(8, 8);
        setStone(3,3,2);
        setStone(4,4,2);
        setStone(3,4,1);
        setStone(4,3,1);
        randomAI = new RandomAI();
        //curBoard = getBoard();
    }

    public void gameController(int player1, int player2){
        this.playerBlack = player1;
        this.playerWhite = player2;

    }

    public ArrayList<String> legalMoves(int cp) {
        curBoard = getBoard();
        List<String> allMoves = new ArrayList<String>();
        boolean change = false;
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
                change = false;
                if (curBoard[x][y] == 0) {
                    try {
                        if (curBoard[x + 1][y] == nCurp) {
                            checker1 = x + 1;
                            while (curBoard[checker1][y] == nCurp) {
                                checker1++;
                                // tempChanges++;
                            }
                            if (curBoard[checker1][y] == curp) {
                                allMoves.add(x + "-" + y);
                                change = true;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                    try {
                        if (curBoard[x - 1][y] == nCurp) {
                            checker1 = x - 1;
                            while (curBoard[checker1][y] == nCurp) {
                                //tempChanges++;
                                checker1--;
                            }
                            if (curBoard[checker1][y] == curp) {
                                allMoves.add(x + "-" + y);
                                change = true;
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //e.printStackTrace();
                    }

                    try {
                        if (curBoard[x][y + 1] == nCurp) {
                            checker2 = y + 1;
                            while (curBoard[x][checker2] == nCurp) {
                                //tempChanges++;
                                checker2++;
                            }
                            if (curBoard[x][checker2] == curp) {
                                allMoves.add(x + "-" + y);
                                change = true;
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
                                allMoves.add(x + "-" + y);
                                change = true;
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
                                allMoves.add(x + "-" + y);
                                change = true;
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
                                allMoves.add(x + "-" + y);
                                change = true;
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
                                allMoves.add(x + "-" + y);
                                change = true;
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
                                allMoves.add(x + "-" + y);
                                change = true;
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
        int x = move[0];
        int y = move[1];
        //System.out.println("Dit is een test in doMove!!!");
        int[][] bard = Arrays.stream(getBoard()).map(int[]::clone).toArray(int[][]::new);
        int[][] bard2 = Arrays.stream(getBoard()).map(int[]::clone).toArray(int[][]::new);
        boolean change = false;
        int checker1;
        int checker2;
        int curp = 2;
        int nCurp = 1;
        if (cp == 1) {
            curp = 1;
            nCurp = 2;
        }
        if (curBoard[x][y] == 0) {
            try {
                if (curBoard[x + 1][y] == nCurp) {
                    checker1 = x + 1;
                    while (curBoard[checker1][y] == nCurp) {
                        bard[checker1][y] = curp;
                        checker1++;
                    }
                    if (curBoard[checker1][y] == curp) {
                        bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                }
                bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
            } catch (ArrayIndexOutOfBoundsException e) {

            }
              try {
                if (curBoard[x - 1][y] == nCurp) {
                    checker1 = x - 1;
                    while (curBoard[checker1][y] == nCurp) {
                        bard[checker1][y] = curp;
                        checker1--;
                    }
                    if (curBoard[checker1][y] == curp) {
                        bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                }
                  bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
            } catch (ArrayIndexOutOfBoundsException e) {
            }

            try {
                if (curBoard[x][y + 1] == nCurp) {
                    checker2 = y + 1;
                    while (curBoard[x][checker2] == nCurp) {
                        bard[x][checker2] = curp;
                        checker2++;
                    }
                    if (curBoard[x][checker2] == curp) {
                        bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                }
                bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
            } catch (ArrayIndexOutOfBoundsException e) {
            }

            try {
                if (curBoard[x][y - 1] == nCurp) {
                    checker2 = y - 1;
                    while (curBoard[x][checker2] == nCurp) {
                        bard[x][checker2] = curp;
                        checker2--;
                    }
                    if (curBoard[x][checker2] == curp) {
                        bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                    }
                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                }
                bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
            } catch (ArrayIndexOutOfBoundsException e) {
            }

            try {
                if (curBoard[x + 1][y + 1] == nCurp) {
                    checker1 = x + 1;
                    checker2 = y + 1;
                    while (curBoard[checker1][checker2] == nCurp) {
                        bard[checker1][checker2] = curp;
                        checker1++;
                        checker2++;
                    }
                    if (curBoard[checker1][checker2] == curp) {
                        bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                }
                bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
            } catch (ArrayIndexOutOfBoundsException e) {
            }

            try {
                if (curBoard[x - 1][y - 1] == nCurp) {
                    checker1 = x - 1;
                    checker2 = y - 1;
                    while (curBoard[checker1][checker2] == nCurp) {
                        bard[checker1][checker2] = curp;
                        checker1--;
                        checker2--;
                    }
                    if (curBoard[checker1][checker2] == curp) {
                        bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                }
                bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            try {
                if (curBoard[x + 1][y - 1] == nCurp) {
                    checker1 = x + 1;
                    checker2 = y - 1;
                    while (curBoard[checker1][checker2] == nCurp) {
                        bard[checker1][checker2] = curp;
                        checker1++;
                        checker2--;
                    }
                    if (curBoard[checker1][checker2] == curp) {
                        bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                }
                bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
            } catch (ArrayIndexOutOfBoundsException e) {
            }

            try {
                if (curBoard[x - 1][y + 1] == nCurp) {
                    checker1 = x - 1;
                    checker2 = y + 1;
                    while (curBoard[checker1][checker2] == nCurp) {
                        bard[checker1][checker2] = curp;
                        checker1--;
                        checker2++;
                    }
                    if (curBoard[checker1][checker2] == curp) {
                        bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
                    bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
            if (change == true) {
                bard2[x][y] = curp;
                if(currentPlayer == 1){currentPlayer++;}
                else{currentPlayer--;}
                System.out.println("     0 1 2 3 4 5 6 7");
                for(int i = 0; i< board.length; i++){
                    System.out.print("  " + (i) + " ");
                    for(int p = 0; p< board[i].length; p++){
                        System.out.print(" " + bard2[i][p]);
                    }
                    System.out.println();
                }
                curBoard = bard2;
                boardChange(curBoard);
            }
        }
        boardChange(curBoard);
    }
}
