package logic.chess;

import board.Position;
import pieces.Piece;
import pieces.chess.*;

import java.util.ArrayList;
import java.util.Stack;
public class ChessMovement{
  private static Stack<Position> lastPositions = new Stack<Position>();
  public static Piece lastPieceTaken = null;

  public static Piece[][] moveChessPiece(Piece[][] board, Position initialPosition, Position targetPosition) {
    if(checkEnPassant(board, initialPosition, targetPosition)) {
      return EnPassant(board, initialPosition, targetPosition);
    } else {
      if(!lastPositions.isEmpty()) {
        Position lastPosition = lastPositions.pop();
        Pawn pawn = (Pawn) board[lastPosition.getRow()][lastPosition.getColumn()];
        pawn.setEnPassantPossible(false);
        board[lastPosition.getRow()][lastPosition.getColumn()] = pawn;
      }
    }
    if(checkCastling(board, initialPosition, targetPosition)) {
      return Castling(board, initialPosition, targetPosition);
    }

    Pawn pawn = checkPawnMovement(board, initialPosition, targetPosition);
    lastPieceTaken = board[targetPosition.getRow()][targetPosition.getColumn()];
    if(pawn != null) {
      board[targetPosition.getRow()][targetPosition.getColumn()] = pawn;
    } else {
      board[targetPosition.getRow()][targetPosition.getColumn()] = board[initialPosition.getRow()][initialPosition.getColumn()];
    }
    board[initialPosition.getRow()][initialPosition.getColumn()] = null;
    
    System.out.println(getAllPawnsData(board));
    return board;
  }

  public static Piece[][] EnPassant(Piece[][] board, Position initialPosition, Position targetPosition){
    Position lastPosition = lastPositions.pop();
    lastPieceTaken = board[lastPosition.getRow()][lastPosition.getColumn()];
    board[lastPosition.getRow()][lastPosition.getColumn()] = null;
    board[targetPosition.getRow()][targetPosition.getColumn()] = board[initialPosition.getRow()][initialPosition.getColumn()];
    board[initialPosition.getRow()][initialPosition.getColumn()] = null;
    return board;
  }

  public static Piece[][] Castling(Piece[][] board, Position initialPosition, Position targetPosition){
    
    return board;
  }


  private static boolean checkEnPassant(Piece[][] board, Position initialPosition, Position targetPosition){
    if(lastPositions.isEmpty()) return false;
    Position lastPosition = lastPositions.firstElement();
    if(Position.positionsDistance(targetPosition, initialPosition) == 2) return false;
    if(Position.positionsDistance(targetPosition, lastPosition) != 1) return false;
    if(board[targetPosition.getRow()][targetPosition.getColumn()] != null) return false;
    if(targetPosition.getColumn() == lastPosition.getColumn()) return true;
    
    return false;
  }

  private static boolean checkCastling(Piece[][] board, Position initialPosition, Position targetPosition){
    return false;
  }

  public static Pawn checkPawnMovement(Piece[][] board, Position initialPosition, Position targetPosition){
    Pawn pawn = null;
    if (board[initialPosition.getRow()][initialPosition.getColumn()] instanceof Pawn) {
      pawn = (Pawn) board[initialPosition.getRow()][initialPosition.getColumn()];
      if(pawn.isFirstMove()) {
        if(Position.positionsDistance(initialPosition, targetPosition) == 2) {
          pawn.setEnPassantPossible(true);
        } 
        pawn.setFirstMove(false);
      }
      lastPositions.addFirst(targetPosition);
    }
    return pawn;
  }

  public static ArrayList<String> getAllPawnsData(Piece[][] board) {
    ArrayList<String> pawnsData = new ArrayList<String>();
    for(int row = 0; row < 8; row++) {
      for(int column = 0; column < 8; column++) {
        if(board[row][column] instanceof Pawn) {
          pawnsData.add(row + "" + column + " " + board[row][column].getColor() + " " + ((Pawn) board[row][column]).isEnPassantPossible());
        }
      }
    }
    return pawnsData;
  }
}
