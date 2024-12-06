package factory;

import pieces.Piece;

public interface PieceFactory {
  Piece createPiece(String type, String color);
}

