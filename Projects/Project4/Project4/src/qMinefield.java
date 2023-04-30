import java.util.Random;


public class qMinefield {
    /**
    Global Section
    */
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001b[36m";
    public static final String ANSI_PURPLE = "\u001b[34;1m";
    public static final String ANSI_PINK = "\u001b[35m";
    public static final String ANSI_RED_BRIGHT = "\u001b[31;1m";
    public static final String ANSI_RED = "\u001b[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_GREY_BG = "\u001b[0m";
    /**
    /**
     * Constructor
     * @param rows       Number of rows.
     * @param columns    Number of columns.
     * @param flags      Number of flags, should be equal to mines
     */

    private  int[][] intField; //int representation of field, used for calculating mines
    private Cell[][] field;

    private int[][] mines; // keeps track of mine coordinates top make revealMines() easier.
    private int rows;
    private int columns;
    private int guesses = 0; // keeps track of flags placed
    private int minesRevealed = 0; // keeps track of mines found with flags
    private int flags; // amount of bombs




    public qMinefield(int rows, int columns, int flags) {
        this.rows = rows;
        this.columns = columns;
        this.field = new Cell[rows][columns];
        this.mines = new int[flags][2]; //init mine coordinates
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++) {
                field[i][j] = new Cell( false, "-"); //idk it made me do this
            }
        }
        this.intField = new int[rows][columns];
        this.flags = flags;

    }
     public Cell[][] getField(){  // if a 0 is found it will reveal all surrounding 0s and edges
        return this.field;
     }
     public int getGuesses(){ return this.guesses;}

