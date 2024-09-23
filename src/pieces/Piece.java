package pieces;

import java.util.ArrayList;

import board.Position;
import enums.Color;

/** 
 * Classe abstrata que define as funções base de uma peça, seja ela de xadrez ou de outro jogo de tabuleiro
 */
public abstract class Piece {
  private Color color;

  public Piece(Color color) {
    this.color = color;
  }


  /**
   * Retorna a cor da peça
   * @return Cor da peça
   */
  public Color getColor(){
    return this.color;
  }

  /**
   * Retorna as direções em que a peça pode se mover
   * @param board Tabuleiro do jogo
   * @param position Posição da peça
   * @return Um vetor de posição, onde cada posição corresponde a um movimento valido
   */
  public abstract ArrayList<String> getAvailableMoves(Piece[][] board, Position position);

  /**
   * Retorna o nome da peça, no tabuleiro iremos usar isso em alguns casos
   * @return Nome da peça
   */
  public abstract String getPieceName();
}
