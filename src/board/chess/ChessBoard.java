package board.chess;

import board.Board;
import board.Position;
import enums.Color;
import exceptions.PieceException;
import factory.GenericPieceFactory;
import factory.PieceFactory;
import logic.chess.ChessMovement;
import pieces.Piece;
import pieces.chess.*;
import java.util.ArrayList;

/**
 * Classe que define como funciona o tabuleiro de xadrez
 */
public class ChessBoard extends Board {
  private ChessPiece[][] board;
  private Position checkPiecePosition;

  protected final String red = "\u001B[31m";

  public ChessBoard() {
    super(8, 8);
    this.board = new ChessPiece[this.rowLength][this.columnLength];
    for(int i = 0; i < this.rowLength; i++){
      for(int j = 0; j < this.columnLength; j++){
        this.board[i][j] = null;
      }
    }
    this.checkPiecePosition = null;
    initBoard();
  }

  /**
   * Está função vai inicializar todas as peças do tabuleiro, como temos certeza
   * da posição delas, não existe a necessidade
   * de chamar uma função para verificar se a posição é valida
   */
  private void initBoard() {
    PieceFactory factory = new GenericPieceFactory();

    // Criando os peões
    for (int i = 0; i < 8; i++) {
        board[1][i] = factory.createPiece("pawn", "white", ChessPiece.class);
        board[6][i] = factory.createPiece("pawn", "black", ChessPiece.class);
    }

    // Criando as peças brancas
    board[0][0] = factory.createPiece("rook", "white", ChessPiece.class);
    board[0][1] = factory.createPiece("knight", "white", ChessPiece.class);
    board[0][2] = factory.createPiece("bishop", "white", ChessPiece.class);
    board[0][3] = factory.createPiece("queen", "white", ChessPiece.class);
    board[0][4] = factory.createPiece("king", "white", ChessPiece.class);
    board[0][5] = factory.createPiece("bishop", "white", ChessPiece.class);
    board[0][6] = factory.createPiece("knight", "white", ChessPiece.class);
    board[0][7] = factory.createPiece("rook", "white", ChessPiece.class);

    // Criando as peças pretas
    board[7][0] = factory.createPiece("rook", "black", ChessPiece.class);
    board[7][1] = factory.createPiece("knight", "black", ChessPiece.class);
    board[7][2] = factory.createPiece("bishop", "black", ChessPiece.class);
    board[7][3] = factory.createPiece("queen", "black", ChessPiece.class);
    board[7][4] = factory.createPiece("king", "black", ChessPiece.class);
    board[7][5] = factory.createPiece("bishop", "black", ChessPiece.class);
    board[7][6] = factory.createPiece("knight", "black", ChessPiece.class);
    board[7][7] = factory.createPiece("rook", "black", ChessPiece.class);
}


  @Override
  public Piece[][] getBoard() {
    return board;
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
    ChessPiece pieceTaken = ChessMovement.lastPieceTaken;

    this.lastMovement.clear();
    this.lastMovement.add(initialPosition);
    this.lastMovement.add(targetPosition);

    System.out.println(board[targetPosition.getRow()][targetPosition.getColumn()].analyseCheck(board, targetPosition));
    if(board[targetPosition.getRow()][targetPosition.getColumn()].analyseCheck(board, targetPosition)) {
      this.checkPiecePosition = targetPosition;
    }
    return pieceTaken;
  }

  public Position getCheckPiecePosition(){
    return this.checkPiecePosition;
  }

  public void setCheckPiecePosition(Position checkPiecePosition){
    this.checkPiecePosition = checkPiecePosition;
  }

  public String showCheck(){
    ArrayList<String> lastMovement = convertLastMovement();
    String checkPosition = this.returnKingPosition(this.board[checkPiecePosition.getRow()][checkPiecePosition.getColumn()].getAvailableMoves(board, checkPiecePosition));
    String s = "    A    B    C    D    E    F    G    H\n";
    for (int i = 0; i < this.rowLength; i++) {
      s += (i + 1) + " ";
      for (int j = 0; j < this.columnLength; j++) {
        // Verifica os movimentos possíveis
        if (checkPosition.equals(i + "" + j)) {
          if (getBoard()[i][j] != null) {
            s += red + "\u27EA" + reset + getBoard()[i][j] + red + " \u27EB " + reset;
          } else {
            s += red + "\u27EA " + " \u27EB " + reset;
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

  public String returnKingPosition(ArrayList<String> availablePositions) {
    for (String availablePosition : availablePositions) {
      int row = availablePosition.charAt(0) - '0';
      int column = availablePosition.charAt(1) - '0';
      if (board[row][column] != null
          && board[row][column] instanceof King) {
        return availablePosition;
      }
    }
    return null;
  }
  
  @Override
  public String showPossibleMoves(Position position) {
    return "";
  }
}
