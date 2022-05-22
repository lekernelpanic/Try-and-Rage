package com.ionops.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.ionops.gui.Button;
import com.ionops.gui.TableFlip;

public class GameOverWindow {

    final float ANIMATION_SPEED = Gdx.graphics.getHeight() * 4;
    final float SCORE_INCREMENT_SPEED = 0.5f;
    Rectangle rectangle;
    Texture backgroundTexture;
    BitmapFont scoreFont;
    Preferences best;
    Button playButton, menuButton;
    TableFlip tableFlip;
    int score, displayedScore;
    float time, rectangleOriginY, rectangleFinalY, scoreX, scoreY, bestX, bestY;
    boolean scoreIsDisplayed, renderingStarted;
    float[] buttonPadding;

    public GameOverWindow() {
        rectangle = new Rectangle();
        backgroundTexture = new Texture("game_over_window.png");

        rectangle.width = Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 8f;
        rectangle.height = Gdx.graphics.getHeight() / 3f;
        rectangleOriginY = -rectangle.height;
        rectangle.x = (Gdx.graphics.getWidth() / 2f) - (rectangle.width / 2);
        rectangle.y = rectangleOriginY;

        rectangleFinalY = (Gdx.graphics.getHeight() / 2f) - (rectangle.height / 2);

        scoreFont = new BitmapFont(Gdx.files.internal("score_font.fnt"), false);
        scoreFont.getData().setScale(7f * Gdx.graphics.getWidth() / 1024);

        best = Gdx.app.getPreferences("best");

        buttonPadding = new float[4];
        buttonPadding[0] = 0;
        buttonPadding[1] = rectangle.width / 15;
        buttonPadding[2] = rectangle.height / 20;
        buttonPadding[3] = rectangle.width / 15;

        playButton = new Button("play_button.atlas", buttonPadding);
        menuButton = new Button("menu_button.atlas", buttonPadding);

        tableFlip = new TableFlip();
    }

    public void render(SpriteBatch batch) {

        renderingStarted = true;

        // Score incrementation
        time += Gdx.graphics.getDeltaTime();
        if (time >= SCORE_INCREMENT_SPEED / score) {
            if (displayedScore < score) {
                displayedScore += 1;
                scoreIsDisplayed = false;
            } else {
                displayedScore = score;
                scoreIsDisplayed = true;
            }
            time = 0;
        } else if (score == 0) {
            displayedScore = score;
            scoreIsDisplayed = true;
        }
        //

        // Position du score / best
        scoreX = rectangle.width / 1.9f;
        scoreY = rectangle.y + rectangle.height - (rectangle.height / 6f);
        bestX = scoreX;
        bestY = scoreY - scoreFont.getLineHeight() - (rectangle.width / 8.5f);
        //

        // Animation
        if (rectangle.y < rectangleFinalY) {
            float dist = ANIMATION_SPEED * Gdx.graphics.getDeltaTime();

            if (rectangleFinalY - rectangle.y >= dist) {
                rectangle.y += dist;
            } else {
                rectangle.y = rectangleFinalY;
            }

        } else {
            rectangle.y = rectangleFinalY;
        }
        //

        // Best score
        if (score > best.getInteger("best")) {
            best.putInteger("best", score);
            best.flush();
        }
        //

        // Draw
        batch.draw(backgroundTexture, rectangle.x, rectangle.y, rectangle.width, rectangle.height);

        scoreFont.draw(batch, Integer.toString(displayedScore), scoreX, scoreY);

        tableFlip.render(batch, rectangle, score);

        if (scoreIsDisplayed) {
            scoreFont.draw(batch, Integer.toString(best.getInteger("best")), bestX, bestY);

            playButton.render(batch, rectangle.x, rectangle.y, rectangle.width / 2, rectangle.height / 3);

            menuButton.render(batch, rectangle.x + (rectangle.width / 2), rectangle.y, (rectangle.width / 2), rectangle.height / 3);
        }
        //
    }

    public void dispose() {
        backgroundTexture.dispose();
        scoreFont.dispose();
        playButton.dispose();
        menuButton.dispose();
        tableFlip.dispose();
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getMenuButton() {
        return menuButton;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean scoreIsDisplayed() {
        return scoreIsDisplayed;
    }
}
