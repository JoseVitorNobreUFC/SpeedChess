package exceptions;

/**
 * Exceção para quando um movimento for fora do tabuleiro
 */
public class BoardException extends RuntimeException{

  public BoardException(String message) {
    super(message);
  }
}
