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

    private int[][] mines;
    private int rows;
    private int columns;
    private int guesses = 0;

    private int flags;
    public qMinefield(int rows, int columns, int flags) {
        this.rows = rows;
        this.columns = columns;
        this.field = new Cell[rows][columns];
        this.mines = new int[flags][2];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++) {
                field[i][j] = new Cell( false, "-");
            }
        }
        this.intField = new int[rows][columns];
        this.flags = flags;

    }
    /**
     * evaluateField
     *
     * @function When a mine is found in the field, calculate the surrounding 9x9 tiles values. If a mine is found, increase the count for the square.
     */
    public void evaluateField() {
        Stack1Gen stack = new Stack1Gen<>();
        for(int i = 0; i < this.flags; i++){
            evaluateHelper(mines[i], stack, mines[i][0], mines[i][1]);
        }
        while(!stack.isEmpty()){
            int[] arr = (int[])stack.pop();
            intField[arr[0]][arr[1]] +=1;
        }

        for(int j = 0; j < rows; j++ ){
            for(int i = 0; i < columns; i++) {
                if(!field[i][j].getStatus().equals("M"))
                    field[i][j].setStatus(Integer.toString(intField[i][j]));
            }
        }
    }

    public void evaluateHelper(int [] arr, Stack1Gen stack, int x, int y){
        if(arr[0] >= 0 && arr[0] < rows && arr[1] >= 0 && arr[1] < columns ){
            stack.push(arr);
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
        for(int i = 0; i < mines; i++){
            Random rand = new Random();
            int row = rand.nextInt(this.rows); int column = rand.nextInt(this.columns);
            while(field[row][column].getStatus().equals("M") && !field[row][column].getRevealed()) {
                row = rand.nextInt(this.rows);
                column = rand.nextInt(this.columns);
            }
            this.mines[i][0] = row; this.mines[i][1] = column;
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
        if (flag) {
            guesses += 1;
            if(guesses <= flags) {
                if (field[x][y].getStatus().equals("M")) {
                    field[x][y].setStatus("F");
                    field[x][y].setRevealed(true);
                    return true;
                }
                field[x][y].setStatus("F");
                return false;
            }
        }
        if(field[x][y].getStatus().equals("0")) {
            revealZeroes(x,y);
            return false;
        }
        if(field[x][y].getStatus().equals("M")) {
            gameOver();
            return true;
        }
        field[x][y].setRevealed(true);
        return false;
    }
    /**
     * gameOver
     *
     * @return boolean Return false if game is not over and squares have yet to be revealed, otheriwse return true.
     */
    public boolean gameOver() {
        if(flags == guesses){
            return true;
        }
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                if(field[i][j].getStatus().equals("M") && !field[i][j].getRevealed()){
                    return false;
                }
                if(field[i][j].getStatus().equals("M") && field[i][j].getRevealed()) {
                    revealMines(i,j);
                    return true;
                }
            }
        }
        return true;
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
        int[] arr = {x, y};
        Stack1Gen stack = new Stack1Gen();
        stack.push(arr);
        while (!stack.isEmpty()) {
            arr = (int[]) stack.pop();
            x = arr[0];
            y = arr[1];
            field[x][y].setRevealed(true);
            for (int i = -1; i <= 1; i++) {
                if (x + i >= 0 && x + i < rows && i != 0) {
                    if (field[x + i][y].getStatus().equals("0")) {
                        arr[0] = x + i;
                        stack.push(arr);
                    }
                }
                if (y + i >= 0 && y + i < columns && i != 0) {
                    if (field[x][y + i].getStatus().equals("0")) {
                        arr[1] = y + i;
                        stack.push(arr);
                    }
                }
            }
        }
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
        for(int i = 0; i< rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(field[i][j].getStatus());
            }
        }
    }

    /**
     * toString
     *
     * @return String The string that is returned only has the squares that has been revealed to the user or that the user has guessed.
     */
    public String toString() {
        String s = " ";
        for(int i = 0; i <= rows; i++){
            if(i == 0){ s+= " ";}
            else{
            s += Integer.toString(i - 1) + " ";}
            for(int j = 0; j < columns; j++){
                if(i == 0) { s += Integer.toString(j) + " "; }
                else if(field[i-1][j].equals(null)){
                    s += "-";
                }
                else if(field[i-1][j].getRevealed()){ s += field[i-1][j].getStatus() + " ";}
                else {s += "- ";}
            }
            s += "\n";

        }
        return s;
    }
    /*
    public Stack1Gen validNeighbors(int[] arr, Stack1Gen stack){
        for(int i = -1; i <= 1; i++){
            int x = arr[0] +i;
            if(x < rows && x >= 0){
                for (int j = -1; j <= 1; j++) {
                    int y = arr[1] + i;
                    if(y < columns && y >= 0){
                        arr[0] = x; arr[1] = y;
                        stack.push(arr);
                    }
                }
            }
        }
        return stack;
    }

    public Stack1Gen validNeighbors0(int[] arr, Stack1Gen stack) {
        for(int i = -1; i <= 1; i++) {
            int x = arr[0] + i;
            if (x < rows && x >= 0) {
                for (int j = -1; j <= 1; j++) {
                    int y = arr[1] + i;
                    if (Math.abs(i) + Math.abs(j) > 1) {
                        continue;
                    }
                    if (y < columns && y >= 0) {
                        if (field[x][y].getStatus().equals("0") && !field[x][y].getRevealed()) {
                            arr[0] = x;
                            arr[1] = y;
                            stack.push(arr);
                            validNeighbors0(arr, stack);
                        }
                    }
                }
            }
        }
        return stack;
    }
    */

}

