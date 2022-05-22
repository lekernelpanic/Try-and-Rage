package com.ionops.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Stars extends Gui {

    final int STARS = 100;
    final float SHOOTING_STAR_SPEED = 1.8f;
    final float SHOOTING_STAR_ANIMATION_SPEED = 0.01f;
    final float SHOOTING_STAR_TIME_LAPSE = 16;
    ArrayList<Rectangle> starsRectangles;
    ArrayList<Animation<TextureRegion>> starsAnimation;
    TextureAtlas atlas;
    Animation<TextureRegion> shootingStarAnimation;
    Rectangle shootingStarRectangle;
    Random random;
    float starsAnimationTime, shootingStarAnimationTime;

    public Stars() {
        rectangle.x = 0;
        rectangle.y = 0;
        rectangle.width = Gdx.graphics.getWidth();
        rectangle.height = Gdx.graphics.getHeight();

        starsRectangles = new ArrayList<>();
        starsAnimation = new ArrayList<>();
        atlas = new TextureAtlas(Gdx.files.internal("star.atlas"));
        random = new Random();

        // shooting star
        shootingStarAnimation = new Animation<TextureRegion>(SHOOTING_STAR_ANIMATION_SPEED, atlas.findRegions("star"));
        shootingStarAnimation.setPlayMode(PlayMode.REVERSED);
        shootingStarRectangle = new Rectangle();
        shootingStarRectangle.width = Gdx.graphics.getWidth() / 200f;
        shootingStarRectangle.height = shootingStarRectangle.width * 20;

        for (int i = 0; i < STARS; i++) { // Creating stars

            starsAnimation.add(new Animation<TextureRegion>((float) (4 + random.nextInt(8)) / 100, atlas.findRegions("star")));
            starsAnimation.get(i).setPlayMode(PlayMode.LOOP_PINGPONG);

            starsRectangles.add(new Rectangle(40, 40, Gdx.graphics.getWidth() / 60f, Gdx.graphics.getWidth() / 60f));
            starsRectangles.get(i).x = rectangle.x + random.nextInt((int) rectangle.width);
            starsRectangles.get(i).y = rectangle.y + random.nextInt((int) rectangle.height);

            starsRectangles.get(i).width = Gdx.graphics.getWidth() / 200f;
            starsRectangles.get(i).height = Gdx.graphics.getWidth() / 200f;

        }
    }

    public void render(SpriteBatch batch) {

        starsAnimationTime += Gdx.graphics.getDeltaTime();
        shootingStarAnimationTime += Gdx.graphics.getDeltaTime();

        if (shootingStarAnimationTime <= SHOOTING_STAR_TIME_LAPSE) {
            shootingStarRectangle.y -= SHOOTING_STAR_SPEED * Gdx.graphics.getHeight() * Gdx.graphics.getDeltaTime();
        } else {
            shootingStarAnimationTime = 0;
            shootingStarRectangle.x = random.nextInt(Gdx.graphics.getWidth());
            shootingStarRectangle.y = Gdx.graphics.getHeight() / 2f + random.nextInt(Gdx.graphics.getHeight() / 2);
        }

        // Stars
        for (int i = 0; i < STARS; i++) {
            batch.draw(starsAnimation.get(i).getKeyFrame(starsAnimationTime), starsRectangles.get(i).x, starsRectangles.get(i).y, starsRectangles.get(i).width, starsRectangles.get(i).height);
        }

        batch.draw(shootingStarAnimation.getKeyFrame(shootingStarAnimationTime), shootingStarRectangle.x, shootingStarRectangle.y, shootingStarRectangle.width, shootingStarRectangle.height);
    }

    @Override
    public void dispose() {
        atlas.dispose();
    }

}
