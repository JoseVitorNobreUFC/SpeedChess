package pieces.chess;

import java.util.ArrayList;

import board.Position;
import enums.Color;
import pieces.Piece;

public class Rook extends ChessPiece{

  public Rook(Position position, Color color) {
    super(position, color);
  }
  
  @Override
  public ArrayList<String> getAvailableMoves(Piece[][] board) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAvailableMoves'");
  }
  
  
  @Override
  public String toString() {
    return this.getColor().equals(Color.WHITE) ? "♜" : "♖";
  }
  
}
