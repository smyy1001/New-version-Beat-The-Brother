package com.f1;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.compression.lzma.Base;

public class CheeseLevel extends BaseScreen{
    
    private BaseActor ball1;
    private BaseActor ball2;
    private float mousey1Speed;
    private float mousey2Speed;
    private BaseActor brick;
    private BaseActor floor;
    private BaseActor winText;
    private BaseActor cheese;
    private boolean win;

    public static Music backgroundMusic;

    private float timeElipsed;
    private Label timeLabel;
    final int mapWidth = 800;
    final int mapHeight = 800;

    public CheeseLevel(Game g){
        super(g);
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean scrolled(float amountX, float amountY) {
        
        return false;
    }

    @Override
    public void create() {

        timeElipsed = 0;

        floor = new BaseActor();
        floor.setTexture(new Texture(Gdx.files.internal("assets/tiles-800-800.jpg")));
        floor.setPosition(0, 0);
        mainStage.addActor(floor);

        // mousey1Speed = 75;
        // mousey2Speed = 50;

        ball1 = new BaseActor();
        ball2 = new BaseActor();

        brick = new BaseActor();
        cheese = new BaseActor();
        ball1.setTexture(new Texture(Gdx.files.internal("assets/blueBall.png")));
        ball2.setTexture(new Texture(Gdx.files.internal("assets/blueBall.png")));
        cheese.setTexture(new Texture(Gdx.files.internal("assets/cheese.png")));
        brick.setTexture(new Texture(Gdx.files.internal("assets/Brick_gray.jpeg")));
        float randX = MathUtils.random(0, viewWidth);
        float randY = MathUtils.random(0, viewHeight);
        cheese.setPosition(randX, randY);
        cheese.setOrigin(cheese.getWidth() / 2, cheese.getHeight() / 2);
        brick.setPosition(300, 300);
        brick.setOrigin(brick.getWidth() / 2, brick.getHeight() / 2);

        mainStage.addActor(cheese);
        mainStage.addActor(brick);

        ball1.setOrigin(ball1.getWidth() / 2, ball1.getHeight() / 2);
        ball2.setOrigin(ball2.getWidth() / 2, ball2.getHeight() / 2);
        //we can use speed variable
        ball1.setPosition(20, 20);
        ball1.setSize(80, 80);
        ball2.setPosition(500, 20);
        ball2.setSize(80, 80);
        brick.setSize(100, 100);
        mainStage.addActor(ball1);
        mainStage.addActor(ball2);

        winText = new BaseActor();
        winText.setTexture(new Texture(Gdx.files.internal("assets/you-win.png")));
        winText.setPosition(170, 60);
        winText.setVisible(false);
        uiStage.addActor(winText);

        BitmapFont font = new BitmapFont();
        String text = "Time: 0";
        LabelStyle style = new LabelStyle(font, Color.NAVY);
        timeLabel = new Label(text, style);
        timeLabel.setFontScale(2);
        timeLabel.setPosition(500, 440);
        uiStage.addActor(timeLabel);

        win = false;
    }

    @Override
    public void update(float dt) {

        Rectangle brickRect = brick.getBoundingRectangle();
        Rectangle mouseyRectangle = ball1.getBoundingRectangle();

        ball1.velocityX = 0;
        ball1.velocityY = 0;
        ball2.velocityX = 0;
        ball2.velocityY = 0;
        if(Gdx.input.isKeyPressed(Keys.LEFT)){
            if(!((ball1.getX() < brick.getX() + brick.getWidth()) && (brick.getY() < ball1.getY() + ball1.getHeight()) && (ball1.getY() < brick.getY() + brick.getHeight()) && !(ball1.getX() + ball1.getWidth() < brick.getX())))
                ball1.velocityX -= (200);
            else {
                ball1.setPosition(ball1.getX() + 20, ball1.getY());
            }
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            if(!(brick.getX() < ball1.getX() + ball1.getWidth() && brick.getY() < ball1.getY() + ball1.getHeight() && ball1.getY() < brick.getY() + brick.getHeight() &&!(ball1.getX() > brick.getX() + brick.getWidth())))
                ball1.velocityX += 200;
            else {
                ball1.setPosition(ball1.getX() - 20, ball1.getY());
            }
        }
        if(Gdx.input.isKeyPressed(Keys.UP)){
            if(!(ball1.getY() + ball1.getHeight() >= brick.getY() &&!(ball1.getY() > brick.getY() + brick.getHeight())&& ball1.getX() + ball1.getHeight() > brick.getX() && ball1.getX() + ball1.getHeight() < brick.getX() + brick.getWidth()))
                ball1.velocityY += (200);
            else {
                ball1.setPosition(ball1.getX(), ball1.getY() - 20);
            }
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)){
            if(!(ball1.getY()  < brick.getY() + brick.getHeight() &&!(ball1.getY() + ball1.getHeight() < brick.getY() )&& ball1.getX() + ball1.getHeight() > brick.getX() && ball1.getX() + ball1.getHeight() < brick.getX() + brick.getWidth()))
                ball1.velocityY -= (200);
            else {
                ball1.setPosition(ball1.getX(), ball1.getY() + 20);
            }
        }

        if(Gdx.input.isKeyPressed(Keys.A)){
            ball2.velocityX -= (150 + mousey2Speed);
        }
        if(Gdx.input.isKeyPressed(Keys.D)){
            ball2.velocityX += (150 + mousey2Speed);
        }
        if(Gdx.input.isKeyPressed(Keys.W)){
            ball2.velocityY += (150);
        }
        if(Gdx.input.isKeyPressed(Keys.S)){
            ball2.velocityY -= (150);
        }
        if(Gdx.input.isKeyPressed(Keys.ENTER)){
            mousey2Speed += 150;
        }

        if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
            game.setScreen(new Settings(game));
        }

