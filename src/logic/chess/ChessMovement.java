package logic.chess;

import board.Position;
import pieces.chess.*;
import java.util.Stack;

/**
 * Classe que controla o movimento das peças de xadrez
 */
public class ChessMovement{
  private static Stack<Position> lastPositions = new Stack<Position>(); // Isso pode ser só uma Position mesmo, mas estou com preguiça de mudar
  public static ChessPiece lastPieceTaken = null;

    /**
     * Move uma peça de xadrez de uma posição inicial para uma posição alvo no tabuleiro.
     * 
     * Essa função verifica movimentos especiais como en passant e roque, e atualiza o tabuleiro de acordo.
     * 
     * @param  board            o estado atual do tabuleiro de xadrez
     * @param  initialPosition  a posição da peça a ser movida
     * @param  targetPosition   a posição onde a peça será movida
     * @return                  o tabuleiro atualizado após o movimento
     */
  public static ChessPiece[][] moveChessPiece(ChessPiece[][] board, Position initialPosition, Position targetPosition) {
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
    
    return board;
  }

  /**
   * Realiza o movimento especial de En Passant no tabuleiro de xadrez.
   * 
   * @param  board            o estado atual do tabuleiro de xadrez
   * @param  initialPosition  a posição da peça a ser movida
   * @param  targetPosition   a posição onde a peça será movida
   * @return                  o tabuleiro atualizado após o movimento
   */
  public static ChessPiece[][] EnPassant(ChessPiece[][] board, Position initialPosition, Position targetPosition){
    Position lastPosition = lastPositions.pop();
    lastPieceTaken = board[lastPosition.getRow()][lastPosition.getColumn()];
    board[lastPosition.getRow()][lastPosition.getColumn()] = null;
    board[targetPosition.getRow()][targetPosition.getColumn()] = board[initialPosition.getRow()][initialPosition.getColumn()];
    board[initialPosition.getRow()][initialPosition.getColumn()] = null;
    return board;
  }

  public static ChessPiece[][] Castling(ChessPiece[][] board, Position initialPosition, Position targetPosition){
    
    return board;
  }


  /**
   * Verifica se o movimento especial de En Passant é válido.
   * 
   * @param  board            o estado atual do tabuleiro de xadrez
   * @param  initialPosition  a posição inicial da peça que está tentando realizar o En Passant
   * @param  targetPosition   a posição alvo da peça que está tentando realizar o En Passant
   * @return                  true se o En Passant é válido, false caso contrário
   */
  private static boolean checkEnPassant(ChessPiece[][] board, Position initialPosition, Position targetPosition){
    if(lastPositions.isEmpty()) return false;
    Position lastPosition = lastPositions.firstElement();
    if(Position.positionsDistance(targetPosition, initialPosition) == 2) return false;
    if(Position.positionsDistance(targetPosition, lastPosition) != 1) return false;
    if(board[targetPosition.getRow()][targetPosition.getColumn()] != null) return false;
    if(targetPosition.getColumn() == lastPosition.getColumn()) return true;
    
    return false;
  }

  private static boolean checkCastling(ChessPiece[][] board, Position initialPosition, Position targetPosition){
    return false;
  }

  /**
   * Modifica os valores internos da peça peão no tabuleiro para fazer o En Passant ser verificavel, além de também
   * ajudar na validação do movimento do peão
   *
   * @param  board            o estado atual do tabuleiro de xadrez
   * @param  initialPosition  a posição inicial do peão
   * @param  targetPosition   a posição alvo do peão
   * @return                  o peão movido, ou null se o movimento não for válido
   */
  public static Pawn checkPawnMovement(ChessPiece[][] board, Position initialPosition, Position targetPosition){
    Pawn pawn = null;
    if (board[initialPosition.getRow()][initialPosition.getColumn()] instanceof Pawn) {
      pawn = (Pawn) board[initialPosition.getRow()][initialPosition.getColumn()];
      if(pawn.isFirstMove()) {
        if(Position.positionsDistance(initialPosition, targetPosition) == 2) {
          pawn.setEnPassantPossible(true);
        } 
        pawn.setFirstMove(false);
      }
      lastPositions.add(targetPosition); // Aqui pode dar merda, não sei
    }
    return pawn;
  }
}
