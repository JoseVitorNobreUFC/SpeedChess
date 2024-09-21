import java.util.Scanner;

import board.Board;
import board.Position;

public class App {
    public static void main(String[] args) throws Exception {
        Board board = new Board();
        System.out.println(board);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Seleciona a peça: ");
            String initialPosition = scanner.nextLine();
            if(initialPosition.equals("end")) break;

            System.out.println("Seleciona o destino: ");
            String targetPosition = scanner.nextLine();

            Position initial = new Position(initialPosition.charAt(0), (Integer.parseInt(initialPosition.substring(1))));
            Position target = new Position(targetPosition.charAt(0), (Integer.parseInt(targetPosition.substring(1))));
            board.movePiece(initial, target);
            System.out.println(board);
        }
    }
}
