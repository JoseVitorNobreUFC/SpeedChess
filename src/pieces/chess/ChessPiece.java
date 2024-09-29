package pieces.chess;

import board.Position;
import enums.Color;
import pieces.Piece;
import java.util.ArrayList;

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

  public boolean analyseCheck(Piece[][] board, Position position) {
    ArrayList<String> availablePositions = getAvailableMoves(board, position);
    if(availablePositions == null) {
      return false;
    }
    for (String availablePosition : availablePositions) {
      int row = availablePosition.charAt(0) - '0';
      int column = availablePosition.charAt(1) - '0';
      if (board[row][column] != null
          && board[row][column].getColor() != this.getColor()
          && board[row][column] instanceof King) {
        return true;
      }
    }
    return false;
  }

}
