package board;

import java.util.ArrayList;

import exceptions.*;
import pieces.Piece;

/**
 * Classe que define como funciona o tabuleiro
 */
public abstract class Board {
  // Primeiro [] representa a linha e o Segundo [] representa a coluna
  protected ArrayList<Position> lastMovement;
  protected int rowLength, columnLength;

  protected final String cyan = "\u001B[36m";
  protected final String reset = "\u001B[0m";
  protected final String yellow = "\u001B[33m";

  public Board(int rowLength, int columnLength) {
    this.rowLength = rowLength;
    this.columnLength = columnLength;
    this.lastMovement = new ArrayList<Position>();
  }

  public abstract Piece[][] getBoard();

  /**
   * Essa função tem como objetivo analisar a movimentação de uma peça, lançando
   * uma exceção caso o movimento seja inválido
   * 
   * @param initialPosition Posição inicial da peça
   * @param targetPosition  Posição final da peça
   * @throws BoardException    Caso uma das posições seja fora do tabuleiro
   * @throws PositionException Caso a posição inicial for nula
   * @throws PieceException    Caso o movimento da peça seja invalido
   */
  protected void analyseMovement(Position initialPosition, Position targetPosition) {
    if (!Position.isValidPosition(targetPosition) || !Position.isValidPosition(initialPosition)) {
      throw new BoardException("\nERRO!!!! Posição fora do tabuleiro\n");
    }

    if (isPositionNull(initialPosition)) {
      throw new PositionException("\nERRO!!!! Esta posição não contem peça\n");
    }

    ArrayList<String> possibleMoves = this.getBoard()[initialPosition.getRow()][initialPosition.getColumn()]
        .getAvailableMoves(getBoard(), initialPosition);
    int row = targetPosition.getRow();
    int column = targetPosition.getColumn();
    if(possibleMoves == null) {
      throw new PieceException("\nERRO!!! Essa peça não pode se mover\n");
    }
    if (!possibleMoves.contains(row + "" + column)) {
      throw new PieceException("\nERRO!!!! Este movimento não é permitido\n");
    }
  }

  /**
   * Essa função tem como objetivo analisar a posição de uma peça, lançando
   * uma exceção caso o movimento seja inválido, em comparação com a função
   * anterior
   * essa é mais usada quando for analisar os movimentos de uma peça veja a função
   * 
   * @see Board#showPossibleMoves Onde essa função é chamada
   * 
   * @param position Posição inicial da peça
   * @throws BoardException    Caso uma das posições seja fora do tabuleiro
   * @throws PositionException Caso a posição inicial for nula
   */
  protected void analyseMovement(Position position) {
    if (!Position.isValidPosition(position)) {
      throw new BoardException("\nERRO!!!! Posição fora do tabuleiro\n");
    }

    if (isPositionNull(position)) {
      throw new PositionException("\nERRO!!!! Esta posição não contem peça\n");
    }
  }

  /**
   * Realiza a transferencia de uma peça no array para outra posição
   * 
   * @param initialPosition Posição inicial da peça
   * @param targetPosition  Posição final da peça
   */
  public abstract Piece movePiece(Position initialPosition, Position targetPosition);

  protected boolean isPositionNull(Position position) {
    return this.getBoard()[position.getRow()][position.getColumn()] == null;
  }

  /**
   * Esse toString irá exibir a representação do tabuleiro no console
   * 
   * @return Representação do tabuleiro
   */
  @Override
  public String toString() {
    String s = "    A    B    C    D    E    F    G    H\n";
    ArrayList<String> lastMovement = convertLastMovement();
    for (int i = 0; i < this.rowLength; i++) {
      s += (i + 1) + " ";
      for (int j = 0; j < this.columnLength; j++) {
        if (getBoard()[i][j] != null) {
          if (lastMovement.contains(i + "" + j)) {
            s += yellow + "\u27EA" + reset + getBoard()[i][j] + yellow + " \u27EB " + reset;
          } else {
            s += "[" + getBoard()[i][j] + " ] ";
          }
        } else {
          if (lastMovement.contains(i + "" + j)) {
            s += yellow + "\u27EA " + " \u27EB " + reset;
          } else {
            s += "[  ] ";
          }
        }
      }
      s += "\n";
    }
    return s;
  }

  /**
   * Está função serve para exibir as possiveis movimentações de uma peça
   * 
   * @param position Posição da peça
   * @return String contendo representação do tabuleiro com os movimentos
   *         possíveis
   */
  public String showPossibleMoves(Position position) {
    this.analyseMovement(position);
    ArrayList<String> lastMovement = convertLastMovement();
    ArrayList<String> possibleMoves = getBoard()[position.getRow()][position.getColumn()].getAvailableMoves(getBoard(), position);
    if(possibleMoves == null) {
      throw new PieceException("\nERRO!!! Essa peça não pode se mover\n");
    }
    String s = "    A    B    C    D    E    F    G    H\n";
    for (int i = 0; i < this.rowLength; i++) {
      s += (i + 1) + " ";
      for (int j = 0; j < this.columnLength; j++) {
        // Verifica os movimentos possíveis
        if (possibleMoves.contains(i + "" + j)) {
          if (getBoard()[i][j] != null) {
            s += cyan + "\u27EA" + reset + getBoard()[i][j] + cyan + " \u27EB " + reset;
          } else {
            s += cyan + "\u27EA " + " \u27EB " + reset;
          }
        } else if (getBoard()[i][j] != null) {
          s += lastMovement.contains(i + "" + j) ? yellow + "\u27EA" + reset + getBoard()[i][j] + yellow + " \u27EB " + reset
              : "[" + getBoard()[i][j] + " ] ";
        } else {
          s += lastMovement.contains(i + "" + j) ? yellow + "\u27EA " + " \u27EB " + reset : "[  ] ";
        }
      }
      s += "\n";
    }
    return s;
  }

  /**
   * Está função serve para pegar a peça contida naquela posição
   * 
   * @param position Posição da peça
   * @return A peça contida naquela posição
   */
  public Piece getPiece(Position position) {
    this.analyseMovement(position);
    return getBoard()[position.getRow()][position.getColumn()];
  }


  /**
   * Retorn o array que contem o ultimo movimento feito
   *
   * @return Retorna um array de posicoes
   */
  public ArrayList<Position> getLastMovement() {
    return this.lastMovement;
  }

  /**
   * Converte o array de posicoes em um array de String
   *
   * @return Retorna um arrayList de String 
   */
  protected ArrayList<String> convertLastMovement() {
    ArrayList<String> lastMovements = new ArrayList<String>();
    if (this.lastMovement.size() == 0) {
      return lastMovements;
    }
    lastMovements.add(this.lastMovement.get(0).getRow() + "" + this.lastMovement.get(0).getColumn());
    lastMovements.add(this.lastMovement.get(1).getRow() + "" + this.lastMovement.get(1).getColumn());
    return lastMovements;
  }
}