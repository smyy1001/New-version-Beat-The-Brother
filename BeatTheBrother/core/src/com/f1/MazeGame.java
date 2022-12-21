package com.f1;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MazeGame extends Game{
    public void create(){
        Gdx.graphics.setWindowedMode(1366, 768);
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        MainMenu cm = new MainMenu(this);
        setScreen(cm);
        
    }
}