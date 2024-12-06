package factory;

import enums.Color;
import pieces.Piece;
import pieces.chess.*;

public class ChessPieceFactory implements PieceFactory {
  @Override
  public Piece createPiece(String type, String colorString) {
      Color color = Color.fromString(colorString);

      switch (type.toLowerCase()) {
          case "king":
              return new King(color);
          case "queen":
              return new Queen(color);
          case "pawn":
              return new Pawn(color);
          case "knight":
              return new Knight(color);
          case "bishop":
              return new Bishop(color);
          case "rook":
              return new Rook(color);
          default:
              throw new IllegalArgumentException("Unknown piece type: " + type);
      }
  }
}


