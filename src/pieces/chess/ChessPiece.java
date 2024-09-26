package pieces.chess;

import enums.Color;
import pieces.Piece;

/** 
 * Classe abstrata onde serão estipuladas algumas regras para uma peça de xadrez
 */
public abstract class ChessPiece implements Piece {
  private Color color;

  public ChessPiece(Color color) {
    this.color = color;
  }

  @Override
  public Color getColor(){
    return this.color;
  }
  
  // TODO: Implementar função de check aqui para as outras classes herdarem  
}
