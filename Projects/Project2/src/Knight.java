import java.lang.Math;
public class Knight {
    private int row;
    private int col;
    private boolean isBlack;

    public Knight(int row, int col, boolean isBlack){
        this.row = row; this.col = col; this.isBlack = isBlack;
    }

    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if(board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)){
            return (Math.abs(endRow - this.row) == 2 && Math.abs(endCol - this.col) == 1) || (Math.abs(endRow - this.row) == 1 && Math.abs(endCol - this.col) == 2);
        }
        return false;
    }

}
