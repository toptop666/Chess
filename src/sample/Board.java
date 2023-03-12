package sample;

import java.util.Iterator;

import static sample.Main.NUMBER_OF_CELLS;

public class Board implements Iterator<Cell> {

    private Cell[][] cells;
    private int size = NUMBER_OF_CELLS;
    private int iteratorI = 0;
    private int iteratorJ = 0;
    private boolean fullPass = false;

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
            this.cells[1][i].setPiece(new Pawn(false, this.cells[1][i].getCoordinate()));
            this.cells[6][i].setPiece(new Pawn(true, this.cells[6][i].getCoordinate()));
            // black pieces
            this.cells[0][0].setPiece(new Rook(false, this.cells[0][0].getCoordinate()));
            this.cells[0][1].setPiece(new Knight(false, this.cells[0][1].getCoordinate()));
            this.cells[0][2].setPiece(new Bishop(false, this.cells[0][2].getCoordinate()));
            this.cells[0][3].setPiece(new Queen(false, this.cells[0][3].getCoordinate()));
            this.cells[0][4].setPiece(new King(false, this.cells[0][4].getCoordinate()));
            this.cells[0][5].setPiece(new Bishop(false, this.cells[0][5].getCoordinate()));
            this.cells[0][6].setPiece(new Knight(false, this.cells[0][6].getCoordinate()));
            this.cells[0][7].setPiece(new Rook(false, this.cells[0][7].getCoordinate()));

            // white pieces
            this.cells[7][0].setPiece(new Rook(true, this.cells[7][0].getCoordinate()));
            this.cells[7][1].setPiece(new Knight(true, this.cells[7][1].getCoordinate()));
            this.cells[7][2].setPiece(new Bishop(true, this.cells[7][2].getCoordinate()));
            this.cells[7][3].setPiece(new Queen(true, this.cells[7][3].getCoordinate()));
            this.cells[7][4].setPiece(new King(true, this.cells[7][4].getCoordinate()));
            this.cells[7][5].setPiece(new Bishop(true, this.cells[7][5].getCoordinate()));
            this.cells[7][6].setPiece(new Knight(true, this.cells[7][6].getCoordinate()));
            this.cells[7][7].setPiece(new Rook(true, this.cells[7][7].getCoordinate()));

        }
    }

    public Cell[][] getCells() {
        return this.cells;
    }

    public Cell getCell(Position position) {
        if(position.getHeight() < 0 || position.getWidth()<0) {
            return null;
        }
        return this.cells[position.getHeight()][position.getWidth()];
    }

    @Override
    public boolean hasNext() {
        if(this.fullPass) {
            this.fullPass = false;
            return true;
        }
        return false;
    }

    @Override
    public Cell next() {
        if(this.iteratorJ> this.cells[0].length) {
            this.iteratorJ = 0;
            this.iteratorI++;
        }
        if(this.iteratorI>this.cells.length) {
            this.iteratorI = 0;
            this.fullPass = true;
            return null;
        }
        return getCell(new Position(iteratorI, iteratorJ++));
    }

    public boolean movePiece(Cell cell, Position position) {
        if(!cell.HasPiece() || this.getCell(position).HasPiece()) {
            return false;
        }

        this.getCell(position).setPiece(cell.getPiece());
        this.getCell(position).getPiece().setPosition(position);
        cell.setPiece(null);
        return true;
    }

    public boolean eat(Cell eat, Cell eaten) throws CloneNotSupportedException {
        if(!eat.HasPiece() || !eaten.HasPiece() || eat.getPiece().isWhite() == eaten.getPiece().isWhite()) {
            return false;
        }
        eat.getPiece().setPosition(eaten.getCoordinate());

        eaten.setPiece((Piece) eat.getPiece().clone());
        eat.removePiece();
        return true;
    }
}
