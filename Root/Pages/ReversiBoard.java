package Root.Pages;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Node;
import java.net.URL;
import java.util.ResourceBundle;

public class ReversiBoard extends ReversiController implements Initializable {

    @FXML
    GridPane gridBoard;
    public static int turn = 1;
    int[][] oldBoard = getBoard();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        createGridBoard(getBoard(), 75, 75);
        setStoneOnBoardStart(3, 4, 1);
        setStoneOnBoardStart(4, 3, 1);
        setStoneOnBoardStart(4, 4, 2);
        setStoneOnBoardStart(3, 3, 2);
    }

    public void createGridBoard(int[][] b, int i1, int i2) {
        for (int i = 0; i < b[0].length; i++) {
            for (int j = 0; j < b[1].length; j++) {
                Pane p = new Pane();
                p.setMinSize(i1, i2);
                p.setLayoutX(i);
                p.setLayoutY(j);
                final int x = i;
                final int y = j;

                setStone(x, y, 0);
                //p.setOnMouseClicked(e -> setStoneOnBoard(x, y, turn));
                gridBoard.add(p, i, j);
            }
        }
    }

    public void setStoneOnBoardStart(int x, int y, int turn) {
        setStone(x, y, turn);
        switch (turn) {
            case 1:
                Circle stone_1 = new Circle();
                stone_1.setCenterX(100.0f);
                stone_1.setCenterY(100.0f);
                stone_1.setRadius(30.0f);
                gridBoard.add(stone_1, x, y);
                this.turn = 2;
                break;
            case 2:
                Circle stone_2 = new Circle();
                stone_2.setCenterX(100.0f);
                stone_2.setCenterY(100.0f);
                stone_2.setRadius(30.0f);
                stone_2.setFill(Color.WHITE);
                gridBoard.add(stone_2, x, y);
                this.turn = 1;
                break;
        }
    }

    public void setStoneOnBoard(int x, int y, int turn) {
        //boardChange(doMove(turn, getBoard()));
        updateBoard();

    }

    public void updateBoard() {
        int[][] newBoard = curBoard;
        System.out.println("Methode updateBoard()");
        System.out.println("     0 1 2 3 4 5 6 7");
        for (int i = 0; i < curBoard.length; i++) {
            System.out.print("  " + (i) + " ");
            for (int p = 0; p < curBoard[i].length; p++) {
                System.out.print(" " + curBoard[p][i]);
                //if(newBoard[p][i] == 1) {
                //reversiBoard.setStoneOnBoard(p, i, 1);
                // } else if(newBoard[p][i] == 2){
                //reversiBoard.setStoneOnBoard(p, i, 2);
                // }
            }
            System.out.println();
        }
        switch (turn) {
            case 1:
                for (int i = 0; i < newBoard.length; i++) {
                    for (int j = 0; j < newBoard[1].length; j++) {

                        if (newBoard[i][j] != oldBoard[i][j]) {
                            Circle stone_1 = new Circle();
                            stone_1.setCenterX(100.0f);
                            stone_1.setCenterY(100.0f);
                            stone_1.setRadius(30.0f);
                            gridBoard.add(stone_1, j, i);
                            this.turn = 2;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < newBoard.length; i++) {
                    for (int j = 0; j < newBoard[1].length; j++) {

                        if (newBoard[i][j] != oldBoard[i][j]) {
                            Circle stone_2 = new Circle();
                            stone_2.setCenterX(100.0f);
                            stone_2.setCenterY(100.0f);
                            stone_2.setRadius(30.0f);
                            stone_2.setFill(Color.WHITE);
                            gridBoard.add(stone_2, j, i);
                            this.turn = 1;
                        }
                    }
                }
                break;
        }
        oldBoard = newBoard;

    }

}



/*
public class ReversiBoard extends ReversiController implements Initializable {

    @FXML
    private GridPane gridBoard = new GridPane();
    public int turn = 1;
    private int[][] GUIboard;
    private boolean testrunner = true;
    //private Thread reversiBoardThread;
    //private ReversiController reversiController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            createGridBoard(board, 75, 75);
            setStoneOnBoardStart(3, 3, 2);
            setStoneOnBoardStart(4, 4, 2);
            setStoneOnBoardStart(3, 4, 1);
            setStoneOnBoardStart(4, 3, 1);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void createGridBoard(int[][] testboard, int i1, int i2) {
        try{
             for (int i = 0; i < testboard[0].length; i++) {
                for (int j = 0; j < testboard[1].length; j++) {
                    Pane p = new Pane();
                    p.setMinSize(i1, i2);
                    p.setLayoutX(i);
                    p.setLayoutY(j);
                    //final int x = i;
                   // final int y = j;
                   // setStone(x, y, 0);
                    gridBoard.add(p, i, j);
                }
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public void setStoneOnBoardStart(int x, int y, int turn) {
        //setStone(x, y, turn);
        switch (turn) {
            case 1:
                Circle stone_1 = new Circle();
                stone_1.setCenterX(100.0f);
                stone_1.setCenterY(100.0f);
                stone_1.setRadius(30.0f);
                gridBoard.add(stone_1, x, y);
                this.turn = 2;
                break;
            case 2:
                Circle stone_2 = new Circle();
                stone_2.setCenterX(100.0f);
                stone_2.setCenterY(100.0f);
                stone_2.setRadius(30.0f);
                stone_2.setFill(Color.WHITE);
                gridBoard.add(stone_2, x, y);
                this.turn = 1;
                break;
        }
    }


    public void setStoneOnBoard(int x, int y, int turn) {

        setStone(x, y, turn);
        this.turn = turn;
        switch (turn) {
            case 1:
                Circle stone_1 = new Circle();
                stone_1.setCenterX(100.0f);
                stone_1.setCenterY(100.0f);
                stone_1.setRadius(30.0f);
                gridBoard.add(stone_1, x, y);
                System.out.println("Setting stone on: " + x + " and: " + y + " for player: " + turn);
                this.turn = 2;

                break;
            case 2:
                Circle stone_2 = new Circle();
                stone_2.setCenterX(100.0f);
                stone_2.setCenterY(100.0f);
                stone_2.setRadius(30.0f);
                stone_2.setFill(Color.WHITE);
                gridBoard.add(stone_2, x, y);
                System.out.println("Setting stone on: " + x + " and: " + y + " for player: " + turn);
                this.turn = 1;
                break;
        }
        updateBoard();


    }

    public void updateBoard(int[][] b) {
        int[][] newBoard = b;
        switch (turn) {
            case 1:
                for (int i = 0; i < newBoard.length; i++) {
                    for (int j = 0; j < newBoard[i].length; j++) {

                        if (newBoard[i][j] ==1) {
                            Circle stone_1 = new Circle();
                            stone_1.setCenterX(100.0f);
                            stone_1.setCenterY(100.0f);
                            stone_1.setRadius(30.0f);
                            gridBoard.add(stone_1, i, j);
                            System.out.println("added Stone on: " + i + " and: " + j + " for player: " + turn);
                            this.turn = 2;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < newBoard.length; i++) {
                    for (int j = 0; j < newBoard[i].length; j++) {

                        if (newBoard[i][j] ==2) {
                            Circle stone_2 = new Circle();
                            stone_2.setCenterX(100.0f);
                            stone_2.setCenterY(100.0f);
                            stone_2.setRadius(30.0f);
                            stone_2.setFill(Color.WHITE);
                            gridBoard.add(stone_2, i, j);
                            System.out.println("added Stone on: " + i + " and: " + j + " for player: " + turn);
                            this.turn = 1;
                        }
                    }
                }
                break;
        }
        GUIboard = getBoard();

    }
}
*/