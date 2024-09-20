package pieces;

import board.Position;
import enums.Color;

public abstract class ChessPiece implements Piece {
  private Position position;
  private Color color;

  public ChessPiece(Position position, Color color) {
    this.position = position;
    this.color = color;
  }

  @Override
  public Position getPosition() {
    return this.position;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

}
