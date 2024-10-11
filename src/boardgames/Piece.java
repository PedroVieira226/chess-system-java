package boardgames;

import chess.Color;

public abstract class Piece {

    protected Position position;
    private Board board;

    public Piece(Board board) {
        this.board = board;
    }

    protected Board getBoard() {
        return board;
    }



    public abstract boolean[][] possibleMoves();//abstrata pois piece é muito generico pra ter uma iplementação, jogar @Override na classe especifica

    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];//retorna se a posição especifica é possivel
    }

    public boolean IsThereAnyPossibleMove() {
        boolean[][] mat  = possibleMoves();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j]) {
                    return true;
                }
        }
    }

        return false;
    }
    }
