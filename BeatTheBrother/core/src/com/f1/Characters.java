package com.f1;

import java.util.ArrayList;
import java.util.Timer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Characters extends BaseActor {

    // Attributes
    private String userName;
    private float speed;
    boolean textureChanged = false;
    boolean nameChanged = false;
    boolean orbPicked = false;
    ArrayList<Orb> activeOrbs = new ArrayList<Orb>();
    float timeSinceOrb;
    boolean won;

    Timer timer = new Timer();

    // The Constructor
    Characters(String userName) {
        setSpeed();
        won = false;
    }

    // Setter of the Speed
    private void setSpeed() { 
        this.speed = 150; 
    }

    // Updating the Speed
    public void updateSpeed(float speed) {
        this.speed += speed;
    }

    // Set Won
    public void setWonToTrue() {
        won = true;
    }
    // Set Won
    public void setWonToFalse() {
        won = false;
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

}
