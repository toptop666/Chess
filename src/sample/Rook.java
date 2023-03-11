package sample;

import java.util.ArrayList;

public class Rook extends Piece {

    public Rook(boolean white, Position position) {
        super(white, position);
    }

    public ArrayList<Position> getAvailableMoves() {
        ArrayList<Position> positions = new ArrayList<Position>();
        for(int i = 0; i<this.getMaxCell(); i++) {
            if(i==this.getPosition().getHeight()) {
                continue;
            }
            Position position1 = new Position(i, this.getPosition().getWidth());
            Position position2 = new Position(this.getPosition().getHeight(), i);
            positions.add(position1);
            positions.add(position2);
        }
        return positions;
    }
}
