package Root.Pages;

import Root.Managers.UIManager;
import Root.Pages.ReversiController;

import Root.Players.ReversiAI;
import Root.Views.Alerthelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import Root.Main;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Offline reversi board voor player vs AI.
 * Extends ReversiContrller
 */
public class OfflineReversiTest extends ReversiController implements Initializable {

    @FXML GridPane gridBoard;
    @FXML protected Label turnLabel;
    @FXML protected Label playerStones;
    @FXML protected Label aiStones;

    private ReversiAI ai;
    private int turn = 1;
    private int[][] oldBoard = getBoard();
    private boolean versusAI = true;
    private List<String> playerBoardClick = new ArrayList<>();

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
        setClickNote();
        for (int i = 0; i < b[0].length; i++) {
            for (int j = 0; j < b[1].length; j++) {
                Pane p = new Pane();
                p.setMinSize(i1, i2);
                p.setLayoutX(i);
                p.setLayoutY(j);
                final int x = i;
                final int y = j;
                setStone(x, y, 0);
                p.setOnMouseClicked(e -> {
                    try {
                        setStoneOnBoard(x, y, turn);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                });
                gridBoard.add(p, i, j);
            }
        }
        updateViews();
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

    public void setStoneOnBoard(int x, int y, int whosTurn) throws InterruptedException {
        System.out.println(turn);
       //setStone(x,y,turn);
        int[] playerMove = new int[2];
        playerMove[0] = y;
        playerMove[1] = x;
        updateViews();
        updateBoard();
        switch (whosTurn) {
            case 1:
                //this.turn =1;
               if(isMoveValid(playerMove[0],playerMove[1])){
                 doMove(this.turn, playerMove);
                   //currentplayer =1;
                   updateBoard();
                   this.turn=2;

                   setStoneOnBoard(0,0,this.turn);//Random x and y value it doesn't set any stone or register, it's for changing the turn to the ai.
               }

                //updateViews();
                break;

            case 2:
                //this.turn=2;
                //aiSet(turn);
                //currentplayer=2;
                TimeUnit.SECONDS.sleep(1);
                int[] aiSET;
                aiSET =  new int[0];
                aiSET = ai.getBestMove(legalMoves(turn), getBoard(), turn);
                if(aiSET.length != 0){
                    doMove(turn, aiSET);
                    System.out.println("AI did move on: " + aiSET[1] + " and: " + aiSET[0] + " for: " + turn);
                    updateBoard();
                }
                updateBoard();
                this.turn=1;
                updateViews();
                System.out.println("Turn after ai set: " + this.turn);
                break;
        }
        updateViews();
    }

    public void updateBoard(){
        updateViews();
       // int tempturn =1;
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
                            //tempturn = 2;
                            turn=2;
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
                            //tempturn = 1;
                            turn=1;
                        }
                    }
                }
                break;
        }
        oldBoard = getBoard();
    }

    public boolean isMoveValid(int x, int y){
        playerBoardClick = legalMoves(1);
        List<String> subList = new ArrayList<String>();
        List<String> allMoves = new ArrayList<String>();
        allMoves.add(x + "-" + y);
        //allMoves = (ArrayList<String>) allMoves;
        for(int i= 0; i< playerBoardClick.size(); i++) {
            //playerBoardClick.remove(2);
            //playerBoardClick.remove(3);
            for(int j= 0; j< playerBoardClick.size(); j++) {
                String removeLast = playerBoardClick.get(j).substring(0, playerBoardClick.get(j).length() -2);
                subList.add(removeLast);
                if (allMoves.get(i).contains(subList.get(j))) {
                    playerBoardClick.clear();
                    subList.clear();
                    return true;
                }
            }
            return false;
        }
        return false;
    }


    public void updateViews(){
        turnPlayerLabel();
        updateAIStonesLabel();
        updatePlayerStonesLabel();
    }

    @FXML
    protected void forfeitGameButton(ActionEvent event) throws IOException {
        emptyBoard();
        UIManager.createScene("Homepage.fxml");
    }

    @FXML
    protected void homePageButton(ActionEvent event) throws IOException {
        emptyBoard();
        UIManager.createScene("Homepage.fxml");
    }

    public void turnPlayerLabel(){
      if(this.turn == 1) {
          turnLabel.setText("Your turn!");
      } else {
          turnLabel.setText("Opponent his turn!");
      }
    }

    public void updatePlayerStonesLabel(){
        playerStones.setText("You have: " + countStones().get(0) + " stones");
    }

    public void updateAIStonesLabel(){
        aiStones.setText("AI has: " + countStones().get(1) + " stones");
    }
/*
    public void aiSet(int t) throws InterruptedException {
        int[] aiSET;
        aiSET =  new int[0];
        aiSET = ai.getBestMove(legalMoves(t), getBoard(), t);
        if(aiSET.length != 0){
            doMove(t, aiSET);
            System.out.println("AI did move on: " + aiSET[1] + " and: " + aiSET[0] + " for: " + t);
            updateBoard();

        }
    }
*/
    public void setClickNote(){
        //PaintRenderJob handleSubmitButtonAction = null;
        //Window ErrorMessage = handleSubmitButtonAction.getScene().getWindow();
        //Alerthelper.showAlert(Alert.AlertType.ERROR, ErrorMessage, "Wait! Error!", "Player is not online");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Information");
        alert.setHeaderText(null);
        alert.setContentText("Note: Click double to register a stone");
        alert.show();
    }



}