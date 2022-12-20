package com.f1;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public abstract class Orb extends BaseActor{
    /**
     * author Sumeyye Acar
     * 08/12/2022
     */

    // Attributes
    int gridX;
    int gridY;
    final int SPEED_UP = 100;
    final int SPEED_DOWN = 100;
    char mazeArrayChar;

    // Methods
    void setGridX(int x) {
        gridX = x;
    }

    void setGridY(int y) {
        gridY = y;
    }

    char getChar() {
        return mazeArrayChar;
    }

    /**
     * The abstract method of the orb class. Method should be called when the orb is
     * picked up
     * and it will trigger the effects of the orb on the player.
     */
    abstract void effect(Characters p1, Characters p2);
    abstract void scheduleRemoveEffect(Characters p1, Characters p2);


    public static Orb getRandomOrb() {
        // Alternatively one can map all subclasses to a map automatically, in case of new orbs
        // new ones will be automatically added to the map without maintaining code.
        //  Map<Integer, Class<? extends Orb>> map = new HashMap<>();
        //  map.put(null, null) ....

        Random rand = new Random();
        int choice = rand.nextInt(5);
        switch (choice) {
            case 0: return new BindOrb();
            case 1: return new FreezeOrb();
            case 2: return new BrickOrb();
            case 3: return new SlowOrb();
            case 4: return new SpeedOrb();
            default:
                try {
                    throw new Exception("Invalid orb type during orb generation.");
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }        
        }
    }
}


class BindOrb extends Orb {

    
    public BindOrb() {
        super();
        super.mazeArrayChar = '1';

    }

    public BindOrb(int gridX, int gridY) {
        super();
        super.mazeArrayChar = '1';
        setGridX(gridX);
        setGridY(gridY);
    }

    @Override
    void effect(Characters p1, Characters p2) {
        // TODO Create the bindOrb effects.
    }

    @Override
    void scheduleRemoveEffect(Characters p1, Characters p2) {
        // TODO Auto-generated method stub
        
    }

}

class FreezeOrb extends Orb {

    
    public FreezeOrb() {
        super();
        super.mazeArrayChar = '2';
    }

    public FreezeOrb(int gridX, int gridY) {
        super();
        super.mazeArrayChar = '2';
        setGridX(gridX);
        setGridY(gridY);
    }

    @Override
    void effect(Characters p1, Characters p2) {

    }

    @Override
    void scheduleRemoveEffect(Characters p1, Characters p2) {
        // TODO Auto-generated method stub
        
    }

}

class BrickOrb extends Orb {

    
    public BrickOrb() {
        super();
        super.mazeArrayChar = '3';

    }

    public BrickOrb(int gridX, int gridY) {
        super();
        super.mazeArrayChar = '3';
        setGridX(gridX);
        setGridY(gridY);
    }

    @Override
    void effect(Characters p1, Characters p2) {

    }

    @Override
    void scheduleRemoveEffect(Characters p1, Characters p2) {
        // TODO Auto-generated method stub
        
    }

}

class SlowOrb extends Orb {

    
    public SlowOrb() {
        super();
        super.mazeArrayChar = '4';

    }

    public SlowOrb(int gridX, int gridY) {
        super();
        super.mazeArrayChar = '4';
        setGridX(gridX);
        setGridY(gridY);

    }

    @Override
    void effect(Characters p1, Characters p2) {
        
        if(p2.getSpeed() < SPEED_DOWN){
            p2.updateSpeed(p2.getSpeed() - 1);
        }
        else{
            p2.updateSpeed(-SPEED_DOWN);
        }
        scheduleRemoveEffect(p1, p2);
    }

    @Override
    void scheduleRemoveEffect(Characters p1, Characters p2) {
        
        final Characters P1 = p1;
        Timer timer = new Timer();
        TimerTask speedTask = new TimerTask() {
            @Override
            public void run() {   
                P1.updateSpeed(-SPEED_DOWN);      
            }
        };
        timer.schedule( speedTask, 3000);
        
    }
}

class SpeedOrb extends Orb {

    
    public SpeedOrb() {
        super();
        super.mazeArrayChar = '5';
    }

    public SpeedOrb(int gridX, int gridY) {
        super();
        super.mazeArrayChar = '5';
        setGridX(gridX);
        setGridY(gridY);
    }
    
    @Override
    void effect(Characters p1, Characters p2) {
        if(p1.getSpeed() < 250){
            p1.updateSpeed(SPEED_UP);
        }
        else{
            p1.updateSpeed(5);
        }
        scheduleRemoveEffect(p1, p2);
    }

    @Override
    void scheduleRemoveEffect(Characters p1, Characters p2) {
        
        final Characters P1 = p1;
        Timer timer = new Timer();
        TimerTask speedTask = new TimerTask() {
            @Override
            public void run() {   
                P1.updateSpeed(-SPEED_UP);      
            }
        };
        timer.schedule( speedTask, 3000);
        
    }

}

