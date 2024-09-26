package game;

import java.util.ArrayList;

import enums.Color;
import pieces.Piece;
import pieces.chess.ChessPiece;

/**
 * Classe que define como funciona o jogador
 */
public abstract class Player {
  protected String name;
  protected Color color;
  protected ArrayList<Piece> pieces;

  public Player(String name) {
    this.name = name;
    this.pieces = new ArrayList<Piece>();
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return this.color;
  }

  public void addPiece(ChessPiece piece) {
    this.pieces.add(piece);
  }

  /**
   * Retorna uma representação em string de todas as peças que um jogador tem.
   * 
   * @return Uma string separada por vírgulas de todas as peças.
   */
  public String getPieces() {
    String pieces = "";
    for(int i = 0; i < this.pieces.size(); i++) {
      pieces += this.pieces.get(i).toString();
      if(i < this.pieces.size() - 1) {
        pieces += " ,";
      }
    }
    return pieces;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
