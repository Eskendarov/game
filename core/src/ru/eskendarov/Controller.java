package ru.eskendarov;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import lombok.Getter;

@Getter
class Controller {

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
        spriteBatch.begin();
        spriteBatch.draw(resources.getBackgroundImage(), 0, 0, Screen.WIDTH, Screen.HEIGHT);
        spriteBatch.end();
        touchController();
        keyController();
        stars.iterate();
        if (enemyShip.getRectangle().overlaps(ownShip.getRectangle())) { // Столкновение //todo overlaps
            // do something
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
            if (enemyShip.getRectangle().x >= Screen.WIDTH - enemyShip.getSize()) {
                right = false;
            }
        }
        if (!right) {
            enemyShip.getRectangle().x -= 50 * Gdx.graphics.getDeltaTime();
            if (enemyShip.getRectangle().x <= 0) {
                right = true;
            }
        }
        spriteBatch.draw(resources.getEnemyShipImage(), enemyShip.getRectangle().x, enemyShip.getRectangle().y, enemyShip.getSize(), enemyShip.getSize());
        spriteBatch.draw(resources.getOwnShipImage(), ownShip.getRectangle().x, ownShip.getRectangle().y, currentSizeShip, currentSizeShip);
        spriteBatch.end();
    }

    private void touchController() {
        if (Gdx.input.isTouched()) {
            if (Gdx.input.isTouched() && Screen.touchPosition.x < Screen.HALF_WIDTH) {
                ownShip.getRectangle().x -= 500 * Gdx.graphics.getDeltaTime();
                Screen.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            }
            if (Gdx.input.isTouched() && Screen.touchPosition.x > Screen.HALF_WIDTH) {
                ownShip.getRectangle().x += 500 * Gdx.graphics.getDeltaTime();
                Screen.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            }
            Screen.camera.unproject(Screen.touchPosition);
            resources.getBlasterSound().play(0.03f);
        }
    }

    private void keyController() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            resources.getBlasterSound().play(.03f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            ownShip.getRectangle().y = ownShip.getRectangle().y + 5;
            currentSizeShip = ownShip.getSize() - 2;
        } else {
            currentSizeShip = ownShip.getSize();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            ownShip.getRectangle().y = ownShip.getRectangle().y - 5;
            currentSizeShip = ownShip.getSize() + 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            ownShip.getRectangle().x -= 500 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            ownShip.getRectangle().x += 500 * Gdx.graphics.getDeltaTime();
        }
        if (ownShip.getRectangle().x < -ownShip.getHalfSize()) {
            ownShip.getRectangle().x = -ownShip.getHalfSize();
        }
        if (ownShip.getRectangle().x > Screen.WIDTH - ownShip.getHalfSize()) {
            ownShip.getRectangle().x = Screen.WIDTH - ownShip.getHalfSize();
        }
        if (ownShip.getRectangle().y < ownShip.getHalfSize() / 3) {
            ownShip.getRectangle().y = ownShip.getHalfSize() / 3;
        }
        if (ownShip.getRectangle().y >= enemyShip.getRectangle().y - ownShip.getSize()) {
            ownShip.getRectangle().y = enemyShip.getRectangle().y - ownShip.getSize();
        }
    }

}