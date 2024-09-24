import exceptions.*;
import game.*;

import java.util.Scanner;

import board.Position;

/**
 * Algumas otimizações precisam ser feitas aqui
 */
public class App {
    final static String reset = "\u001B[0m";
    final static String red = "\u001B[31m";
    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);

        String player1, player2;
        System.out.print("Digite o nome do primeiro jogador: ");
        player1 = ler.nextLine();
        System.out.print("Digite o nome do segundo jogador: ");
        player2 = ler.nextLine();

        Game game = new Game(new Player(player1), new Player(player2));
        int i = 0;
        String move = "";
        String action = "";
        Position initialPosition, targetPosition;
        while (true) {
            try {
                Player playerToMove;
                if(i % 2 == 0) {
                    playerToMove = game.getPlayer1();
                } else {
                    playerToMove = game.getPlayer2();
                }

                System.out.println(game);
                System.out.print("Digite a posição da peça que voce deseja mover " + playerToMove + ": ");
                move = ler.nextLine();
                
                if(move.length() == 4) {
                    initialPosition = new Position(move.substring(0, 2));
                    targetPosition = new Position(move.substring(2, 4));
                } else {
                    initialPosition = new Position(move);
                    
                    System.out.println(game.showPossibleMoves(initialPosition, playerToMove));
                    System.out.println("Deseja realmente mover esta peça? [S/N]");
                    action = ler.nextLine();
                    if(!action.equalsIgnoreCase("s")) {
                        continue;
                    } else if(action.equalsIgnoreCase("exit")) {
                        break;
                    }

                    System.out.print("Digite a posição para onde deseja mover a peça: ");
                    move = ler.nextLine();
                    targetPosition = new Position(move);
                }
                game.movePiece(initialPosition, targetPosition, playerToMove);
                

                if(game.endGame(targetPosition) != null) {
                    System.out.println(game);
                    System.out.println("\u001B[32m" + "O vencedor é: " + game.endGame(targetPosition) + "\u001B[0m");
                    break;
                }
                i++;
            } catch (BoardException e) {
                System.err.println(red + e.getMessage() + reset);
            } catch (PieceException e) {
                System.err.println(red + e.getMessage() + reset);
            } catch (PlayerException e) {
                System.err.println(red + e.getMessage() + reset);
            } catch (PositionException e) {
                System.err.println(red + e.getMessage() + reset);
            } catch (IllegalArgumentException e) {
                System.err.println(red + e.getMessage() + reset);
            }
        }
        ler.close();
    }
}
