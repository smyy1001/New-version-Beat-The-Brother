// package com.ytoraman;

// import com.badlogic.gdx.Game;
// import java.util.ArrayList;

// public class Character extends BaseScreen{

//     /**
//      * author Sumeyye Acar 
//      * 08/12/2022
//      */

//     // Attributes 
//     private String userName;
//     private int x; 
//     private int y; 
//     private char c;
//     private float speed;
//     private ArrayList<Orb> orbInventory; 

//     // constructor
//     public Character(String userName, char c, Game g) {
//         super(g);
//         this.userName = userName;
//         this.c = c;
//         setX(c);
//         setY();
//         setSpeed();
//         setInventory();
//     }

//     // Initialize the Inventory
//     private void setInventory() {
//         orbInventory = new ArrayList<Orb>();
//     }

//     // Adding and removing Orbs
//     public void addOrbToInventory(Orb o) {
//         orbInventory.add(o);
//     }
//     public void removeOrbToInventory(Orb o) {
//         orbInventory.remove(o);
//     }

//     // Setter of the location
//     private void setX(char c) {// starting point for the left or right player
//         x = (c=='R') ? 100: 0; //! the numbers are just examples
//     }
//     private void setY() {// starting point
//         y = 100;//! the numbers are just examples
//     }

//     // Updating location method
//     public void updateCoordinates(int x, int y) {
//         this.x+=x;
//         this.y+=y;
//     }

//     // Setter of the Speed
//     private void setSpeed() {
//         this.speed = 5; //! the number is just an example
//     }

//     // Updating the Speed
//     public void updateSpeed(float speed) {
//         this.speed+=speed;
//     }

//     // Getters for each private attribute
//     public int getX() {
//         return x;
//     }
//     public int getY() {
//         return y;
//     }
//     public char getC() {
//         return c;
//     }
//     public float getSpeed() {
//         return speed;
//     }
//     public String getUserName() {
//         return userName;
//     }
//     public ArrayList<Orb> getInventory() {
//         return orbInventory;
//     }

//     @Override
//     public boolean keyTyped(char character) {
//         // TODO Auto-generated method stub
//         return false;
//     }

//     @Override
//     public boolean scrolled(float amountX, float amountY) {
//         // TODO Auto-generated method stub
//         return false;
//     }

//     @Override
//     public void create() {
//         // TODO Auto-generated method stub
        
//     }

//     @Override
//     public void update(float dt) {
//         // TODO Auto-generated method stub
        
//     }
// }
    
