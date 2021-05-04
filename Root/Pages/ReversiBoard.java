package Root.Pages;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.net.URL;
import java.util.ResourceBundle;

public class ReversiBoard extends ReversiController implements Initializable {

    @FXML
    GridPane gridBoard;
    public static int turn = 1;
    private int[][] board = getBoard();
    private boolean testrunner = true;
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

    public void createGridBoard(int[][] board, int i1, int i2) {
        try{
             for (int i = 0; i < board[0].length; i++) {
                for (int j = 0; j < board[1].length; j++) {
                    Pane p = new Pane();
                    p.setMinSize(i1, i2);
                    p.setLayoutX(i);
                    p.setLayoutY(j);
                    final int x = i;
                    final int y = j;
                    //setStone(x, y, 0);
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
        //updateBoard(x, y, turn);
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
        updateBoard();
    }

    public void updateBoard() {
        while(testrunner) {
            switch (turn) {
                case 1:
                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board[i].length; j++) {
                            if (board[i][j] == 1) {
                                Circle stone_1 = new Circle();
                                stone_1.setCenterX(100.0f);
                                stone_1.setCenterY(100.0f);
                                stone_1.setRadius(30.0f);
                                stone_1.setFill(Color.BLACK);
                                gridBoard.add(stone_1, i, j);
                                this.turn = 2;
                            }
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < board.length; i++) {
                        for (int j = 0; j < board[i].length; j++) {
                            if (board[i][j] == 2) {
                                Circle stone_2 = new Circle();
                                stone_2.setCenterX(100.0f);
                                stone_2.setCenterY(100.0f);
                                stone_2.setRadius(30.0f);
                                stone_2.setFill(Color.WHITE);
                                gridBoard.add(stone_2, i, j);
                                this.turn = 1;
                            }
                        }
                    }
                    break;
            }
        }
    }
}