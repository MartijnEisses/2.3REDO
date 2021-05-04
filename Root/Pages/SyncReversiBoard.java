package Root.Pages;

import Root.Managers.Board;
import javafx.application.Platform;

public class SyncReversiBoard extends ReversiController{

    private int[][] tempBoard;


    public SyncReversiBoard(){
        tempBoard = new int[8][8];
    }

    public void setBoard(int[][] board){
        this.tempBoard = board;
    }

    public int[][] getTempBoard(){
        return tempBoard;
    }



}
