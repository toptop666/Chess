package sample;

import java.util.ArrayList;

import static sample.Main.NUMBER_OF_CELLS;


public abstract class Piece implements Cloneable {

    private boolean white;
    private Position position;

    public Piece(boolean white, Position position) {
        this.white = white;
        this.position = position;
    }

    public Piece(Piece piece) {
        this.white = piece.isWhite();
        this.position = piece.getPosition();
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

    public Object clone() throws CloneNotSupportedException {
        Piece clone = (Piece) super.clone();
        clone.position = (Position) this.position.clone();
        return clone;
    }


}
