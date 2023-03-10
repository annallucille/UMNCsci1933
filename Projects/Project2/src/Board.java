public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
        board[0][0] = new Piece('\u2656', 0, 0, true);
        board[0][1] = new Piece('\u2658', 0, 1, true);
        board[0][2] = new Piece('\u2657', 0, 2, true);
        board[0][3] = new Piece('\u2654', 0, 3, true);
        board[0][4] = new Piece('\u2655', 0, 4, true);
        board[0][7] = new Piece('\u2656', 0, 7, true);
        board[0][6] = new Piece('\u2658', 0, 6, true);
        board[0][5] = new Piece('\u2657', 0, 5, true);
        for(int i = 0; i<=7; i++){
            board[1][i] = new Piece('\u2659', 1, i, true);
        }

        board[7][0] = new Piece('\u265c', 7, 0, false);
        board[7][1] = new Piece('\u265e', 7, 1, false);
        board[7][2] = new Piece('\u265d', 7, 2, false);
        board[7][3] = new Piece('\u265a', 7, 3, false);
        board[7][4] = new Piece('\u265b', 7, 4, false);
        board[7][7] = new Piece('\u265c', 7, 7, false);
        board[7][6] = new Piece('\u265e', 7, 6, false);
        board[7][5] = new Piece('\u265d', 7, 5, false);
        for(int i = 0; i<=7; i++){
            board[6][i] = new Piece('\u265c', 6, i, false);
        }
    }

    // Accessor Methods
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        board[row][col] = new Piece(piece.getCharacter(), row, col, piece.getIsBlack());
    }

    // Game functionality methods
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if(board[startRow][startCol].isMoveLegal(this, endRow,endCol)){
            board[startRow][startCol].setPosition(endRow, endCol);
            return true;
        }
        return false;
    }


    public boolean isGameOver() {
        int k = 0;
        for(int i=0; i <=7;i++){
            for(int j=0; j <=7;j++) {
                if (board[i][j].getCharacter() == '\u2654' || board[i][j].getCharacter() == '\u265a')
                    k+=1;
            }
        }
        return k == 1;
    }


    public String toString() {
        String s = "";
        for(int i =0; i<=7;i++){
            for(int j=0; j<=7;j++) {
                s += "|" + board[i][j].toString() + "|";
            }
            s += "\n";
        }
        return s;
    }

    public void clear() {
        for(int i =0; i<=7;i++){
            for(int j=0; j<=7;j++) {
              board[i][j] = new Piece(' ', i, j, false);
            }
        }

    }

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        Piece startPiece = board[startRow][startCol];
        Piece endPiece = board[endRow][endCol];
        if(startPiece.isMoveLegal(this, endRow, endCol)) {
            if (startRow <= 7 && endRow <= 7 && startCol <= 7 && endCol <= 7) {
                if (startPiece.getIsBlack() == isBlack) {
                    if (startPiece.getIsBlack() != endPiece.getIsBlack())
                        return true;
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }


    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if(java.lang.Math.abs(startRow-endRow)==1||java.lang.Math.abs(startRow-endRow)==0) {
            if (java.lang.Math.abs(startCol - endCol) == 1||java.lang.Math.abs(startCol - endCol) == 0)
                return true;
            return false;
        }
        return false;
    }


    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if ((startCol - endCol) == 0){
            for( int i = 1; i<=java.lang.Math.abs(startRow-endRow); i++){
                if (board[startRow+i][startCol]!=null)
                    return false;
            }
            return true;
        }
        return false;
    }


    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if ((startRow - endRow) == 0){
            for( int i = 1; i<=java.lang.Math.abs(startCol-endCol); i++){
                if (board[startRow][startCol+i]!=null)
                    return false;
            }
            return true;
        }
        return false;
    }

    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if ((startRow - endRow) == (startCol - endCol)){
            for( int i = 1; i<=java.lang.Math.abs(startRow-endRow); i++){
                if (board[startRow+i][startCol+i]!=null)
                    return false;
            }
            return true;
        }
        return false;
    }
}
