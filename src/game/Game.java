package game;

import board.*;
import enums.Color;
import exceptions.PlayerException;
import pieces.chess.ChessPiece;

/**
 * Classe que define como vai funcionar o fluxo do jogo
 */
public class Game {
  private Board board;
  private Player player1;
  private Player player2;
  private String reset = "\u001B[0m";
  private String bold = "\u001b[1m";
  private String gray = bold + "\u001B[90m";

  public Game(String player1, String player2) {
    this.player1 = new Player(player1);
    this.player2 = new Player(player2);
    
    this.player1.setColor(Color.WHITE);
    this.player2.setColor(Color.BLACK);
    board = new Board();
  }

  public Board getBoard() {
    return board;
  }

  public Player getPlayer1() {
    return player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public void movePiece(Position initialPosition, Position targetPosition, Player playerToMove) {
    ChessPiece piece = board.movePiece(initialPosition, targetPosition);

    if(piece != null) {
      playerToMove.addPiece(piece);
    }
  }

  public String showPossibleMoves(Position position, Player playerToMove) {
    if(!playerToMove.getColor().equals(board.getPiece(position).getColor())) {
      throw new PlayerException("\nERRO!!!! A peça selecionada não é sua\n");
    }

    String s = "Peças tomadas por " + player1 + ": " + player1.getPieces() + "\n\n";
    s += board.showPossibleMoves(position);
    s += "\nPeças tomadas por " + player2 + ": " + player2.getPieces() + "\n";
    return s;
  }

  @Override
  public String toString() {
    String s = bold + "Peças tomadas por " + this.getPlayer1() + ": [" + reset + gray + this.getPlayer1().getPieces();
    s += reset + bold + " ]\n\n" + reset;
    s += board.toString();
    s += gray + "\nPeças tomadas por " + this.getPlayer2() + ": [" + reset + bold + this.getPlayer2().getPieces();
    s += gray + " ]\n" + reset;
    return s;
  }

  public Player endGame() {
    if(player1.hasKing()) {
      return player1;
    } else if(player2.hasKing()) {
      return player2;
    }
    return null;
  }

  public void promotePawn(String piece, Position initialPosition, Position targetPosition) {
    if(this.board.checkPromotion(initialPosition, targetPosition)) {
      this.board.pawnPromotion(piece, targetPosition);
    }
  }
}
