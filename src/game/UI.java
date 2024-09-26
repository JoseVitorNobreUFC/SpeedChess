package game;

import java.util.Scanner;

import board.Position;
import enums.Color;

public abstract class UI {
  protected Scanner sc = new Scanner(System.in);
  protected String red = "\u001B[31m";
  protected String reset = "\u001B[0m";
  protected String green = "\u001B[32m";
  protected String bold = "\u001b[1m";
  protected String gray = bold + "\u001B[90m";
  
  public abstract Game getGame();

  public Player decidePlayerToMove(int iterator) {
    if (iterator % 2 == 0) {
      return getGame().getPlayer1();
    } else {
      return getGame().getPlayer2();
    }
  }

  public void printBoard() {
    System.out.println(getGame());
  }

  public int action(Player playerToMove) {
    String playerColor = playerToMove.getColor().equals(Color.WHITE) ? bold : gray;
    System.out.print(playerColor + "Digite a posição da peça que voce deseja mover " + playerToMove + ": " + reset);
    String command = sc.nextLine();
    
    if (command.equals("forfeit")) {
      System.out.println(red + bold + "Jogador " + playerToMove + " desistiu do jogo" + reset);
      if(playerToMove.equals(getGame().getPlayer1())) {
        System.out.println(green + bold + "O vencedor é: " + getGame().getPlayer2() + reset + "\n");
      } else {
        System.out.println(green + bold + "O vencedor é: " + getGame().getPlayer1() + reset + "\n");
      }
      sc.close();
      return -1;
    } else if (command.length() == 4) {
      Position initialPosition = new Position(command.substring(0, 2));
      Position targetPosition = new Position(command.substring(2, 4));
      getGame().movePiece(initialPosition, targetPosition, playerToMove);
    } else {
      Position initialPosition = new Position(command);
      System.out.println(getGame().showPossibleMoves(initialPosition, playerToMove));
      System.out.println("Deseja realmente mover esta peça? [S/N]");
      if(!sc.nextLine().equalsIgnoreCase("S")) {
        return 0;
      } 
      System.out.print("Digite a posição para onde deseja mover a peça: ");
      Position targetPosition = new Position(sc.nextLine());
      getGame().movePiece(initialPosition, targetPosition, playerToMove);
    }

    return 1;
  }

  public boolean checkEndGame() {
    Player winner = getGame().endGame();
    if(winner != null) {
      System.out.println(getGame());
      System.out.println(green + bold + "O vencedor é: " + winner + reset + "\n");
      sc.close(); 
      return true;
    }
    return false;
  }
}
