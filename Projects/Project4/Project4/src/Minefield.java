import java.util.Queue;
import java.util.Random;

public class qMinefield {
    /**
    Global Section
    */
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BRIGHT = "\u001b[34;1m";
    public static final String ANSI_BLUE = "\u001b[34m";
    public static final String ANSI_RED_BRIGHT = "\u001b[31;1m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_GREY_BG = "\u001b[0m";
    /**
     * Constructor
     * @param rows       Number of rows.
     * @param columns    Number of columns.
     * @param flags      Number of flags, should be equal to mines
     */

    private int[][] intField;
    private Cell[][] field;

    private int rows;
    private int columns;
    private int guesses;
    public qMinefield(int rows, int columns, int flags) {
        this.rows = rows;
        this.columns = columns;
        this.field = new Cell[rows][columns];
        this.intField = new int[rows][columns];
        this.guesses = flags;
        //this.createMines(rand.nextInt(rows), rand.nextInt(columns), this.mines);

    }
    /**
     * evaluateField
     *
     * @function When a mine is found in the field, calculate the surrounding 9x9 tiles values. If a mine is found, increase the count for the square.
     */
    public void evaluateField() {
        for(int j = 0; j < rows; j++ ){
            for(int i = 0; i < columns; i++){
                if(field[j][i].getStatus().equals("M")){
                    intField[j-1][i] += 1;
                    intField[j-1][i-1] += 1;
                    intField[j-1][i+1] += 1;
                    intField[j][i-1] += 1;
                    intField[j][i+1] += 1;
                    intField[j+1][i-1] += 1;
                    intField[j+1][i] += 1;
                    intField[j+1][i+1] += 1;
                }
            }
        }
        for(int j = 0; j < rows; j++ ) {
            for (int i = 0; i < columns; i++) {
                if(!field[j][i].getStatus().equals("M")){
                    field[j][i].setStatus(Integer.toString(intField[j][i]));
                }
            }
        }
    }
    /**
     * createMines
     *
     * @param x       Start x, avoid placing on this square.
     * @param y        Start y, avoid placing on this square.
     * @param mines      Number of mines to place.
     */
    public void createMines(int x, int y, int mines) {
        for(int i = 0; i <= mines; i++){
            Random rand = new Random();
            int row = rand.nextInt(this.rows); int column = rand.nextInt(this.columns);
            while(field[row][column].getStatus().equals("M")) {
                row = rand.nextInt(this.rows);
                column = rand.nextInt(this.columns);
            }
            field[row][column].setStatus("M");
        }


    }

    /**
     * guess
     *
     * @param x       The x value the user entered.
     * @param y       The y value the user entered.
     * @param flag    A boolean value that allows the user to place a flag on the corresponding square.
     * @return boolean Return false if guess did not hit mine or if flag was placed, true if mine found.
     */
    public boolean guess(int x, int y, boolean flag) {
        if(field[x][y].getStatus().equals("M")){
            field[x][y].setStatus("F");
            field[x][y].setRevealed(true);
            return true;
        }
        field[x][y].setStatus("F");
        return false;
    }
    /**
     * gameOver
     *
     * @return boolean Return false if game is not over and squares have yet to be revealed, otheriwse return true.
     */
    public boolean gameOver() {
        for(int i = 0; i < this.rows; i++)

    }

    /**
     * revealField
     *
     * This method should follow the psuedocode given.
     * Why might a stack be useful here rather than a queue?
     *
     * @param x      The x value the user entered.
     * @param y      The y value the user entered.
     */
    public void revealZeroes(int x, int y) {


    }

    /**
     * revealMines
     *
     * This method should follow the psuedocode given.
     * Why might a queue be useful for this function?
     *
     * @param x     The x value the user entered.
     * @param y     The y value the user entered.
     */
    public void revealMines(int x, int y) {


    }

    /**
     * revealStart
     *
     * @param x       The x value the user entered.
     * @param y       The y value the user entered.
     */
    public void revealStart(int x, int y) {

    }
    /**
     * printMinefield
     *
     * @fuctnion This method should print the entire minefield, regardless if the user has guessed a square.
     * *This method should print out when debug mode has been selected. 
     */
    public void printMinefield() {

    }

    /**
     * toString
     *
     * @return String The string that is returned only has the squares that has been revealed to the user or that the user has guessed.
     */
    public String toString() {

    }
}
