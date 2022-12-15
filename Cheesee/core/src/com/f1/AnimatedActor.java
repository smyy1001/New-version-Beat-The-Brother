package com.f1;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class AnimatedActor extends BaseActor{
    public float elapsedTime;
    public Animation<TextureRegion> anim;

    public AnimatedActor(){
        super();
        elapsedTime = 0;
        anim = null;
    }

    public void setAnimation(Animation<TextureRegion> a){
        TextureRegion t = (TextureRegion) a.getKeyFrame(elapsedTime);
        setTexture(t.getTexture());
        anim = a;
    }

    public void act(float dt){
        super.act(dt);
        if(velocityX != 0 || velocityY != 0){
            setRotation(MathUtils.atan2(velocityY, velocityX) * MathUtils.radiansToDegrees);
        }
    }

    public void draw(Batch batch, float parentAlpha){
        TextureRegion t2 = (TextureRegion) anim.getKeyFrame(elapsedTime);
        region.setRegion(t2.getTexture());
        super.draw(batch, parentAlpha);
    }
}
