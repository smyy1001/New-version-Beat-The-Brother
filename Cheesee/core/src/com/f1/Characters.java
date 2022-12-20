package com.f1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Characters extends BaseActor {

    // Attributes
    private String userName;
    private float speed;
    boolean textureChanged = false;
    boolean nameChanged = false;
    int score = 0;
    boolean orbPicked = false;
    ArrayList<Orb> activeOrbs = new ArrayList<Orb>();
    float timeSinceOrb;

    Timer timer = new Timer();

    // The Constructor
    Characters(String userName) {
        setSpeed();
    }

    // Setter of the Speed
    private void setSpeed() { // * the speed methods will be important when we implement the speed orb
        this.speed = 150; // ! the number is just an example
    }

    // Updating the Speed
    public void updateSpeed(float speed) {
        this.speed += speed;
    }

    // Update Score
    public void updateScore() {
        score++;
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

    public int getScore() {
        return score;
    }

}
