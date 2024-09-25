package game;

import java.util.ArrayList;
import java.util.Scanner;

import board.Position;
import enums.Color;
import exceptions.PieceException;

public class UI {
  private Scanner sc = new Scanner(System.in);
  private Game game;
  private String red = "\u001B[31m";
  private String reset = "\u001B[0m";
  private String green = "\u001B[32m";
  private String bold = "\u001b[1m";
  private String gray = bold + "\u001B[90m";

  public UI() {
    game = initGame();
  }

  public Game initGame() {
    System.out.print(bold + "Digite o nome do jogador com as peças brancas: ");
    String player1 = sc.nextLine();
    System.out.println(reset);

    System.out.print(gray +"Digite o nome do jogador com as peças pretas: ");
    String player2 = sc.nextLine();
    System.out.println(reset);
    return new Game(player1, player2);
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

  public int action(Player playerToMove) {
    String playerColor = playerToMove.getColor().equals(Color.WHITE) ? bold : gray;
    System.out.print(playerColor + "Digite a posição da peça que voce deseja mover " + playerToMove + ": " + reset);
    String command = sc.nextLine();
    
    if (command.equals("forfeit")) {
      System.out.println(red + bold + "Jogador " + playerToMove + " desistiu do jogo" + reset);
      if(playerToMove.equals(game.getPlayer1())) {
        System.out.println(green + bold + "O vencedor é: " + game.getPlayer2() + reset + "\n");
      } else {
        System.out.println(green + bold + "O vencedor é: " + game.getPlayer1() + reset + "\n");
      }
      sc.close();
      return -1;
    } else if (command.length() == 4) {
      Position initialPosition = new Position(command.substring(0, 2));
      Position targetPosition = new Position(command.substring(2, 4));
      game.movePiece(initialPosition, targetPosition, playerToMove);
    } else {
      Position initialPosition = new Position(command);
      System.out.println(game.showPossibleMoves(initialPosition, playerToMove));
      System.out.println("Deseja realmente mover esta peça? [S/N]");
      if(!sc.nextLine().equalsIgnoreCase("S")) {
        return 0;
      } 
      System.out.print("Digite a posição para onde deseja mover a peça: ");
      Position targetPosition = new Position(sc.nextLine());
      game.movePiece(initialPosition, targetPosition, playerToMove);
    }

    return 1;
  }

  public boolean checkEndGame() {
    Player winner = game.endGame();
    if(winner != null) {
      System.out.println(game);
      System.out.println(green + bold + "O vencedor é: " + winner + reset + "\n");
      sc.close(); 
      return true;
    }
    return false;
  }

  public void checkPawnPromotion(){
    ArrayList<Position> lastMovements = game.getBoard().getLastMovement();
    if((lastMovements.get(1).getRow() == 7 || lastMovements.get(1).getRow() == 0)
        && game.getBoard().getPiece(lastMovements.get(1)).getPieceName().equals("Pawn")) {
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
}
