package com.f1;
import java.util.ArrayList;

public class Characters {
        // Attributes 
        private String userName;
        private int colorNum;//1-Red 2-Blue 3-Green 4-Purple
        private char c;
        private float speed;
        private ArrayList<Orb> orbInventory;

    Characters( String userName, char c, int color ) {
        setSpeed();
        setInventory();
    }
    
    // Initialize the Inventory
    private void setInventory() {
        orbInventory = new ArrayList<Orb>();
    }
    // Adding and removing Orbs
    public void addOrbToInventory(Orb o) {
        orbInventory.add(o);
    }
    public void removeOrbToInventory(Orb o) {
        orbInventory.remove(o);
    }

    // Setter of the Speed
    private void setSpeed() {
        this.speed = 5; //! the number is just an example
    }

    // Updating the Speed
    public void updateSpeed(float speed) {
        this.speed+=speed;
    }

    // Getters for each private attribute
    public char getC() {
        return c;
    }
    public float getSpeed() {
        return speed;
    }
    public String getUserName() {
        return userName;
    }
    public ArrayList<Orb> getInventory() {
        return orbInventory;
    }
    public int getColor() {
        return colorNum;
    }
}