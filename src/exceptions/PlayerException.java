package exceptions;

/**
 * Exceção para quando um jogador estiver fazendo um movimento sem ser o turno dele
 */
public class PlayerException extends RuntimeException {
  public PlayerException(String message) {
    super(message);
  }
}
