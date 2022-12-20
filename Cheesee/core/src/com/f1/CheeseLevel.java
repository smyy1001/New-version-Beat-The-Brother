package com.f1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class CheeseLevel extends BaseScreen {

    CheeseGame game;
    ArrayList<BaseActor> bricks;
    List<BaseActor> orbs = new ArrayList<BaseActor>();
    static Characters player1 = new Characters("Player1");
    static Characters player2 = new Characters("Player2");
    private Button pauseExitButton;
    private Button sound;
    private BaseActor finish;
    private CheckBox soundButton;
    private Button pauseContinueButton;
    private BaseActor blackBackground;
    private Label pauseMainMenu;
    // private float mousey1Speed;
    // private float mousey2Speed;
    private BaseActor brick;
    private BaseActor orb;
    private BaseActor winText;
    private BaseActor cheese;
    private boolean win;
    public static Music backgroundMusic;
    private boolean pausedScreen;
    maze mazeObj;
    private float timeElapsed;
    private Label timeLabel;
    final int mapWidth = 1366;
    final int mapHeight = 758;
    static int currenti=0;
    static int currentj=0;
    static int borderi =0;
    static int borderj =0;

    private float slowDown; // how much the character is going to slow down with slow down orb
    private float speedUp; // how much the character is going to speed up with speed up orb
    
    Sound IUH;

    public CheeseLevel(CheeseGame g) {
        super(g);
        this.game = g;
        bricks = new ArrayList<BaseActor>();
        int mazeHeight = 9;
        int mazeWidth = 8;
        
        slowDown = 80;
        speedUp = 80;

        mazeObj = new maze(mazeHeight,mazeWidth);
        mazeObj.generateMaze();
        borderi = mazeObj.maze.length;
        borderj = mazeObj.maze[0].length;
        renderTextures(mazeObj);  
        IUH = Gdx.audio.newSound(Gdx.files.internal("assets/Minecraft Death Sound Effect.mp3"));      
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

        pausedScreen = false;

        timeElapsed = 0;

        cheese = new BaseActor();
        cheese.setTexture(new Texture(Gdx.files.internal("assets/cheese.png")));
        float randX = MathUtils.random(0, viewWidth);
        float randY = MathUtils.random(0, viewHeight);
        cheese.setPosition(randX, randY);
        cheese.setOrigin(cheese.getWidth() / 2, cheese.getHeight() / 2);

        mainStage.addActor(cheese);

        // The Players
        if (!player1.textureChanged) {
            player1.setTexture(new Texture(Gdx.files.internal("assets/redBall.png")));
        }
        if (!player2.textureChanged) {
            player2.setTexture(new Texture(Gdx.files.internal("assets/blueBall.png")));
        }
        player1.setPosition(20, 20);
        player2.setPosition(1205, 20);
        player1.setSize(35, 35);
        player2.setSize(35, 35);
        mainStage.addActor(player1);
        mainStage.addActor(player2);

        // Finish
        finish = new BaseActor();
        finish.setTexture(new Texture(Gdx.files.internal("assets/finish.png")));
        finish.setSize(90,90);
        finish.setPosition(Gdx.graphics.getWidth()/2 - 50 - finish.getWidth()/2, 700);
        mainStage.addActor(finish);

        // "Win" Text
        winText = new BaseActor();
        winText.setTexture(new Texture(Gdx.files.internal("assets/you-win.png")));
        winText.setPosition(170, 60);
        winText.setVisible(false);
        uiStage.addActor(winText);

        BitmapFont font = new BitmapFont();
        String text = "Time: 0";
        LabelStyle style = new LabelStyle(font, Color.WHITE);
        timeLabel = new Label(text, style);
        timeLabel.setFontScale(2);
        timeLabel.setPosition(850, 740);
        uiStage.addActor(timeLabel);

        win = false;
    }

    @Override
    public void update(float dt) {

        

        // The whole movement part
        Rectangle player1Rectangle = player1.getBoundingRectangle();
        Rectangle player2REctangle = player2.getBoundingRectangle();
        if (!pausedScreen) {

            mainStage.addActor(timeLabel);
            player1.velocityX = 0;
            player1.velocityY = 0;
            player2.velocityX = 0;
            player2.velocityY = 0;

            float previousX= player1.getX();
            float previousY= player1.getY();
            float previousX2= player2.getX();
            float previousY2= player2.getY();
            // int indexX = (int) (player1.getX() / 19.02);
            // int indexY = (int) (player1.getY() / 19.02);

            // First Player
            if(Gdx.input.isKeyPressed(Keys.A)){
                if( isBrick(player1.getX(), player1.getY(), 'L',player1) ){
                    player1.velocityX = 0;//previousX;
                }else{
                    player1.velocityX -= (player1.getSpeed());
                    previousX = player1.velocityX;
                }
            }

            if(Gdx.input.isKeyPressed(Keys.D)){
                if( isBrick(player1.getX(), player1.getY(), 'R',player1) ){
                    player1.velocityX = 0;//previousX;
                }else{
                    player1.velocityX += (player1.getSpeed());
                    previousX = player1.velocityX;
                }
            }

            if(Gdx.input.isKeyPressed(Keys.W)){
                if( isBrick(player1.getX(), player1.getY(), 'U',player1) ){
                    player1.velocityY = 0;//previousY;            
                }else{
                    player1.velocityY += (player1.getSpeed());
                    previousY = player1.velocityY;
                }
            }

            if(Gdx.input.isKeyPressed(Keys.S)){
                if( isBrick(player1.getX(), player1.getY(), 'D',player1) ){
                    player1.velocityY = 0;//previousY;
                }else{
                    player1.velocityY -= (player1.getSpeed());
                    previousY = player1.velocityY;
                }
            }

            // Second Player
            if(Gdx.input.isKeyPressed(Keys.LEFT)){
                if( isBrick(player2.getX(), player2.getY(), 'L',player2) ){
                    player2.velocityX = previousX2;
                    IUH.play();
                }else{
                    player2.velocityX -= (player2.getSpeed());
                    previousX2 = player2.velocityX;
                }
            }

            if(Gdx.input.isKeyPressed(Keys.RIGHT)){
                if( isBrick(player2.getX(), player2.getY(), 'R',player2) ){
                    player2.velocityX = 0;//previousX;
                }else{
                    player2.velocityX += (player2.getSpeed());
                    previousX2 = player2.velocityX;
                }
            }

            if(Gdx.input.isKeyPressed(Keys.UP)){
                if( isBrick(player2.getX(), player2.getY(), 'U',player2) ){
                    player2.velocityY = 0;//previousY;            
                }else{
                    player2.velocityY += (player2.getSpeed());
                    previousY2 = player2.velocityY;
                }
            }

            if(Gdx.input.isKeyPressed(Keys.DOWN)){
                if( isBrick(player2.getX(), player2.getY(), 'D',player2) ){
                    player2.velocityY = previousY2;
                    IUH.play();
                }else{
                    player2.velocityY -= (player2.getSpeed());
                    previousY2 = player2.velocityY;
                }
            }

            // Escape button
            if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
                pausedScreen = true;
                Skin skin = new Skin(Gdx.files.internal("assets/Glassy_UI_Skin/glassyui/glassy-ui.json"));
                blackBackground = new BaseActor();
                blackBackground.setTexture(new Texture(Gdx.files.internal("assets/blackBackground.jpeg")));
                blackBackground.setPosition(0, 0);
                blackBackground.setSize(Gdx.graphics.getWidth() * 4, Gdx.graphics.getHeight() * 4);
                mainStage.addActor(blackBackground);

                pauseExitButton = new TextButton("Exit", skin, "small");
                pauseExitButton.setPosition(300+283, 350);
                pauseExitButton.setSize(200, 60);
                mainStage.addActor(pauseExitButton);

                pauseContinueButton = new TextButton("Continue", skin, "small");
                pauseContinueButton.setPosition(300+283, 450);
                pauseContinueButton.setSize(200, 60);
                mainStage.addActor(pauseContinueButton);

                pauseMainMenu = new Label("Press M to display the Main Menu", skin);
                pauseMainMenu.setPosition(260+283, 200);
                pauseMainMenu.setSize(100, 100);
                mainStage.addActor(pauseMainMenu);

                soundButton = new CheckBox("", skin);
                soundButton.setPosition(480+283, 550);
                soundButton.setSize(50, 50);
                mainStage.addActor(soundButton);

                sound = new TextButton("SOUND OFF", skin, "small");
                sound.setPosition(265+283, 550);
                sound.setSize(180, 60);
                mainStage.addActor(sound);
            }

            // player1.setX(25f);
            // player1.setY(25f);

            // player2.setX(MathUtils.clamp(player2.getX(), 0, mapWidth - player2.getWidth()));
            // player2.setY(25f);


            handleOrbs();

            if (!win) {
                timeElapsed += dt;
                timeLabel.setText("Time: " + (int) timeElapsed);
            }

        }
        // if pause == true
        else {
            timeLabel.remove();
            handlePause();
        }
    }

    /** Checks if orbs are touched and handles their behaviour accordingly. Called on every update(). */
    public void handleOrbs() {


        for (Iterator<BaseActor> iterator = orbs.iterator(); iterator.hasNext(); ) {
            BaseActor orb = iterator.next();

            if (player1.getBoundingRectangle().contains(orb.getBoundingRectangle())) {
                // Pick up and add the orb to inventory, if possible
                System.out.println("Touching orb " + orb);
                ((Orb) orb).effect(player1, player2);
                player1.orbPicked = true;
                // Make the orb disappear with animation
                disappearOrb( (Orb) orb);
                // Remove the orb from the list of orbs to be checked for collision.
                iterator.remove();
                
            }

            if (player2.getBoundingRectangle().contains(orb.getBoundingRectangle())) {
                // Pick up and add the orb to inventory, if possible
                System.out.println("Touching orb " + orb);
                ((Orb) orb).effect(player2, player1);
                player2.orbPicked = true;
                // Make the orb disappear with animation
                disappearOrb( (Orb) orb);
                // Remove the orb from the list of orbs to be checked for collision
                iterator.remove();
            }
        }
        
    
    }

    private void disappearOrb(BaseActor orb) {
        // Disappearing of the orb
        Action spinShrinkFadeOut = Actions.parallel(
                // Transparency volume
                Actions.alpha(1),
                // Rotate by 360 degrees
                Actions.rotateBy(360, 1),
                // Shrink and fade out in 1 second
                Actions.scaleTo(0, 0, 1),
                Actions.fadeOut(1) );
        // Add this action
        orb.addAction(spinShrinkFadeOut);
    }
    
    // a function that slows downs the enemy which is given as a parameter for the function
    public void slowDown(Characters Enemy) {
        Enemy.updateSpeed(Enemy.getSpeed() - slowDown);
    }
    // a function that sp3eds up the character given
    public void speedUp(Characters theCharacterToBeSpeedUpped) {
        theCharacterToBeSpeedUpped.updateSpeed(theCharacterToBeSpeedUpped.getSpeed() + speedUp);
    }
    

    public void handlePause() {
        if (Gdx.input.isKeyPressed(Keys.M)) {
            game.setScreen(new CheeseMenu(game));
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        }
        if (pauseContinueButton.isPressed()) {
            pausedScreen = false;
            pauseExitButton.remove();
            pauseContinueButton.remove();
            pauseMainMenu.remove();
            blackBackground.remove();
            sound.remove();
            soundButton.remove();
        }
        if (pauseExitButton.isPressed()) {
            Gdx.app.exit();
        }
        // about the background music
        if (soundButton.isChecked()) {
            CheeseLevel.backgroundMusic.stop();
            CheeseLevel.backgroundMusic.setLooping(false);
        } else {
            CheeseLevel.backgroundMusic.play();
            CheeseLevel.backgroundMusic.setLooping(true);
        }
    }

    public boolean isBrick(double xBall, double yBall, char direction, Characters player){
        double mapXInd = xBall/19.2;
        double mapYInd = yBall/19.2 ; 
        double playerSize = player.getWidth()/19.2 ;

        if('U' == direction){                                                                                  
            if(mazeObj.mirroredMaze[(int)(mapYInd+playerSize)][(int)(mapXInd+ playerSize/2)] !=  'X' ){
                return false;
            }else{
                return true;
            }

        }else if('D'== direction){

            if(mazeObj.mirroredMaze[(int)mapYInd][(int)(mapXInd+ playerSize/2)] !=  'X' ){
                return false;
            }else{
                return true;
            }

        }else if('R'== direction){

            if(mazeObj.mirroredMaze[(int)(mapYInd+ playerSize/2)][(int)(mapXInd+ playerSize)] !=  'X'){
                return false;
            }else{
                return true;
            }

        }else{
            if(mazeObj.mirroredMaze[(int)(mapYInd+ playerSize/2)][(int)mapXInd] !=  'X' ){
                return false;
            }else{
                return true;
            }
        }
    }

    public void renderTextures(maze mazeObj) {
        // TODO add the textures for start and end
        for (int i = 0; i < mazeObj.mirroredMaze.length; i++) {
            for (int j = 0; j < mazeObj.mirroredMaze[0].length; j++) {
                switch (mazeObj.mirroredMaze[i][j]) {
                    case 'X':
                        brick = generateAndRenderBaseActor(j, i, "assets/Brick_gray.jpeg");
                        bricks.add(brick);
                        currenti = i;
                        currentj = j;
                        break;

                    case '1':
                        orb = new BindOrb(i, j);
                        renderBaseActor(orb, j, i, "assets/Orb_Blind.png");
                        orbs.add(orb);
                        break;
                
                    case '2':
                        orb = new FreezeOrb(i, j);
                        renderBaseActor(orb, j, i, "assets/Orb_Freeze.png");
                        orbs.add(orb);
                        break;
                    
                    case '3':
                        orb = new BrickOrb(i, j);
                        renderBaseActor(orb, j, i, "assets/Orb_Brick.png");
                        orbs.add(orb);
                        break;
                
                    case '4':
                        orb = new SlowOrb(i, j);
                        renderBaseActor(orb, j, i, "assets/Orb_Slow.png");
                        orbs.add(orb);
                        break; 
                    
                    case '5':
                        orb = new SpeedOrb(i, j);
                        renderBaseActor(orb, j, i, "assets/Orb_Speed.png");
                        orbs.add(orb);
                        break;

                    default:
                        break;
                }
            }
        }

    }

    private BaseActor generateAndRenderBaseActor(int gridX, int gridY, String pathToTexture) {
        BaseActor returnObj = new BaseActor();
        returnObj.setTexture(new Texture(Gdx.files.internal(pathToTexture)));
        returnObj.setSize(19.2f, 19.2f);
        returnObj.setPosition(gridX * 19.2f, gridY * 19.2f);
        returnObj.setOrigin(returnObj.getWidth() / 2, returnObj.getHeight() / 2);
        mainStage.addActor(returnObj);
        return returnObj;
    }

    private void renderBaseActor(BaseActor bActor, int gridX, int gridY, String pathToTexture) {
        bActor.setTexture(new Texture(Gdx.files.internal(pathToTexture)));
        bActor.setSize(19.2f, 19.2f);
        bActor.setPosition(gridX * 19.2f, gridY * 19.2f);
        bActor.setOrigin(bActor.getWidth() / 2, bActor.getHeight() / 2);
        mainStage.addActor(bActor);
    }

}
