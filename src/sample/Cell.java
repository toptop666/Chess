package sample;

public class Cell {

    private Position coordinate;
    private boolean hasPiece;
    private Piece piece;


    public Cell(Position coordinate, boolean hasPiece, Piece piece) {
        this.coordinate = coordinate;
        this.hasPiece = hasPiece;
        if(!hasPiece) {
            this.piece = null;
            return;
        }
        this.piece = piece;
    }

    public Position getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Position coordinate) {
        this.coordinate = coordinate;
    }

    public boolean HasPiece() {
        return hasPiece;
    }

    public void setHasPiece(boolean hasPiece) {
        this.hasPiece = hasPiece;
    }

    public Piece getPiece() {
        if(!this.hasPiece) {
            return null;
        }
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.hasPiece = (piece != null);
    }
}
