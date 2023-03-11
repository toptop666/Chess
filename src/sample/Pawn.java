package sample;

import java.util.ArrayList;

public class Pawn extends Piece {

    private boolean hasMoves = false;

    public Pawn(boolean white, Position position) {
        super(white, position);
    }

    @Override
    public ArrayList<Position> getAvailableMoves() {
        ArrayList<Position> moves = new ArrayList<>();
        if(this.isWhite()) {
            if(this.getPosition().getHeight()>0) {
                moves.add(new Position(this.getPosition().getHeight()-1, this.getPosition().getWidth()));
            }
            if(!this.hasMoves) {
                moves.add(new Position(this.getPosition().getHeight()-2, this.getPosition().getWidth()));
            }
        }
        else {
            if(this.getPosition().getHeight()<7) {
                moves.add(new Position(this.getPosition().getHeight()+1, this.getPosition().getWidth()));
            }
            if(!this.hasMoves) {
                moves.add(new Position(this.getPosition().getHeight()+2, this.getPosition().getWidth()));
            }
        }

        return moves;
    }

    public boolean HasMoves() {
        return hasMoves;
    }

    public void setHasMoves(boolean hasMoves) {
        this.hasMoves = hasMoves;
    }
}
