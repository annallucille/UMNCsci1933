import java.util.Scanner;


public class Fibonacci{
    public int n;
    public int fib;

    public static int fibonacciRecursive(int n){
        if(n==2||n==1){
            return 1;
        }
        else{
            return fibonacciRecursive(n-1) + fibonacciRecursive(n-2);
        }
    }

    public static int fibonacciIterative(int n){
        int fib = 1;
        int next = 1;
        int last = 0;
        for(int i=2; i<=n; i++){
            next = fib;
            fib += last;
            last = next;
        }
        return fib;
    }

    public static void main(String args[]) {
        System.out.println("Enter an integer n to get the nth Fibonacci number:");
        Scanner myScanner = new Scanner(System.in);
        int n = myScanner.nextInt(); // gets an integer from command line
        System.out.println("The " + n + "th Fibonacci number using fibonacciRecursive is " + fibonacciRecursive(n));
        System.out.println("The " + n + "th Fibonacci number using fibonacciIterative is " + fibonacciIterative(n));
    }
    
}