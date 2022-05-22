package com.ionops.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FadeTransition extends Gui {

    final float ANIMATION_FRAME_DURATION = 0.6f;
    TextureAtlas atlas;
    Animation<TextureRegion> animation;
    float animationTime;
    boolean fadeOut;

    public FadeTransition() {

        rectangle.width = Gdx.graphics.getWidth();
        rectangle.height = Gdx.graphics.getHeight();

        atlas = new TextureAtlas(Gdx.files.internal("fade_transition.atlas"));
        animation = new Animation<TextureRegion>(ANIMATION_FRAME_DURATION, atlas.findRegions("fade_transition"));

        if (!fadeOut) {
            animation.setPlayMode(Animation.PlayMode.REVERSED);
        }

    }

    public void render(SpriteBatch batch, boolean fadeOut) {
        animation.setPlayMode(fadeOut ? Animation.PlayMode.NORMAL : Animation.PlayMode.REVERSED);
        this.fadeOut = fadeOut;
        animationTime++;
        super.render(batch, animation.getKeyFrame(animationTime, false));
    }

    public void dispose() {
        atlas.dispose();
    }

    public boolean isFinished() {
        return animation.isAnimationFinished(animationTime);
    }

    public void resetAnimationTime() {
        animationTime = 0;
    }

}