        ball1.setX(MathUtils.clamp(ball1.getX(), 0, mapWidth - ball1.getWidth()));
        ball1.setY(MathUtils.clamp(ball1.getY(), 0, mapHeight - ball1.getHeight()));

        ball2.setX(MathUtils.clamp(ball2.getX(), 0, mapWidth - ball2.getWidth()));
        ball2.setY(MathUtils.clamp(ball2.getY(), 0, mapHeight - ball2.getHeight()));

        Rectangle cheeseRectangle = cheese.getBoundingRectangle();
        Rectangle mousey2Rectangle = ball2.getBoundingRectangle();

        //If 2 rectangle are in the same location
        if(!win && (cheeseRectangle.contains(mouseyRectangle) || cheeseRectangle.contains(mousey2Rectangle))){
            mousey1Speed += 100;
            mousey2Speed += 50;
            win = true;
            //Disappearing of the cheese
            Action spinShrinkFadeOut = Actions.parallel(
                //Transparency volume
                Actions.alpha(1),
                //360 degree
                Actions.rotateBy(360, 1),
                //0 for 2 seconds
                Actions.scaleTo(0, 0, 2),
                Actions.fadeOut(1)
            );
            //Add this action
            cheese.addAction(spinShrinkFadeOut);
            Action fadeInColorCycleForever = Actions.sequence(
                Actions.alpha(0),
                //Visible
                Actions.show(),
                //Duration of fadeIn
                Actions.fadeIn(2),
                Actions.forever(
                    Actions.sequence(
                        //Transitions from the color at the time this action starts to the specified color.
                        Actions.color(new Color(1, 0, 0, 1), 1),
                        Actions.color(new Color(0,0, 1, 1), 1)
                    )
                )
            );
            winText.addAction(fadeInColorCycleForever);
            // winText.setVisible(true);
        }
        if(!win){
            timeElipsed += dt;
            timeLabel.setText("Time: " + (int)timeElipsed);
        }
        Camera cam = mainStage.getCamera();
        cam.position.set(ball1.getX() + ball1.getOriginX(), ball1.getY() + ball1.getOriginY(), 0);
        cam.position.x = MathUtils.clamp(cam.position.x, viewWidth / 2, mapWidth - viewWidth / 2);
        cam.position.y = MathUtils.clamp(cam.position.y, viewHeight / 2, mapHeight - viewHeight / 2);
        cam.update();
    }

    private void pauseGame(){

    }

    // public boolean isMoveable(){
    //     if(mousey.getX() == brick.getX() - brick.getHeight() || ){
    //         return false;
    //     }
    //     else if(mousey.getY() == brick.getY() - brick.getHeight() || ){

    //     }
    //     else{

    //     }
    // }

    // public void move(){
    //     if(isMoveable()){

    //     }
    // }
}
