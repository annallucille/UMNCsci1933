import java.util.Scanner;
import java.lang.Math;




public class HistogramApp{
    static Histogram histo;
    static boolean ad = true;

    public static void main(String args[]){
        System.out.println("Enter lowerbound");
        Scanner myScanner = new Scanner(System.in);
        int lowerbound = myScanner.nextInt();
        System.out.println("Enter upperbound");
        Scanner myScanner2 = new Scanner(System.in);
        int upperbound = myScanner2.nextInt();

        Histogram histo = new Histogram(lowerbound,upperbound);


        Scanner myScanner3;
        while(ad == true){
            System.out.println("Add a data point :)");
            myScanner3 = new Scanner(System.in);
            int i = myScanner3.nextInt();
            ad = histo.add(i);
        }
        if(ad ==false){
            System.out.println(histo);
        }


    }
}