package chess;

import boardgames.BoardException;

public class ChessException extends BoardException {

    public ChessException(String message) {
        super(message);
    }

    private static final long serialVersionUID = 1L;
}
