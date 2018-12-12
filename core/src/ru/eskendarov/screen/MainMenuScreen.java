package ru.eskendarov.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import ru.eskendarov.StarWars;

public class MainMenuScreen implements Screen, InputProcessor {

    private final StarWars starWars;
    private final Texture buttonExit1;
    private final Texture buttonExit2;
    private final Rectangle buttonRec = new Rectangle();
    private final Music newGameSound;


    public MainMenuScreen(final StarWars starWars) {
        this.starWars = starWars;
        new ScreenOption();
        Gdx.input.setInputProcessor(this);
        buttonExit1 = new Texture(Gdx.files.internal("textures/button.png"));
        buttonExit2 = new Texture(Gdx.files.internal("textures/buttonInvert.png"));
        buttonRec.set(ScreenOption.HALF_WIDTH - (float) buttonExit1.getWidth() / 2, (float) ScreenOption.HEIGHT / 2 - (float) buttonExit1.getHeight() / 2, buttonExit1.getWidth(), buttonExit1.getHeight());
        newGameSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/new_game.wav"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.05f, .05f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ScreenOption.camera.update();
        starWars.getSpriteBatch().setProjectionMatrix(ScreenOption.camera.combined);
        starWars.getSpriteBatch().begin();
        starWars.getSpriteBatch().draw(buttonExit1, buttonRec.x, buttonRec.y, buttonRec.width, buttonRec.height);
        if (Gdx.input.getInputProcessor().mouseMoved(Gdx.input.getX(), Gdx.input.getY())) {
            starWars.getSpriteBatch().draw(buttonExit2, buttonRec.x, buttonRec.y, buttonRec.width, buttonRec.height);
        }
        starWars.getSpriteBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        newGameSound.setVolume(0.3f);
        newGameSound.play();
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
        buttonExit1.dispose();
        buttonExit2.dispose();
        newGameSound.dispose();
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
        if (buttonRec.contains(Gdx.input.getX(), Gdx.input.getY())) {
            buttonRec.set(buttonRec.x += 5, buttonRec.y += 5, buttonRec.width -= 10, buttonRec.height -= 10);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (buttonRec.contains(Gdx.input.getX(), Gdx.input.getY())) {
            starWars.setScreen(new GameScreen(starWars));
            dispose();
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return buttonRec.contains(Gdx.input.getX(), Gdx.input.getY());
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
