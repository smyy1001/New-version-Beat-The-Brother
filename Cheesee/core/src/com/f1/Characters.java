package com.f1;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Characters extends BaseActor {

    // Attributes
    private String userName;
    private float speed;
    private ArrayList<Orb> orbInventory;
    boolean textureChanged = false;
    boolean nameChanged = false;

    // The Constructor
    Characters(String userName) {
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
    private void setSpeed() { // * the speed methods will be important when we implement the speed orb
        this.speed = 5; // ! the number is just an example
    }

    // Updating the Speed
    public void updateSpeed(float speed) {
        this.speed += speed;
    }

    // updating the Texture
    void updateTexture(String tx) {
        setTexture(new Texture(Gdx.files.internal(tx)));
        textureChanged = true;
    }

    // updating the UserName
    void updateUserName(String st) {
        userName = st;
        nameChanged = true;
    }

    // Getters for each private attribute
    public float getSpeed() {
        return speed;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<Orb> getInventory() {
        return orbInventory;
    }

}