package board;

import exceptions.PositionException;

/**
 * Classe que define como funciona as coordenadas do tabuleiro
 */
public class Position {
  private int row;
  private int column;

  public Position(String position) {
    if(position.length() != 2) {
      throw new PositionException("\nERRO!!!! Posição inválida\n");
    }
    this.row = Integer.parseInt(position.substring(1)) - 1; // Ajustando para posição em array
    this.column = convertColumnLetterToNumber(position.charAt(0));
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  /**
   * Verifica se uma posição é válida
   * 
   * @param position Posição
   * @return Se a posição é válida
   */
  public static boolean isValidPosition(Position position) {
    return position.getRow() >= 0 && position.getRow() < 8 && position.getColumn() >= 0 && position.getColumn() < 8;
  }

  /**
   * Calcula a distância entre duas coordenadas
   * 
   * @param position1 Primeira coordenada
   * @param position2 Segunda coordenada
   * @return Distância entre as coordenadas
   */
  public static int positionsDistance(Position position1, Position position2) {
    if(position1.getRow() != position2.getRow() && position1.getColumn() != position2.getColumn()) {
      return (Math.abs(position1.getRow() - position2.getRow()) + Math.abs(position1.getColumn() - position2
      .getColumn())) / 2;
    }
    return Math.abs(position1.getRow() - position2.getRow()) + Math.abs(position1.getColumn() - position2
        .getColumn());
  }

  /**
   * Converte uma coluna em letra para um número, independente se a letra for maiuscula ou minuscula
   * 
   * @param columnLetter Coluna em letra
   * @return Coluna em número
   */
  private int convertColumnLetterToNumber(char columnLetter) {
    char lowerCaseLetter = Character.toLowerCase(columnLetter);
    return lowerCaseLetter - 'a';
  }

  /**
   * Nesse toString() a gente mostra a posição da seguinte forma: (A1, B2, C3, D4,
   * E5, F6, G7, H8)
   */
  @Override
  public String toString() {
    char columnLetter = (char) (column + 'A');
    return "" + columnLetter + (row + 1);
  }
}
