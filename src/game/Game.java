package game;

import board.Board;
import board.Position;
import exceptions.PlayerException;

public abstract class Game {
  
  private String reset = "\u001B[0m";
  private String bold = "\u001b[1m";
  private String gray = bold + "\u001B[90m";

  public abstract Board getBoard();

  public abstract Player getPlayer1();

  public abstract Player getPlayer2();

  public abstract void movePiece(Position initialPosition, Position targetPosition, Player playerToMove);

  /**
   * Exibe os movimentos possíveis para uma posição específica no tabuleiro, 
   *   junto com as peças capturadas por cada jogador.
   * 
   * @param position  a posição para exibir os movimentos possíveis
   * @param playerToMove  o jogador que está fazendo o movimento
   * @return          uma representação em string dos movimentos possíveis e peças capturadas
   */
  public String showPossibleMoves(Position position, Player playerToMove) {
    if(!playerToMove.getColor().equals(this.getBoard().getPiece(position).getColor())) {
      throw new PlayerException("\nERRO!!!! A peça selecionada não é sua\n");
    }

    String s = bold + "Peças tomadas por " + this.getPlayer1() + ": [" + reset + gray + this.getPlayer1().getPieces();
    s += reset + bold + " ]\n\n" + reset;
    s += this.getBoard().showPossibleMoves(position);
    s += gray + "\nPeças tomadas por " + this.getPlayer2() + ": [" + reset + bold + this.getPlayer2().getPieces();
    s += gray + " ]\n" + reset;
    return s;
  }

  /**
   * Exibe o tabuleiro e as peças capturadas por cada um dos jogadores.
   * 
   * @return uma representação em string do tabuleiro e as peças capturadas
   */
  @Override
  public String toString() {
    String s = bold + "Peças tomadas por " + this.getPlayer1() + ": [" + reset + gray + this.getPlayer1().getPieces();
    s += reset + bold + " ]\n\n" + reset;
    s += this.getBoard().toString();
    s += gray + "\nPeças tomadas por " + this.getPlayer2() + ": [" + reset + bold + this.getPlayer2().getPieces();
    s += gray + " ]\n" + reset;
    return s;
  }

  public abstract Player endGame();
}
