package com.ionops.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

public class Button extends Gui {

    TextureAtlas atlas;
    float[] padding;

    public Button(String atlas) {
        super();
        this.atlas = new TextureAtlas(Gdx.files.internal(atlas));
    }

    public Button(String atlas, float[] padding) {
        super();
        this.atlas = new TextureAtlas(Gdx.files.internal(atlas));
        this.padding = padding;
    }

    public void render(SpriteBatch batch) {

        if (padding != null) {
            rectangle.x += padding[3];
            rectangle.y += padding[2];
            rectangle.width -= padding[1] + padding[3];
            rectangle.height -= padding[0] + padding[2];
        }

        if (!isTouched()) {
            super.render(batch, atlas.findRegion("button"));
        } else {
            super.render(batch, atlas.findRegion("button_touched"));
        }
    }

    public void render(SpriteBatch batch, float x, float y, float width, float height) {

        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = width;
        rectangle.height = height;

        render(batch);
    }

    @Override
    public void dispose() {
        atlas.dispose();
    }

    public boolean isTouched() {
        Rectangle rectangleTouch = new Rectangle(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 1, 1);
        return rectangle.overlaps(rectangleTouch) && Gdx.input.isTouched();
    }

    public boolean isTouched(int x, int y) {
        return rectangle.overlaps(new Rectangle(x, Gdx.graphics.getHeight() - y, 1, 1));
    }

}
