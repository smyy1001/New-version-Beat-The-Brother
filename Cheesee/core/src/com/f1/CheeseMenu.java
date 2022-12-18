package com.f1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class CheeseMenu implements Screen{
    SpriteBatch batch;
    Sprite startButtonSprite;
    Rectangle startRect;
    Sprite exitButtonSprite;
    Rectangle exitRect;
    Sprite charactersButtonSprite;
    Rectangle characterRect;
    Sprite backgroundSprite;
    CheeseGame game;

    //Input coordinations
    Vector3 tmp = new Vector3();

    public CheeseMenu(CheeseGame game){
        this.game = game;

        // Background
        CheeseLevel.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/backgroundSong.mp3"));
        CheeseLevel.backgroundMusic.setLooping(true);
        CheeseLevel.backgroundMusic.play();

        batch = new SpriteBatch();

        // Textures
        charactersButtonSprite = new Sprite(new Texture(Gdx.files.internal("assets/charactersButton.png")));
        startButtonSprite = new Sprite(new Texture(Gdx.files.internal("assets/startButton.png")));
        exitButtonSprite = new Sprite(new Texture(Gdx.files.internal("assets/exit.png")));
        backgroundSprite = new Sprite(new Texture(Gdx.files.internal("assets/background.png")));

        //Size our objects into position
        startButtonSprite.setSize(225, 100);
        exitButtonSprite.setSize(100, 100);
        charactersButtonSprite.setSize(300, 80);

        // Positions
        startButtonSprite.setPosition(1366/2 - startButtonSprite.getWidth()/2, 450);
        exitButtonSprite.setPosition(1366/2 - exitButtonSprite.getWidth()/2, 150);
        charactersButtonSprite.setPosition(1366/2 - charactersButtonSprite.getWidth()/2, 300);
        
        //Alpha is setting the transparency
        backgroundSprite.setSize(1366, 768);
        backgroundSprite.setAlpha(0.2f);
    }

    // Overridden methods
    @Override
    public void show() {}
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {
        batch.dispose();
    }


    // Drawing the Sprites
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        backgroundSprite.draw(batch);
        startButtonSprite.draw(batch);
        charactersButtonSprite.draw(batch);
        exitButtonSprite.draw(batch);
        batch.end();

        handleTouch();
    }

    // Handling interactions on the screen
    void handleTouch(){
        if(Gdx.input.justTouched()){
            tmp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            float touchX = tmp.x;
            float touchY = tmp.y;

            startRect = startButtonSprite.getBoundingRectangle();
            characterRect = charactersButtonSprite.getBoundingRectangle();
            exitRect = exitButtonSprite.getBoundingRectangle();

            if(exitRect.contains(touchX,touchY)) {
                Gdx.app.exit();
            }
            else if(startRect.contains(touchX, touchY)) {
                game.setScreen(new CheeseLevel(game));
            }
            else if(characterRect.contains(touchX, touchY)){
                game.setScreen(new CharacterScreen(game));
            }
        }
    }
}