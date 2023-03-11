package sample;

import java.util.ArrayList;

public class Bishop extends Piece{
    public Bishop(boolean white, Position position) {
        super(white, position);
    }

    @Override
    public ArrayList<Position> getAvailableMoves() {
        ArrayList<Position> moves = new ArrayList<Position>();
        for(int i = 0; i<8; i++) {
            moves.add(new Position(this.getPosition().getHeight()+i, this.getPosition().getWidth()+i));
            moves.add(new Position(this.getPosition().getHeight()+i, this.getPosition().getWidth()-i));
            moves.add(new Position(this.getPosition().getHeight()-i, this.getPosition().getWidth()+i));
            moves.add(new Position(this.getPosition().getHeight()-i, this.getPosition().getWidth()-i));
        }
        moves.removeIf(position -> position.equals(new Position(-1, -1)));

        return moves;
    }
}
