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
    int color;

    Timer timer = new Timer();

    // The Constructor
    Characters(String userName, int color) {
        setSpeed();
        this.userName = userName;
        won = false;
        this.color = color;
    }

    // Setter of the Speed
    private void setSpeed() { 
        this.speed = 150; 
    }

    // 0 : blue / 1 : green / 2 : red / 3 : purple 
    public void updateColor(int c) {
        this.color = c;
        if(color == 0) {
            updateSpeed(-10);
        }
        else if(color == 1) {
            updateSpeed(+10);
        }
        else if(color == 3) {
            setSize(getWidth()+15, getHeight()+15);
        }
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
