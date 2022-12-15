package com.f1;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class PausedScreen extends BaseScreen {

    // Attributes 
    CheeseGame game;
    Button exitButton;
    Button continueButton;
    Skin skin;
    Label mainMenu;

    PausedScreen(CheeseGame g) {
        super(g);
        game = g;
    }

    @Override
    public boolean keyTyped(char character) { return false; }

    @Override
    public boolean scrolled(float amountX, float amountY) {return false;}

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("assets/Glassy_UI_Skin/glassyui/glassy-ui.json"));

        exitButton = new TextButton("EXIT",skin,"small");
        exitButton.setPosition(350, 300);
        exitButton.setSize(100,60);
        mainStage.addActor(exitButton);

        continueButton = new TextButton("Continue", skin, "small");
        continueButton.setPosition(325, 380);
        continueButton.setSize(150, 60);
        mainStage.addActor(continueButton);

        mainMenu = new Label("Press M to display the Main Menu", skin);
        mainMenu.setPosition(260,200);
        mainMenu.setSize(100, 60);
        mainStage.addActor(mainMenu);
    }

    @Override
    public void update(float dt) {
        if( Gdx.input.isKeyPressed(Keys.M) ) {
            game.setScreen(new CheeseMenu(game));
        }
        if( exitButton.isChecked() ) {
            Gdx.app.exit();
        }
        if( continueButton.isChecked() ) {
            //TODO
        }
    }
    
}
