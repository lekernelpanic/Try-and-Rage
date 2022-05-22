package com.ionops.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public abstract class Gui {

    protected Rectangle rectangle;

    public Gui() {
        rectangle = new Rectangle();
    }

    public void render(SpriteBatch batch, Texture texture) {
        batch.draw(texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void render(SpriteBatch batch, TextureRegion texture) {
        batch.draw(texture, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public abstract void dispose();

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void move(float dx, float dy) {
        dx = dx * Gdx.graphics.getWidth();
        dy = dy * Gdx.graphics.getHeight();
        rectangle.x += dx * Gdx.graphics.getDeltaTime();
        rectangle.y += dy * Gdx.graphics.getDeltaTime();
    }
}
