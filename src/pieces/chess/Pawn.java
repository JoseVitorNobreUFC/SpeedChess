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
  private boolean enPassantPossible = false;

  public Pawn(Color color) {
    super(color);
  }

  @Override
  public ArrayList<String> getAvailableMoves(Piece[][] board, Position position) {
    ArrayList<String> availablePositions = new ArrayList<String>();

    if(this.getColor().equals(Color.WHITE)) {
      availablePositions.addAll(getMovesInADirection(board, position, 1));
    } else{
      availablePositions.addAll(getMovesInADirection(board, position, -1));
    }
    availablePositions.addAll(this.getEnPassant(board, position));
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
   * @return lista de posições disponíveis
   */
  private ArrayList<String> getMovesInADirection(Piece[][] board, Position position, int rowDirection) {
    ArrayList<String> availablePositions = new ArrayList<String>();
    
    int row = position.getRow() + rowDirection;
    int column = position.getColumn();
    if(row >= 0 && row < 8) {
      if(board[row][column] == null) {
        availablePositions.add(row + "" + column);
        if(firstMove && board[row + rowDirection][column] == null) {
          availablePositions.add((row + rowDirection) + "" + column);
        }
      }
      if(column > 0 && board[row][column - 1] != null && board[row][column - 1].getColor() != this.getColor()) {
        availablePositions.add(row + "" + (column - 1));
      }
      if(column < 7 && board[row][column + 1] != null && board[row][column + 1].getColor() != this.getColor()) {
        availablePositions.add(row + "" + (column + 1));
      }
    }
    return availablePositions;
  }
  
  @Override
  public String toString() {
    return this.getColor().equals(Color.WHITE) ? "\u001b[1m\u265F\u001B[0m" : "\u001b[1m\u001B[90m\u2659\u001B[0m";
  }

  private ArrayList<String> getEnPassant(Piece[][] board, Position position) {
    ArrayList<String> availablePositions = new ArrayList<String>();
    int row = position.getRow();
    int column = position.getColumn();
    Color color = board[row][column].getColor();

    if(color.equals(Color.WHITE) && row == 4) {
      Pawn pawn;
      if(board[row][column - 1] instanceof Pawn) {
        pawn = (Pawn) board[row][column - 1];
        if(pawn.isEnPassantPossible()) {
          availablePositions.add((row + 1) + "" + (column - 1));
        }
      } else if(board[row][column + 1] instanceof Pawn) {
        pawn = (Pawn) board[row][column + 1];
        if(pawn.isEnPassantPossible()) {
          availablePositions.add((row + 1) + "" + (column + 1));
        }
      }
    } else if(color.equals(Color.BLACK) && row == 3) {
      Pawn pawn;
      if(board[row][column - 1] instanceof Pawn) {
        pawn = (Pawn) board[row][column - 1];
        if(pawn.isEnPassantPossible()) {
          availablePositions.add((row - 1) + "" + (column - 1));
        }
      } else if(board[row][column + 1] instanceof Pawn) {
        pawn = (Pawn) board[row][column + 1];
        if(pawn.isEnPassantPossible()) {
          availablePositions.add((row - 1) + "" + (column + 1));
        }
      }
    }
    return availablePositions;
  }

  public boolean isFirstMove() {
    return this.firstMove;
  }

  public void setFirstMove(boolean firstMove) {
    this.firstMove = firstMove;
  }

  public boolean isEnPassantPossible() {
    return this.enPassantPossible;
  }

  public void setEnPassantPossible(boolean enPassantPossible) {
    this.enPassantPossible = enPassantPossible;
  }
}
