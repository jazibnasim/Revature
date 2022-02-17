package main;

public class Cat extends Animal{

    private int health = 50;
    private int strength = 10;
    private int size = 4;

    public String attackCall(){
        return "Meow!";
    }

    public int attack(){
        return 0;
    }

    public int getHealth(){
       return this.health;
    }

    public int setHealth(int health){
        return this.health = health;
    }

    public int getStrength(){
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString(){
        return "{Cat:\nhealth = " +this.health + "\nstrength = " + this.strength + "\n}";
    }

    public static void main(String[] args) {
        System.out.println("hello!");
    }
}
