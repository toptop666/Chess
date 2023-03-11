package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;

import static sample.Main.NUMBER_OF_CELLS;


public class Controller {

    @FXML
    private GridPane grid;

    @FXML
    private ImageView image;

    private Button[][] board;
    private final int CELLS_IN_ROW = NUMBER_OF_CELLS;
    public enum piecesNames {KING, ROOK, BISHOP, QUEEN, KNIGHT, PAWN};
    private LoadPiecesModels piecesModels;


    public void initialize() throws IOException {

        Board board = new Board();
        drawBoard();
        this.piecesModels = new LoadPiecesModels("resources\\piecesModels");
        drawBoardState(board);

    }


    private void drawBoard() {
        boolean blackOrWhite = true;
        // true = white, false = black
        this.board = new Button[this.CELLS_IN_ROW][this.CELLS_IN_ROW];
        for(int i = 0; i<this.CELLS_IN_ROW; i++) {
            for(int j = 0; j<this.CELLS_IN_ROW; j++) {
                Button temp = new Button("");
                temp.setPrefSize(this.grid.getPrefWidth(), this.grid.getPrefHeight());
                temp.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        handleButton(event);
                    }
                });
                temp.setBackground(new Background(new BackgroundFill((blackOrWhite)?Color.WHITE:Color.BROWN, null, null)));
                if(i%this.CELLS_IN_ROW != this.CELLS_IN_ROW) {
                    blackOrWhite = !blackOrWhite;

                }
                this.board[i][j] = temp;
                this.grid.add(temp, i, j);
            }
            blackOrWhite = !blackOrWhite;
        }
    }

    private void drawBoardState(Board board) {
        for(int i = 0; i<this.CELLS_IN_ROW; i++) {
            for(int j = 0; j<this.CELLS_IN_ROW; j++) {
                if(board.getCells()[i][j].HasPiece()) {
                    ImageView imageView = new ImageView(this.piecesModels.getBlackPieces().get(board.getCells()[i][j].getPiece().getClass()));
                    imageView.setFitWidth(this.board[i][j].getWidth());
                    imageView.setFitHeight(this.board[i][j].getHeight());
                    this.board[i][j].setGraphic(imageView);
                }
            }
        }
    }

    // TODO
    public void handleButton(ActionEvent event){System.out.println("press");}

}
