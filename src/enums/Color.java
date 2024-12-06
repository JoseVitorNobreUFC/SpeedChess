package enums;

/** 
 * Enumerador que vai definir a cor das peças
 */
public enum Color {
  WHITE, BLACK;

  public static Color fromString(String color) {
      if (color == null || color.isEmpty()) {
          throw new IllegalArgumentException("Color cannot be null or empty");
      }
      switch (color.toLowerCase()) {
          case "white":
              return WHITE;
          case "black":
              return BLACK;
          default:
              throw new IllegalArgumentException("Invalid color: " + color);
      }
  }
}
