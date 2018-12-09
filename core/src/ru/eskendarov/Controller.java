package ru.eskendarov;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import lombok.Getter;
import ru.eskendarov.ships.EnemyShip;
import ru.eskendarov.ships.OwnShip;

import static com.badlogic.gdx.Input.Keys.SPACE;


@Getter
class Controller implements InputProcessor {

    private final SpriteBatch spriteBatch = new SpriteBatch();
    private final Resources resources = new Resources();
    private final OwnShip ownShip = new OwnShip();
    private final EnemyShip enemyShip = new EnemyShip();
    private final Stars stars = new Stars();
    private boolean right;
    private int currentSizeShip;

    Controller() {
        System.out.println("controller create");
        ownShip.initPosition();
        enemyShip.initPosition();
    }

    void film() {
        Screen.camera.update();
        spriteBatch.setProjectionMatrix(Screen.camera.combined);
//        spriteBatch.getProjectionMatrix().idt();
//        Gdx.input.setInputProcessor(this);
        spriteBatch.begin();
        spriteBatch.draw(resources.getBackgroundImage(), 0, 0, Screen.WIDTH, Screen.HEIGHT);
        spriteBatch.end();
        touchController();
        keyController();
        stars.iterate();
        if (ownShip.getRectangle().overlaps(enemyShip.getRectangle())) { // Столкновение //todo overlaps
            System.out.println("OverLaps");
        }
        spriteBatch.begin();
        for (final Rectangle starsRec : stars.getStars()) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                starsRec.x += 20 * Gdx.graphics.getDeltaTime();
                if (starsRec.x >= Screen.WIDTH) {
                    starsRec.x = 0;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                starsRec.x -= 20 * Gdx.graphics.getDeltaTime();
                if (starsRec.x <= 0) {
                    starsRec.x = Screen.WIDTH;
                }
            }
            spriteBatch.draw(resources.getStarImage(), starsRec.x, starsRec.y, stars.getSize(), stars.getSize());
        }
        if (right) {
            enemyShip.getRectangle().x += 50 * Gdx.graphics.getDeltaTime();
            if (enemyShip.getRectangle().x >= Screen.WIDTH - enemyShip.getSIZE()) {
                right = false;
            }
        }
        if (!right) {
            enemyShip.getRectangle().x -= 50 * Gdx.graphics.getDeltaTime();
            if (enemyShip.getRectangle().x <= 0) {
                right = true;
            }
        }
        spriteBatch.draw(resources.getEnemyShipImage(), enemyShip.getRectangle().x, enemyShip.getRectangle().y, enemyShip.getSIZE(), enemyShip.getSIZE());
        spriteBatch.draw(resources.getOwnShipImage(), ownShip.getRectangle().x, ownShip.getRectangle().y, currentSizeShip, currentSizeShip);
        spriteBatch.end();
    }

    private void touchController() {
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() < Screen.HALF_WIDTH) {
                ownShip.getRectangle().x -= 500 * Gdx.graphics.getDeltaTime();
            }
            if (Gdx.input.getX() > Screen.HALF_WIDTH) {
                ownShip.getRectangle().x += 500 * Gdx.graphics.getDeltaTime();
            }
            Screen.camera.unproject(Screen.touchPosition);
            resources.getBlasterSound().play(0.03f);
        }
    }

    private void keyController() {

        if (Gdx.input.isKeyPressed(SPACE)) {
            resources.getBlasterSound().play(.03f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            ownShip.getRectangle().y = ownShip.getRectangle().y + 5;
            currentSizeShip = ownShip.getSIZE() - 2;
        } else {
            currentSizeShip = ownShip.getSIZE();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            ownShip.getRectangle().y = ownShip.getRectangle().y - 5;
            currentSizeShip = ownShip.getSIZE() + 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            ownShip.getRectangle().x -= 500 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            ownShip.getRectangle().x += 500 * Gdx.graphics.getDeltaTime();
        }
        if (ownShip.getRectangle().x < -ownShip.getHALF_SIZE()) {
            ownShip.getRectangle().x = -ownShip.getHALF_SIZE();
        }
        if (ownShip.getRectangle().x > Screen.WIDTH - ownShip.getHALF_SIZE()) {
            ownShip.getRectangle().x = Screen.WIDTH - ownShip.getHALF_SIZE();
        }
        if (ownShip.getRectangle().y < ownShip.getHALF_SIZE() / 3) {
            ownShip.getRectangle().y = (float) ownShip.getHALF_SIZE() / 3;
        }
        if (ownShip.getRectangle().y >= enemyShip.getRectangle().y - ownShip.getSIZE()) {
            ownShip.getRectangle().y = enemyShip.getRectangle().y - ownShip.getSIZE();
        }
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