package Managers;

import java.util.*;
import Managers.Board;
import Managers.RandomAI;
public class ReversiManager implements Manager {

    private int currentPlayer; // ZWART is 1 & WIT is 2
    private int newBoard;

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

    @Override
    public void Start() {
        // TODO Auto-generated method stub
        System.out.println("Start method in ReversiManager is starting");
        Board newBoard = new Board(8,8);
        newBoard.setStone(3,3,2);
        newBoard.setStone(4,4,2);
        newBoard.setStone(3,4,1);
        newBoard.setStone(4,3,1);
        newBoard.drawBoard();
    }

    @Override
    public void Update() {
        // TODO Auto-generated method stub

    }
    /*
        Convert int to board position.

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
*/

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
                                allMoves.add(y + "-" + x);
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
                                allMoves.add(y + "-" + x);
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
                                allMoves.add(y + "-" + x);
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
                                allMoves.add(y + "-" + x);
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
                                allMoves.add(y + "-" + x);
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
                                allMoves.add(y + "-" + x);
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
                                allMoves.add(y + "-" + x);
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
                                allMoves.add(y + "-" + x);
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

    public int[][] doMove(int[][] curBoard, int cp, int x, int y) {
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
