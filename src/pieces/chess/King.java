package pieces.chess;

import java.util.ArrayList;

import board.Position;
import enums.Color;
import pieces.Piece;

public class King extends ChessPiece {
  
  public King(Position position, Color color) {
    super(position, color);
  }
  
  @Override
  public ArrayList<String> getAvailableMoves(Piece[][] board, Position position) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAvailableMoves'");
  }

  @Override
  public String toString() {
    return this.getColor().equals(Color.WHITE) ? "♚" : "♔";
  }
  
}
