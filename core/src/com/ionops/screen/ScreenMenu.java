package com.ionops.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ionops.gui.Button;
import com.ionops.gui.FadeTransition;
import com.ionops.gui.Stars;
import com.ionops.keylistener.KeyListenerMenu;
import com.ionops.tryandrage.TryAndRage;
import com.ionops.utils.ObstacleGenerator;

public class ScreenMenu implements Screen {

    KeyListenerMenu keyListener;
    SpriteBatch batch;
    Preferences best;
    Texture background;
    Stars stars;
    Texture foreground;
    BitmapFont bestFont;
    ObstacleGenerator obstacleGen;
    Button startButton;
    Image gameTitle;
    FadeTransition transition;
    float bestScoreX, bestScoreY;
    boolean animationIn, animationOut;

    public void create() {
        keyListener = new KeyListenerMenu(this);
        Gdx.input.setInputProcessor(keyListener);
        Gdx.input.setCatchKey(Input.Keys.BACK, false);

        batch = new SpriteBatch();
        background = new Texture("background.png");
        stars = new Stars();
        foreground = new Texture("foreground_menu.png");
        obstacleGen = new ObstacleGenerator(null, 2f, 0, true, true);
        transition = new FadeTransition();

        animationIn = true;
        animationOut = false;

        // config titre
        gameTitle = new Image(new Texture("try_and_rage.png"));
        gameTitle.setWidth(Gdx.graphics.getWidth() / 1.5f);
        gameTitle.setHeight(Gdx.graphics.getHeight() / 20f);
        gameTitle.setX(Gdx.graphics.getWidth() / 2f - gameTitle.getWidth() / 2);
        gameTitle.setY(Gdx.graphics.getHeight() / 1.3f);
        //

        // config button
        startButton = new Button("play_button.atlas");
        startButton.getRectangle().width = Gdx.graphics.getWidth() / 2.5f;
        startButton.getRectangle().height = Gdx.graphics.getHeight() / 8f;
        startButton.getRectangle().x = Gdx.graphics.getWidth() / 2f - startButton.getRectangle().width / 2;
        startButton.getRectangle().y = Gdx.graphics.getHeight() / 2.8f;
        //

        // config score
        bestScoreX = Gdx.graphics.getWidth() * 0.37f;
        bestScoreY = Gdx.graphics.getHeight() * 0.0465f;
        //

        bestFont = new BitmapFont(Gdx.files.internal("score_font.fnt"), false);
        bestFont.getData().setScale(5f * Gdx.graphics.getWidth() / 1024);
        best = Gdx.app.getPreferences("best");

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        {
            batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            stars.render(batch);
            batch.draw(foreground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            bestFont.draw(batch, Integer.toString(best.getInteger("best")), bestScoreX, bestScoreY);
            obstacleGen.render(batch);
            startButton.render(batch);
            gameTitle.draw(batch, 1);

            if (animationIn || animationOut) {
                transition.render(batch, animationOut);
            }
        }
        batch.end();

        if (transition.isFinished()) {
            if (animationOut) {
                TryAndRage.SCREEN_IN_GAME.create();
                TryAndRage.getInstance().setScreen(TryAndRage.SCREEN_IN_GAME);
            } else if (animationIn) {
                transition.resetAnimationTime();
                animationIn = false;
            }
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(keyListener);
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        stars.dispose();
        foreground.dispose();
        bestFont.dispose();
        obstacleGen.dispose();
        startButton.dispose();
        transition.dispose();
    }

    public Button getStartButton() {
        return startButton;
    }

    public void start() {
        animationOut = true;
    }

}
