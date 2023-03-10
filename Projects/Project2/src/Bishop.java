public class Bishop {
    private int row;
    private int col;
    private boolean isBlack;
    public Bishop(int row, int col, boolean isBlack){
        this.row = row; this.col = col; this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board,int endRow, int endCol) {
        Piece endPiece = board.getPiece(endRow, endCol);
        if (board.verifyDiagonal(this.row, this.col, endRow, endCol) ) {
            if (endPiece == null)
                return true;
            else if (endPiece.getIsBlack() != this.isBlack)
                return true;
            return false;
        }
        return false;
    }
}


