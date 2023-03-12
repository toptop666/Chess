package sample;


import java.util.*;

public class Movement {

    private Board board;

    public Movement(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Position> availableMoves(Cell cell) {
        if(cell == null || !cell.HasPiece()) {
            return null;
        }
        ArrayList<Position> moves = cell.getPiece().getAvailableMoves();
        ArrayList<Position> possibleEats = new ArrayList<>();
        if(cell.getPiece().isWhite()) {
            List<Position> movesToRemove = new ArrayList<>();

            for (Position position : moves) {
                if (this.board.getCell(position).HasPiece()) {
                    if (!this.board.getCell(position).getPiece().isWhite()) {
                        possibleEats.add(position);
                        movesToRemove.add(position);
                    }
                }
            }

            moves.removeAll(movesToRemove);
        } else {
            List<Position> movesToRemove = new ArrayList<>();

            for (Position position : moves) {
                if (this.board.getCell(position).HasPiece()) {
                    if (this.board.getCell(position).getPiece().isWhite()) {
                        possibleEats.add(position);
                        movesToRemove.add(position);
                    }
                }
            }

            moves.removeAll(movesToRemove);
        }
        if(! (cell.getPiece() instanceof Knight)) {
            moves.removeIf(position -> this.board.getCell(position).HasPiece());
            removePositionsBeyondEnemyLines(cell.getCoordinate(), moves);
            moves.add(cell.getCoordinate());
            removeUnreachablePositions(moves, possibleEats);
            moves.remove(cell.getCoordinate());
            moves.addAll(possibleEats);
            moves = reachablePositions(moves, cell.getCoordinate());
            moves.remove(cell.getCoordinate());
        }
        if(cell.getPiece() instanceof Pawn) {
            this.pawnMoves(cell, moves);
        }
        moves.removeIf(pos-> this.board.getCell(pos).HasPiece() && this.board.getCell(pos).getPiece().isWhite() == cell.getPiece().isWhite());
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
        ArrayList<Position> deletablePositions = new ArrayList<>();
        boolean delete = true;
        for(Position mayBeDeleted :list2) {
            delete = true;
            for(Position position : list1) {
                if(isAdjacent(position, mayBeDeleted)) {
                    delete = false;
                }
            }
            if(delete) {
                deletablePositions.add(mayBeDeleted);
            }
        }
        list2.removeAll(deletablePositions);
    }

    public void removePositionsBeyondEnemyLines(Position startingPosition, ArrayList<Position> positions) {
        // Create a new HashSet to store the reachable positions
        Set<Position> reachablePositions = new HashSet<>();
        reachablePositions.add(startingPosition);

        // Create a new Queue to store the positions to check
        Queue<Position> positionsToCheck = new LinkedList<>();
        positionsToCheck.offer(startingPosition);

        // Loop until there are no more positions to check
        while (!positionsToCheck.isEmpty()) {
            Position currentPosition = positionsToCheck.poll();
            for (Position position : positions) {
                if (isPositionReachable(currentPosition, position) && !reachablePositions.contains(position)) {
                    reachablePositions.add(position);
                    positionsToCheck.offer(position);
                }
            }
        }

        // Remove unreachable positions from the original list
        positions.retainAll(reachablePositions);
    }

    private boolean isPositionReachable(Position a, Position b) {
        int dx = Math.abs(a.getWidth() - b.getWidth());
        int dy = Math.abs(a.getHeight() - b.getHeight());
        return (dx == 1 && dy == 1) || (dx == 0 && dy == 1) || (dx == 1 && dy == 0);
    }


    public boolean move(Cell cell, Position position) throws CloneNotSupportedException {
        ArrayList<Position> moves = availableMoves(cell);
        if(moves == null || !moves.contains(position)) {
            return false;
        }
        if(cell.getPiece() instanceof Pawn) {
            ((Pawn) cell.getPiece()).setHasMoves(true);
        }
        if(this.board.getCell(position).HasPiece()) {
            if(this.board.getCell(position).getPiece().isWhite() != cell.getPiece().isWhite()) {
                this.board.eat(cell, this.board.getCell(position));
                return true;
            }
        }
        this.board.movePiece(cell, position);
        return true;
    }

    private boolean pawnMoves(Cell cell, ArrayList<Position> moves) {
        Position illegalPos = new Position(-1, -1);
        Cell eat1 = this.board.getCell(new Position(cell.getPiece().getPosition().getHeight() + 1, cell.getPiece().getPosition().getWidth()+1));
        Cell eat2 = this.board.getCell(new Position(cell.getPiece().getPosition().getHeight() + 1, cell.getPiece().getPosition().getWidth()-1));
        Cell block = this.board.getCell(new Position(cell.getPiece().getPosition().getHeight() + 1, cell.getPiece().getPosition().getWidth()));
        if(cell.getPiece().isWhite()) {
            eat1 = this.board.getCell(new Position(cell.getPiece().getPosition().getHeight() - 1, cell.getPiece().getPosition().getWidth()+1));
            eat2 = this.board.getCell(new Position(cell.getPiece().getPosition().getHeight() - 1, cell.getPiece().getPosition().getWidth()-1));
            block = this.board.getCell(new Position(cell.getPiece().getPosition().getHeight() - 1, cell.getPiece().getPosition().getWidth()));
        }
        if(eat1 != null && eat1.HasPiece() && eat1.getPiece().isWhite() != cell.getPiece().isWhite() && !eat1.getCoordinate().equals(illegalPos)) {
            moves.add(eat1.getCoordinate());
        }
        if(eat2 != null && eat2.HasPiece() && eat2.getPiece().isWhite() != cell.getPiece().isWhite() && !eat2.getCoordinate().equals(illegalPos)) {
            moves.add(eat2.getCoordinate());
        }
        if(block != null && block.HasPiece()) {
            moves.remove(block.getCoordinate());
        }
        return true;
    }

}
