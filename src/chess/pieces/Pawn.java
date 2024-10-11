package chess.pieces;

import boardgames.Board;
import boardgames.Piece;
import boardgames.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private ChessMatch match;

    public Pawn(Board board, Color color, ChessMatch match) {
        super(board, color);
        this.match = match;
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        if (getColor() == Color.WHITE) {
            p.setValue(position.getRow() - 1, position.getColumn());
            if (getBoard().PositionExists(p) && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            p.setValue(position.getRow() - 2, position.getColumn());
            Position p2 = new Position(position.getRow() - 1, position.getColumn());
            if (getBoard().PositionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().PositionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValue(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().PositionExists(p) && getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValue(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().PositionExists(p) && getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            // en passant white
            if(position.getRow() == 3){
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if(getBoard().PositionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == match.getEnPassantVulnerable()){
                    mat[left.getRow() - 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if(getBoard().PositionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == match.getEnPassantVulnerable()){
                    mat[right.getRow() - 1][right.getColumn()] = true;
                }
            }
        }
        else{
            p.setValue(position.getRow() + 1, position.getColumn());
            if (getBoard().PositionExists(p) && !getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            p.setValue(position.getRow() + 2, position.getColumn());
            Position p2 = new Position(position.getRow() + 1, position.getColumn());
            if (getBoard().PositionExists(p) && !getBoard().thereIsAPiece(p) && getBoard().PositionExists(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValue(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().PositionExists(p) && getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValue(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().PositionExists(p) && getBoard().thereIsAPiece(p)) {
                mat[p.getRow()][p.getColumn()] = true;
            }

            //en passant black
            if(position.getRow() == 4){
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if(getBoard().PositionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == match.getEnPassantVulnerable()){
                    mat[left.getRow() + 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if(getBoard().PositionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == match.getEnPassantVulnerable()){
                    mat[right.getRow() + 1][right.getColumn()] = true;
                }
            }
        }
            return mat;
        }

    }

