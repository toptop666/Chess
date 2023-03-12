package sample;

public class Game {

    private Board board;
    private Movement movement;
    private boolean whiteTurn;

    public Game() {
        this.board = new Board();
        this.movement = new Movement(this.board);
        this.whiteTurn = true;
    }

    public Board getBoard() {
        return board;
    }

    public Movement getMovement() {
        return movement;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void turn() {
        this.whiteTurn = !this.whiteTurn;
    }
}
