package com.ionops.keylistener;

import com.badlogic.gdx.InputProcessor;
import com.ionops.screen.ScreenMenu;

public class KeyListenerMenu implements InputProcessor {

    ScreenMenu screenInstance;

    public KeyListenerMenu(ScreenMenu screenInstance) {
        this.screenInstance = screenInstance;
    }

    @Override
    public boolean keyDown(int keycode) {
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
        if (screenInstance.getStartButton().isTouched(screenX, screenY)) {
            screenInstance.start();
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

}
