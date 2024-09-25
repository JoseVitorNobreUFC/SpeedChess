package pieces.chess;

import enums.Color;
import pieces.Piece;

/** 
 * Classe abstrata onde serão estipuladas algumas regras para uma peça de xadrez
 */
public abstract class ChessPiece implements Piece {
  protected boolean firstMove = true;
  private Color color;

  public ChessPiece(Color color) {
    this.color = color;
  }

  @Override
  public Color getColor(){
    return this.color;
  }
  
  public boolean isFirstMove() {
    return firstMove;
  }

  public void takeFirstMove() {
    this.firstMove = false;
  }
}
