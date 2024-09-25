import exceptions.*;
import game.*;

public class App {
    final static String reset = "\u001B[0m";
    final static String red = "\u001B[31m";
    public static void main(String[] args) throws Exception {
        UI aplicacao = new UI();
        int iterator = 0;
        while (!aplicacao.checkEndGame()) {
            try {
                Player playerToMove = aplicacao.decidePlayerToMove(iterator);

                aplicacao.printBoard();
                if(aplicacao.action(playerToMove) == -1) { // Estudar porque peão B7 não pode ir A8
                    break;
                }

                aplicacao.checkPawnPromotion();
                iterator++;
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
    }
}
