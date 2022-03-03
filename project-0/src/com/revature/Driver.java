package com.revature;
import javax.jws.soap.SOAPBinding;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Driver {




    User currentUser;

    public Driver() {


    }


    public static void main(String[] args) throws InterruptedException {
	// write your code here

        Scanner scanner = new Scanner(System.in);
        User user = new User();

        boolean moveOn = false;

        do{
            System.out.println("Welcome to Bank of Imagine!");
            System.out.println("1. Register");
            System.out.println("2. Login");

            String choice = scanner.nextLine();
            switch(choice){
                case "1":
                    System.out.println("What is your first name?");
                    String enteredFirstName = scanner.nextLine();
                    user.setFirstName(enteredFirstName);

                    System.out.println("What is your last name?");
                    String enteredLastName = scanner.nextLine();
                    user.setLastName(enteredLastName);

                    System.out.println("Create a user name.");
                    String chosenUserName = scanner.nextLine();
                    user.setUserName(chosenUserName);

                    System.out.println("Create a password.");
                    String chosenPassword = scanner.nextLine();
                    user.setPassword(chosenPassword);



                    TimeUnit.SECONDS.sleep(1);

                    System.out.println("Account created! Please log-in!");
                    TimeUnit.SECONDS.sleep(1);
                    break;
                case "2":
                    System.out.println("Enter user name: ");
                    String enteredUserName = scanner.next();

                    System.out.println("Enter password: ");
                    String enteredPassword= scanner.next();



                    TimeUnit.SECONDS.sleep(1);

                    if(enteredUserName.equals(user.getUserName()) && enteredPassword.equals(user.getPassword())){
                       System.out.println("Welcome back " + user.getFirstName() + " " + user.getLastName() + ".");
                       moveOn = true;
                       TimeUnit.SECONDS.sleep(1);
                       break;

                   }




            }



        }while (moveOn == false);
    }
}
