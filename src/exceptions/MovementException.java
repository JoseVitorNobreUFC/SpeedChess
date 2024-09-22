package exceptions;

/**
 * Exceção para quando o movimento que a peça estiver fazendo não corresponder com os movimentos disponiveis
 */
public class MovementException extends RuntimeException {
  public MovementException(String message) {
    super(message);
  }  
}
