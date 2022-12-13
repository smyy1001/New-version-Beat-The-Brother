package com.f1;
public abstract class Orb {
    /**
     * author Sumeyye Acar
     * 08/12/2022
     */

    // Attributes
    int gridX;
    int gridY;

    // Methods
    void setGridX(int x) {
        gridX = x;
    }
    void setGridY(int y) {
        gridY = y;
    }
    void effect(){}//! what will happen when the orb is used
    void toVisual(){} //! how an orb will be presented in the Inventory
}

class bindOrb extends Orb {
    @Override
    void effect() {
        // TODO Auto-generated method stub
        super.effect();
    }
    @Override
    void toVisual() { 
        // TODO Auto-generated method stub
        super.toVisual();
    }
}
class freezeOrb extends Orb {
    @Override
    void effect() {
        // TODO Auto-generated method stub
        super.effect();
    }
    @Override
    void toVisual() {
        // TODO Auto-generated method stub
        super.toVisual();
    }
}
class brickOrb extends Orb {
    @Override
    void effect() {
        // TODO Auto-generated method stub
        super.effect();
    }
    @Override
    void toVisual() {
        // TODO Auto-generated method stub
        super.toVisual();
    }
}
class slowOrb extends Orb {
    @Override
    void effect() {
        // TODO Auto-generated method stub
        super.effect();
    }
    @Override
    void toVisual() {
        // TODO Auto-generated method stub
        super.toVisual();
    }
}
class speedOrb extends Orb {
    @Override
    void effect() {
        // TODO Auto-generated method stub
        super.effect();
    }
    @Override
    void toVisual() {
        // TODO Auto-generated method stub
        super.toVisual();
    }
}
