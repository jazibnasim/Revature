package com.revature.service;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.repo.UserRepo;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.text.NumberFormat;

/** This is the Main Menu */
public class MainMenu {
    static User user = new User(null, null, 0.00);
    static Account userAccount = new Account();

    static Scanner scanner = new Scanner(System.in);
    static NumberFormat formatter = NumberFormat.getCurrencyInstance();

    static int choice;
    static int secondChoice;
    static UserRepo userRepo = new UserRepo();
    static User currentUser = null;

    public static void run() throws InterruptedException, SQLException {

            do{
                   System.out.println("Welcome to Bank of Imagine!");
                   System.out.println("1. Register");
                   System.out.println("2. Login");
                   System.out.println("3. Exit");

                   choice = scanner.nextInt();
                   switch(choice){
                       case 1:
                           register();
                           break;

                       case 2:
                           login();
                           break;
                       case 3:
                           System.out.println("Thank you for banking with Imagine! Good-bye!");
                           TimeUnit.SECONDS.sleep(3);
                           break;
                   }

            }while (choice != 3);

   }
    // This method takes input from scanner/user and populates the createUser() method
    public static void register() throws InterruptedException, SQLException {

        System.out.print("Create a user name: ");
        String chosenUserName = scanner.next();
        user.setUserName(chosenUserName);

        System.out.print("Create a password: ");
        String chosenPassword = scanner.next();
        user.setPassword(chosenPassword);

        UserRepo userRepo = new UserRepo();
        userRepo.createUser(user.getUserName(), user.getPassword(), user.getBalance());

        TimeUnit.SECONDS.sleep(2);

        System.out.println("Account created! Please log-in!");
        TimeUnit.SECONDS.sleep(3);

    }

    //This method logs user in by comparing inputted data with username pulled in from database
    public static void login() throws InterruptedException, SQLException {
        System.out.print("Enter user name: ");
        String enteredUserName = scanner.next();

        System.out.print("Enter password: ");
        String enteredPassword= scanner.next();


        TimeUnit.SECONDS.sleep(1);



        if(userRepo.checkLogin(enteredUserName,enteredPassword));
        {
            TimeUnit.SECONDS.sleep(1);
            MainMenu.innerMenu();
        }

//       else {
//
//            System.out.println("Incorrect username or password! Please try again.");
//            TimeUnit.SECONDS.sleep(2);
//        }
    }

    /** This is the Account Menu */

    public static void innerMenu() throws InterruptedException, SQLException {
        do {
            System.out.println("Welcome!");
            System.out.println("1. Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Log-out");

            secondChoice = scanner.nextInt();
            switch(secondChoice){
                case 1:
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Your balance is " + formatter.format(user.getBalance()));
                    TimeUnit.SECONDS.sleep(2);
                    break;
                case 2:
                    System.out.println("How much would you like to deposit?");
         /* - */    double deposit = scanner.nextDouble();

                    double depositChangeBalance = user.getBalance() + deposit;
                    //userRepo.updateBalance(user.getUserName(), changeBalance);

                    if(deposit > 0){
                        userRepo.updateBalance(user.getUserName(), depositChangeBalance);
                        user.setBalance(depositChangeBalance);
                    } else {
                        System.out.println("The number you provided is a negative. Please provide a number in the proper format.");
                        TimeUnit.SECONDS.sleep(2);
                    }
                    break;
                case 3:
                    System.out.println("How much would you like to withdraw?");
        /* - */     double withdraw = scanner.nextDouble();

                    if(userAccount.getBalance() - withdraw > 0 && withdraw > 0){

                        double withdrawChangeBalance = user.getBalance() - withdraw;
                        userAccount.setBalance(userAccount.getBalance() - withdraw);
                    } else if (userAccount.getBalance() - withdraw < 0 && withdraw > 0) {
                        System.out.println("Cannot complete. Account balance will overdraft.");
                    } else if (withdraw < 0){
                        System.out.println("The number you provided is a negative. Please provide a number in the proper format.");
                        TimeUnit.SECONDS.sleep(2);
                    } else {
                        System.out.println("Please enter a number in proper format.");

                     }
                    break;
                case 4:
                    break;

            }

        } while (secondChoice != 4) ;
    }

}
