public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8];
    }

    // Accessor Methods
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }

    public void setPiece(int row, int col, Piece piece) {
        if (piece == null){
            board[row][col] = null;
        }
        else {
            board[row][col] = new Piece(piece.getCharacter(), row, col, piece.getIsBlack());
        }
    }

    // Game functionality methods
    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
            this.setPiece(endRow, endCol, board[startRow][startCol]);
            this.setPiece(startRow, startCol, null);
    }


    public boolean isGameOver() {
        int k = 0;
        for(int i=0; i <=7;i++){
            for(int j=0; j <=7;j++) {
                if(board[i][j]==null)
                    k+=0;
                else if (board[i][j].getCharacter() == '\u2654' || board[i][j].getCharacter() == '\u265a')
                    k+=1;
            }
        }
        return k == 1;
    }


    public String toString() {
        String s = "";
        for(int i =0; i<=7;i++){
            s+="|";
            for(int j=0; j<=7;j++) {
                if(board[i][j]==null){
                    s +=  " " + "|";
                }
                else {
                    s += board[i][j].toString() + "|";
                }
            }
            s +="\n";
        }
        return s;
    }

    public void clear() {
        for(int i =0; i<=7;i++){
            for(int j=0; j<=7;j++) {
              board[i][j] = null;
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
        if (startRow <= 7 && endRow <= 7 && startCol <= 7 && endCol <= 7  && startRow >= 0 && endRow >= 0 && startCol >= 0 && endCol >= 0) {
            Piece startPiece = board[startRow][startCol];
            Piece endPiece = board[endRow][endCol];
            if(startPiece != null){
                if (endPiece == null || startPiece.getIsBlack() != endPiece.getIsBlack())
                    return startPiece.getIsBlack() == isBlack;
            }
        }
        return false;
    }


    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if(java.lang.Math.abs(startRow-endRow)==1||java.lang.Math.abs(startRow-endRow)==0) {
            return Math.abs(startCol - endCol) == 1 || Math.abs(startCol - endCol) == 0;
        }
        return false;
    }


    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if(startRow - endRow ==0 && startCol - endCol <=0) {
            for (int i = 0; i == java.lang.Math.abs(startCol - endCol); i++) {
                if (board[startRow][startCol+i] != null)
                    return false;
            }
            return true;
        }
        else if(startRow - endRow ==0 && startCol - endCol >=0){
            for (int i = 0; i == java.lang.Math.abs(startCol - endCol); i++) {
                if (board[startRow][startCol-i] != null)
                    return false;
            }
            return true;
        }
        return false;
    }


    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol - endCol == 0 && startRow - endRow <= 0){
            for( int i = 0; i==java.lang.Math.abs(startRow-endRow); i++){
                if (board[startRow+i][startCol]!=null)
                    return false;
            }
            return true;
        }
        else if(startCol- endCol == 0 && startRow - endRow >=0){
            for (int i = 0; i==java.lang.Math.abs(startRow-endRow); i++) {
                if (board[startRow-i][startCol] != null)
                    return false;
            }
            return true;
        }
        return false;
    }

    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
        if (java.lang.Math.abs(startRow - endRow) == java.lang.Math.abs(startCol - endCol)){
            if(startRow-endRow <= 0 && startCol - endCol <= 0){
                for( int i = 0; i==java.lang.Math.abs(startRow-endRow); i++) {
                    if (board[startRow + i][startCol + i] != null)
                        return false;
                }
                return true;
            }
            else if(startRow-endRow >= 0 && startCol - endCol <= 0){
                for( int i = 0; i==java.lang.Math.abs(startRow-endRow); i++) {
                    if (board[startRow - i][startCol + i] != null)
                        return false;
                }
                return true;
            }
            else if(startRow-endRow >= 0 && startCol - endCol >= 0){
                for( int i = 0; i==java.lang.Math.abs(startRow-endRow); i++) {
                    if (board[startRow - i][startCol - i] != null)
                        return false;
                }
                return true;
            }
            else if(startRow-endRow <= 0 && startCol - endCol >= 0){
                for( int i = 0; i==java.lang.Math.abs(startRow-endRow); i++) {
                    if (board[startRow + i][startCol - i] != null)
                        return false;
                }
                return true;
            }
        }
        return false;
    }
}
