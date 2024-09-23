import exceptions.*;
import game.*;

import java.util.Scanner;

import board.Position;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);

        String player1, player2;
        System.out.print("Digite o nome do primeiro jogador: ");
        player1 = ler.nextLine();
        System.out.print("Digite o nome do segundo jogador: ");
        player2 = ler.nextLine();

        Game game = new Game(new Player(player1), new Player(player2));
        int i = 0;
        String position = "";
        String confirmation = "";
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
                position = ler.nextLine();
                Position initialPosition = new Position(position.charAt(0), Integer.parseInt(position.substring(1)));
                
                System.out.println(game.showPossibleMoves(initialPosition, playerToMove));
                System.out.println("Deseja realmente mover esta peça? [S/N]");
                confirmation = ler.nextLine();
                if(!confirmation.equalsIgnoreCase("s")) {
                    continue;
                } else if(confirmation.equalsIgnoreCase("exit")) {
                    break;
                }
                
                System.out.print("Digite a posição para onde deseja mover a peça: ");
                position = ler.nextLine();
                Position targetPosition = new Position(position.charAt(0), Integer.parseInt(position.substring(1)));
                game.movePiece(initialPosition, targetPosition);
                i++;
            } catch (BoardException e) {
                System.err.println("\nERRO!!!" + e.getMessage());
            } catch (PieceException e) {
                System.err.println("\nERRO!!!" + e.getMessage());
            } catch (PlayerException e) {
                System.err.println("\nERRO!!!" + e.getMessage());
            } catch (PositionException e) {
                System.err.println("\nERRO!!!" + e.getMessage());
            }
        }
        ler.close();
    }
}
