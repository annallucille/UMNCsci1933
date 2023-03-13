public class Queen {

    int row; int col; boolean isBlack;

    public Queen(int row, int col, boolean isBlack){
        this.row = row; this.col = col; this.isBlack = isBlack;
    }
    
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        Piece endPiece = board.getPiece(endRow, endCol);
        if (board.verifyHorizontal(this.row, this.col, endRow, endCol) || board.verifyVertical(this.row, this.col, endRow, endCol)) {
            return endPiece == null || endPiece.getIsBlack() != this.isBlack;
        } else if (board.verifyDiagonal(this.row, this.col, endRow, endCol)) {
            return board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack);
        }
        return false;
    }
    
}
