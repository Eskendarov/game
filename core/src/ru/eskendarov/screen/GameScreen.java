package ru.eskendarov.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import lombok.Getter;
import ru.eskendarov.Controller;
import ru.eskendarov.Resources;
import ru.eskendarov.StarWars;

@Getter
public class GameScreen implements Screen, InputProcessor {

    private final StarWars starWars;
    private final Controller controller;
    private final Resources resources;

    GameScreen(final StarWars starWars) {
        this.starWars = starWars;
        new ScreenOption();
        resources = new Resources();
        controller = new Controller();
    }

    @Override
    public void render(float delta) {
        controller.film();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        resources.initSound();
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
        resources.dispose();
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
    public boolean scrolled(int amount) {
        return false;
    }

}
