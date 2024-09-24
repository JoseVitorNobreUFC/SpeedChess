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
        UI aplicacao = new UI();
        int iterator = 0;
        while (true) {
            try {
                Player playerToMove = aplicacao.decidePlayerToMove(iterator);

                aplicacao.printBoard();
                if(aplicacao.action(playerToMove) == -1) {
                    break;
                }

                if(aplicacao.checkEndGame()) {
                    break;
                }
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
