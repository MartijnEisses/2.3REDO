package Root.Pages;

import Root.Managers.UIManager;
import Root.Players.ReversiAI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReversiBoard extends ReversiController implements Initializable {

    @FXML GridPane gridBoard;
    @FXML protected Label turnLabel;
    private ReversiAI ai;
    private int turn = 1;
    private int currentplayer =1;
    int[][] oldBoard = getBoard();

    public ReversiBoard(){
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

    public void setStoneOnBoardStart(int x, int y, int whosTurn) {
        setStone(x, y, whosTurn);

        switch (whosTurn) {
            case 1:
                Circle stone_1 = new Circle();
                stone_1.setCenterX(100.0f);
                stone_1.setCenterY(100.0f);
                stone_1.setRadius(30.0f);
                gridBoard.add(stone_1, x, y);
                currentplayer = 2;
                break;
            case 2:
                Circle stone_2 = new Circle();
                stone_2.setCenterX(100.0f);
                stone_2.setCenterY(100.0f);
                stone_2.setRadius(30.0f);
                stone_2.setFill(Color.WHITE);
                gridBoard.add(stone_2, x, y);
                currentplayer = 1;
                break;
        }
    }

    public void setStoneOnBoard(int x, int y, int turn) {
        int[] test = new int[2];
        test[0] = y;
        test[1] = x;
        //System.out.println("x: " + test[0] + " y: " + test[1]);
        updateBoard();
        try {
            if(turn ==1) {
                turnPlayerLabel();
                currentplayer =1;
                doMove(1,test);

                //aiSet(turn);
            } else if(turn==2){
                turnPlayerLabel();
                currentplayer=2;
                aiSet(turn);
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
                            turn = 2;
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
                            turn = 1;
                        }
                    }
                }
                break;
        }
        oldBoard = getBoard();
    }

    @FXML
    protected void forfeitGameButton(ActionEvent event) throws IOException {
        emptyBoard();
        UIManager.createScene("Homepage.fxml");
    }

    public void turnPlayerLabel(){
        if(currentplayer==2){
            turnLabel.setText("Opponent his turn!");
        }else if(currentplayer ==1){
            turnLabel.setText("Your turn!");
        }
    }

    public void aiSet(int t) throws InterruptedException {
        int[] aiSET;
        aiSET = ai.getBestMove(legalMoves(t), getBoard(), t);
        doMove(t, aiSET);
        System.out.println("AI did move on: " + aiSET[1] + " and: " + aiSET[0] + " for: " + t);
        updateBoard();
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