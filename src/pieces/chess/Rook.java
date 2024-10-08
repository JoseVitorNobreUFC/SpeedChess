package pieces.chess;

import java.util.ArrayList;

import board.Position;
import enums.Color;
import pieces.Piece;

public class Rook extends ChessPiece{
  private boolean firstMove = true;

  public Rook(Color color) {
    super(color);
  }
  
  @Override
  public ArrayList<String> getAvailableMoves(Piece[][] board, Position position) {
    ArrayList<String> availablePositions = new ArrayList<String>();

    availablePositions.addAll(getMovesInADirection(board, position, -1, 0));
    availablePositions.addAll(getMovesInADirection(board, position, 1, 0));
    availablePositions.addAll(getMovesInADirection(board, position, 0, -1));
    availablePositions.addAll(getMovesInADirection(board, position, 0, 1));
    
    if(availablePositions.isEmpty()) {
      return null;
    }
    return availablePositions;
  }

  
  /**
   * Nessa função você vai fornecer o tabuleiro para o algoritmo encontrar os limites da movimentação, em 
   * seguida vai fornecer a posição da peça e a direção que a mesma vai se mover. Por fim, o algoritmo vai retornar a
   * lista de posições disponíveis
   * 
   * @param board Tabuleiro do jogo
   * @param position posição da peça a ser analisada
   * @param rowDirection direção da linha	-1 para cima, 1 para baixo, 0 não aplica direção
   * @param columnDirection direção da coluna -1 para esquerda, 1 para direita, 0 não aplica direção
   * @return lista de posições disponíveis
   */
  private ArrayList<String> getMovesInADirection(Piece[][] board, Position position, int rowDirection, int columnDirection) {
    ArrayList<String> availablePositions = new ArrayList<String>();
    
    int row = position.getRow() + rowDirection;
    int column = position.getColumn() + columnDirection;
    while (row >= 0 && row < 8 && column >= 0 && column < 8) {
      if (board[row][column] == null) {
        availablePositions.add(row + "" + column);
        row += rowDirection;
        column += columnDirection;
      } else {
        if (board[row][column].getColor() != this.getColor()) {
          availablePositions.add(row + "" + column);
        }
        break;
      }
    }
    return availablePositions;
  }
  
  @Override
  public String toString() {
    return this.getColor().equals(Color.WHITE) ? "\u001b[1m\u265C\u001B[0m" : "\u001b[1m\u001B[90m\u2656\u001B[0m";
  }

  public boolean isFirstMove() {
    return firstMove;
  }

  public void setFirstMove(boolean firstMove) {
    this.firstMove = firstMove;
  }
}
