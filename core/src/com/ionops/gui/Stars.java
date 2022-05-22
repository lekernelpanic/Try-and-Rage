package com.ionops.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Stars extends Gui {

    final int STARS = 100;
    final float SHOOTING_STAR_SPEED = 1.8f;
    final float SHOOTING_STAR_ANIMATION_SPEED = 0.01f;
    final float SHOOTING_STAR_TIME_LAPSE = 16;
    ArrayList<Rectangle> starsRectangles;
    ArrayList<Animation> starsAnimation;
    TextureAtlas atlas;
    Animation shootingStarAnimation;
    Rectangle shootingStarRectangle;
    Random random;
    float starsAnimationTime, shootingStarAnimationTime;

    public Stars() {
        rectangle.x = 0;
        rectangle.y = 0;
        rectangle.width = Gdx.graphics.getWidth();
        rectangle.height = Gdx.graphics.getHeight();

        starsRectangles = new ArrayList<Rectangle>();
        starsAnimation = new ArrayList<Animation>();
        atlas = new TextureAtlas(Gdx.files.internal("star.atlas"));
        random = new Random();

        // shooting star
        shootingStarAnimation = new Animation(SHOOTING_STAR_ANIMATION_SPEED, atlas.findRegions("star"));
        shootingStarAnimation.setPlayMode(PlayMode.REVERSED);
        shootingStarRectangle = new Rectangle();
        shootingStarRectangle.width = Gdx.graphics.getWidth() / 200;
        shootingStarRectangle.height = shootingStarRectangle.width * 20;

        for (int i = 0; i < STARS; i++) { // création des étoiles

            starsAnimation.add(new Animation((float) (4 + random.nextInt(8)) / 100, atlas.findRegions("star")));
            starsAnimation.get(i).setPlayMode(PlayMode.LOOP_PINGPONG);

            starsRectangles.add(new Rectangle(40, 40, Gdx.graphics.getWidth() / 60, Gdx.graphics.getWidth() / 60));
            starsRectangles.get(i).x = rectangle.x + random.nextInt((int) rectangle.width);
            starsRectangles.get(i).y = rectangle.y + random.nextInt((int) rectangle.height);

            starsRectangles.get(i).width = Gdx.graphics.getWidth() / 200;
            starsRectangles.get(i).height = starsRectangles.get(i).width;

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
            shootingStarRectangle.y = Gdx.graphics.getHeight() / 2 + random.nextInt(Gdx.graphics.getHeight() / 2);
        }

        // étoiles
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
