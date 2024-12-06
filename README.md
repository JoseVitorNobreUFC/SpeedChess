# SpeedChess
### Padrão de Projeto:
Factory Method

### Problema:
Ao desenvolver um jogo de Tabuleiro, seja Xadrez, Damas ou até mesmo multiplos jogos de tabuleiro há um grande problema no controle das instancias das peças, dependendo do jogo existem multiplos tipos de peça e controlar elas em apenas uma classe pode aumentar a complexidade

### Consequencia:
A consequencia pode ser um pouco abstrata, mas em geral pode ser tempo de desenvolvimento ao colocar uma classe Tabuleiro para fazer a gerencia das instancias das peças, você pode se complicar em deixar o código muito extenso a medida que for adicionando mais peças, enquanto ainda falta a lógica de controle de movimentação

### Solução Trazida:
Anteriomente o código do ChessBoard.java era assim: <br/>
```
private void initBoard() {
  for (int i = 0; i < 8; i++) {
    board[1][i] = new Pawn(Color.WHITE);
    board[6][i] = new Pawn(Color.BLACK);
  }

  board[0][0] = new Rook(Color.WHITE);
  board[0][1] = new Knight(Color.WHITE);
  board[0][2] = new Bishop(Color.WHITE);
  board[0][3] = new Queen(Color.WHITE);
  board[0][4] = new King(Color.WHITE);
  board[0][5] = new Bishop(Color.WHITE);
  board[0][6] = new Knight(Color.WHITE);
  board[0][7] = new Rook(Color.WHITE);

  board[7][0] = new Rook(Color.BLACK);
  board[7][1] = new Knight(Color.BLACK);
  board[7][2] = new Bishop(Color.BLACK);
  board[7][3] = new Queen(Color.BLACK);
  board[7][4] = new King(Color.BLACK);
  board[7][5] = new Bishop(Color.BLACK);
  board[7][6] = new Knight(Color.BLACK);
  board[7][7] = new Rook(Color.BLACK);
}
```

Usando FactoryMethod

```
private void initBoard() {
    PieceFactory factory = new GenericPieceFactory();

    for (int i = 0; i < 8; i++) {
        board[1][i] = factory.createPiece("pawn", "white", ChessPiece.class);
        board[6][i] = factory.createPiece("pawn", "black", ChessPiece.class);
    }

    board[0][0] = factory.createPiece("rook", "white", ChessPiece.class);
    board[0][1] = factory.createPiece("knight", "white", ChessPiece.class);
    board[0][2] = factory.createPiece("bishop", "white", ChessPiece.class);
    board[0][3] = factory.createPiece("queen", "white", ChessPiece.class);
    board[0][4] = factory.createPiece("king", "white", ChessPiece.class);
    board[0][5] = factory.createPiece("bishop", "white", ChessPiece.class);
    board[0][6] = factory.createPiece("knight", "white", ChessPiece.class);
    board[0][7] = factory.createPiece("rook", "white", ChessPiece.class);

    board[7][0] = factory.createPiece("rook", "black", ChessPiece.class);
    board[7][1] = factory.createPiece("knight", "black", ChessPiece.class);
    board[7][2] = factory.createPiece("bishop", "black", ChessPiece.class);
    board[7][3] = factory.createPiece("queen", "black", ChessPiece.class);
    board[7][4] = factory.createPiece("king", "black", ChessPiece.class);
    board[7][5] = factory.createPiece("bishop", "black", ChessPiece.class);
    board[7][6] = factory.createPiece("knight", "black", ChessPiece.class);
    board[7][7] = factory.createPiece("rook", "black", ChessPiece.class);
}
```

Apesar de isso não parecer uma mudança que afeta muito, isso só acontece porque no momento ainda temos o tabuleiro de Xadrez, se em algum momento fizessemos um tabuleiro de Damas, poderiamos ainda usar o factory para criar uma peça de dama, tendo que apenas fazer poucas alterações ao código e resultando em mais eficiencia e menos tempo de desenvolvimento.
