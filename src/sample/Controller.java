package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.ArrayList;

import static sample.Main.NUMBER_OF_CELLS;


public class Controller {

    @FXML
    private GridPane grid;

    @FXML
    private ImageView image;

    private Button[][] buttons;
    private final int CELLS_IN_ROW = NUMBER_OF_CELLS;
    private LoadPiecesModels piecesModels;
    private Game game;
    private boolean tapOnPiece = false;
    private Position selectedPosition;


    public void initialize() throws IOException {

        this.game = new Game();
        drawBoard();
        this.piecesModels = new LoadPiecesModels("resources\\piecesModels");
        drawBoardState();

    }


    private void drawBoard() {
        boolean blackOrWhite = true;
        // true = white, false = black
        this.buttons = new Button[this.CELLS_IN_ROW][this.CELLS_IN_ROW];
        Font font = new Font("Arial", 24);
        for(int i = 0; i<this.CELLS_IN_ROW; i++) {
            for(int j = 0; j<this.CELLS_IN_ROW; j++) {
                Button temp = new Button("");
                temp.setFont(font);
                temp.setPrefSize(this.grid.getPrefWidth(), this.grid.getPrefHeight());
                int finalI = i;
                int finalJ = j;
                temp.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            handleButton(event, finalI, finalJ);
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                temp.setBackground(new Background(new BackgroundFill((blackOrWhite)?Color.WHITE:Color.BROWN, null, null)));
                if(i%this.CELLS_IN_ROW != this.CELLS_IN_ROW) {
                    blackOrWhite = !blackOrWhite;

                }
                this.buttons[i][j] = temp;
                this.grid.add(temp, j, i);
            }
            blackOrWhite = !blackOrWhite;
        }
    }


    private void drawBoardState() {
        for(int i = 0; i<this.CELLS_IN_ROW; i++) {
            for(int j = 0; j<this.CELLS_IN_ROW; j++) {
                this.buttons[i][j].setGraphic(null);
                if(this.game.getBoard().getCells()[i][j].HasPiece()) {
                    ImageView imageView = new ImageView();
                    if(this.game.getBoard().getCells()[i][j].getPiece().isWhite()) {
                        imageView = new ImageView(this.piecesModels.getWhitePieces().get(this.game.getBoard().getCells()[i][j].getPiece().getClass()));
                    }
                    else {
                        imageView = new ImageView(this.piecesModels.getBlackPieces().get(this.game.getBoard().getCells()[i][j].getPiece().getClass()));

                    }
                    imageView.setFitWidth(60);
                    imageView.setFitHeight(60);
                    this.buttons[i][j].setGraphic(imageView);
                }
            }
        }
    }

    private void restartAllButtonsText() {
        for(int i = 0; i<this.CELLS_IN_ROW; i++) {
            for (int j = 0; j < this.CELLS_IN_ROW; j++) {
                this.buttons[i][j].setText("");
            }
        }
    }

    // TODO
    public void handleButton(ActionEvent event, int x, int y) throws CloneNotSupportedException {
        if(this.tapOnPiece) {
            boolean flag = false;
            if(((Button) event.getSource()).getText().equals("o") || this.selectedPosition == null) {
                flag = this.game.getMovement().move(this.game.getBoard().getCell(this.selectedPosition), new Position(x, y));
            }
            if(flag) {
                drawBoardState();
                game.turn();
            }
            restartAllButtonsText();
            this.tapOnPiece = false;
            this.selectedPosition = null;
            return;
        }

        if(this.game.isWhiteTurn() && this.game.getBoard().getCells()[x][y].HasPiece() && this.game.getBoard().getCells()[x][y].getPiece().isWhite()) {
            ArrayList<Position> moves = this.game.getMovement().availableMoves(this.game.getBoard().getCell(new Position(x, y)));
            this.selectedPosition = new Position(x, y);
            for(Position position : moves) {
                this.buttons[position.getHeight()][position.getWidth()].setText("o");
            }
        }
        if(!this.game.isWhiteTurn() && this.game.getBoard().getCells()[x][y].HasPiece() && !this.game.getBoard().getCells()[x][y].getPiece().isWhite()) {
            ArrayList<Position> moves = this.game.getMovement().availableMoves(this.game.getBoard().getCell(new Position(x, y)));
            this.selectedPosition = new Position(x, y);
            for(Position position : moves) {
                this.buttons[position.getHeight()][position.getWidth()].setText("o");
            }
        }
        this.tapOnPiece = true;

    }

}
