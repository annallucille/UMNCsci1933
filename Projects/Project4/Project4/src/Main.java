import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        int flags = 0;
        String flag;
        boolean isOver = false;
        boolean flagRm;
        Scanner scan = new Scanner(System.in);
        System.out.println("Game Mode: (e/m/h)");
        String ans = scan.next();

        if (ans.equals("t")) {
        x = 3;
        y = 3;
        flags = 1;
        }

        if (ans.equals("e")) {
            x = 5;
            y = 5;
            flags = 5;
        }

        if (ans.equals("m")) {
            x = 9;
            y = 9;
            flags = 12;
        }

        if (ans.equals("h")) {
            x = 20;
            y = 20;
            flags = 40;
        }

        System.out.println("Start Game? (y/n)");
        ans = scan.next();

        if (ans.equals("y")) {
            qMinefield myField = new qMinefield(x, y, flags);
            myField.printMinefield();
            System.out.println("enter your first guess: [row] [column]");
            x = scan.nextInt();
            y = scan.nextInt();

            myField.createMines(x, y, flags);
            myField.evaluateField();
            myField.convert();

            Cell[][] field = myField.getField();
            if (field[x][y].getStatus().equals("0")) {
                myField.revealZeroes(x, y);
            }
            else{
                myField.revealMines(x,y);
            }
            System.out.println(myField.toString());


            while (!myField.gameOver() && !isOver) {
                System.out.println("Flags left: " + (flags - myField.getGuesses()));
                System.out.println("enter your guess: [row] [column] [flag (y/n/r (remove))]]");
                x = scan.nextInt();
                y = scan.nextInt();
                flag = scan.next();
                while(!myField.verifyIndices(x,y)){
                    System.out.println("invalid guess.");
                    System.out.println("Flags left: " + (flags - myField.getGuesses()));
                    System.out.println("enter your guess: [row] [column] [flag (y/n/r (remove))]");
                    x = scan.nextInt(); y = scan.nextInt(); flag = scan.next();
                }

                if (flag.equals("y")) {
                    isOver = myField.guess(x, y, true);
                }
                else if(flag.equals("r")){
                    flagRm = myField.flagRemove(x,y);
                    if(!flagRm){
                        System.out.println("Flag does not exist. Please go again");
                    }
                }
                else {
                    isOver = myField.guess(x, y, false);
                    if (field[x][y].getStatus().equals("0")) {
                        myField.revealZeroes(x, y);
                    }
                }
                System.out.println(myField.toString());
            }

            if(isOver){
                System.out.println("Nice try!");
                System.out.println("Mines Found: " + myField.getMinesRevealed());
            }
            else{
                System.out.println("Great Work!");
            }
            myField.printEnd();
        }

      if (ans.equals("d")) {
        qMinefield myField = new qMinefield(x, y, flags);
        myField.printMinefield();
        System.out.println("Flags left: " + (flags - myField.getGuesses()));
        System.out.println("enter your first guess: [row] [column]");
        x = scan.nextInt();
        y = scan.nextInt();
        myField.createMines(x, y, flags);
        myField.evaluateField();
        myField.convert();
        Cell[][] field = myField.getField();
        if(field[x][y].getStatus().equals("0")) {
            myField.revealZeroes(x, y);
        }
        else{
            myField.revealMines(x,y);
        }
        myField.printMinefield();


        while (!myField.gameOver() && !isOver) {
            System.out.println("Flags left: " + (flags - myField.getGuesses()));
            System.out.println("enter your guess: [row] [column] [flag (y/n/r (remove))]");
            x = scan.nextInt();
            y = scan.nextInt();
            flag = scan.next();
            if (flag.equals("y")) {
                isOver = myField.guess(x, y, true);
            }
            else if(flag.equals("r")){
                flagRm = myField.flagRemove(x,y);
                if(!flagRm){
                    System.out.println("Flag does not exist. Please go again");
                }
            }
            else {
                isOver = myField.guess(x, y, false);
            }
            myField.printMinefield();

        }
        if(isOver || myField.getMinesRevealed() != flags){
            System.out.println("Nice try!");
            System.out.println("Mines Found: " + myField.getMinesRevealed());
        }
        else{
            System.out.println("Great Work!");
        }
        myField.printEnd();
      }
    }

}