package com.ionops.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.ionops.utils.ObstacleGenerator;

import java.util.Random;

public class Obstacle extends Gui {

    Texture obstacleTexture;
    Random random;
    ObstacleGenerator obstacleGen;
    Explosion explosion;
    float speed, barY;
    boolean passed;

    public Obstacle(ObstacleGenerator obstacleGen, float speed, float barY) {
        super();

        random = new Random();

        rectangle.width = Gdx.graphics.getWidth() / 25;
        rectangle.height = rectangle.width;
        rectangle.x = random.nextInt(Gdx.graphics.getWidth() - (int) rectangle.width);
        rectangle.y = Gdx.graphics.getHeight();

        obstacleTexture = new Texture("obstacle.png");

        this.obstacleGen = obstacleGen;
        this.speed = speed;
        this.barY = barY;
    }

    public void render(SpriteBatch batch) {
        super.render(batch, obstacleTexture);

        if (explosion != null) {
            explosion.render(batch);
        }

        if (!passed && ((rectangle.y + rectangle.height / 2) < barY)) {
            obstacleGen.newObstaclePassed();
            passed = true;
        }
    }

    @Override
    public void dispose() {
        obstacleTexture.dispose();
    }

    public boolean isOut() {
        return rectangle.y + rectangle.height <= 0;
    }

    public boolean isPassed(Rectangle rectangle) {
        return this.rectangle.y + this.rectangle.height < rectangle.y;
    }

    public void goDown() {
        move(0, -speed);
    }

    public void explosion() {
        explosion = new Explosion(rectangle);
    }

}
