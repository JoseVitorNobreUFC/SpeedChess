package board;

import java.util.ArrayList;

import enums.Color;
import exceptions.*;
import pieces.Piece;
import pieces.chess.*;

public class Board {
  private Piece[][] board; // Primeiro [] representa a linha e o Segundo [] representa a coluna

  public Board() {
    board = new Piece[8][8];
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
      board[1][i] = new Pawn(new Position(2, i + 1), Color.WHITE);
      board[6][i] = new Pawn(new Position(7, i + 1), Color.BLACK);
    }

    // Criando as peças brancas
    board[0][0] = new Rook(new Position(1, 1), Color.WHITE);
    board[0][1] = new Knight(new Position(1, 2), Color.WHITE);
    board[0][2] = new Bishop(new Position(1, 3), Color.WHITE);
    board[0][3] = new Queen(new Position(1, 4), Color.WHITE);
    board[0][4] = new King(new Position(1, 5), Color.WHITE);
    board[0][5] = new Bishop(new Position(1, 6), Color.WHITE);
    board[0][6] = new Knight(new Position(1, 7), Color.WHITE);
    board[0][7] = new Rook(new Position(1, 8), Color.WHITE);

    // Criando as peças pretas
    board[7][0] = new Rook(new Position(8, 1), Color.BLACK);
    board[7][1] = new Knight(new Position(8, 2), Color.BLACK);
    board[7][2] = new Bishop(new Position(8, 3), Color.BLACK);
    board[7][3] = new Queen(new Position(8, 4), Color.BLACK);
    board[7][4] = new King(new Position(8, 5), Color.BLACK);
    board[7][5] = new Bishop(new Position(8, 6), Color.BLACK);
    board[7][6] = new Knight(new Position(8, 7), Color.BLACK);
    board[7][7] = new Rook(new Position(8, 8), Color.BLACK);

    // Instancia de teste
    board[4][2] = new Bishop(new Position(5, 3), Color.BLACK);
    board[4][6] = new Rook(new Position(5, 7), Color.WHITE);

    // Nessa posição ele pode se mover para D3, F3, C4, C6, G4, G6, D7 e F7
    board[5][4] = new Knight(new Position(6, 5), Color.BLACK);
  }

  /**
   * Essa função tem como objetivo analisar a movimentação de uma peça, lançando
   * uma exceção caso o movimento seja inválido
   * 
   * @param initialPosition Posição inicial da peça
   * @param targetPosition  Posição final da peça
   * @throws BoardException    Caso uma das posições seja fora do tabuleiro
   * @throws PositionException Caso a posição inicial for nula
   * @throws PieceException    Caso as peças contidas na posição inicial e final
   *                           forem aliadas
   * @throws PlayerException   Caso na hora de um jogador movimentar uma peça que
   *                           não seja a dele
   */
  private void analyseMovement(Position initialPosition, Position targetPosition) {
    if (!Position.isValidPosition(targetPosition) || !Position.isValidPosition(initialPosition)) {
      throw new BoardException("Posição fora do tabuleiro");
    }

    if (isPositionNull(initialPosition)) {
      throw new PositionException("Esta posição não contem peça");
    }

    if (this.board[targetPosition.getRow()][targetPosition.getColumn()] != null) {
      Piece targetPiece = this.board[targetPosition.getRow()][targetPosition.getColumn()];
      Piece initialPiece = this.board[initialPosition.getRow()][initialPosition.getColumn()];

      if (targetPiece.getColor().equals(initialPiece.getColor())) {
        throw new PieceException("Está posição contem uma peça aliada");
      }
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
    ArrayList<String> possibleMoves = board[position.getRow()][position.getColumn()].getAvailableMoves(board);
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
}