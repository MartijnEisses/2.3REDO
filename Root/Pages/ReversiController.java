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

    public ReversiController() {
        super(8, 8);
        setStone(3,3,2);
        setStone(4,4,2);
        setStone(3,4,1);
        setStone(4,3,1);
        randomAI = new RandomAI();

    }

    public void gameController(int player1, int player2){
        this.playerBlack = player1;
        this.playerWhite = player2;

    }




    public ArrayList<String> legalMoves( int cp) {
        int[][] curBoard = getBoard();
        int totChanges = 0;
        int tempChanges = 0;
        int[][] bard = Arrays.stream(curBoard).map(int[]::clone).toArray(int[][]::new);
        int[][] bard2 = Arrays.stream(curBoard).map(int[]::clone).toArray(int[][]::new);
        List<String> allMoves = new ArrayList<String>();
        boolean change = false;

        int checker1;
        int checker2;
        int curp = 1;
        int nCurp = 2;
        if (cp == 2) {
            curp = 2;
            nCurp = 1;
        }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                change = false;
                // totChanges = 0;
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

                    //tempChanges = 0;
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


                    tempChanges = 0;
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
                    //tempChanges = 0;
                    try {
                        if (curBoard[x][y - 1] == nCurp) {
                            checker2 = y - 1;

                            while (curBoard[x][checker2] == nCurp) {
                                //tempChanges++;

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
                    //tempChanges = 0;
                    try {
                        if (curBoard[x + 1][y + 1] == nCurp) {
                            checker1 = x + 1;
                            checker2 = y + 1;

                            while (curBoard[checker1][checker2] == nCurp) {

                                checker1++;

                                //tempChanges++;

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
                    //tempChanges = 0;
                    try {
                        if (curBoard[x - 1][y - 1] == nCurp) {
                            checker1 = x - 1;
                            checker2 = y - 1;

                            while (curBoard[checker1][checker2] == nCurp) {
                                tempChanges++;
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

                    //tempChanges = 0;
                    try {
                        if (curBoard[x + 1][y - 1] == nCurp) {
                            checker1 = x + 1;
                            checker2 = y - 1;

                            while (curBoard[checker1][checker2] == nCurp) {

                                checker1++;

                                //tempChanges ++;

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
                    //tempChanges = 0;
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
        int [][] curBoard = getBoard();
        int x = move[0];
        int y = move [1];
        //System.out.println("Dit is een test in doMove!!!");
        int[][] bard = Arrays.stream(curBoard).map(int[]::clone).toArray(int[][]::new);
        int[][] bard2 = Arrays.stream(curBoard).map(int[]::clone).toArray(int[][]::new);
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

                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            bard = Arrays.stream(bard2).map(int[]::clone).toArray(int[][]::new);
            try {
                if (curBoard[x][y + 1] == nCurp) {
                    checker2 = y + 1;

                    while (curBoard[x][checker2] == nCurp) {
                        bard[x][checker2] = curp;
                        checker2++;
                    }

                    // }
                    if (curBoard[x][checker2] == curp) {
                        bard2 = Arrays.stream(bard).map(int[]::clone).toArray(int[][]::new);
                        change = true;
                    }
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
                        change = true;
                    }
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
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            if (change == true) {
                bard2[x][y] = curp;
                if(currentPlayer == 1){currentPlayer++;}
                else{currentPlayer--;}
                boardChange(bard2);
                System.out.println("     0 1 2 3 4 5 6 7");
                for(int i =0; i< board.length; i++){
                    System.out.print("  " + (i) + " ");
                    for(int p = 0; p< board[i].length; p++){
                        System.out.print(" " + bard2[i][p]);
                    }
                    System.out.println();
                }
            }
        }
         System.out.println("     0 1 2 3 4 5 6 7");
            for(int i =0; i< board.length; i++){

                System.out.print("  " + (i) + " ");
                for(int p = 0; p< board[i].length; p++){
                    System.out.print(" " +curBoard[i][p]);
                }
                System.out.println();
          }
        boardChange(curBoard);
    }
}
