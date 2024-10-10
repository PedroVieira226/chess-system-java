package chess;

import boardgames.Board;
import boardgames.Piece;
import boardgames.Position;


public abstract class ChessPiece extends Piece {

    private Color color;


    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }



    public Color getColor() {
        return color;
    }

    protected boolean isThereOpponentPiece(Position position) {
        ChessPiece p = (ChessPiece)(getBoard().piece(position));
        return p != null && p.getColor() != getColor();
    }
}
