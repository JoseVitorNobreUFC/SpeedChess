package exceptions;

import java.util.InputMismatchException;

/**
 * Exceção para quando uma peça está fazendo um movimento inválido
 */
public class PieceException extends InputMismatchException {
  public PieceException(String message) {
    super(message);
  }
}
