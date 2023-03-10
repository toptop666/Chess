package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.io.IOException;


public class Controller {

    @FXML
    private GridPane grid;

    private Button[] board;
    private final int CELLS_IN_ROW = 8;

    public void initialize() throws IOException {
        boolean blackOrWhite = true;
        // true = white, false = black
        this.board = new Button[this.CELLS_IN_ROW*this.CELLS_IN_ROW];
        for(int i = 0; i<this.CELLS_IN_ROW*this.CELLS_IN_ROW; i++) {
            Button temp = new Button("");
            temp.setPrefSize(this.grid.getPrefWidth(), this.grid.getPrefHeight());
            temp.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    handleButton(event);
                }
            });
            if(blackOrWhite) {
                temp.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            }
            else {
                temp.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

            }
            if(i%this.CELLS_IN_ROW != this.CELLS_IN_ROW - 1) {
                blackOrWhite = !blackOrWhite;

            }
            this.board[i] = temp;
            this.grid.add(temp, i%this.CELLS_IN_ROW, i/this.CELLS_IN_ROW);
        }

    }

    // TODO
    public void handleButton(ActionEvent event){System.out.println("press");}

}
