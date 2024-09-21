package board;

import enums.Color;
import pieces.Piece;
import pieces.chess.*;

public class Board {
  private Piece[][] board;

  public Board() {
    board = new Piece[8][8];
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        board[i][j] = null;
      }
    }

    initBoard();
  }

  public void initBoard() {
    // Criando os Peões
    for (int i = 0; i < 8; i++) {
      board[1][i] = new Pawn(new Position(i, 1), Color.BLACK);
      board[6][i] = new Pawn(new Position(i, 6), Color.WHITE);
    }

    // Criando peças pretas
    board[0][0] = new Rook(new Position(0, 0), Color.BLACK);
    board[0][1] = new Knight(new Position(1, 0), Color.BLACK);
    board[0][2] = new Bishop(new Position(2, 0), Color.BLACK);
    board[0][3] = new Queen(new Position(3, 0), Color.BLACK);
    board[0][4] = new King(new Position(4, 0), Color.BLACK);
    board[0][5] = new Bishop(new Position(5, 0), Color.BLACK);
    board[0][6] = new Knight(new Position(6, 0), Color.BLACK);
    board[0][7] = new Rook(new Position(7, 0), Color.BLACK);
    
    // Criando peças brancas
    board[7][0] = new Rook(new Position(0, 7), Color.WHITE);
    board[7][1] = new Knight(new Position(1, 7), Color.WHITE);
    board[7][2] = new Bishop(new Position(2, 7), Color.WHITE);
    board[7][3] = new Queen(new Position(3, 7), Color.WHITE);
    board[7][4] = new King(new Position(4, 7), Color.WHITE);
    board[7][5] = new Bishop(new Position(5, 7), Color.WHITE);
    board[7][6] = new Knight(new Position(6, 7), Color.WHITE);
    board[7][7] = new Rook(new Position(7, 7), Color.WHITE);
  }

  public void movePiece(Position initialPosition, Position targetPosition) {
    System.out.println(initialPosition.getRow() + " " + initialPosition.getColumn() + " -> " + targetPosition.getRow() + " " + targetPosition.getColumn());
    board[targetPosition.getRow()][targetPosition.getColumn()] = board[initialPosition.getRow()][initialPosition.getColumn()];
    board[initialPosition.getRow()][initialPosition.getColumn()] = null;
  }
  
  @Override
  public String toString() {
    String s = "    A    B    C    D    E    F    G    H\n";
    for (int i = 0; i < 8; i++) {
      s += (i+1) + " ";
      for (int j = 0; j < 8; j++) {
        if(board[i][j] != null) {
          s += "[" + board[i][j] + " ] ";
        } else {
          s += "[  ] ";
        }
      }
      s += "\n";
    }
    return s;
  }
}