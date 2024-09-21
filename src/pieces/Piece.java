package pieces;

import java.util.ArrayList;

import board.Position;
import enums.Color;

/** 
 * Classe abstrata que define as funções base de uma peça, seja ela de xadrez ou de outro jogo de tabuleiro
 */
public abstract class Piece {
  private Position position;
  private Color color;

  public Piece(Position position, Color color) {
    this.position = position;
    this.color = color;
  }

  /**
   * Retorna a posição da peça
   * @return Posição da peça no tabuleiro
   */
  public Position getPosition(){
    return this.position;
  }

  /**
   * Move a peça para uma nova posição
   * @param position Nova posição da peça
   */
  // public abstract void move(Position position);

  /**
   * Retorna a cor da peça
   * @return Cor da peça
   */
  public Color getColor(){
    return this.color;
  }

  /**
   * Retorna as direções em que a peça pode se mover
   * @return Um vetor de posição, onde cada posição corresponde a um movimento valido
   */
  public abstract ArrayList<String> getAvailableMoves(Piece[][] board);
}
