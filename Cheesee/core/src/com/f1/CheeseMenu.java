package com.f1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class CheeseMenu implements Screen{
    
    // Attributes 
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
    Vector2 tmp = new Vector2();

    public CheeseMenu(CheeseGame game){
        this.game = game;

        // Background
        CheeseLevel.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/backgroundSong.mp3"));
        CheeseLevel.backgroundMusic.setLooping(true);
        CheeseLevel.backgroundMusic.play();

        batch = new SpriteBatch();

        // Textures
        charactersButtonSprite = new Sprite(new Texture(Gdx.files.internal("assets/character.png")));
        startButtonSprite = new Sprite(new Texture(Gdx.files.internal("assets/start.png")));
        exitButtonSprite = new Sprite(new Texture(Gdx.files.internal("assets/exit.png")));
        backgroundSprite = new Sprite(new Texture(Gdx.files.internal("assets/background2.png")));

        //Size our objects into position
        startButtonSprite.setSize(330, 100);
        exitButtonSprite.setSize(330, 100);
        charactersButtonSprite.setSize(330, 110);

        // Positions
        startButtonSprite.setPosition(1366/2 - startButtonSprite.getWidth()/2, 420);
        exitButtonSprite.setPosition(1366/2 - exitButtonSprite.getWidth()/2, 100);
        charactersButtonSprite.setPosition(1366/2 - charactersButtonSprite.getWidth()/2, 260);
        
        //Alpha is setting the transparency
        backgroundSprite.setSize(1366, 768);
        backgroundSprite.setAlpha(0.9f);
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
            tmp.set(Gdx.input.getX(), Gdx.input.getY());
            float touchX = tmp.x;
            float touchY = 768-tmp.y;

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