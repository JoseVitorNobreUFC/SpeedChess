package pieces.chess;

import board.Position;
import enums.Color;

/**
 * Classe que define o comportamento de um peão
 */
public class Pawn extends ChessPiece{

  public Pawn(Position position, Color color) {
    super(position, color);
  }

  @Override
  public void move(Position position) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'move'");
  }

  @Override
  public Position[] getAvailableMoves() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAvailableMoves'");
  }
  
  @Override
  public String toString() {
    return this.getColor().equals(Color.WHITE) ? "♟" : "♙";
  }
}
