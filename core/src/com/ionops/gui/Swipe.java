package com.ionops.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Swipe extends Gui {

    final float ANIMATION_FRAME_DURATION = 0.008f;
    TextureAtlas atlas;
    Animation<TextureRegion> animation;
    float animationTime;
    boolean stop;

    public Swipe(Bar bar) {
        super();

        atlas = new TextureAtlas(Gdx.files.internal("swipe.atlas"));
        animation = new Animation<TextureRegion>(ANIMATION_FRAME_DURATION, atlas.findRegions("swipe"));

        rectangle.width = Gdx.graphics.getWidth() * 0.7f;
        rectangle.height = bar.getRectangle().y * 0.35f;
        rectangle.x = (Gdx.graphics.getWidth() / 2f) - (rectangle.width / 2);
        rectangle.y = (bar.getRectangle().y / 2) - (rectangle.height / 2);
    }

    public void render(SpriteBatch batch) {

        if (stop) {
            animationTime += Gdx.graphics.getDeltaTime();
        }

        super.render(batch, animation.getKeyFrame(animationTime, false));
    }

    @Override
    public void dispose() {
        atlas.dispose();
    }

    public void stop() {
        stop = true;
    }

}
