public class King {
    private int row;
    private int col;
    private boolean isBlack;

    public King(int row, int col, boolean isBlack){
        row = row; col = col; isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        Piece endPiece = board.getPiece(endRow, endCol);
        int x = java.lang.Math.abs(this.row - endRow) + java.lang.Math.abs(this.col - endCol);
        if(board.verifyAdjacent(this.row, this.col, endRow, endCol) && x != 0){
            if (endPiece == null || endPiece.getIsBlack() != this.isBlack) {
                return true;
            }
            return false;
        }
        return false;
    }
    }
