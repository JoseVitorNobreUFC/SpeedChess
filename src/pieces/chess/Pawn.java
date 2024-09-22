package pieces.chess;

import java.util.ArrayList;

import board.Position;
import enums.Color;
import pieces.Piece;

/**
 * Classe que define o comportamento de um peão
 */
public class Pawn extends ChessPiece{

  private boolean firstMove = true;

  public Pawn(Position position, Color color) {
    super(position, color);
  }

  @Override
  public ArrayList<String> getAvailableMoves(Piece[][] board) {
    ArrayList<String> availablePositions = new ArrayList<String>();

    if(this.getColor().equals(Color.WHITE)) {
      availablePositions.addAll(getMovesInADirection(board, 1));
    } else{
      availablePositions.addAll(getMovesInADirection(board, -1));
    }
    return availablePositions;
  }

  /**
   * Nessa função você vai fornecer o tabuleiro para o algoritmo encontrar os limites da movimentação, em 
   * seguida vai fornecer a posição da peça e a direção que a mesma vai se mover. Por fim, o algoritmo vai retornar a
   * lista de posições disponíveis
   * 
   * @param board Tabuleiro do jogo
   * @param rowDirection direção da linha	-1 para cima, 1 para baixo, 0 não aplica direção
   * @return lista de posições disponíveis
   */
  private ArrayList<String> getMovesInADirection(Piece[][] board, int rowDirection) {
    ArrayList<String> availablePositions = new ArrayList<String>();
    
    int row = this.getPosition().getRow() + rowDirection;
    int column = this.getPosition().getColumn();
    if(row >= 0 && row < 8) {
      if(board[row][column] == null) {
        availablePositions.add(row + "" + column);
        if(firstMove && board[row + rowDirection][column] == null) {
          availablePositions.add((row + rowDirection) + "" + column);
        }
      }
      if(column > 1 && board[row][column - 1] != null && board[row][column - 1].getColor() != this.getColor()) {
        availablePositions.add(row + "" + (column - 1));
      }
      if(column < 6 && board[row][column + 1] != null && board[row][column + 1].getColor() != this.getColor()) {
        availablePositions.add(row + "" + (column + 1));
      }
    }
    return availablePositions;
  }
  
  @Override
  public String toString() {
    return this.getColor().equals(Color.WHITE) ? "♟" : "♙";
  }

  public void tookFirstMove() {
    this.firstMove = false;
  }
}
