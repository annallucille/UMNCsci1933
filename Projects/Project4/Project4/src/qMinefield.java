import java.util.Random;


public class qMinefield {
    /**
    Global Section
    */
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE_BRIGHT = "\u001b[34;1m";
    public static final String ANSI_BLUE = "\u001b[35m";
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

    private  int[][] intField;
    private Cell[][] field;

    private int[][] mines;
    private int rows;
    private int columns;
    private int guesses = 0;
    private int minesRevealed = 0;
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
     public Cell[][] getField(){
        return this.field;
     }
    /**
     * evaluateField
     *
     * @function When a mine is found in the field, calculate the surrounding 9x9 tiles values. If a mine is found, increase the count for the square.
     */
    public void evaluateField() {
        Stack1Gen stack = new Stack1Gen<>();
        int x; int y; int[] temp = new int [2];
        for(int i = 0; i < this.flags; i++){
            temp[0] = mines[i][0]; temp[1] = this.mines[i][1];
            evaluateHelper(temp, stack, i);
        }
        System.out.println("----");
        while(!stack.isEmpty()){
             y = (int) stack.pop();
             x = (int) stack.pop();
            intField[x][y] += 1;
        }


    }

    public void convert(){
        for(int i = 0; i < rows; i++ ){
            for(int j = 0; j < columns; j++) {
                if(!field[i][j].getStatus().equals("M")) {
                    field[i][j].setStatus(Integer.toString(intField[i][j]));
                }
            }
        }
    }

