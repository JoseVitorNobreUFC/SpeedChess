package board;

import java.util.ArrayList;

import enums.Color;
import exceptions.*;
import pieces.chess.*;

/**
 * Classe que define como funciona o tabuleiro do xadrez
 */
public class Board {
  private ChessPiece[][] board; // Primeiro [] representa a linha e o Segundo [] representa a coluna

  public Board() {
    board = new ChessPiece[8][8];
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        board[i][j] = null;
      }
    }

    initBoard();
  }

  /**
   * Está função vai inicializar todas as peças do tabuleiro, como temos certeza
   * da posição delas, não existe a necessidade
   * de chamar uma função para verificar se a posição é valida
   */
  private void initBoard() {
    // Criando os peões
    for (int i = 0; i < 8; i++) {
      board[1][i] = new Pawn(Color.WHITE);
      board[6][i] = new Pawn(Color.BLACK);
    }

    // Criando as peças brancas
    board[0][0] = new Rook(Color.WHITE);
    board[0][1] = new Knight(Color.WHITE);
    board[0][2] = new Bishop(Color.WHITE);
    board[0][3] = new Queen(Color.WHITE);
    board[0][4] = new King(Color.WHITE);
    board[0][5] = new Bishop(Color.WHITE);
    board[0][6] = new Knight(Color.WHITE);
    board[0][7] = new Rook(Color.WHITE);

    // Criando as peças pretas
    board[7][0] = new Rook(Color.BLACK);
    board[7][1] = new Knight(Color.BLACK);
    board[7][2] = new Bishop(Color.BLACK);
    board[7][3] = new Queen(Color.BLACK);
    board[7][4] = new King(Color.BLACK);
    board[7][5] = new Bishop(Color.BLACK);
    board[7][6] = new Knight(Color.BLACK);
    board[7][7] = new Rook(Color.BLACK);

  }

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
  private void analyseMovement(Position initialPosition, Position targetPosition) {
    if (!Position.isValidPosition(targetPosition) || !Position.isValidPosition(initialPosition)) {
      throw new BoardException("Posição fora do tabuleiro");
    }

    if (isPositionNull(initialPosition)) {
      throw new PositionException("Esta posição não contem peça");
    }
    
    ArrayList<String> possibleMoves = board[initialPosition.getRow()][initialPosition.getColumn()].getAvailableMoves(board, initialPosition);
    int row = targetPosition.getRow();
    int column = targetPosition.getColumn();
    if(!possibleMoves.contains(row + "" + column)) {
      throw new PieceException("Este movimento não é permitido");
    }
  }

  /**
   * Essa função tem como objetivo analisar a posição de uma peça, lançando
   * uma exceção caso o movimento seja inválido, em comparação com a função anterior
   * essa é mais usada quando for analisar os movimentos de uma peça veja a função
   * @see Board#showPossibleMoves Onde essa função é chamada
   * 
   * @param position Posição inicial da peça
   * @throws BoardException    Caso uma das posições seja fora do tabuleiro
   * @throws PositionException Caso a posição inicial for nula
   */
  private void analyseMovement(Position position) {
    if (!Position.isValidPosition(position)) {
      throw new BoardException("Posição fora do tabuleiro");
    }

    if (isPositionNull(position)) {
      throw new PositionException("Esta posição não contem peça");
    }
  }

  /**
   * Realiza a transferencia de uma peça no array para outra posição
   * 
   * @param initialPosition Posição inicial da peça
   * @param targetPosition  Posição final da peça
   */
  public void movePiece(Position initialPosition, Position targetPosition) { // Lembrar de mover internamente a posição da peça
    this.analyseMovement(initialPosition, targetPosition);

    if(board[initialPosition.getRow()][initialPosition.getColumn()].isFirstMove()) {
      board[initialPosition.getRow()][initialPosition.getColumn()].takeFirstMove();
    }
    board[targetPosition.getRow()][targetPosition.getColumn()] = board[initialPosition.getRow()][initialPosition
        .getColumn()];
    board[initialPosition.getRow()][initialPosition.getColumn()] = null;
  }

  private boolean isPositionNull(Position position) {
    return board[position.getRow()][position.getColumn()] == null;
  }

  /**
   * Esse toString irá exibir a representação do tabuleiro no console
   * 
   * @return Representação do tabuleiro
   */
  @Override
  public String toString() {
    String s = "    A    B    C    D    E    F    G    H\n";
    for (int i = 0; i < 8; i++) {
      s += (i + 1) + " ";
      for (int j = 0; j < 8; j++) {
        if (board[i][j] != null) {
          s += "[" + board[i][j] + " ] ";
        } else {
          s += "[  ] ";
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
   * @return String contendo representação do tabuleiro com os movimentos possíveis
   */
  public String showPossibleMoves(Position position) {
    this.analyseMovement(position);
    ArrayList<String> possibleMoves = board[position.getRow()][position.getColumn()].getAvailableMoves(board, position);
    String s = "    A    B    C    D    E    F    G    H\n";
    for (int i = 0; i < 8; i++) {
      s += (i + 1) + " ";
      for (int j = 0; j < 8; j++) {
        if(possibleMoves.contains(i + "" + j)) {
          if(board[i][j] != null) {
            s += "⟪" + board[i][j] + " ⟫ ";
          } else {
            s += "⟪  ⟫ ";
          }
        } else if (board[i][j] != null) {
            s += "[" + board[i][j] + " ] ";
        } else {
          s += "[  ] ";
        }
      }
      s += "\n";
    }
    return s;
  }

  public ChessPiece getPiece(Position position) {
    this.analyseMovement(position);
    return board[position.getRow()][position.getColumn()];
  }
}