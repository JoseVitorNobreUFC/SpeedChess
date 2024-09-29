package game.chess;

import java.util.ArrayList;

import board.Position;
import enums.Color;
import exceptions.PieceException;
import game.UI;
import pieces.chess.Pawn;

/**
 * Classe que define a interface do jogo de xadrez
 */
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

  /**
   * Verifica se o último movimento do jogo é uma promoção de peão e solicita ao jogador que escolha
   * qual peça promover.
   *
   * Essa função recupera o último movimento do tabuleiro do jogo e verifica se a posição de destino
   * está na primeira ou última linha do tabuleiro e se a peça na posição de destino é um peão. Se
   * ambas as condições forem atendidas, solicita ao jogador que escolha qual peça promover o peão.
   * A entrada do jogador é passada para o método promotePawn do jogo, que realiza a promoção.
   */
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
  public void processCheck() {
    if(this.game.analyseCheck()) {
      this.game.fixCheck();
    }
  }
}
