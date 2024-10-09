package chess.pieces;

import boardgames.Board;
import boardgames.Piece;
import boardgames.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[8][8];

        Position p = new Position(0, 0);
        // above
        p.setValue(position.getRow() - 1, position.getColumn());
        while(getBoard().PositionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }

        if(getBoard().PositionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //left
        p.setValue(position.getRow(), position.getColumn() - 1);
        while(getBoard().PositionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }

        if(getBoard().PositionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //right
        p.setValue(position.getRow(), position.getColumn() + 1);
        while(getBoard().PositionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }

        if(getBoard().PositionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //below
        p.setValue(position.getRow() + 1, position.getColumn());
        while(getBoard().PositionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }

        if(getBoard().PositionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }


        return mat;

    }
}
