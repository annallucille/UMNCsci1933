import java.util.Scanner;


public class BankAccount {
    public String username;
    private String password;
    protected double balance;

    public static void main(String[] args){
        BankAccount myAccount = new BankAccount();
        myAccount.username = "myAccount";
        myAccount.password = "CSCI1933 rules!";

        myAccount.deposit("myAccount", "CSCI1933 rules!", 100.50);
        System.out.println("My account\'s balance is: " + myAccount.balance);

        System.out.println("Enter your Username:");
        Scanner userScanner = new Scanner(System.in);
        String userInput = userScanner.nextLine(); 
        System.out.println("Username: " + userInput);

        System.out.println("Enter your Password:");
        Scanner passwordScanner = new Scanner(System.in);
        String passwordInput = passwordScanner.nextLine(); 

    
        System.out.println(userInput + " Account Balance is: " + myAccount.getBalance(userInput, passwordInput));

        // Transfer test 

        BankAccount account000001 = new BankAccount("Dan","1234",1205.98);
        BankAccount account000002 = new BankAccount("Bob","5678",190.54);

        System.out.println("Enter your Username:");
        Scanner userScanner2 = new Scanner(System.in);
        String userInput2 = userScanner2.nextLine(); 
        System.out.println("Username: " + userInput);

        System.out.println("Enter Reciver Username:");
        Scanner user3Scanner = new Scanner(System.in);
        String userInput3 = user3Scanner.nextLine(); 
        System.out.println("Reciver Username : " + userInput3);

        System.out.println("Enter your Password:");
        Scanner passwordScanner2 = new Scanner(System.in);
        String passwordInput2 = passwordScanner2.nextLine(); 

        System.out.println("Enter ammount to be transfered :");
        Scanner amountScanner = new Scanner(System.in);
        double amountInput = amountScanner.nextDouble(); 



        account000001.transfer(account000002, userInput3, passwordInput2, amountInput);

        System.out.println(userInput2 +" Balance is now: "+ account000001.getBalance(userInput2,passwordInput2));

    }


    public BankAccount(String u, String p, double b){
        account.username = u;
        account.password = p;
        account.balance = b;
    }
    public BankAccount(){}

    public double getBalance(String enteredUser, String enteredPassword){
        if(username.equals(enteredUser) && password.equals(enteredPassword)){
            return balance;
        }
        return -1;
    }
    public boolean setPassword(String oldPassword, String newPassword) {
        if (password.equals(oldPassword)){
            password = newPassword;
            return true;
        } 
        else {
            return false;
        }
    }
    public void setName(String newName) {
        username = newName;
    }
    public String getName() {
        return username;
    }

// opperators
    public void withdraw(String enteredUser, String enteredPassword, double amount) {
        if (username.equals(enteredUser) && password.equals(enteredPassword) && balance >= amount) {
            balance = balance - amount;
        }
    }
    public void deposit(String enteredUser, String enteredPassword, double amount) {
        if (username.equals(enteredUser)&&password.equals(enteredPassword)) {
            balance = balance + amount;
        }
    }
    public void transfer(BankAccount other, String enteredUser, String enteredPassword, double amount){
        if(other.username.equals(enteredUser) && password.equals(enteredPassword) && balance >= amount) {
            other.deposit(eneteredUser, other.password, amount);
            account.withdral(account.username, enteredPassword, amount);
        }
    }
}