    public void evaluateHelper(int[] arr, Stack1Gen stack, int k){
        for(int i = -1; i <= 1; i++){
            arr[0] += i;
            for(int j = - 1; j <= 1; j++){
                arr[1] += j;
                if(verifyIndices(arr[0], arr[1])){
                    stack.push(arr[0]);
                    stack.push(arr[1]);
                    arr[1] = this.mines[k][1];
                }
                else {
                    arr[1] = this.mines[k][1];

                }
            }
            arr[0] = this.mines[k][0];
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
            while(field[row][column].getStatus().equals("M")  || column == y || row == x) {
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
     *      * @return boolean Return false if guess did not hit mine or if flag was placed, true if mine found.
     */
    public boolean guess(int x, int y, boolean flag) {
        if (flag) {
            if(guesses == flags){
                gameOver();
            }
            if(guesses < flags) {
                guesses += 1;
                if (field[x][y].getStatus().equals("M")) {
                    field[x][y].setStatus("F");
                    field[x][y].setRevealed(true);
                    minesRevealed += 1;
                    return false;
                }
                field[x][y].setStatus("F");
                field[x][y].setRevealed(true);
                return false;
            }
        }
        if(field[x][y].getStatus().equals("0")) {
            field[x][y].setRevealed(true);
            revealZeroes(x,y);
            return false;
        }
        if(field[x][y].getStatus().equals("M")) {
            gameOver();
            return true;
        }
        field[x][y].setRevealed(true);
        revealEdges(x,y);
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
        if(minesRevealed == flags){
            return true;
        }

        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                if(field[i][j].getStatus().equals("M") && !field[i][j].getRevealed()){
                    return false;
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
        Stack1Gen stack = new Stack1Gen();
        stack.push(x);
        stack.push(y);
        getNeighbors(x,y,stack);
        while(!stack.isEmpty()){
            y = (int) stack.pop(); x = (int) stack.pop();
            this.field[x][y].setRevealed(true);
            revealEdges(x,y);
            getNeighbors(x,y,stack);
        }
    }

    public void revealEdges(int x, int y){
        int c = x; int d = y;
        for(int i = -1; i <= 1; i++) {
            if(i == 0){continue;}
            c += i;
            if (!field[c][y].getRevealed() && !field[c][y].getStatus().equals("M")) {
                field[c][y].setRevealed(true);
            }
            d += i;
            if (!field[x][d].getRevealed() && !field[x][d].getStatus().equals("M")) {
                field[x][d].setRevealed(true);
            }
            d = y; c = x;
        }
    }

    public void getNeighbors(int x, int y, Stack1Gen stack){
        int c = x; int z = y;
        for(int i = -1; i <= 1; i++){
            c += i;
            if(verifyIndices(c, y)) {
                if (this.field[c][y].getStatus().equals("0") && !this.field[c][y].getRevealed()) {
                    stack.push(c);
                    stack.push(y);
                }
            }
            z += i;
            if(verifyIndices(x, z)) {
                if(this.field[x][z].getStatus().equals("0") && !this.field[x][z].getRevealed()) {
                    stack.push(x);
                    stack.push(z);
                }
            }
            c = x; z = y;
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
        Q1Gen queue = new Q1Gen<>(); int[] arr = {x, y}; Cell fieldPoint;
        queue.add(this.field[x][y]);
        getMine(arr, queue);
        while(queue.length() != 0){
            fieldPoint = (Cell) queue.remove();
            fieldPoint.setRevealed(true);
            if(fieldPoint.getStatus().equals("M")){
                flags -= 1;
            }
        }

    }

    public void getMine(int[] arr, Q1Gen queue){
        int x = arr[0]; int y = arr[1];
        for(int i = -1; i < 1; i++){
            x += i;
            for(int j = -1; j < 1; j++){
                y += i;
                if(verifyIndices(x,y)){
                    queue.add(field[x][y]);
                }
                if(field[x][y].getStatus().equals("M")){
                    return;
                }
                y = arr[1] ;
            }
            x = arr[0];
        }

    }


    /**
     * printMinefield
     *
     * @fuctnion This method should print the entire minefield, regardless if the user has guessed a square.
     * *This method should print out when debug mode has been selected. 
     */
    public void printMinefield() {
        String s  = "";
        for(int i = 0; i< rows + 1; i++) {
            if(i == 0){ s+= " ";}
            else{
                s += Integer.toString(i - 1) + " ";}
            for (int j = 0; j < columns; j++) {
                if(i == 0) { s += Integer.toString(j) + " "; }
                else {
                    if(field[i-1][j].getRevealed()){
                        String z = field[i - 1][j].getStatus();
                        if (z.equals("0")) {
                            s += ANSI_BLUE+ "0" +  ANSI_GREY_BG + " ";
                        }
                        if (field[i - 1][j].getStatus().equals("1")) {
                            s += ANSI_BLUE_BRIGHT + "1" +  ANSI_GREY_BG + " ";
                        }
                        if(z.equals("2")){
                            s += ANSI_GREEN + "2" +  ANSI_GREY_BG + " ";
                        }
                        if(z.equals("3")){
                            s += ANSI_YELLOW + "3" +  ANSI_GREY_BG + " ";
                        }
                        if(z.equals("M")){
                            s += ANSI_RED_BRIGHT+ "M" +  ANSI_GREY_BG + " ";
                        }
                        if(z.equals("F")){
                            s += ANSI_RED + "F" +  ANSI_GREY_BG + " ";
                        }
                    }
                    else {
                        s += field[i - 1][j].getStatus() + " ";
                    }
                }
            }
            s += "\n";
        }
        System.out.println(s);
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
                else if(field[i-1][j].getRevealed()) {
                    String z = field[i - 1][j].getStatus();
                    if (z.equals("0")) {
                        s += ANSI_BLUE+ "0" +  ANSI_GREY_BG + " ";
                    }
                    if (field[i - 1][j].getStatus().equals("1")) {
                        s += ANSI_BLUE_BRIGHT + "1" +  ANSI_GREY_BG + " ";
                    }
                    if(z.equals("2")){
                        s += ANSI_GREEN + "2" +  ANSI_GREY_BG + " ";
                    }
                    if(z.equals("3")){
                        s += ANSI_YELLOW + "3" +  ANSI_GREY_BG + " ";
                    }
                    if(z.equals("M")){
                        s += ANSI_RED_BRIGHT+ "M" +  ANSI_GREY_BG + " ";
                    }
                    if(z.equals("F")){
                        s += ANSI_RED + "F" +  ANSI_GREY_BG + " ";
                    }
                }
                else {s += "- ";}
            }
            s += "\n";

        }
        return s;
    }



    public void revealBoard(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j< columns; j++){
                field[i][j].setRevealed(true);
            }
        }
    }
    public boolean verifyIndices(int x, int y){
        return x >= 0 && x < rows && y >= 0 && y < columns;
    }

}

