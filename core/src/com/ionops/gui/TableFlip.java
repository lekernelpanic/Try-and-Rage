package com.ionops.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;

public class TableFlip extends Gui {

    TextureAtlas atlas;
    AtlasRegion texture;

    public TableFlip() {
        super();
        atlas = new TextureAtlas(Gdx.files.internal("table_flip.atlas"));
    }

    public void render(SpriteBatch batch, Rectangle gameOverRectangle, int score) {

        rectangle.x = gameOverRectangle.x + gameOverRectangle.width * 0.011f;
        rectangle.y = gameOverRectangle.y + gameOverRectangle.height * 0.424f;
        rectangle.width = gameOverRectangle.width * 0.389f;
        rectangle.height = gameOverRectangle.height * 0.558f;

        if (score == 0) {
            texture = atlas.findRegion("0");
        } else if (score == 42) {
            texture = atlas.findRegion("42");
        } else if (score == 69) {
            texture = atlas.findRegion("69");
        } else if (score == 161) {
            texture = atlas.findRegion("161");
        } else if (score == 300) {
            texture = atlas.findRegion("300");
        } else if (score == 314) {
            texture = atlas.findRegion("314");
        } else if (score < 10) {
            texture = atlas.findRegion("score-0");
        } else if (score < 20) {
            texture = atlas.findRegion("score-1");
        } else if (score < 40) {
            texture = atlas.findRegion("score-2");
        } else if (score < 70) {
            texture = atlas.findRegion("score-3");
        } else if (score < 100) {
            texture = atlas.findRegion("score-4");
        } else if (score < 200) {
            texture = atlas.findRegion("score-5");
        } else if (score >= 200) {
            texture = atlas.findRegion("score-6");
        } else {
            texture = atlas.findRegion("score-0");
        }

        super.render(batch, texture);
    }

    public void dispose() {
        atlas.dispose();
    }

}
