package main;

public class Dog extends Animal{
    private int health = 80;
    private int strength = 40;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String attackCall(){

        return "Arf!";
    }
}
