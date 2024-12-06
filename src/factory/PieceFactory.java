package factory;

import pieces.Piece;

public interface PieceFactory {
  <T extends Piece> T createPiece(String pieceType, String color, Class<T> pieceClass);
}