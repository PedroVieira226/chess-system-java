package chess.pieces;

import boardgames.Board;
import boardgames.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "K";
    }

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[8][8];

        Position p = new Position(0, 0);

        //above
        p.setValue(position.getRow() - 1, position.getColumn());
        if(getBoard().PositionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //below
        p.setValue(position.getRow() + 1, position.getColumn());
        if(getBoard().PositionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //right
        p.setValue(position.getRow(), position.getColumn() + 1);
        if(getBoard().PositionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //left
        p.setValue(position.getRow(), position.getColumn() - 1);
        if(getBoard().PositionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //upright
        p.setValue(position.getRow() -1 , position.getColumn() + 1);
        if(getBoard().PositionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //upleft
        p.setValue(position.getRow() -1, position.getColumn() - 1);
        if(getBoard().PositionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //downright
        p.setValue(position.getRow() + 1, position.getColumn() + 1);
        if(getBoard().PositionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //downleft
        p.setValue(position.getRow() + 1, position.getColumn() - 1);
        if(getBoard().PositionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }


        return mat;

    }
}
