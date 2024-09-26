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
  //TODO: Chamar função que verifica check-mate
}
