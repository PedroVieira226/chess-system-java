package chess;

import boardgames.Board;
import boardgames.Piece;
import boardgames.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;

    public ChessMatch() {
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
    }

    public ChessPiece[][] getpieces() {
        ChessPiece[][] mat = new ChessPiece[8][8];//aqui eu opero com a matriz de pieces, no geral, position é mais para a interação com o jogador
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);//retorna o tabuleiro inteiro, o estado atual dele
            }
        }
        return mat;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
           Position position = sourcePosition.toPosition();
           validateSourcePosition(position);
           return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {//obvio
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);Piece capturedPiece = makeMove(source, target);//move a peça apos a checagem

        if(testCheck(currentPlayer)){
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }
        check = (testCheck(opponent(currentPlayer))) ? true : false;

        nextTurn();




        return (ChessPiece)capturedPiece;//== null
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);// == null
        Piece capturedPiece = board.removePiece(target);// == null, e abre espaço pra fazer o da prox linha
        board.placePiece(p, target);//eu to removendo a peça da posição inicial e movendo

        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);

        }

        return capturedPiece;// == null
    }

    private void undoMove(Position source, Position target, Piece capturedPiece) {
        Piece p = board.removePiece(target);
        board.placePiece(p, source);

        if(capturedPiece != null){
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(p);
        }

    }

    private void validateSourcePosition(Position sourcePosition) {
        if(!board.thereIsAPiece(sourcePosition)){
            throw new ChessException("There is no piece on source position");
        }
        if(currentPlayer != ((ChessPiece)board.piece(sourcePosition)).getColor()){
            throw new ChessException("The chosen piece is not yours");
        }
        if(!board.piece(sourcePosition).IsThereAnyPossibleMove()){
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    private void validateTargetPosition(Position sourcePosition, Position targetPosition){
        if(!board.piece(sourcePosition).possibleMove(targetPosition)){
            throw new ChessException("The chosen piece can't move to the target position");
        }
    }

    private Color opponent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x->((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece piece : list){
            if(piece instanceof King){
                return (ChessPiece)piece;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x->((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        for(Piece piece : opponentPieces){
          boolean[][] mat = piece.possibleMoves();
          if(mat[kingPosition.getRow()][kingPosition.getColumn()]){
              return true;
          }
        }
        return false;
    }


    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup(){
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));
        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
}
