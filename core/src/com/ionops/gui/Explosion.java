package com.ionops.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Explosion extends Gui {

    final float RECTANGLE_SCALE = 3f;
    final float ANIMATION_FRAME_DURATION = 0.11f;
    TextureAtlas atlas;
    Animation<TextureRegion> animation;
    float animationTime;

    public Explosion(Rectangle rectangle) {
        super();
        this.rectangle = new Rectangle(rectangle);

        this.rectangle.width *= RECTANGLE_SCALE;
        this.rectangle.height *= RECTANGLE_SCALE;
        this.rectangle.x -= this.rectangle.width / 2 - rectangle.width / 2;
        this.rectangle.y -= this.rectangle.height / 2 - rectangle.height / 2;

        atlas = new TextureAtlas(Gdx.files.internal("explosion.atlas"));
        animation = new Animation<TextureRegion>(ANIMATION_FRAME_DURATION, atlas.findRegions("explosion"));
    }

    public void render(SpriteBatch batch) {
        animationTime += Gdx.graphics.getDeltaTime();
        super.render(batch, animation.getKeyFrame(animationTime, false));
    }

    @Override
    public void dispose() {
        atlas.dispose();
    }

}
