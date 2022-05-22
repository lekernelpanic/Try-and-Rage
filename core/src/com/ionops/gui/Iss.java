package com.ionops.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Iss extends Gui {

    Texture issTexture;
    Random random;
    boolean stop;

    public Iss() {
        rectangle.width = Gdx.graphics.getWidth() / 4.5f;
        rectangle.height = rectangle.width * 0.64f;
        rectangle.x = Gdx.graphics.getWidth() * 1.2f;
        rectangle.y = Gdx.graphics.getHeight() * 0.7f;

        issTexture = new Texture("iss.png");
        random = new Random();

        stop = true;
    }

    public void render(SpriteBatch batch) {

        if (!stop) {

            if (rectangle.x >= -rectangle.width) {
                move(-0.042f, 0);

            } else {
                rectangle.x = Gdx.graphics.getWidth() * 1.2f;
            }

        }

        super.render(batch, issTexture);
    }

    @Override
    public void dispose() {
        issTexture.dispose();
    }

    public void start() {
        stop = false;
    }

    public void stop() {
        stop = true;
    }

}
