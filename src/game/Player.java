package game;

import enums.Color;

/**
 * Classe que define como funciona o jogador
 */
public class Player {
  private String name;
  private Color color;

  public Player(String name) {
    this.name = name;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return this.color;
  }
  
  @Override
  public String toString() {
    return this.name;
  }
}
