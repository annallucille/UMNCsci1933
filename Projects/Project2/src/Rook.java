public class Rook {
    private int row;
    private int col;
    private boolean isBlack;
    public Rook(int row, int col, boolean isBlack){
        row = row; col = col; isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board,int endRow, int endCol){
        Piece endPiece = board.getPiece(endRow,endCol);
        if (board.verifyHorizontal(this.row, this.col, endRow, endCol) || board.verifyVertical(this.row,this.col, endRow, endCol)){
            return board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack);
        }
        return false;
    }
}
