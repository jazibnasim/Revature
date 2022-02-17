package main;

public class Main {

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.setHealth(24);
        System.out.println(cat.getHealth());
        cat.setSize(10);
        System.out.println(cat.getSize());
        System.out.println("hello!");
        System.out.println(cat.toString());
        Cat c2 = new Cat();
    }
}
