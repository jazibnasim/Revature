package com.revature.service;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.repo.UserRepo;
import com.revature.util.ConnectionBridge;


import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.text.NumberFormat;



public class MainMenu {
    static User user = new User("jazib123", "nick", "jazib", "nasim");
    static Account userAccount = new Account();

    static Scanner scanner = new Scanner(System.in);
    static NumberFormat formatter = NumberFormat.getCurrencyInstance();

    static int choice;
    static int secondChoice;



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

    public static void register() throws InterruptedException, SQLException {

        System.out.print("Enter first name: ");
        String enteredFirstName = scanner.next();
        user.setFirstName(enteredFirstName);

        System.out.print("Enter last name: ");
        String enteredLastName = scanner.next();
        user.setLastName(enteredLastName);

        System.out.print("Create a user name: ");
        String chosenUserName = scanner.next();
        user.setUserName(chosenUserName);

        System.out.print("Create a password: ");
        String chosenPassword = scanner.next();
        user.setPassword(chosenPassword);

        UserRepo userRepo = new UserRepo();
        userRepo.createUser(user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName());

        TimeUnit.SECONDS.sleep(2);

        System.out.println("Account created! (Please log-in!");
        TimeUnit.SECONDS.sleep(3);

    }

    public static void login() throws InterruptedException, SQLException {
        System.out.print("Enter user name: ");
        String enteredUserName = scanner.next();

        System.out.print("Enter password: ");
        String enteredPassword= scanner.next();


        TimeUnit.SECONDS.sleep(1);

        UserRepo userRepo = new UserRepo();

        if(userRepo.validateUserName(enteredUserName, enteredPassword));
        {
            TimeUnit.SECONDS.sleep(1);
            MainMenu.innerMenu();
        }

//        if(enteredUserName.equals(user.getUserName()) && enteredPassword.equals(user.getPassword())){
//            TimeUnit.SECONDS.sleep(1);
//            MainMenu.innerMenu();

//        } else {
//
//            System.out.println("Incorrect username or password! Please try again.");
//            TimeUnit.SECONDS.sleep(2);
//        }
    }

    public static void innerMenu() {
        do {
            System.out.println("Welcome " + user.getFirstName() + "!");
            System.out.println("1. Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Log-out");

            secondChoice = scanner.nextInt();
            switch(secondChoice){
                case 1:
                    System.out.println("Your balance is " + formatter.format(userAccount.getBalance()));
                    break;
                case 2:
                    System.out.println("How much would you like to deposit?");
                    double deposit = scanner.nextDouble();
                    userAccount.setBalance(userAccount.getBalance() + deposit);
                    break;
                case 3:
                    System.out.println("How much would you like to withdraw?");
                    double withdraw = scanner.nextDouble();
                    if(userAccount.getBalance() > 0){
                        userAccount.setBalance(userAccount.getBalance() - withdraw);
                    } else {
                        System.out.println("Cannot complete. Account balance will overdraft.");
                    }
                    break;
                case 4:
                    break;

            }



        } while (secondChoice != 4) ;
    }



}
