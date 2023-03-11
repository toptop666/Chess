package sample;


import java.util.*;

public class Game {

    private Board board;

    public Game() {
        this.board = new Board();
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Position> availableMoves(Cell cell) {
        if(!cell.HasPiece()) {
            return null;
        }
        ArrayList<Position> moves = cell.getPiece().getAvailableMoves();
        ArrayList<Position> possibleEats = new ArrayList<>();
        if(cell.getPiece().isWhite()) {
            for(Position position : moves) {
                if(this.board.getCell(position).HasPiece()) {
                    if(!this.board.getCell(position).getPiece().isWhite()) {
                        possibleEats.add(position);
                    }
                }
            }
        } else {
            for(Position position : moves) {
                if(this.board.getCell(position).HasPiece()) {
                    if(this.board.getCell(position).getPiece().isWhite()) {
                        possibleEats.add(position);
                    }
                }
            }
        }
        moves.removeIf(position -> this.board.getCell(position).HasPiece());
        removeUnreachablePositions(moves, possibleEats);
        moves.addAll(possibleEats);
        if(! (cell.getPiece() instanceof Knight)) {
            moves = reachablePositions(moves, cell.getCoordinate());
            moves.remove(cell.getCoordinate());
        }
        return moves;
    }

    private ArrayList<Position> reachablePositions(ArrayList<Position> positions, Position start) {
        HashSet<Position> reachable = new HashSet<>();
        HashSet<Position> visited = new HashSet<>();
        visited.add(start);
        Stack<Position> stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            Position current = stack.pop();
            for (Position pos : positions) {
                if (!visited.contains(pos) && isAdjacent(current, pos)) {
                    visited.add(pos);
                    reachable.add(pos);
                    stack.push(pos);
                }
            }
        }
        reachable.add(start);
        return new ArrayList<>(reachable);
    }

    private boolean isAdjacent(Position a, Position b) {
        int dx = Math.abs(a.getWidth() - b.getWidth());
        int dy = Math.abs(a.getHeight() - b.getHeight());
        return dx <= 1 && dy <= 1 && (dx + dy != 0);
    }

    public void removeUnreachablePositions(ArrayList<Position> list1, ArrayList<Position> list2) {
        Set<Position> reachablePositions = new HashSet<>();
        Queue<Position> queue = new LinkedList<>();

        // Add all positions in list1 to reachablePositions set and queue
        for (Position p : list1) {
            reachablePositions.add(p);
            queue.add(p);
        }

        // Perform BFS to find all reachable positions from list1
        while (!queue.isEmpty()) {
            Position current = queue.remove();
            for (Position neighbor : list2) {
                if (isAdjacent(current, neighbor) && !reachablePositions.contains(neighbor)) {
                    reachablePositions.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        // Remove all positions from list2 that are not in reachablePositions
        list2.removeIf(p -> !reachablePositions.contains(p));
    }


    public boolean move(Cell cell, Position position) {
        ArrayList<Position> moves = availableMoves(cell);
        if(moves == null || !moves.contains(position)) {
            return false;
        }
        if(cell.getPiece() instanceof Pawn) {
            ((Pawn) cell.getPiece()).setHasMoves(true);
        }
        this.board.movePiece(cell, position);
        return true;
    }

}
