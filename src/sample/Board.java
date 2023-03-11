package sample;

import static sample.Main.NUMBER_OF_CELLS;

public class Board {

    private Cell[][] cells;
    private int size = NUMBER_OF_CELLS;

    public Board() {
        this.cells = new Cell[this.size][this.size];
        for(int i = 0; i<this.size; i++) {
            for(int j = 0; j<this.size; j++) {
                Cell cell = new Cell(new Position(i, j), false, null);
                this.cells[i][j] = cell;
            }
        }
        this.drawStartingBoard();
    }

    private void drawStartingBoard() {
        for(int i = 0; i<this.size; i++) {
            this.cells[i][1].setPiece(new Pawn(true, this.cells[1][i].getCoordinate()));
            this.cells[i][6].setPiece(new Pawn(false, this.cells[6][i].getCoordinate()));
            for(int j = 0; j<8; j+=7) {
                this.cells[0][j].setPiece(new Rook(true, this.cells[1][j].getCoordinate()));
                this.cells[7][j].setPiece(new Rook(true, this.cells[1][j].getCoordinate()));
                this.cells[1][j].setPiece(new Knight(true, this.cells[1][j].getCoordinate()));
                this.cells[6][j].setPiece(new Knight(true, this.cells[1][j].getCoordinate()));
                this.cells[2][j].setPiece(new Bishop(true, this.cells[1][j].getCoordinate()));
                this.cells[5][j].setPiece(new Bishop(true, this.cells[1][j].getCoordinate()));
                this.cells[4][j].setPiece(new Queen(true, this.cells[1][j].getCoordinate()));
                this.cells[3][j].setPiece(new King(true, this.cells[1][j].getCoordinate()));
            }
        }
    }

    public Cell[][] getCells() {
        return this.cells;
    }

}
