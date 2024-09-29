package game.chess;

import board.*;
import board.chess.ChessBoard;
import enums.Color;
import game.Game;
import game.Player;
import pieces.chess.ChessPiece;

/**
 * Classe que define como vai funcionar o fluxo do jogo de xadrez
 */
public class ChessGame extends Game {
  private ChessPlayer player1;
  private ChessPlayer player2;
  private ChessBoard board;

  private final String yellow = "\u001B[33m";

  public ChessGame(String player1, String player2) {
    this.player1 = new ChessPlayer(player1);
    this.player2 = new ChessPlayer(player2);
    
    this.player1.setColor(Color.WHITE);
    this.player2.setColor(Color.BLACK);
    board = new ChessBoard();
  }

  @Override
  public void movePiece(Position initialPosition, Position targetPosition, Player playerToMove) {
    ChessPiece piece = (ChessPiece) board.movePiece(initialPosition, targetPosition);

    if(piece != null) {
      playerToMove.addPiece(piece);
    }
  }

  @Override
  public Player endGame() {
    if(player1.hasKing()) {
      return player1;
    } else if(player2.hasKing()) {
      return player2;
    }
    return null;
  }

  @Override
  public Board getBoard() {
    return this.board;
  }

  @Override
  public Player getPlayer1() {
    return this.player1;
  }

  @Override
  public Player getPlayer2() {
    return this.player2;
  }

  /**
   * Promove um peão para uma peça especificada após um movimento de promoção válido.
   *
   * @param piece           a peça para a qual o peão será promovido
   * @param initialPosition a posição inicial do peão
   * @param targetPosition  a posição alvo do peão
   */
  public void promotePawn(String piece, Position initialPosition, Position targetPosition) {
    if(this.board.checkPromotion(initialPosition, targetPosition)) {
      this.board.pawnPromotion(piece, targetPosition);
    }
  }
  
  public void fixCheck(){
    if(this.board.getCheckPiecePosition() == null){
      return;
    }

    Position checkPiecePosition = this.board.getCheckPiecePosition();
    ChessPiece piece = (ChessPiece) this.board.getPiece(checkPiecePosition);
    String color = piece.getColor().equals(Color.WHITE) ? "preto" : "branco";
    while(this.board.getCheckPiecePosition() != null){
      System.out.println(yellow + bold + "Rei " + color + " se encontra em cheque!" + reset);
      System.out.println(this.board.showCheck());
      break;
    }
  }

  public boolean analyseCheck() {
    return this.board.getCheckPiecePosition() != null;
  }
  // Casos de Empate:
  // 1. Empate por acordo
  // 2. Empate por afogamento
  // 3. Empate por tripla repetição
  // 4. Empate por insuficiencia de material
  // 5. Empate por 50 lances
}
