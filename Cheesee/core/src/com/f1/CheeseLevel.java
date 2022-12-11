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

public class CheeseLevel extends BaseScreen{
    
    private AnimatedActor mousey;
    private AnimatedActor mousey2;
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

        mousey = new AnimatedActor();
        mousey2 = new AnimatedActor();

        brick = new BaseActor();
        cheese = new BaseActor();
        cheese.setTexture(new Texture(Gdx.files.internal("assets/cheese.png")));
        brick.setTexture(new Texture(Gdx.files.internal("assets/brick-11.png")));
        float randX = MathUtils.random(0, viewWidth);
        float randY = MathUtils.random(0, viewHeight);
        cheese.setPosition(randX, randY);
        cheese.setOrigin(cheese.getWidth() / 2, cheese.getHeight() / 2);
        brick.setPosition(300, 300);
        brick.setOrigin(brick.getWidth() / 2, brick.getHeight() / 2);

        mainStage.addActor(cheese);
        mainStage.addActor(brick);


        TextureRegion[] frames = new TextureRegion[4];
        for(int i = 0; i < 4; i++){
            String filename =  "assets/mouse" + i + ".png";
            Texture tex = new Texture(Gdx.files.internal(filename));
            tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
            frames[i] = new TextureRegion(tex);
        }

        TextureRegion[] frames2 = new TextureRegion[4];
        for(int i = 0; i < 4; i++){
            String filename =  "assets/mouse" + i + ".png";
            Texture tex = new Texture(Gdx.files.internal(filename));
            tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
            frames2[i] = new TextureRegion(tex);
        }

        Array<TextureRegion> framesArray = new Array<TextureRegion>(frames);
        Array<TextureRegion> framesArray2 = new Array<TextureRegion>(frames2);
        //How we are basically looping our pictures to be displayed one to another
        Animation<TextureRegion> anim = new Animation<TextureRegion>(0.1f, framesArray, Animation.PlayMode.LOOP_PINGPONG);
        Animation<TextureRegion> anim2 = new Animation<TextureRegion>(0.1f, framesArray2, Animation.PlayMode.LOOP_PINGPONG);
        mousey.setAnimation(anim);
        mousey2.setAnimation(anim2);
        //Origin middle of the mouse
        mousey.setOrigin(mousey.getWidth() / 2, mousey.getHeight() / 2);
        mousey2.setOrigin(mousey2.getWidth() / 2, mousey2.getHeight() / 2);
        //we can use speed variable
        mousey.setPosition(20, 20);
        mousey2.setPosition(500, 20);
        mainStage.addActor(mousey);
        mainStage.addActor(mousey2);

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
        //Acceleration

        Rectangle brickRect = brick.getBoundingRectangle();
        Rectangle mouseyRectangle = mousey.getBoundingRectangle();

        mousey.velocityX = 0;
        mousey.velocityY = 0;
        mousey2.velocityX = 0;
        mousey2.velocityY = 0;

        if(Gdx.input.isKeyPressed(Keys.LEFT)){
            if(!(mousey.getX() < brick.getX() + brick.getWidth() && brick.getY() < mousey.getY() + mousey.getHeight() && mousey.getY() < brick.getY() + brick.getHeight()))
                mousey.velocityX -= (200 + mousey1Speed);
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            if(!(brick.getX() < mousey.getX() + mousey.getWidth() && brick.getY() < mousey.getY() + mousey.getHeight() && mousey.getY() < brick.getY() + brick.getHeight()))
                mousey.velocityX += 200;
        }
        if(Gdx.input.isKeyPressed(Keys.UP)){
            if(!(mousey.getY() + mousey.getHeight() > brick.getY() && mousey.getX() + mousey.getHeight() > brick.getX() && mousey.getX() + mousey.getHeight() < brick.getX() + brick.getWidth()))
                mousey.velocityY += (200);
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)){
            if(!(mousey.getY()  < brick.getY() + brick.getHeight() && mousey.getX() + mousey.getHeight() > brick.getX() && mousey.getX() + mousey.getHeight() < brick.getX() + brick.getWidth()))
                mousey.velocityY -= (200);
        }

        if(Gdx.input.isKeyPressed(Keys.A)){
            mousey2.velocityX -= (150 + mousey2Speed);
        }
        if(Gdx.input.isKeyPressed(Keys.D)){
            mousey2.velocityX += (150 + mousey2Speed);
        }
        if(Gdx.input.isKeyPressed(Keys.W)){
            mousey2.velocityY += (150);
        }
        if(Gdx.input.isKeyPressed(Keys.S)){
            mousey2.velocityY -= (150);
        }
        if(Gdx.input.isKeyPressed(Keys.ENTER)){
            mousey2Speed += 150;
        }

        if(Gdx.input.isKeyPressed(Keys.ESCAPE)){
            game.setScreen(new Settings(game));
        }

        mousey.setX(MathUtils.clamp(mousey.getX(), 0, mapWidth - mousey.getWidth()));
        mousey.setY(MathUtils.clamp(mousey.getY(), 0, mapHeight - mousey.getHeight()));

        mousey2.setX(MathUtils.clamp(mousey2.getX(), 0, mapWidth - mousey2.getWidth()));
        mousey2.setY(MathUtils.clamp(mousey2.getY(), 0, mapHeight - mousey2.getHeight()));

        Rectangle cheeseRectangle = cheese.getBoundingRectangle();
        Rectangle mousey2Rectangle = mousey2.getBoundingRectangle();

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
        cam.position.set(mousey.getX() + mousey.getOriginX(), mousey.getY() + mousey.getOriginY(), 0);
        cam.position.x = MathUtils.clamp(cam.position.x, viewWidth / 2, mapWidth - viewWidth / 2);
        cam.position.y = MathUtils.clamp(cam.position.y, viewHeight / 2, mapHeight - viewHeight / 2);
        cam.update();
    }

    // public boolean keyDown(int keycode){
    //     if(keycode == KEYS.M){
    //         game.setScreen(new CheeseMenu(game));
    //     }
    //     return false;
    // }

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
