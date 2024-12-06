package factory;

import enums.Color;
import pieces.Piece;
import pieces.chess.*;

public class GenericPieceFactory implements PieceFactory {
  @Override
  public <T extends Piece> T createPiece(String pieceType, String color, Class<T> pieceClass) {
      Color pieceColor = Color.valueOf(color.toUpperCase());
      Piece piece;

      switch (pieceType.toLowerCase()) {
          case "pawn":
              piece = new Pawn(pieceColor);
              break;
          case "rook":
              piece = new Rook(pieceColor);
              break;
          case "knight":
              piece = new Knight(pieceColor);
              break;
          case "bishop":
              piece = new Bishop(pieceColor);
              break;
          case "queen":
              piece = new Queen(pieceColor);
              break;
          case "king":
              piece = new King(pieceColor);
              break;
          default:
              throw new IllegalArgumentException("Invalid piece type: " + pieceType);
      }

      if (pieceClass.isInstance(piece)) {
          return pieceClass.cast(piece);
      } else {
          throw new ClassCastException("Cannot cast " + piece.getClass() + " to " + pieceClass);
      }
  }
}
