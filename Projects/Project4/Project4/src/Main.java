import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int x = 0;
        int y = 0;
        int flags = 0;
        System.out.println("Start Game? (e/m/h/q)");
        Scanner scan = new Scanner(System.in);

        if (scan.next().equals("e")) {
            x = 5;
            y = 5;
            flags = 5;
        }

        if (scan.next().equals("m")) {
            x = 9;
            y = 9;
            flags = 12;
        }

        if (scan.next().equals("h")) {
            x = 20;
            y = 20;
            flags = 40;
        }

        qMinefield myField = new qMinefield(x, y, flags);
        System.out.println("enter your first guess: [x] [y]");
        x = scan.nextInt();
        y = scan.nextInt();
        myField.createMines(x, y, flags);
        myField.evaluateField();
        myField.revealZeroes(x, y);
        System.out.println(myField.toString());


        while (!myField.gameOver()) {
            System.out.println("enter your guess: [x] [y] [flag (y/n)]");
            x = scan.nextInt();
            y = scan.nextInt();

            if (scan.next().equals("y")) {
                myField.guess(x, y, true);
                System.out.println(myField.toString());
            }

            myField.guess(x, y, false);
            System.out.println(myField.toString());
        }
    }
}