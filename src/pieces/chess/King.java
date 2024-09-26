package pieces.chess;

import java.util.ArrayList;

import board.Position;
import enums.Color;
import pieces.Piece;

public class King extends ChessPiece {
  private boolean firstMove = true;

  public King(Color color) {
    super(color);
  }
  
  @Override
  public ArrayList<String> getAvailableMoves(Piece[][] board, Position position) {
    ArrayList<String> availablePositions = new ArrayList<String>();

    // Movimentação Diagonal
    availablePositions.addAll(getMoveInADirection(board, position, -1, -1));
    availablePositions.addAll(getMoveInADirection(board, position, -1, 1));
    availablePositions.addAll(getMoveInADirection(board, position, 1, -1));
    availablePositions.addAll(getMoveInADirection(board, position, 1, 1));
    
    // Movimentação reta
    availablePositions.addAll(getMoveInADirection(board, position, -1, 0));
    availablePositions.addAll(getMoveInADirection(board, position, 1, 0));
    availablePositions.addAll(getMoveInADirection(board, position, 0, -1));
    availablePositions.addAll(getMoveInADirection(board, position, 0, 1));

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
  private ArrayList<String> getMoveInADirection(Piece[][] board, Position position, int rowDirection, int columnDirection) {
    ArrayList<String> availablePositions = new ArrayList<String>();
    
    int row = position.getRow() + rowDirection;
    int column = position.getColumn() + columnDirection;
    if (row >= 0 && row < 8 && column >= 0 && column < 8) {
      if (board[row][column] == null) {
        availablePositions.add(row + "" + column);
        row += rowDirection;
        column += columnDirection;
      } else {
        if (board[row][column].getColor() != this.getColor()) {
          availablePositions.add(row + "" + column);
        }
      }
    }
    return availablePositions;
  }

  @Override
  public String toString() {
    return this.getColor().equals(Color.WHITE) ? "\u001b[1m\u265A\u001B[0m" : "\u001b[1m\u001B[90m\u2654\u001B[0m";
  }

  private ArrayList<String> checkDangerArea(Piece[][] board, Position kingPosition, ArrayList<String> availablePositions) {
    ArrayList<String> dangerPositions = new ArrayList<String>();

    int row = kingPosition.getRow();
    int column = kingPosition.getColumn();


    return dangerPositions;
  }

  private boolean checkDangerSpot(Piece[][] board, Position kingPosition, String spot) {
    
    return false;
  }

  public boolean isFirstMove() {  
    return this.firstMove;
  }

  public void setFirstMove(boolean firstMove) {
    this.firstMove = firstMove;
  }
}
