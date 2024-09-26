package board.chess;

import board.Board;
import board.Position;
import enums.Color;
import exceptions.PieceException;
import logic.chess.ChessMovement;
import pieces.Piece;
import pieces.chess.*;

public class ChessBoard extends Board {
  public ChessBoard() {
    super(8, 8);
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
   * Verifica se o peão pode ser promovido, através da checagem de se ele está na
   * linha 0 ou 7
   * 
   * @param initialPosition Posição inicial da peça
   * @param targetPosition  Posição final da peça
   * @return Retorna um valor booleano
   */
  public boolean checkPromotion(Position initialPosition, Position targetPosition) {
    if (board[targetPosition.getRow()][targetPosition.getColumn()] instanceof Pawn
        && (targetPosition.getRow() == 0 || targetPosition.getRow() == 7)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Promova um peão
   * 
   * @param piece Peça que vai ser promovida
   * @param pawnPosition Posição da peça
   */
  public void pawnPromotion(String piece, Position pawnPosition) {
    Color color = board[pawnPosition.getRow()][pawnPosition.getColumn()].getColor();
    switch (piece) {
      case "0":
        board[pawnPosition.getRow()][pawnPosition.getColumn()] = new Queen(color);
        break;
      case "1":
        board[pawnPosition.getRow()][pawnPosition.getColumn()] = new Rook(color);
        break;
      case "2":
        board[pawnPosition.getRow()][pawnPosition.getColumn()] = new Knight(color);
        break;
      case "3":
        board[pawnPosition.getRow()][pawnPosition.getColumn()] = new Bishop(color);
        break;
      default:
        throw new PieceException("\nERRO!!! Valor invalido\n");
    }
  }

  @Override
  public Piece movePiece(Position initialPosition, Position targetPosition) { 
    this.analyseMovement(initialPosition, targetPosition);

    
    this.board = ChessMovement.moveChessPiece(this.board, initialPosition, targetPosition);
    Piece pieceTaken = ChessMovement.lastPieceTaken;

    this.lastMovement.clear();
    this.lastMovement.add(initialPosition);
    this.lastMovement.add(targetPosition);
    return pieceTaken;
  }
}
