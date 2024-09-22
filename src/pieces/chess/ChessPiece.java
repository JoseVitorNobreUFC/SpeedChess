package pieces.chess;

import board.Position;
import enums.Color;
import pieces.Piece;

/** 
 * Classe abstrata onde serão estipuladas algumas regras para uma peça de xadrez
 */
public abstract class ChessPiece extends Piece {
  protected boolean firstMove = true;

  public ChessPiece(Position position, Color color) {
    super(position, color);
  }

  public boolean isFirstMove() {
    return firstMove;
  }

  public void takeFirstMove() {
    this.firstMove = false;
  }
}
