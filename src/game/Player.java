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

  public void addPiece(ChessPiece piece) {
    this.pieces.add(piece);
  }

  public String getPieces() {
    String pieces = "[\u001B[33m";
    for(int i = 0; i < this.pieces.size(); i++) {
      pieces += this.pieces.get(i).toString();
      if(i < this.pieces.size() - 1) {
        pieces += " ,";
      }
    }
    pieces += "\u001B[0m ]";
    return pieces;
  }
  
  public boolean hasKing() {
    for(ChessPiece piece : this.pieces) {
      if(piece.getPieceName().equals("King")) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
