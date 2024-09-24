package game;

import java.util.Scanner;

import board.Position;

public class UI {
  private Scanner sc = new Scanner(System.in);
  private Game game;
  private String red = "\u001B[31m";
  private String reset = "\u001B[0m";
  private String green = "\u001B[32m";
  private String bold = "\u001b[1m";
  private String gray = "\u001B[90m";

  public UI() {
    game = initGame();
  }

  public Game initGame() {
    System.out.print("Digite o nome do jogador com as peças brancas: " + bold);
    Player player1 = new Player(sc.nextLine());
    System.out.println(reset);

    System.out.print("Digite o nome do jogador com as peças pretas: " + bold + gray);
    Player player2 = new Player(sc.nextLine());
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
    System.out.print("Digite a posição da peça que voce deseja mover " + playerToMove + ": ");
    String command = sc.nextLine();
    
    if (command.equals("exit")) {
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
      System.out.println("\u001B[32m" + "O vencedor é: " + winner + "\u001B[0m");
      sc.close(); 
      return true;
    }
    return false;
  }
}
