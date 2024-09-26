package game.chess;

import java.util.ArrayList;

import board.Position;
import enums.Color;
import exceptions.PieceException;
import game.Player;
import game.UI;
import pieces.chess.Pawn;

public class ChessUI extends UI {
  private ChessGame game;

  public ChessUI() {
    System.out.print(bold + "Digite o nome do jogador com as peças brancas: ");
    String player1 = sc.nextLine();
    System.out.println(reset);

    System.out.print(gray +"Digite o nome do jogador com as peças pretas: ");
    String player2 = sc.nextLine();
    System.out.println(reset);
    this.game = new ChessGame(player1, player2);
  }

  @Override
  public ChessGame getGame() {
    return this.game;
  }

  public Player decidePlayerToMove(int iterator) {
    if (iterator % 2 == 0) {
      return game.getPlayer1();
    } else {
      return game.getPlayer2();
    }
  }

  public void printBoard() {
    System.out.println(game);
  }

  public void checkPawnPromotion(){
    ArrayList<Position> lastMovements = game.getBoard().getLastMovement();
    if((lastMovements.get(1).getRow() == 7 || lastMovements.get(1).getRow() == 0)
        && game.getBoard().getPiece(lastMovements.get(1)) instanceof Pawn) {
      Color color = game.getBoard().getPiece(lastMovements.get(1)).getColor();
      Position initialPosition = lastMovements.get(0);
      Position targetPosition = lastMovements.get(1);
        while (true) {
        System.out.println("Digite para qual peça voce deseja promover: ");
        if(color == Color.WHITE) {
          System.out.println("[0] \u265B  [1] \u265C  [2] \u265E  [3] \u265D");
        } else {
          System.out.println("[0] \u2655  [1] \u2656  [2] \u2658  [3] \u2657");
        }
        try {
          this.game.promotePawn(sc.nextLine(), initialPosition, targetPosition);
          break;
        } catch (PieceException e) {
          System.err.println(red + e.getMessage() + reset);
        }
      }
    }
  }

  // TODO: Chamar função do game para verificar se o rei ficou em xeque
}
