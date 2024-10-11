package chess.pieces;

import boardgames.Board;
import boardgames.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "N";
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[8][8];

        Position p = new Position(0, 0);

        p.setValue(position.getRow() - 1, position.getColumn() - 2);
        if (getBoard().PositionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setValue(position.getRow() - 2, position.getColumn() - 1);
        if (getBoard().PositionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setValue(position.getRow() - 2, position.getColumn() + 1);
        if (getBoard().PositionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setValue(position.getRow() - 1, position.getColumn() + 2);
        if (getBoard().PositionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setValue(position.getRow() + 1, position.getColumn() + 2);
        if (getBoard().PositionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setValue(position.getRow() + 2, position.getColumn() + 1);
        if (getBoard().PositionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setValue(position.getRow() + 2, position.getColumn() - 1);
        if (getBoard().PositionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }


        p.setValue(position.getRow() + 1, position.getColumn() - 2);
        if (getBoard().PositionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }


        return mat;

    }
}




