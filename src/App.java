import exceptions.*;
import game.*;
import game.chess.ChessUI;

public class App {
    final static String reset = "\u001B[0m";
    final static String red = "\u001B[31m";
    public static void main(String[] args) throws Exception {
        ChessUI aplicacao = new ChessUI();
        int iterator = 0;
        while (!aplicacao.checkEndGame()) {
            try {
                Player playerToMove = aplicacao.decidePlayerToMove(iterator);
                aplicacao.processCheck();

                aplicacao.printBoard();

                int action = aplicacao.action(playerToMove);
                if(action == -1) {
                    break;
                } else if(action == 0) {
                    continue;
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
