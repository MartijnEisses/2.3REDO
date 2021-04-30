package Root.Pages;

import Root.Managers.Board;
import Root.Players.RandomAI;
import Root.Players.playertype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ReversiTemp extends Board {
    Terminal term = new Terminal();
    final double CORNERPLACE = 10;
    final double EDGEPLACE = 5;
    final double PLACENEXTCORNER = -10;
    double stonePosition[][] = new double[8][8];
    private int currentPlayer; // wit is 2 - zwart is 1.
    private RandomAI randomAI;
    private Random random;
    private boolean finished = true;

    public ReversiTemp() {
        super(8, 8);
        setStone(3,3,2);
        setStone(4,4,2);
        setStone(3,4,1);
        setStone(4,3,1);
        currentPlayer = 1;
        randomAI = new RandomAI();
    }

    public void startReversi(int player1, int player2){
        System.out.println("Reversi begint!!!");
        System.out.println("Het is jou beurt!!!!!");
        drawBoard();

         while(finished){
           String action = term.parce();
           doSome(action);
        }
    }

    public void doSome(String doSo) {
        String[] fullWord = doSo.split(" ");
        if(fullWord[0].equals("set")){
            String[] fullSet = fullWord[1].split("-");
            int x = Integer.parseInt(fullSet[0]);
            int y = Integer.parseInt(fullSet[1]);
            int[] result = new int[fullSet.length];
            for (int i = 0; i < fullSet.length; i++) {
                result[i] = Integer.parseInt(fullSet[i]);
            }
            boardChange(doMove(getBoard(), currentPlayer, result));
            //drawBoard();
            if(currentPlayer== 1){
                currentPlayer = 2;
            }
            if(currentPlayer == 2) {
                boardChange(doMove(getBoard(), currentPlayer, randomAI.setRandomMove(legalMoves(getBoard(), currentPlayer), getBoard(), currentPlayer)));
                //boardChange(randomAI.calculateRandomMove(legalMoves(getBoard(), currentPlayer), getBoard(), currentPlayer));
                //bord.boardChange(ai.calculateRandomMove(legal.legalMoves(bord.getBord(), currentPlayer), bord.getBord(), currentPlayer));
                currentPlayer = 1;
            //    currentPlayer = !currentPlayer;
            }
            if(currentPlayer == 1){
                System.out.println("\nHet is jou beurt!!");
            }else {
                System.out.println("\nHet is de beurt van de AI!!");
            }
            drawBoard();
            System.out.println("You currently have: " + countStones().get(0) + " stones");
            System.out.println("The AI currently has: " + countStones().get(1)+ " stones");
        }
        if(fullWord[0].equals("moves")){
            legalMoves(getBoard(), currentPlayer);
            legalMoves(getBoard(), currentPlayer).forEach((n) -> System.out.println(n));
        }
    }

    public int[] convertToBoardPosition(int p) {
        int[] position = new int[2];
        int counter = 0;
        for (int x = 0; x < board[0].length; x++) {
            for (int y = 0; y < board[1].length; y++) {
                if (p == counter) {
                    position[0] = x;
                    position[1] = y;
                }
                counter++;
            }
        }
        return position;
    }

    public ArrayList<String> legalMoves(int[][] curBoard, int cp) {
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
    public int[][] doMove(int[][] curBoard, int cp, int[] move) {
        //System.out.println("Dit is een test in doMove!!!");
        int x = move[0];
        int y = move [1];
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
                        // if (checker2 != 8 && checker2 != -1) {
                        bard[x][checker2] = curp;
                        checker2--;
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
                return bard2;
            }
        }
        return curBoard;
    }
}
