package com.f1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;


public class CheeseMenu implements Screen{
    SpriteBatch batch;
    OrthographicCamera camera;

    Texture startButtonTexture;
    Texture exitButtonTexture;
    Texture settingsButtonTexture;
    Texture backgroundTexture;
    Texture charactersButtonTexture;
    Sprite startButtonSprite;
    Sprite exitButtonSprite;
    Sprite charactersButtonSprite;
    Sprite settingsButtonSprite;
    Sprite backgroundSprite;

    private static float BUTTON_RESIZE_FACTOR = 800f;
    private static float START_VERT_POSITION_FACTOR = 2.2f;
    private static float SETTINGS_VERT_POSITION_FACTOR = 2.8f;
    private static float CHARACTERS_VERT_POSITION_FACTOR = 3.7f;
    private static float EXIT_VERT_POSITION_FACTOR = 6.5f;

    CheeseGame game;

    //Input coordinations
    Vector3 tmp = new Vector3();

    public CheeseMenu(CheeseGame game){

        CheeseLevel.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/backgroundSong.mp3"));
        CheeseLevel.backgroundMusic.setLooping(true);
        CheeseLevel.backgroundMusic.play();

        this.game = game;

        //Dimensions of the viewPoints
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();

        camera = new OrthographicCamera(width, height);

        camera.setToOrtho(false);

        batch = new SpriteBatch();
        startButtonTexture = new Texture(Gdx.files.internal("assets/startButton.png"));
        exitButtonTexture = new Texture(Gdx.files.internal("assets/exit.png"));
        charactersButtonTexture = new Texture(Gdx.files.internal("assets/charactersButton.png"));
        settingsButtonTexture = new Texture(Gdx.files.internal("assets/settings.png"));
        backgroundTexture = new Texture(Gdx.files.internal("assets/background.png"));

        charactersButtonSprite = new Sprite(charactersButtonTexture);
        startButtonSprite = new Sprite(startButtonTexture);
        exitButtonSprite = new Sprite(exitButtonTexture);
        settingsButtonSprite = new Sprite(settingsButtonTexture);
        backgroundSprite = new Sprite(backgroundTexture);

        //Size our objects into position

        startButtonSprite.setSize(startButtonSprite.getWidth() * (width / BUTTON_RESIZE_FACTOR) / 6, startButtonSprite.getHeight() * (width / BUTTON_RESIZE_FACTOR) / 6);
        exitButtonSprite.setSize(exitButtonSprite.getWidth() * (width / BUTTON_RESIZE_FACTOR) / 6, exitButtonSprite.getHeight() * (width / BUTTON_RESIZE_FACTOR) / 6);
        settingsButtonSprite.setSize(settingsButtonSprite.getWidth() * (width / BUTTON_RESIZE_FACTOR) / 6, settingsButtonSprite.getHeight() * (width / BUTTON_RESIZE_FACTOR) / 6);
        charactersButtonSprite.setSize(charactersButtonSprite.getWidth() * (width / BUTTON_RESIZE_FACTOR) / 3, charactersButtonSprite.getHeight() * (width / BUTTON_RESIZE_FACTOR) / 3);

        startButtonSprite.setPosition((width / 2f - startButtonSprite.getWidth() / 2), width / START_VERT_POSITION_FACTOR);
        exitButtonSprite.setPosition((width / 2f - exitButtonSprite.getWidth() / 2), width / EXIT_VERT_POSITION_FACTOR);
        settingsButtonSprite.setPosition((width / 2f - settingsButtonSprite.getWidth() / 2), width / SETTINGS_VERT_POSITION_FACTOR);
        charactersButtonSprite.setPosition((width / 2f - charactersButtonSprite.getWidth() / 2), width / CHARACTERS_VERT_POSITION_FACTOR);
        //Alpha is setting the transparency
        backgroundSprite.setSize(width, height);
        backgroundSprite.setAlpha(0.2f);
    }


    @Override
    public void show() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        backgroundSprite.draw(batch);
        startButtonSprite.draw(batch);
        settingsButtonSprite.draw(batch);
        exitButtonSprite.draw(batch);
        charactersButtonSprite.draw(batch);
        batch.end();

        handleTouch();
    }


    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void pause() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void resume() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void hide() {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void dispose() {
        startButtonTexture.dispose();
        exitButtonTexture.dispose();
        settingsButtonTexture.dispose();
        charactersButtonTexture.dispose();
        batch.dispose();
    }

    void handleTouch(){
        if(Gdx.input.justTouched()){
            tmp.set(Gdx.input.getX(), Gdx.input.getY(), 0);

            camera.unproject(tmp);
            float touchX = tmp.x;
            float touchY = tmp.y;

            Rectangle startRect = startButtonSprite.getBoundingRectangle();
            Rectangle exitRect = exitButtonSprite.getBoundingRectangle();
            Rectangle setRect = settingsButtonSprite.getBoundingRectangle();
            if(startRect.contains(touchX, touchY)){
                game.setScreen(new CheeseLevel(game));
            }
            else if(exitRect.contains(touchX, touchY)){
                Gdx.app.exit();
            }
            else if(setRect.contains(touchX, touchY)){
                game.setScreen(new Settings(game));
            }
            else if(touchX >= charactersButtonSprite.getX() && touchX <= charactersButtonSprite.getX() + charactersButtonSprite.getWidth() && touchY >= charactersButtonSprite.getY() && touchY <= charactersButtonSprite.getY() + charactersButtonSprite.getHeight()){
          
            }
        }
        // if (Gdx.input.isKeyPressed(Keys.TAB)){
        //     Boolean fullScreen = Gdx.graphics.isFullscreen();
        //         Graphics.DisplayMode currentMode = Gdx.graphics.getDisplayMode();
        //         if (fullScreen == true)
        //             Gdx.graphics.setWindowedMode(currentMode.width, currentMode.height);
        //         else
        //             Gdx.graphics.setFullscreenMode(currentMode);
        // }
    }
}
