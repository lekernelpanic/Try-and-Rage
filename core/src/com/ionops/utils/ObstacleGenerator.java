package com.ionops.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ionops.gui.Bar;
import com.ionops.gui.Obstacle;
import com.ionops.tryandrage.TryAndRage;

import java.util.ArrayList;
import java.util.Random;

public class ObstacleGenerator {

    ArrayList<Obstacle> obstacles;
    Bar bar;
    Random random;
    int obstacleOut, obstaclePassed;
    float time, frequency, speed;
    boolean stop, randomGeneration, bonusOnScreen;

    public ObstacleGenerator(Bar bar, float frequency, float speed, boolean randomGeneration, boolean start) {

        this.bar = bar;
        this.frequency = frequency;
        this.speed = speed;
        this.randomGeneration = randomGeneration;
        stop = !start;

        obstacles = new ArrayList<Obstacle>();
        random = new Random();
    }

    public void render(SpriteBatch batch) {
        time += Gdx.graphics.getDeltaTime();

        if (time >= frequency) {

            // spawn obstacles
            if (!stop) {
                obstacles.add(nextObstacle());
            }
            //

            // Netoyage du tableau
            for (int i = 0; i < obstacles.size(); i++) {
                if ((obstacles.get(i) != null) && obstacles.get(i).isOut()) {
                    obstacles.remove(i);
                    obstacleOut++;
                }
            }
            //

            time = 0;
        }

        // Render obstacles
        for (int i = 0; i < obstacles.size(); i++) {
            if (obstacles.get(i) != null) {
                Obstacle obstacle = obstacles.get(i);

                if (!stop) {
                    obstacle.goDown();

                    // Gestion des colisions
                    if (bar != null && bar.isInCollision(obstacle.getRectangle())) {
                        TryAndRage.SCREEN_INGAME.gameOver();
                        obstacle.explosion();
                    }
                    //
                }
                obstacle.render(batch);
            }
        }
        //
    }

    public void dispose() {
        bar.dispose();

        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).dispose();
        }
    }

    private Obstacle nextObstacle() {

        if (randomGeneration) {
            return new Obstacle(this, (float) ((42 + random.nextInt(184)) / 1000f), 0);
        } else {
            return new Obstacle(this, speed, bar.getRectangle().y + bar.getRectangle().height / 2);
        }
    }

    public void start() {
        stop = false;
    }

    public void newObstaclePassed() {
        obstaclePassed++;
    }

    public int getObstaclePassed() {
        return obstaclePassed;
    }

    public boolean getStop() {
        return stop;
    }

    public void stop() {
        stop = true;
    }

}
