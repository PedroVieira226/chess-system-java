package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ChessMatch match = new ChessMatch();

        while(true){
            try {
                UI.clearScreen();
                UI.printMatch(match);
                System.out.println();
                System.out.println("Source");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = match.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(match.getpieces(), possibleMoves);

                System.out.println();
                System.out.println("Target");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = match.performChessMove(source, target);
            }

            catch(ChessException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }

            catch(InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }

        }

    }
    /*Position ---> char + int
    * Piece ---> Piece[][]*/



}
