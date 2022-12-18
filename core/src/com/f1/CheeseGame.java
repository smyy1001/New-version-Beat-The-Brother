package com.f1;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class CheeseGame extends Game{
    public void create(){
        Gdx.graphics.setWindowedMode(1230, 1230);
        CheeseMenu cm = new CheeseMenu(this);
        setScreen(cm);
    }
}
