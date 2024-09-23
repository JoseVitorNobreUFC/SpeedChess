package game;

import board.*;
import enums.Color;
import exceptions.PlayerException;

/**
 * Classe que define como vai funcionar o fluxo do jogo
 */
public class Game {
  private Board board;
  private Player player1;
  private Player player2;

  public Game(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
    
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

  public void movePiece(Position initialPosition, Position targetPosition) {
    board.movePiece(initialPosition, targetPosition);
  }

  public String showPossibleMoves(Position position, Player playerToMove) {
    if(!playerToMove.getColor().equals(board.getPiece(position).getColor())) {
      throw new PlayerException("A peça selecionada não é sua");
    }
    return board.showPossibleMoves(position);
  }

  @Override
  public String toString() {
    return board.toString();
  }
}
