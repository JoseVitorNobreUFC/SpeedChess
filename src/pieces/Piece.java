package pieces;

import java.util.ArrayList;

import board.Position;
import enums.Color;

/** 
 * Classe abstrata que define as funções base de uma peça, seja ela de xadrez ou de outro jogo de tabuleiro
 */
public interface Piece {

  public Color getColor();
  /**
   * Retorna as direções em que a peça pode se mover
   * @param board Tabuleiro do jogo
   * @param position Posição da peça
   * @return Um vetor de posição, onde cada posição corresponde a um movimento valido
   */
  public abstract ArrayList<String> getAvailableMoves(Piece[][] board, Position position);
}
