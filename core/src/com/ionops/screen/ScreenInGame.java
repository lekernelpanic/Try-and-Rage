package com.ionops.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ionops.gui.Bar;
import com.ionops.gui.FadeTransition;
import com.ionops.gui.Iss;
import com.ionops.gui.Stars;
import com.ionops.gui.Swipe;
import com.ionops.keylistener.KeyListenerInGame;
import com.ionops.tryandrage.TryAndRage;
import com.ionops.utils.GameOverWindow;
import com.ionops.utils.ObstacleGenerator;

public class ScreenInGame implements Screen {

    final float GAME_OVER_LATENCE = 0.8f;
    KeyListenerInGame keyListener;
    SpriteBatch batch;
    Texture background;
    Stars stars;
    Texture foreground;
    Iss iss;
    Bar bar;
    ObstacleGenerator obstacleGen;
    Swipe swipe;
    BitmapFont counterFont;
    GameOverWindow gameOverWindow;
    FadeTransition transition;
    float time;
    boolean gameOver, reCreate, returnMenu;
    float counterY;
    float FREQUENCY_OBSTACLES = 0.42f;
    float SPEED_OBSTACLES = 0.3f;

    public void create() {
        keyListener = new KeyListenerInGame(this);
        Gdx.input.setInputProcessor(keyListener);
        Gdx.input.setCatchBackKey(true);

        batch = new SpriteBatch();
        background = new Texture("background.png");
        stars = new Stars();
        foreground = new Texture("foreground_in_game.png");
        bar = new Bar();
        swipe = new Swipe(bar);
        obstacleGen = new ObstacleGenerator(bar, FREQUENCY_OBSTACLES, SPEED_OBSTACLES, false, false);
        iss = new Iss();
        counterFont = new BitmapFont(Gdx.files.internal("score_font.fnt"), false);
        gameOverWindow = new GameOverWindow();
        transition = new FadeTransition();

        time = 0;
        gameOver = false;
        reCreate = false;
        returnMenu = false;

        counterFont.getData().setScale(10f * Gdx.graphics.getWidth() / 1024);
        counterY = Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 8;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        keyListener.tick();
        batch.begin();
        {
            // render
            batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            stars.render(batch);
            batch.draw(foreground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            iss.render(batch);
            bar.render(batch);
            obstacleGen.render(batch);
            swipe.render(batch);

            if (!gameOver) {
                displayScore(batch);
            }
            //

            // Game Over
            if (gameOver) {
                time += Gdx.graphics.getDeltaTime();

                if (time > GAME_OVER_LATENCE) {
                    gameOverWindow.render(batch);
                } else {
                    displayScore(batch);
                }
            }

            // transitions
            transition.render(batch, (returnMenu || reCreate));

        }
        batch.end();

        // Switch screen quand la transition est terminée
        if (transition.isFinished()) {
            if (returnMenu) {
                TryAndRage.SCREEN_MENU.create();
                TryAndRage.getInstance().setScreen(TryAndRage.SCREEN_MENU);
            } else if (reCreate) {
                create();
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

    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        stars.dispose();
        foreground.dispose();
        iss.dispose();
        bar.dispose();
        obstacleGen.dispose();
        swipe.dispose();
        counterFont.dispose();
        gameOverWindow.dispose();
        transition.dispose();
    }

    public Iss getIss() {
        return iss;
    }

    public Bar getBar() {
        return bar;
    }

    public ObstacleGenerator getObstacleGen() {
        return obstacleGen;
    }

    public Swipe getSwipe() {
        return swipe;
    }

    public GameOverWindow getGameOverWindow() {
        return gameOverWindow;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void returnMenu() {
        returnMenu = true;
        transition.resetAnimationTime();
    }

    public void reCreate() {
        reCreate = true;
        transition.resetAnimationTime();
    }

    private void displayScore(SpriteBatch batch) {
        String counterValue = Integer.toString(obstacleGen.getObstaclePassed());
        float counterX = (Gdx.graphics.getWidth() / 2) - (counterFont.getSpaceWidth() * counterValue.length()) / 2;

        counterFont.draw(batch, counterValue, counterX, counterY);
    }

    public void gameOver() {
        gameOver = true;
        gameOverWindow.setScore(obstacleGen.getObstaclePassed());
        obstacleGen.stop();
        iss.stop();
        Gdx.input.vibrate(200);
    }
}
