package sample;

import java.util.ArrayList;

import static sample.Main.NUMBER_OF_CELLS;


public abstract class Piece {

    private boolean white;
    private Position position;
    private int maxCell = NUMBER_OF_CELLS;

    public Piece(boolean white, Position position) {
        this.white = white;
        this.position = position;
    }

    public abstract ArrayList<Position> getAvailableMoves();

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getMaxCell() {
        return maxCell;
    }
}