     public int getMinesRevealed(){
        return minesRevealed; //if game lost tells u how many you found
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
            temp[0] = mines[i][0]; temp[1] = this.mines[i][1]; //push(x); push(y); => y = pop(); x = pop();
            evaluateHelper(temp, stack, i);
        }
        System.out.println("----");
        while(!stack.isEmpty()){
             y = (int) stack.pop();  //push(x); push(y); => y = pop(); x = pop();
             x = (int) stack.pop();
            intField[x][y] += 1;
        }


    }

    public void convert(){ //convert() is its own function because it is used to convert my integer representation of the field to a Cell representation
        for(int i = 0; i < rows; i++ ){
            for(int j = 0; j < columns; j++) {
                if(!field[i][j].getStatus().equals("M")) { field[i][j].setStatus(Integer.toString(intField[i][j]));}
                else{ intField[i][j] = 1000;}  //sets value to be something impossible so i can remove flags later
            }
        }
    }


    public void evaluateHelper(int[] arr, Stack1Gen stack, int k){
        for(int i = -1; i <= 1; i++){  //im very proud of this looping
            arr[0] += i;
            for(int j = - 1; j <= 1; j++){
                arr[1] += j;
                if(verifyIndices(arr[0], arr[1])){
                    stack.push(arr[0]);  //push(x); push(y); => y = pop(); x = pop();
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
            while(field[row][column].getStatus().equals("M")  || column == y || row == x) { //cant place mines on first guess
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
            if(guesses == flags){ //instant game over if no flags left
                gameOver();
            }
            if(guesses < flags) {
                guesses += 1;
                if (field[x][y].getStatus().equals("M")) {
                    field[x][y].setStatus("F");
                    field[x][y].setRevealed(true);
                    minesRevealed += 1; // +1 mine found
                    return false;
                }
                field[x][y].setStatus("F");
                field[x][y].setRevealed(true);
                return false;
            }
        }
        if(field[x][y].getStatus().equals("0")) {
            field[x][y].setRevealed(true);
            revealZeroes(x,y);  //reveals all 0s if a 0 is found
            return false;
        }
        if(field[x][y].getStatus().equals("M")) {
            return true;  //gives an instant game over
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
        return flags == guesses || minesRevealed == flags;
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
            getNeighbors(x,y,stack);
            revealEdges(x,y);  // loops through to reveal the edges like real mine sweeper
        }
    }


    public void getNeighbors(int x, int y, Stack1Gen stack){
        int c = x; int z = y;
        for(int i = -1; i <= 1; i++){   //im very proud of this looping
            if(i == 0){ continue; }  // doesnt include square guessed
            c += i;  //checks left and right neighbors
            if(verifyIndices(c, y)) {
                if (this.field[c][y].getStatus().equals("0") && !this.field[c][y].getRevealed()) {
                    stack.push(c);
                    stack.push(y);
                }
            }
            z += i;  // check up and down neighbors
            if(verifyIndices(x, z)) {
                if(this.field[x][z].getStatus().equals("0") && !this.field[x][z].getRevealed()) {
                    stack.push(x);
                    stack.push(z);
                }
            }
            c = x; z = y;  //resets to og coordinates for next i loop
        }

    }

    public void revealEdges(int x, int y){  //just like the real minesweeper this reveals all the numbers surrounding the 0 shape
        int c = x; int d = y;
        for(int i = -1; i <= 1; i++) { //im very proud of this looping
            if(i == 0){continue;}
            c += i;
            if (verifyIndices(c,y) && !field[c][y].getRevealed() && !field[c][y].getStatus().equals("M")) {
                field[c][y].setRevealed(true);
            }
            c = x; d += i;
            if (verifyIndices(x,d) && !field[x][d].getRevealed() && !field[x][d].getStatus().equals("M")) {
                field[x][d].setRevealed(true);
            }
            d = y;
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
        Q1Gen queue = new Q1Gen<>(); Cell fieldPoint; Random rand = new Random(); int r; int c;
        queue.add(this.field[x][y]);
        getMine(x, y, queue);
        while(queue.length() != 0){
            fieldPoint = (Cell) queue.remove();
            fieldPoint.setRevealed(true);
            if(fieldPoint.getStatus().equals("M")){
                guesses += 1; minesRevealed += 1;
                return;
            }
            c = -1; r = -1;
            while(!verifyIndices(r,c)) {
                r = rand.nextInt(4);
                switch (r) {
                    case 1:
                        r = x - 1;
                        c = y;
                        break;
                    case 2:
                        r = x + 1;
                        c = y;
                        break;
                    case 3:
                        c = y - 1;
                        r = x;
                        break;
                    default:
                        c = y + 1;
                        r = x;
                        break;
                }
            }
            getMine(r, c, queue);
        }

    }

    public void getMine(int c, int d, Q1Gen queue){
        int x = c; int y = d;
        for(int i = -1; i <= 1; i++){
            x += i;
            if(verifyIndices(x,y)){
                queue.add(field[x][y]);
                if(field[x][y].getStatus().equals("M")){
                    return;
                }
            }
            x = c; y += i;
            if(verifyIndices(x,y)){
                queue.add(field[x][y]);
                if(field[x][y].getStatus().equals("M")){
                    return;
                }
            }
            y = d;
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
            if(i == 0){ s+= "  ";}
            else if(i - 1 < 10){s += "0" + Integer.toString(i - 1) + " ";} //im  just proud of these, they made the game line up nicely
            else {s += Integer.toString(i - 1) + " ";}
            for (int j = 0; j < columns; j++) {
                if (i == 0) {
                    if (j < 10) { s += "0" + Integer.toString(j) + " "; }
                    else { s += Integer.toString(j) + " ";}
                }
                else {
                    if(field[i-1][j].getRevealed()){ //it took me forever to get this to work properly
                        String z = field[i - 1][j].getStatus();
                        switch (z) {  //switch statements are now my one true love
                            case "0":   s += ANSI_PINK + "0" + ANSI_GREY_BG + "  ";
                                break;
                            case "1":   s += ANSI_PURPLE + "1" + ANSI_GREY_BG + "  ";
                                break;
                            case "2":   s += ANSI_BLUE + "2" + ANSI_GREY_BG + "  ";
                                break;
                            case "M":   s += ANSI_RED + "M" + ANSI_GREY_BG + "  ";
                                break;
                            case "F":   s += ANSI_YELLOW + "F" + ANSI_GREY_BG + "  ";
                                break;
                            default:    s += ANSI_GREEN + field[i - 1][j].getStatus() + ANSI_GREY_BG + "  "; //any other number will appear in green
                                break;
                        }

                    }
                    else {
                        s += field[i - 1][j].getStatus() + "  ";
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
    public String toString() { //im really really proud of how i did this
        String s = "  ";
        for(int i = 0; i <= rows; i++){
            if(i == 0){ s+= " ";}
            else if(i - 1 < 10){s += "0" + Integer.toString(i - 1) + " ";}
            else{s += Integer.toString(i - 1) + " ";}
            for(int j = 0; j < columns; j++) {
                if (i == 0) {
                    if (j < 10) { s += "0" + Integer.toString(j) + " "; }
                    else { s += Integer.toString(j) + " "; }
                } else if (field[i - 1][j].getRevealed()) {
                    String z = field[i - 1][j].getStatus();
                    switch (z) {
                        case "0":   s += ANSI_PINK + "0" + ANSI_GREY_BG + "  ";
                                    break;
                        case "1":   s += ANSI_PURPLE + "1" + ANSI_GREY_BG + "  ";
                                    break;
                        case "2":   s += ANSI_BLUE + "2" + ANSI_GREY_BG + "  ";
                                    break;
                        case "M":   s += ANSI_RED + "M" + ANSI_GREY_BG + "  ";
                                    break;
                        case "F":   s += ANSI_YELLOW + "F" + ANSI_GREY_BG + "  ";
                                    break;
                        default:    s += ANSI_GREEN + field[i - 1][j].getStatus() + ANSI_GREY_BG + "  "; //any other number will appear in green
                                    break;
                    }
                } else { s += "-  ";}
            }
            s += "\n";

        }
        return s;
    }

    public void printEnd(){
        String s  = "";
        revealBoard();
        for(int i = 0; i< rows + 1; i++) {
            if(i == 0){ s+= "  ";}
            else if(i - 1 < 10){s += "0" + Integer.toString(i - 1) + " ";} //im  just proud of these, they made the game line up nicely
            else {s += Integer.toString(i - 1) + " ";}
            for (int j = 0; j < columns; j++) {
                if (i == 0) {
                    if (j < 10) { s += "0" + Integer.toString(j) + " "; }
                    else { s += Integer.toString(j) + " ";}
                }
                else {
                    String z = field[i - 1][j].getStatus();
                    switch (z) {  //switch statements are now my one true love
                            case "0":   s += ANSI_PINK + "0" + ANSI_GREY_BG + "  ";
                                break;
                            case "1":   s += ANSI_PURPLE + "1" + ANSI_GREY_BG + "  ";
                                break;
                            case "2":   s += ANSI_BLUE + "2" + ANSI_GREY_BG + "  ";
                                break;
                            case "M":   s += ANSI_RED + "M" + ANSI_GREY_BG + "  ";
                                break;
                            case "F":{  if(intField[i -1][j] == 1000){   // tells you which ones you got correct by making them red
                                        s += ANSI_RED_BRIGHT + "F" + ANSI_GREY_BG + "  ";
                                        break;
                                        }
                                        s += ANSI_YELLOW + "F" + ANSI_GREY_BG + "  ";
                                        break;
                                    }
                            default:    s += ANSI_GREEN + field[i - 1][j].getStatus() + ANSI_GREY_BG + "  "; //any other number will appear in green
                                break;
                        }

                    }
            }
            s += "\n";
        }
        System.out.println(s);
    }




    public void revealBoard(){ //shows you the board after :) thought it was a cool feature to have
        for(int i = 0; i < rows; i++){
            for(int j = 0; j< columns; j++){
                field[i][j].setRevealed(true);
            }
        }
    }
    public boolean verifyIndices(int x, int y){ //Since there were so many verifications of bounds i made a helper function that compares x and y coordinates to the bounds of the array
        return x >= 0 && x < rows && y >= 0 && y < columns;
    }

    public boolean flagRemove(int x, int y){
        if(field[x][y].getStatus().equals("F")){
            if(intField[x][y] == 1000){    //value set for mines
                field[x][y].setStatus("M");
                field[x][y].setRevealed(false);
                guesses -= 1;  //updates to remove guess from total number of guesses
                minesRevealed -= 1;   //updates the amount of mines found to be one less
                return true;
            }
            field[x][y].setStatus(Integer.toString(intField[x][y]));
            guesses -= 1; //updates to remove guess from total number of guesses
            return true;
        }
        return false;  //if no flag program will reprompt user for coordinates and flag value
    }

}

