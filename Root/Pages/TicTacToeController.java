package Root.Pages;

import Root.Managers.TicTacToeManager;
import Root.Managers.UIManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TicTacToeController extends TicTacToeManager implements Initializable {

    @FXML GridPane gridBoard;
    @FXML protected Label turnLabel;
    @FXML protected Label playerLabel;
    @FXML protected Label AILabel;
    public int turn = 1;
    public boolean versusAI = true;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        createGridBoard(getBoard(), 175, 175);
    }

    public void createGridBoard(int[][] b, int i1, int i2) {
        updateAllViews();
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
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
                gridBoard.add(p, i, j);
            }
        }
    }

    public void setStoneOnBoard(int x, int y, int turn) throws IOException {
        System.out.println(turn);
        if (isEmpty(x, y)) {
            setStone(x, y, turn);
            switch (turn % 2) {
                case 0: // AI
                    AddImages(new Image("root/Views/X.png"), 140, x, y);
                    Move(boardPosition[x][y], turn);
                    this.turn++;
                    break;
                case 1: // HUMAN
                    AddImages(new Image("root/Views/O.png"), 140, x, y);
                    Move(boardPosition[x][y], turn);
                    this.turn++;
                    break;
            }
            // 1st turn is always the player
            if (versusAI && this.turn % 2 == 0) {
                int bestmove = BestMove();
                setStoneOnBoard(oBoardPosition[bestmove][0], oBoardPosition[bestmove][1], this.turn);
            }
            if (CheckForWin() != null) {
                Color winner = CheckForWin();
                emptyBoard(3, 3);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game Information");
                alert.setHeaderText(null);
                if (winner == Color.WHITE) {
                    alert.setContentText("Congrats you have won!");
                } else if (winner == Color.BLACK) {
                    alert.setContentText("The computer has won");
                } else if (winner == Color.EMPTY) {
                    alert.setContentText("TIE!");
                }
                alert.showAndWait();
                UIManager.createScene("Homepage.fxml");
            }
        }
    }

    private void AddImages(Image image, int size, int x, int y) {
        ImageView img_x = new ImageView(image);
        img_x.setFitHeight(140);
        img_x.setFitWidth(140);
        gridBoard.add(img_x, x, y);
    }

    @FXML
    protected void forfeitGameButton(ActionEvent event) throws IOException {
        emptyBoard(3, 3);
        UIManager.createScene("TicTacToeBoard.fxml");
    }

    @FXML
    protected void homePageButton(ActionEvent event) throws IOException {
        emptyBoard(3, 3);
        UIManager.createScene("Homepage.fxml");
    }

    public void updateAllViews(){
        turnPlayerLabel();
        playerLetterLabel();
        AILetterLabel();
    }

    public void turnPlayerLabel() {
       turnLabel.setText("It is your turn!");
    }

    public void playerLetterLabel() {
        playerLabel.setText("You are O.");
    }

    public void AILetterLabel() {
        AILabel.setText("The opponent is X.");
    }



}
