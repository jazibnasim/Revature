

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Main {

       public void startGame(){
           acronym("Hello What is your name?");
       }


        public ArrayList acronym(String phrase) {
            // TODO Write an implementation for this method declaration
            ArrayList<String> words = new ArrayList<>();
            ArrayList<String> acro = new ArrayList<>();
            for (String n : words) {
                words.add(String.valueOf(phrase.split(" ")));

            }
            return words;
        }

    public static void main(String[] args) {
	// write your code here
        String phrase = "hello world";

        ArrayList <String> answer = new ArrayList<>();

        for (String c : phrase.split("\\s+", "-")) {
            answer.add(String.valueOf(c.toUpperCase().charAt(0)));
        }


        String listString = String.join("", answer);
        System.out.println(listString);
    }
}
