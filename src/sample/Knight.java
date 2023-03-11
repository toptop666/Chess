package sample;

import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(boolean white, Position position) {
        super(white, position);
    }

    @Override
    public ArrayList<Position> getAvailableMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        moves.add(new Position(this.getPosition().getHeight()+2, this.getPosition().getWidth()+1));
        moves.add(new Position(this.getPosition().getHeight()+2, this.getPosition().getWidth()-1));
        moves.add(new Position(this.getPosition().getHeight()-2, this.getPosition().getWidth()+1));
        moves.add(new Position(this.getPosition().getHeight()-2, this.getPosition().getWidth()-1));
        moves.add(new Position(this.getPosition().getHeight()+1, this.getPosition().getWidth()+2));
        moves.add(new Position(this.getPosition().getHeight()-1, this.getPosition().getWidth()+2));
        moves.add(new Position(this.getPosition().getHeight()+1, this.getPosition().getWidth()-2));
        moves.add(new Position(this.getPosition().getHeight()-1, this.getPosition().getWidth()-2));
        moves.removeIf(position -> position.equals(new Position(-1, -1)));
        return moves;
    }
}
