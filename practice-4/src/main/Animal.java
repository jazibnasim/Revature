package main;

public abstract class Animal {

    int health;
    int strength;
    int size;

    Animal(){
        super();
     this.health = 100;
     this.strength = 20;

    }

    Animal(Integer newSize){
        super();
        this.size = newSize;
    }

    public String attackCall(){
        return "Make sound!";
    }


}

