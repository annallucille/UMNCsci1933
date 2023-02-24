import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;


public class BookshelfReader {

    public static Bookshelf readBooksFromFile(String fName){
        Scanner scan = null; 
        String s; String[] lst; int lines = 0;
        Bookshelf b= new Bookshelf();

        try {scan = new Scanner(new File(fName)); 
            while(scan.hasNextLine()){
                s = scan.nextLine();
                lines++;
            }
            scan.close();
    
            b = new Bookshelf(lines); scan = new Scanner(new File(fName));
            for(int i = 0; i <= lines-1; i++){
                s = scan.nextLine();
                lst = s.split(",");
                Book book = new Book(lst[0], lst[1], Double.parseDouble(lst[2]));
                b.books[i] = book;
            }
            scan.close();
        
        } 
        catch (Exception e) { System.out.println("Useful error message goes here.");}
        return b;
    }


    public static void writeShelfToFile(Bookshelf b, String fName){
        PrintWriter p = null; // declare p outside try-catch block
        try {
            p = new PrintWriter(new File(fName));
            p.println(b.toString()); 
            p.close();
        } 
        catch (Exception e) {System.out.println("Fuck you");}
    }
}
