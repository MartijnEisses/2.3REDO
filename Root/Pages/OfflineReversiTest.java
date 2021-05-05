package Root.Pages;

import Root.Pages.ReversiController;

import Root.Players.ReversiAI;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;


import javafx.event.ActionEvent;
import java.net.URL;
import Root.Main;
import java.util.ResourceBundle;

public class OfflineReversiTest extends ReversiController implements Initializable {

    @FXML
    GridPane gridBoard;
    private ReversiAI ai;
    private int turn = 1;
    private int currentplayer =1;
    int[][] oldBoard = getBoard();

    public OfflineReversiTest(){
        ai = new ReversiAI();
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            createGridBoard(getBoard(), 75, 75);
            setStoneOnBoardStart(3, 4, 1);
            setStoneOnBoardStart(4, 3, 1);
            setStoneOnBoardStart(4, 4, 2);
            setStoneOnBoardStart(3, 3, 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createGridBoard(int[][] b, int i1, int i2) throws InterruptedException {
        for (int i = 0; i < b[0].length; i++) {
            for (int j = 0; j < b[1].length; j++) {
                Pane p = new Pane();
                p.setMinSize(i1, i2);
                p.setLayoutX(i);
                p.setLayoutY(j);
                final int x = i;
                final int y = j;

                setStone(x, y, 0);
                p.setOnMouseClicked(e -> setStoneOnBoard(x, y, turn));
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
        //boardChange(doMove(getBoard(), turn, y, x));
        int[] test = new int[2];
        test[0] = y;
        test[1] = x;
        System.out.println("x: " + test[0] + " y: " + test[1]);
        updateBoard();
        try {
            if(turn ==1) {
                doMove(1,test);
            } else if(turn==2){
                aiSet();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateBoard(){
        int[][] newBoard = getBoard();
        switch (turn) {
            case 1:
                for (int i = 0; i < newBoard.length; i++) {
                    for (int j = 0; j < newBoard[1].length; j++) {

                        if (newBoard[i][j] != oldBoard[i][j]) {
                            Circle stone_1 = new Circle();
                            stone_1.setCenterX(100.0f);
                            stone_1.setCenterY(100.0f);
                            stone_1.setRadius(30.0f);
                            gridBoard.add(stone_1, i, j);
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
                            gridBoard.add(stone_2, i, j);
                            this.turn = 1;
                        }
                    }
                }
                break;
        }
        oldBoard = getBoard();
    }

    public void aiSet() throws InterruptedException {
        int[] aiSET;
        aiSET = ai.getBestMove(legalMoves(2), getBoard(), 2);
        doMove(2, aiSET);
        System.out.println("AI did move on: " + aiSET[1] + " and: " + aiSET[0] + " for: " + 2);
        updateBoard();
    }
}