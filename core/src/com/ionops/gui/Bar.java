package com.ionops.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

public class Bar extends Gui {

    final float ANIMATION_SPEED = 1.8f;
    Rectangle rectangleLeft;
    Rectangle rectangleRight;
    TextureAtlas atlas;
    float space, middle, spaceFinal;

    public Bar() {
        super();

        rectangle.y = Gdx.graphics.getHeight() / 8f;
        rectangle.width = Gdx.graphics.getWidth();
        rectangle.height = Gdx.graphics.getHeight() / 42f;

        space = rectangle.width;
        middle = rectangle.width / 2;
        atlas = new TextureAtlas(Gdx.files.internal("bar.atlas"));

        rectangleLeft = new Rectangle(rectangle);
        rectangleRight = new Rectangle(rectangle);

        rectangleLeft.width = Gdx.graphics.getWidth();
        rectangleRight.width = Gdx.graphics.getWidth();

        setSpace(0.28f);
    }

    public void render(SpriteBatch batch) {

        if ((space > spaceFinal) || (space < spaceFinal)) {
            double dist = rectangle.width * Gdx.graphics.getDeltaTime() * ANIMATION_SPEED;

            if (space - spaceFinal >= dist) {
                space -= dist;
            } else if (spaceFinal - space >= dist) {
                space += dist;
            } else {
                space = spaceFinal;
            }
        }
        //

        rectangleLeft.x = rectangle.x - (rectangleLeft.width - middle + (space / 2));
        rectangleRight.x = rectangle.x + middle + (space / 2);

        batch.draw(atlas.findRegion("left"), rectangleLeft.x, rectangleLeft.y, rectangleLeft.width, rectangleLeft.height);

        batch.draw(atlas.findRegion("right"), rectangleRight.x, rectangleRight.y, rectangleRight.width, rectangleRight.height);

    }

    @Override
    public void dispose() {
        atlas.dispose();
    }

    public void setMiddle(float middle) {
        if ((middle >= 0) && (middle <= rectangle.width)) {
            this.middle = middle + rectangle.x;
        }
    }

    public void setSpace(float spaceScale) {
        if ((spaceScale >= 0) && (spaceScale <= 1)) {
            this.spaceFinal = rectangle.width * spaceScale;
        }
    }

    public boolean isInCollision(Rectangle rect) {
        return rectangleLeft.overlaps(rect) || rectangleRight.overlaps(rect);
    }
}
