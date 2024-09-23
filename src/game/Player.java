package game;

import java.util.ArrayList;

import enums.Color;
import pieces.chess.ChessPiece;

/**
 * Classe que define como funciona o jogador
 */
public class Player {
  private String name;
  private Color color;
  private ArrayList<ChessPiece> pieces;

  public Player(String name) {
    this.name = name;
    this.pieces = new ArrayList<ChessPiece>();
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return this.color;
  }

  public String getPieces() {
    return this.pieces.toString();
  }
  
  @Override
  public String toString() {
    return this.name;
  }
}
