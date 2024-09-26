package logic;

import board.Position;
import pieces.Piece;

/**
 * Classe que controla o movimento das peças
 */
public interface Movement {
  public static Piece[][] movePiece(Piece[][] board, Position initialPosition, Position targetPosition) {
    board[targetPosition.getRow()][targetPosition.getColumn()] = board[initialPosition.getRow()][initialPosition.getColumn()];
    board[initialPosition.getRow()][initialPosition.getColumn()] = null;
    return board;
  }
}
