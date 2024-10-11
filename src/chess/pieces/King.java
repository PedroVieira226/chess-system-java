package chess.pieces;

import boardgames.Board;
import boardgames.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch match;

    public King(Board board, Color color, ChessMatch match) {
        super(board, color);
        this.match = match;
    }

    @Override
    public String toString(){
        return "K";
    }

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    private boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
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

        //castling kignside
        if(getMoveCount() == 0 && !match.getCheck()){
            //castling king side
            Position posR1 = new Position(position.getRow(), position.getColumn() + 3);
            if(testRookCastling(posR1)){
                Position p1 =  new Position(position.getRow(), position.getColumn() + 1);
                Position p2 =  new Position(position.getRow(), position.getColumn() + 2);
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null){
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }
            //castling queenside
            Position posR2 = new Position(position.getRow(), position.getColumn() - 4);
            if(testRookCastling(posR2)){
                Position p1 =  new Position(position.getRow(), position.getColumn() - 1);
                Position p2 =  new Position(position.getRow(), position.getColumn() - 2);
                Position p3 =  new Position(position.getRow(), position.getColumn() - 3);
                if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null){
                    mat[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }


        return mat;

    }
}
