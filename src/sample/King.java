package sample;

import java.util.ArrayList;

public class King extends Piece{
    public King(boolean white, Position position) {
        super(white, position);
    }

    @Override
    public ArrayList<Position> getAvailableMoves() {
        ArrayList<Position> moves = new ArrayList<Position>();
        moves.add(new Position(this.getPosition().getHeight()+1, this.getPosition().getWidth()+1));
        moves.add(new Position(this.getPosition().getHeight()+1, this.getPosition().getWidth()-1));
        moves.add(new Position(this.getPosition().getHeight()-1, this.getPosition().getWidth()+1));
        moves.add(new Position(this.getPosition().getHeight()-1, this.getPosition().getWidth()-1));
        moves.add(new Position(this.getPosition().getHeight(), this.getPosition().getWidth()+1));
        moves.add(new Position(this.getPosition().getHeight(), this.getPosition().getWidth()-1));
        moves.add(new Position(this.getPosition().getHeight()+1, this.getPosition().getWidth()));
        moves.add(new Position(this.getPosition().getHeight()-1, this.getPosition().getWidth()));
        moves.removeIf(position -> position.equals(new Position(-1, -1)));

        return moves;
    }
}
