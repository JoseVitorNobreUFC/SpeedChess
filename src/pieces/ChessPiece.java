package pieces;

import board.Position;
import enums.Color;

/** 
 * Classe abstrata onde serão estipuladas algumas regras para uma peça de xadrez
 */
public abstract class ChessPiece extends Piece {

  public ChessPiece(Position position, Color color) {
    super(position, color);
  }
}
