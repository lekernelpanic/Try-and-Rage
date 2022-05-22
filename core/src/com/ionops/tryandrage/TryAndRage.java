package com.ionops.tryandrage;

import com.badlogic.gdx.Game;
import com.ionops.screen.ScreenInGame;
import com.ionops.screen.ScreenMenu;

public class TryAndRage extends Game {

    public static final ScreenMenu SCREEN_MENU = new ScreenMenu();
    public static final ScreenInGame SCREEN_INGAME = new ScreenInGame();
    private static TryAndRage instance;

    public TryAndRage() {
        instance = this;
    }

    public static TryAndRage getInstance() {
        return instance;
    }

    @Override
    public void create() {
        SCREEN_MENU.create();
        setScreen(SCREEN_MENU);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
