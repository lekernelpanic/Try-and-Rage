package com.ionops.keylistener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.ionops.screen.ScreenInGame;

public class KeyListenerInGame implements InputProcessor {

    ScreenInGame screenInstance;

    public KeyListenerInGame(ScreenInGame screenInstance) {
        this.screenInstance = screenInstance;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.BACK) {
            if (screenInstance.getGameOver() || screenInstance.getGameOverWindow().scoreIsDisplayed()) {
                screenInstance.returnMenu();
            }

            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        // Play & Menu buttons
        if (screenInstance.getGameOverWindow().getPlayButton().isTouched(screenX, screenY)) {
            screenInstance.reCreate();
            return true;
        } else if (screenInstance.getGameOverWindow().getMenuButton().isTouched(screenX, screenY)) {
            screenInstance.returnMenu();
            return true;
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public void tick() {
        if (Gdx.input.isTouched()) {
            screenInstance.getSwipe().stop();

            if (screenInstance.getGameOver()) {
                screenInstance.getObstacleGen().start();
                screenInstance.getIss().start();
                screenInstance.getBar().setMiddle(Gdx.input.getX());
            }
        }
    }
}
