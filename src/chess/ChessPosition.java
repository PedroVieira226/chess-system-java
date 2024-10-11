package chess;

import boardgames.Piece;
import boardgames.Position;

public class ChessPosition { // Posições do tabuleiro

    private char column;
    private int row;

    public ChessPosition(char column, int row) {//sempre que eu usar a porra do chessposition é porque eu estou operando com uma entrada do tipo "char + int", ou seja, interação com user
        if(column < 'a' || column > 'h' || row < 1 || row > 8)
            throw new ChessException("Error instantiating ChessPosition. Valid values are" +
                    " from a1 to h8");
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    protected Position toPosition(){
        return new Position( 8 - row, column - 'a');//de char + int para piece[][]
    }

    protected static ChessPosition fromPosition(Position position){
        return new ChessPosition((char)('a' + position.getColumn()),8 - position.getRow() );//o contrario
    }

    @Override
    public String toString() {
        return "" + column + row;
    }
}
