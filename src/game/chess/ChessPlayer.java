package game.chess;

import game.Player;
import pieces.Piece;
import pieces.chess.King;

/**
 * Classe que define como funciona o jogador
 */
public class ChessPlayer extends Player {

  public ChessPlayer(String name) {
    super(name);
  }

  public boolean hasKing() {
    for(Piece piece : this.pieces) {
      if(piece instanceof King) {
        return true;
      }
    }
    return false;
  }
}
