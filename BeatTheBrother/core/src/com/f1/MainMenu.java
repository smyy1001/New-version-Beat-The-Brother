package com.f1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MainMenu implements Screen{
    
    // Attributes 
    SpriteBatch batch;
    Sprite startButtonSprite;
    Rectangle startRect;
    Sprite exitButtonSprite;
    Rectangle exitRect;
    Sprite charactersButtonSprite;
    Rectangle characterRect;
    Sprite backgroundSprite;
    MazeGame game;
    Label label1;
    Label label2;
    BaseActor background;
    BaseActor scoreLabel;
    float timer;
    static boolean musicBoolean = true;

    //Input coordinations
    Vector2 tmp = new Vector2();

    public MainMenu(MazeGame game){
        this.game = game;

        timer =0;

        MazeLevel.player1Score = 0;
        MazeLevel.player2Score = 0;

        if(musicBoolean) {
            // Background
            MazeLevel.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/backgroundSong.mp3"));
            MazeLevel.backgroundMusic.setLooping(true);
            MazeLevel.backgroundMusic.play();
            musicBoolean =false;
        }

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
        startButtonSprite.setPosition(Gdx.graphics.getWidth()/2 - startButtonSprite.getWidth()/2, 420);
        exitButtonSprite.setPosition(Gdx.graphics.getWidth()/2 - exitButtonSprite.getWidth()/2, 100);
        charactersButtonSprite.setPosition(Gdx.graphics.getWidth()/2 - charactersButtonSprite.getWidth()/2, 260);
        
        //Alpha is setting the transparency
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backgroundSprite.setAlpha(0.9f);

        // the Loading Screen
        background = new BaseActor();
        background.setTexture(new Texture(Gdx.files.internal("assets/blackBackground.jpeg")));
        background.setSize(1500, 1000);
        background.setPosition(0, 0);
        scoreLabel = new BaseActor();
        scoreLabel.setTexture(new Texture(Gdx.files.internal("assets/SCORE.png")));
        scoreLabel.setSize(250, 80);
        scoreLabel.setPosition(500, 400);
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
            float touchY = Gdx.graphics.getHeight() - 1 - tmp.y;
            startRect = startButtonSprite.getBoundingRectangle();
            characterRect = charactersButtonSprite.getBoundingRectangle();
            exitRect = exitButtonSprite.getBoundingRectangle();

            if(exitRect.contains(touchX,touchY)) {
                Gdx.app.exit();
            }
            else if(startRect.contains(touchX, touchY)) {
                game.setScreen(new LoadingScreen(game));
            }
            else if(characterRect.contains(touchX, touchY)){
                game.setScreen(new CharacterScreen(game));
            }
        }
    }
}