package com.revature.service;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.repo.AccountRepo;
import com.revature.repo.UserRepo;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.text.NumberFormat;

/** This is the Main Menu */
public class MainMenu {
    static User user = new User(0,null, null, 0.00);
    static Account userAccount = new Account();

    static Scanner scanner = new Scanner(System.in);
    static NumberFormat formatter = NumberFormat.getCurrencyInstance();

    static int choice;
    static int secondChoice;
    static UserRepo userRepo = new UserRepo();
    static AccountRepo accountRepo = new AccountRepo();
    static User currentUser;

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
    public static void register() throws InterruptedException {

        System.out.print("Create a user name: ");
        String chosenUserName = scanner.next();
        user.setUserName(chosenUserName);

        System.out.print("Create a password: ");
        String chosenPassword = scanner.next();
        user.setPassword(chosenPassword);

        userRepo.createUser(user.getUserName(), user.getPassword());

        TimeUnit.SECONDS.sleep(2);

        System.out.println("Account created! Please log-in!");
        TimeUnit.SECONDS.sleep(3);

    }

    //This method logs user in by comparing inputted data with username pulled in from database
    public static void login() throws InterruptedException, SQLException {

        System.out.print("Enter user name: ");
        String enteredUserName = scanner.next();  //input

        System.out.print("Enter password: ");
        String enteredPassword= scanner.next();  //input

        TimeUnit.SECONDS.sleep(1);

            userRepo.checkLogin(enteredUserName, enteredPassword);  //method call
            TimeUnit.SECONDS.sleep(1);
            MainMenu.innerMenu();   //Inner Menu call
    }

    /** This is the Account Menu */

    public static void innerMenu() throws InterruptedException {
        do {
            System.out.println("Welcome!");
            System.out.println("1. Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Log-out");

            //currentUser = accountRepo.currentUser;
            currentUser = userRepo.currentUser;

            secondChoice = scanner.nextInt();
            switch(secondChoice){
                case 1: //check balance
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Your balance is " + formatter.format(currentUser.getBalance()));
                    TimeUnit.SECONDS.sleep(2);
                    break;
                case 2: //deposit
                    System.out.println("How much would you like to deposit?");
         /* - */    double deposit = scanner.nextDouble();

                    //Validation to prevent exceptions and errors
                    if(deposit > 0){
                        double depositChangeBalance = currentUser.getBalance() + deposit;
                        accountRepo.updateBalance(currentUser.getUserName(), depositChangeBalance);
                        currentUser.setBalance(depositChangeBalance);
                    } else {
                        System.out.println("The number you provided is a negative. Please provide a number in the proper format.");
                        TimeUnit.SECONDS.sleep(2);
                    }
                    break;

                case 3: //withdraw
                    System.out.println("How much would you like to withdraw?");
        /* - */     double withdraw = scanner.nextDouble();

                    //Validation to prevent exceptions and errors
                    if(currentUser.getBalance() - withdraw > 0 && withdraw > 0){

                        double withdrawChangeBalance = currentUser.getBalance() - withdraw;
                        userRepo.updateBalance(currentUser.getUserName(), withdrawChangeBalance);
                        currentUser.setBalance(withdrawChangeBalance);

                    } else if (currentUser.getBalance() - withdraw < 0 && withdraw > 0) {
                        System.out.println("Cannot complete. Account balance will overdraft.");
                    } else if (withdraw < 0){
                        System.out.println("The number you provided is a negative. Please provide a number in the proper format.");
                        TimeUnit.SECONDS.sleep(2);
                    } else {
                        System.out.println("Please enter a number in proper format.");

                     }
                    break;
                case 4: //logout
                    break;
            }

        } while (secondChoice != 4) ; //don't log out until user inputs "4"
    }

}
