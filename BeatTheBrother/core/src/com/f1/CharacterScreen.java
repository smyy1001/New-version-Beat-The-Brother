package com.f1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class CharacterScreen extends BaseScreen {

    // Attributes
    MazeGame game;
    private Skin skin;
    private Label mainMenu;
    private Label player1;
    private Label player2;
    private TextButton startButton;
    private BaseActor red1;
    private BaseActor red2;
    private BaseActor blue1;
    private BaseActor blue2;
    private BaseActor green1;
    private BaseActor green2;
    private BaseActor purple1;
    private BaseActor purple2;
    private CheckBox charChoose;
    private CheckBox charChoose2;
    private CheckBox charChoose3;
    private CheckBox charChoose4;
    private CheckBox charChoose5;
    private CheckBox charChoose6;
    private CheckBox charChoose7;
    private CheckBox charChoose8;

    // constructor
    public CharacterScreen(MazeGame g) {
        super(g);
        this.game = g;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    @Override
    public void create() {
        // The Skin
        skin = new Skin(Gdx.files.internal("assets/Glassy_UI_Skin/glassyui/glassy-ui.json"));

        // Start Button
        startButton = new TextButton("START!", skin, "small");
        startButton.setSize(120, 50);
        startButton.setPosition(340+283, 20);

        // Check-Boxes
        charChoose = new CheckBox("", skin);
        charChoose2 = new CheckBox("", skin);
        charChoose3 = new CheckBox("", skin);
        charChoose4 = new CheckBox("", skin);
        charChoose5 = new CheckBox("", skin);
        charChoose6 = new CheckBox("", skin);
        charChoose7 = new CheckBox("", skin);
        charChoose8 = new CheckBox("", skin);
        charChoose5.setPosition(150+283, 450);
        charChoose6.setPosition(300+283, 450);
        charChoose7.setPosition(450+283, 450);
        charChoose8.setPosition(600+283, 450);
        charChoose.setPosition(150+283, 100);
        charChoose2.setPosition(300+283, 100);
        charChoose3.setPosition(450+283, 100);
        charChoose4.setPosition(600+283, 100);

        // The Characters
        blue1 = new BaseActor();
        blue1.setTexture(new Texture(Gdx.files.internal("assets/blueBall.png")));
        blue1.setPosition(100+283, 140);
        blue1.setSize(120, 120);
        //
        blue2 = new BaseActor();
        blue2.setTexture(new Texture(Gdx.files.internal("assets/blueBall.png")));
        blue2.setPosition(100+283, 490);
        blue2.setSize(120, 120);
        //
        green1 = new BaseActor();
        green1.setTexture(new Texture(Gdx.files.internal("assets/greenBall.png")));
        green1.setPosition(250+283, 140);
        green1.setSize(120, 120);
        //
        green2 = new BaseActor();
        green2.setTexture(new Texture(Gdx.files.internal("assets/greenBall.png")));
        green2.setPosition(250+283, 490);
        green2.setSize(120, 120);
        //
        red1 = new BaseActor();
        red1.setTexture(new Texture(Gdx.files.internal("assets/redBall.png")));
        red1.setPosition(400+283, 140);
        red1.setSize(120, 120);
        //
        red2 = new BaseActor();
        red2.setTexture(new Texture(Gdx.files.internal("assets/redBall.png")));
        red2.setPosition(400+283, 490);
        red2.setSize(120, 120);
        //
        purple1 = new BaseActor();
        purple1.setTexture(new Texture(Gdx.files.internal("assets/purpleBall.png")));
        purple1.setPosition(550+283, 140);
        purple1.setSize(120, 120);
        //
        purple2 = new BaseActor();
        purple2.setTexture(new Texture(Gdx.files.internal("assets/purpleBall.png")));
        purple2.setPosition(550+283, 490);
        purple2.setSize(120, 120);

        // The Headings
        player1 = new Label("Player-1", skin);
        player2 = new Label("Player-2", skin);
        player1.setSize(200, 200);
        player2.setSize(200, 200);
        player1.setColor(com.badlogic.gdx.graphics.Color.WHITE);
        player2.setColor(com.badlogic.gdx.graphics.Color.WHITE);
        player1.setPosition(10+283, 600);
        player2.setPosition(10+283, 250);
        // Main Menu Warning
        mainMenu = new Label("Press M to display the Main Menu", skin);
        mainMenu.setPosition(235+283, 350);
        mainMenu.setColor(com.badlogic.gdx.graphics.Color.GOLDENROD);
        mainMenu.setSize(150, 80);

        // Adding all actors
        mainStage.addActor(charChoose);
        mainStage.addActor(charChoose2);
        mainStage.addActor(charChoose3);
        mainStage.addActor(charChoose4);
        mainStage.addActor(charChoose5);
        mainStage.addActor(charChoose6);
        mainStage.addActor(charChoose7);
        mainStage.addActor(charChoose8);
        mainStage.addActor(player1);
        mainStage.addActor(player2);
        mainStage.addActor(blue1);
        mainStage.addActor(blue2);
        mainStage.addActor(green1);
        mainStage.addActor(green2);
        mainStage.addActor(red1);
        mainStage.addActor(red2);
        mainStage.addActor(purple1);
        mainStage.addActor(purple2);
        mainStage.addActor(startButton);
        mainStage.addActor(mainMenu);
    }

    @Override
    public void update(float dt) {
        CheckBox[] player1 = { charChoose5, charChoose6, charChoose7, charChoose8 };
        CheckBox[] player2 = { charChoose, charChoose2, charChoose3, charChoose4 };
        String[] playerName1 = { "assets/blueBall.png", "assets/greenBall.png", "assets/redBall.png",
                "assets/purpleBall.png" };
        String[] playerName2 = { "assets/blueBall.png", "assets/greenBall.png", "assets/redBall.png",
                "assets/purpleBall.png" };

        if (Gdx.input.isKeyPressed(Keys.M)) {
            game.setScreen(new MainMenu(game));
        }
        if (startButton.isChecked()) {
            int firstPlayer = 0;
            int secondPlayer = 0;
            for (int i = 0; i < 4; i++) {
                if (player1[i].isChecked()) {
                    firstPlayer++;
                }
                if (player2[i].isChecked()) {
                    secondPlayer++;
                }
            }
            for (int a = 0; a < 4; a++) {
                if (firstPlayer == 1 && player1[a].isChecked()) {
                    MazeLevel.player1.updateTexture(playerName1[a]);
                    MazeLevel.player1.updateColor(a);
                }
                if (secondPlayer == 1 && player2[a].isChecked()) {
                    MazeLevel.player2.updateTexture(playerName2[a]);
                    MazeLevel.player2.updateColor(a);
                }
            }
            if (firstPlayer == 1 && secondPlayer == 1) {
                game.setScreen(new LoadingScreen(game));
            }
        }

        // the first player chooses a character
        if (charChoose5.isChecked()) {
            if (!(charChoose.isChecked() || charChoose2.isChecked() || charChoose3.isChecked()
                    || charChoose4.isChecked())) {
                charChoose.setVisible(false);
                charChoose.remove();
                charChoose6.remove();
                charChoose7.remove();
                charChoose8.remove();
                blue1.setVisible(false);
                blue1.remove();
            } else {
                for (int i = 0; i < 4; i++) {
                    if (player2[i].isChecked()) {
                        for (int a = 0; a < 4; a++) {
                            if (a != 0 && a != i) {
                                player1[a].remove();
                            }
                        }
                    }
                }
            }
        } else if (charChoose6.isChecked()) {
            if (!(charChoose.isChecked() || charChoose2.isChecked() || charChoose3.isChecked()
                    || charChoose4.isChecked())) {
                charChoose2.setVisible(false);
                charChoose2.remove();
                charChoose5.remove();
                charChoose7.remove();
                charChoose8.remove();
                green1.setVisible(false);
                green1.remove();
            } else {
                for (int i = 0; i < 4; i++) {
                    if (player2[i].isChecked()) {
                        for (int a = 0; a < 4; a++) {
                            if (a != 1 && a != i) {
                                player1[a].remove();
                            }
                        }
                    }
                }
            }
        }

        else if (charChoose7.isChecked()) {
            if (!(charChoose.isChecked() || charChoose2.isChecked() || charChoose3.isChecked()
                    || charChoose4.isChecked())) {
                charChoose3.setVisible(false);
                charChoose3.remove();
                charChoose5.remove();
                charChoose6.remove();
                charChoose8.remove();
                red1.setVisible(false);
                red1.remove();
            } else {
                for (int i = 0; i < 4; i++) {
                    if (player2[i].isChecked()) {
                        for (int a = 0; a < 4; a++) {
                            if (a != 2 && a != i) {
                                player1[a].remove();
                            }
                        }
                    }
                }
            }
        }

        else if (charChoose8.isChecked()) {
            if (!(charChoose.isChecked() || charChoose2.isChecked() || charChoose3.isChecked()
                    || charChoose4.isChecked())) {
                charChoose4.setVisible(false);
                charChoose4.remove();
                charChoose5.remove();
                charChoose6.remove();
                charChoose7.remove();
                purple1.setVisible(false);
                purple1.remove();
            } else {
                for (int i = 0; i < 4; i++) {
                    if (player2[i].isChecked()) {
                        for (int a = 0; a < 4; a++) {
                            if (a != 3 && a != i) {
                                player1[a].remove();
                            }
                        }
                    }
                }
            }
        }

        // if the second player chooses first
        if (charChoose.isChecked()) {
            if (!(charChoose8.isChecked() || charChoose5.isChecked() || charChoose6.isChecked()
                    || charChoose7.isChecked())) {
                charChoose5.setVisible(false);
                charChoose5.remove();
                charChoose4.remove();
                charChoose2.remove();
                charChoose3.remove();
                blue2.setVisible(false);
                blue2.remove();
            } else {
                for (int i = 0; i < 4; i++) {
                    if (player1[i].isChecked()) {
                        for (int a = 0; a < 4; a++) {
                            if (a != 0 && a != i) {
                                player2[a].remove();
                            }
                        }
                    }
                }
            }
        }

        else if (charChoose2.isChecked()) {
            if (!(charChoose8.isChecked() || charChoose5.isChecked() || charChoose6.isChecked()
                    || charChoose7.isChecked())) {
                charChoose6.setVisible(false);
                charChoose6.remove();
                charChoose4.remove();
                charChoose.remove();
                charChoose3.remove();
                green2.setVisible(false);
                green2.remove();
            } else {
                for (int i = 0; i < 4; i++) {
                    if (player1[i].isChecked()) {
                        for (int a = 0; a < 4; a++) {
                            if (a != 1 && a != i) {
                                player2[a].remove();
                            }
                        }
                    }
                }
            }
        }

        else if (charChoose3.isChecked()) {
            if (!(charChoose8.isChecked() || charChoose5.isChecked() || charChoose6.isChecked()
                    || charChoose7.isChecked())) {
                charChoose7.setVisible(false);
                charChoose7.remove();
                charChoose.remove();
                charChoose4.remove();
                charChoose2.remove();
                red2.setVisible(false);
                red2.remove();
            } else {
                for (int i = 0; i < 4; i++) {
                    if (player1[i].isChecked()) {
                        for (int a = 0; a < 4; a++) {
                            if (a != 2 && a != i) {
                                player2[a].remove();
                            }
                        }
                    }
                }
            }
        }

        else if (charChoose4.isChecked()) {
            if (!(charChoose8.isChecked() || charChoose5.isChecked() || charChoose6.isChecked()
                    || charChoose7.isChecked())) {
                charChoose8.setVisible(false);
                charChoose8.remove();
                charChoose.remove();
                charChoose2.remove();
                charChoose3.remove();
                purple2.setVisible(false);
                purple2.remove();
            } else {
                for (int i = 0; i < 4; i++) {
                    if (player1[i].isChecked()) {
                        for (int a = 0; a < 4; a++) {
                            if (a != 3 && a != i) {
                                player2[a].remove();
                            }
                        }
                    }
                }
            }
        }

        // if the first user changes his/her mind
        if (!charChoose5.isChecked() && !charChoose.isVisible() && !blue1.isVisible()) {
            charChoose.setVisible(true);
            blue1.setVisible(true);
            mainStage.addActor(charChoose);
            mainStage.addActor(charChoose6);
            mainStage.addActor(charChoose7);
            mainStage.addActor(charChoose8);
            mainStage.addActor(blue1);
        } else if (!charChoose6.isChecked() && !charChoose2.isVisible() && !green1.isVisible()) {
            charChoose2.setVisible(true);
            green1.setVisible(true);
            mainStage.addActor(charChoose2);
            mainStage.addActor(charChoose5);
            mainStage.addActor(charChoose7);
            mainStage.addActor(charChoose8);
            mainStage.addActor(green1);
        } else if (!charChoose7.isChecked() && !charChoose3.isVisible() && !red1.isVisible()) {
            charChoose3.setVisible(true);
            red1.setVisible(true);
            mainStage.addActor(charChoose3);
            mainStage.addActor(charChoose6);
            mainStage.addActor(charChoose5);
            mainStage.addActor(charChoose8);
            mainStage.addActor(red1);
        } else if (!charChoose8.isChecked() && !charChoose4.isVisible() && !purple1.isVisible()) {
            charChoose4.setVisible(true);
            purple1.setVisible(true);
            mainStage.addActor(charChoose4);
            mainStage.addActor(charChoose6);
            mainStage.addActor(charChoose5);
            mainStage.addActor(charChoose7);
            mainStage.addActor(purple1);
        }

        // if the second player changes his/her mind
        if (!charChoose.isChecked() && !charChoose5.isVisible() && !blue2.isVisible()) {
            charChoose5.setVisible(true);
            blue2.setVisible(true);
            mainStage.addActor(charChoose5);
            mainStage.addActor(charChoose2);
            mainStage.addActor(charChoose3);
            mainStage.addActor(charChoose4);
            mainStage.addActor(blue2);
        } else if (!charChoose2.isChecked() && !charChoose6.isVisible() && !green2.isVisible()) {
            charChoose6.setVisible(true);
            green2.setVisible(true);
            mainStage.addActor(charChoose6);
            mainStage.addActor(charChoose);
            mainStage.addActor(charChoose3);
            mainStage.addActor(charChoose4);
            mainStage.addActor(green2);
        } else if (!charChoose3.isChecked() && !charChoose7.isVisible() && !red2.isVisible()) {
            charChoose7.setVisible(true);
            red2.setVisible(true);
            mainStage.addActor(charChoose7);
            mainStage.addActor(charChoose2);
            mainStage.addActor(charChoose);
            mainStage.addActor(charChoose4);
            mainStage.addActor(red2);
        } else if (!charChoose4.isChecked() && !charChoose8.isVisible() && !purple2.isVisible()) {
            charChoose8.setVisible(true);
            purple2.setVisible(true);
            mainStage.addActor(charChoose8);
            mainStage.addActor(charChoose2);
            mainStage.addActor(charChoose);
            mainStage.addActor(charChoose3);
            mainStage.addActor(purple2);
        }
    }
}