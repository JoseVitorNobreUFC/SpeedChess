package exceptions;

/**
 * Exceção para quando uma peça está fazendo um movimento inválido
 */
public class PieceException extends RuntimeException{
  public PieceException(String message) {
    super(message);
  }
}
