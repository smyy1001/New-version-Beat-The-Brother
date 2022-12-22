package com.f1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LoadingScreen extends BaseScreen{

    // Attributes
    BaseActor background;
    BaseActor scoreLabel;
    BaseActor score1;
    BaseActor score2;
    BaseActor dash;
    BaseActor loading;
    MazeGame game;
    Skin skin;
    Label label1;
    //Input coordinations
    Vector2 tmp = new Vector2();

    // constructor
    public LoadingScreen(MazeGame g) {
        super(g);
        this.game = g;
    }

    @Override
    public boolean keyTyped(char character) {return false;}

    @Override
    public boolean scrolled(float amountX, float amountY) {return false;}

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("assets/Glassy_UI_Skin/glassyui/glassy-ui.json"));

        background = new BaseActor();
        background.setTexture(new Texture(Gdx.files.internal("assets/blackBackground.jpeg")));
        background.setSize(1500, 1000);
        background.setPosition(0, 0);
        mainStage.addActor(background);

        loading = new BaseActor();
        loading.setTexture(new Texture(Gdx.files.internal("assets/loading.png")));
        loading.setSize(400, 80);
        loading.setPosition(Gdx.graphics.getWidth()/2-loading.getWidth()/2, 180);

        dash = new BaseActor();
        dash.setTexture(new Texture(Gdx.files.internal("assets/dash.jpg")));
        dash.setSize(100,50);
        dash.setPosition(610, 410);

        scoreLabel = new BaseActor();
        scoreLabel.setTexture(new Texture(Gdx.files.internal("assets/SCORE.png")));
        scoreLabel.setSize(250, 80);
        scoreLabel.setPosition(Gdx.graphics.getWidth()/2 - scoreLabel.getWidth()/2 - 20, 600);
        mainStage.addActor(scoreLabel);

        label1 = new Label("Click Anywhere To Start!", skin);
        label1.setColor(com.badlogic.gdx.graphics.Color.GOLDENROD);
        label1.setPosition(Gdx.graphics.getWidth()/2 - label1.getWidth()/2, 50);
        mainStage.addActor(label1);

        score1 = new BaseActor();
        if(MazeLevel.player1Score == 0) {
            score1.setTexture(new Texture(Gdx.files.internal("assets/number0.png")));
        }
        else if(MazeLevel.player1Score == 1) {
            score1.setTexture(new Texture(Gdx.files.internal("assets/number1.png")));
        }
        else{
            score1.setTexture(new Texture(Gdx.files.internal("assets/number2.png")));
        }
        score1.setSize(200,200);
        score1.setPosition(400, 335);


        score2 = new BaseActor();
        if(MazeLevel.player2Score == 0) {
            score2.setTexture(new Texture(Gdx.files.internal("assets/number0.png")));
        }
        else if(MazeLevel.player2Score == 1) {
            score2.setTexture(new Texture(Gdx.files.internal("assets/number1.png")));
        }
        else{
            score2.setTexture(new Texture(Gdx.files.internal("assets/number2.png")));
        }
        score2.setSize(200,200);
        score2.setPosition(720, 335);

        mainStage.addActor(score1);
        mainStage.addActor(score2);
        mainStage.addActor(dash);
        mainStage.addActor(loading);
    }

    @Override
    public void update(float dt) {
        Rectangle rect = new Rectangle(0, 0, 1500,1000);
        if(Gdx.input.justTouched()){
            tmp.set(Gdx.input.getX(), Gdx.input.getY());
            float touchX = tmp.x;
            float touchY = Gdx.graphics.getHeight() - 1 - tmp.y;
            if(rect.contains(touchX,touchY)) {
                game.setScreen(new MazeLevel(game));
            }
        }
        if (Gdx.input.isKeyPressed(Keys.M)) {
            game.setScreen(new MainMenu(game));
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        }
    }
}