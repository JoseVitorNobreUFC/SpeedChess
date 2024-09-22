package pieces.chess;

import java.util.ArrayList;

import board.Position;
import enums.Color;
import pieces.Piece;

public class Knight extends ChessPiece {

  public Knight(Position position, Color color) {
    super(position, color);
  }

  @Override
  public ArrayList<String> getAvailableMoves(Piece[][] board) {
    ArrayList<String> availablePositions = new ArrayList<String>();

    availablePositions.add(getMovesInADirection(board, 2, 1));
    availablePositions.add(getMovesInADirection(board, 2, -1));
    availablePositions.add(getMovesInADirection(board, -2, 1));
    availablePositions.add(getMovesInADirection(board, -2, -1));
    availablePositions.add(getMovesInADirection(board, 1, 2));
    availablePositions.add(getMovesInADirection(board, 1, -2));
    availablePositions.add(getMovesInADirection(board, -1, 2));
    availablePositions.add(getMovesInADirection(board, -1, -2));

    return availablePositions;
  }

  /**
   * Nessa função você vai fornecer o tabuleiro para o algoritmo encontrar os limites da movimentação, em 
   * seguida vai fornecer a posição da peça e a direção que a mesma vai se mover. Por fim, o algoritmo vai retornar a
   * lista de posições disponíveis
   * 
   * @param board Tabuleiro do jogo
   * @param rowDirection direção da linha	-1 para cima, 1 para baixo
   * @param columnDirection direção da coluna -1 para esquerda, 1 para direita
   * @return lista de posições disponíveis
   */
  private String getMovesInADirection(Piece[][] board, int rowDirection, int columnDirection) {
    int row = this.getPosition().getRow() + rowDirection;
    int column = this.getPosition().getColumn() + columnDirection;

    if (row >= 0 && row < 8 && column >= 0 && column < 8) {
      if (board[row][column] == null) {
        return row + "" + column;
      } else {
        if (board[row][column].getColor() != this.getColor()) {
          return row + "" + column;
        }
      }
    }
    return "";
  }

  @Override
  public String toString() {
    return this.getColor().equals(Color.WHITE) ? "♞" : "♘";
  }
  
}
