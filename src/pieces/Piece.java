package pieces;

import board.Position;
import enums.Color;

public interface Piece {

  public Position getPosition();
  public void move(Position position);
  public Color getColor();
  public Position[] getAvailableMoves();
}
