package boardgames;

public class Board {

    private int rows;
    private int columns;
    private Piece[][] pieces;

    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardException("Error creating board: there must be" +
                    " at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }

    public int getRows() {
        return rows;
    }



    public int getColumns() {
        return columns;
    }



    public Piece piece(int row, int column) {
        if(!PositionExists(row, column)){
            throw new BoardException("Postion not on the board");
        }
        return pieces[row][column];//matriz pieces como é operada
    }


    public Piece piece(Position position){
        if(!PositionExists(position)){
            throw new BoardException("Postion not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];// matriz do tabuleiro digamos assim, b1 etc
    }

    public void placePiece(Piece piece, Position position) {
        if(thereIsAPiece(position)){
            throw new BoardException("There is already a piece on position" + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;//peça null antes na posição assume "piece"
        piece.position = position;//atualiza a posição da piece
    }

    private boolean PositionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    public boolean PositionExists(Position position){
        return PositionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position) {
        if(!PositionExists(position)){
            throw new BoardException("Postion not on the board");
        }
        return piece(position) != null;
    }

    public Piece removePiece(Position position){
        if(!PositionExists(position)){
            throw new BoardException("Postion not on the board");
        }
        if(piece(position) == null){
            return null;
        }
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }



}
