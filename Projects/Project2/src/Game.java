
import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        Board board = new Board(); Piece piece = new Piece();
        String s; int startRow; int startCol; int endRow; int endCol;

        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
        System.out.print(board.toString());

        while (!board.isGameOver()) {
            System.out.print("Whites Turn!\n");
            System.out.print("What is your move? [start row] [start column] [end row] [end column]: ");
            Scanner scan1 = new Scanner(System.in);
            startRow = scan1.nextInt(); startCol = scan1.nextInt(); endRow = scan1.nextInt(); endCol = scan1.nextInt();
            piece = board.getPiece(startRow, startCol);

            while (!piece.isMoveLegal(board, endRow, endCol)) {
                System.out.print(board.verifySourceAndDestination(startRow, startCol, endRow, endCol, false));
                System.out.print("Please enter a legal move!\n");
                System.out.print("What is your move? [start row] [start column] [end row] [end column]: ");
                scan1 = new Scanner(System.in);
                startRow = scan1.nextInt(); startCol = scan1.nextInt(); endRow = scan1.nextInt(); endCol = scan1.nextInt();
                piece = board.getPiece(startRow, startCol);
            }

            if (board.getPiece(endRow, endCol) != null) {
                board.setPiece(endRow, endCol, null);
            }

            board.movePiece(startRow, startCol, endRow, endCol);
            System.out.print(board.toString());

            System.out.print("Blacks Turn!\n");
            System.out.print("What is your move? [start row] [start column] [end row] [end column]");
            Scanner scan2 = new Scanner(System.in);
            startRow = scan2.nextInt(); startCol = scan2.nextInt(); endRow = scan2.nextInt(); endCol = scan2.nextInt();
            piece = board.getPiece(startRow, startCol);

            while (!piece.isMoveLegal(board, endRow, endCol) ) {
                System.out.print("Blacks Turn!\n");
                System.out.print("What is your move? [start row] [start column] [end row] [end column]");
                scan2 = new Scanner(System.in);
                startRow = scan2.nextInt(); startCol = scan2.nextInt(); endRow = scan2.nextInt(); endCol = scan2.nextInt();
                piece = board.getPiece(startRow, startCol);
            }

            if (board.getPiece(endRow, endCol) != null){
                board.setPiece(endRow, endCol, null);
                board.movePiece(startRow, startCol, endRow, endCol);
                System.out.print(board.toString());
            }

            board.movePiece(startRow, startCol, endRow, endCol);
            System.out.print(board.toString());
        }
    }
}

