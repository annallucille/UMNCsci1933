public class Knight {
    private int row;
    private int col;
    private boolean isBlack;

    public Knight(int row, int col, boolean isBlack){
        row = row; col = col; isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board,int endRow, int endCol) {
        Piece endPiece = board.getPiece(endRow, endCol);
        if (java.lang.Math.abs(endRow - this.row) == 3 && java.lang.Math.abs(endCol - this.col) == 1) {
            if (endPiece == null || endPiece.getIsBlack() != this.isBlack) {
                return true;
            }
            return false;
        }
        else if(java.lang.Math.abs(endRow - this.row) == 1 && java.lang.Math.abs(endCol - this.col) == 3){
            if (endPiece == null || endPiece.getIsBlack() != this.isBlack) {
                return true;
            }
            return false;
        }
        return false;
    }

}
