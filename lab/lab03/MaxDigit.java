import java.util.Scanner;
import java.lang.Math;


public class MaxDigit{
    public static int digit;
    public static int largestDigit = 0;

    public static int recursiveMaxDigit(int num){
        int digit = num%10;
        if(num==0){
            return largestDigit;
        }
        else if(digit>largestDigit){
            largestDigit = digit;
            num = (num-digit)/10;
            return recursiveMaxDigit(num);
        }
        else{
            num = (num-digit)/10;
            return recursiveMaxDigit(num);
        }
    }

    public static int iterativeMaxDigit(int num){
        int length = (int) (Math.log10(num) + 1);
        for(int i=1; i<=length; i++){
            digit = num%10;
            if (digit>largestDigit){
                largestDigit = digit;
            }
        }
        return largestDigit;
    }
    
    public static void main(String args[]) {
        System.out.println("Enter a large number :)");
        Scanner myScanner = new Scanner(System.in);
        int num = myScanner.nextInt(); // gets an integer from command line
        System.out.println("The largest didgit of " + num + " using rurcursion is " + recursiveMaxDigit(num));
        System.out.println("The largest didgit of " + num + " using iteration is " + iterativeMaxDigit(num));
        }

}