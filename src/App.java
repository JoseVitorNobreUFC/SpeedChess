import exceptions.*;
import java.util.Scanner;

import board.Board;
import board.Position;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner(System.in);
        Board board = new Board();
        String position = "";
        String confirmation = "";
        while (true) {
            try {
                System.out.println(board);
                System.out.print("Digite a posição da peça que voce deseja mover: ");
                position = ler.nextLine();
                Position initialPosition = new Position(position.charAt(0), Integer.parseInt(position.substring(1)));
                
                System.out.println(board.showPossibleMoves(initialPosition));
                
                System.out.println("Deseja realmente mover esta peça? [S/N]");
                confirmation = ler.nextLine();
                if(!confirmation.equalsIgnoreCase("s")) {
                    continue;
                }
                
                System.out.print("Digite a posição para onde deseja mover a peça: ");
                position = ler.nextLine();
                Position targetPosition = new Position(position.charAt(0), Integer.parseInt(position.substring(1)));
                board.movePiece(initialPosition, targetPosition);
                break;
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
