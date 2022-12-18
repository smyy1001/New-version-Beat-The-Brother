package com.f1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class Settings extends BaseScreen{
    
    private BaseActor background;
    private CheckBox soundButton;
    private CheckBox fullScreenButton;
    Button button1;
    Button button2;
    Skin textButtonStyle;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;
    
    CheeseGame game;

    //Input coordinations
    Vector3 tmp = new Vector3();

    public Settings (Game g){
        super(g);
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("assets/Glassy_UI_Skin/glassyui/glassy-ui.json"));

        button1 = new TextButton("Sound",skin,"small");

        soundButton = new CheckBox("", skin);

        button1.setSize(120,60);
        button1.setPosition(150,300);
        soundButton.setSize(50, 50);
        soundButton.setPosition(450, 300);

        background = new BaseActor();
        background.setTexture(new Texture(Gdx.files.internal("assets/blackBackground.jpeg")));
        background.setPosition(0, 0);
        mainStage.addActor(button1);
        mainStage.addActor(soundButton);
    }

    @Override
    public void update(float dt) {
        if(soundButton.isChecked()){
            CheeseLevel.backgroundMusic.stop();
        }
        else{
            CheeseLevel.backgroundMusic.play();
            CheeseLevel.backgroundMusic.setLooping(true);
        }
    }
}
