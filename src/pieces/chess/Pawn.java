package pieces.chess;

import java.util.ArrayList;

import board.Position;
import enums.Color;
import pieces.Piece;

/**
 * Classe que define o comportamento de um peão
 */
public class Pawn extends ChessPiece{

  public Pawn(Position position, Color color) {
    super(position, color);
  }

  @Override
  public ArrayList<String> getAvailableMoves(Piece[][] board, Position position) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAvailableMoves'");
  }
  
  @Override
  public String toString() {
    return this.getColor().equals(Color.WHITE) ? "♟" : "♙";
  }
}
