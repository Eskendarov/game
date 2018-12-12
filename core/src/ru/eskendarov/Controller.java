package ru.eskendarov;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.Getter;
import ru.eskendarov.arsenal.Bullet;
import ru.eskendarov.screen.ScreenOption;
import ru.eskendarov.ships.EnemyCraft;
import ru.eskendarov.ships.OwnCraft;

@Getter
public class Controller implements InputProcessor {

    public static final SpriteBatch spriteBatch = new SpriteBatch();
    private final Resources resources = new Resources();
    private final OwnCraft ownCraft = new OwnCraft();
    private final EnemyCraft enemyCraft = new EnemyCraft();
    private final Stars stars = new Stars();
    Bullet bullet = new Bullet();
    private boolean right;
    private float animateTimer;
    private boolean changeEnemyCraft;

    /*
     * Отрисовка объектов, основной метод render(), GameScreen класса.
     * */
    public void film() {
        ScreenOption.camera.update();
        spriteBatch.setProjectionMatrix(ScreenOption.camera.combined);
        Gdx.input.setInputProcessor(this);
        spriteBatch.begin();
        spriteBatch.draw(resources.getBackgroundImage(), 0, 0, ScreenOption.WIDTH, ScreenOption.HEIGHT);
        touchController();
        keyController();
        stars.generate(resources);

        ownCraft.setBounds();
        enemyCraft.setBounds();
//        if (ownCraft.getPosition().overlaps(enemyCraft.getPosition())) { // Столкновение //todo overlaps
//            System.out.println("OverLaps method");
//        }


        enemyShipMotion();

        timer(Gdx.graphics.getDeltaTime());//todo

//        spriteBatch.draw(resources.getEnemyShipImage1(), enemyCraft.getPosition().x, enemyCraft.getPosition().y, enemyCraft.getSIZE(), enemyCraft.getSIZE());
        spriteBatch.draw(resources.getOwnShipImage(), ownCraft.getPosition().x, ownCraft.getPosition().y, ownCraft.getSIZE(), ownCraft.getSIZE());
        spriteBatch.end();
    }

    private void enemyShipMotion() {
        if (right) {
            enemyCraft.getPosition().x += 200 * Gdx.graphics.getDeltaTime();
            if (enemyCraft.getPosition().x >= ScreenOption.WIDTH - enemyCraft.getSIZE()) {
                right = false;
            }
        }
        if (!right) {
            enemyCraft.getPosition().x -= 200 * Gdx.graphics.getDeltaTime();
            if (enemyCraft.getPosition().x <= enemyCraft.getLeftBounds()) {
                right = true;
            }
        }
    }

    private void touchController() {
        if (Gdx.input.isTouched()) {
            if (Gdx.input.getX() < ScreenOption.HALF_WIDTH) {
                ownCraft.getPosition().x -= 200 * Gdx.graphics.getDeltaTime();
                if (ownCraft.getPosition().x <= Gdx.input.getX() - ownCraft.getHALF_SIZE()) {
                    ownCraft.getPosition().x = Gdx.input.getX() - ownCraft.getHALF_SIZE();
                }
            }
            if (Gdx.input.getX() > ScreenOption.HALF_WIDTH) {
                ownCraft.getPosition().x += 200 * Gdx.graphics.getDeltaTime();
                if (ownCraft.getPosition().x >= Gdx.input.getX() - ownCraft.getHALF_SIZE()) {
                    ownCraft.getPosition().x = Gdx.input.getX() - ownCraft.getHALF_SIZE();
                }
            }
        }
    }

    private void keyController() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            ownCraft.getPosition().y = ownCraft.getPosition().y + 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            ownCraft.getPosition().y = ownCraft.getPosition().y - 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            ownCraft.getPosition().x -= 500 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            ownCraft.getPosition().x += 500 * Gdx.graphics.getDeltaTime();
        }
    }

    private void timer(final float deltaTime) {
        animateTimer -= deltaTime;
        if (changeEnemyCraft) {
            spriteBatch.draw(resources.getEnemyShipImage1(), enemyCraft.getPosition().x, enemyCraft.getPosition().y, enemyCraft.getSIZE(), enemyCraft.getSIZE());
            if (animateTimer <= 0) {
                changeEnemyCraft = false;
                animateTimer = 10;
            }
        } else if (!changeEnemyCraft) {
            spriteBatch.draw(resources.getEnemyShipImage2(), ScreenOption.WIDTH - enemyCraft.getSIZE() - enemyCraft.getPosition().x, enemyCraft.getPosition().y, enemyCraft.getSIZE(), enemyCraft.getSIZE());
            if (animateTimer <= 0) {
                changeEnemyCraft = true;
                animateTimer = 10;
            }
        }
    }


    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            resources.getBlasterSound().play(0.03f);
            spriteBatch.begin();
            bullet.fire(resources);
            spriteBatch.end();
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
        resources.getBlasterSound().play(0.03f);
        System.out.println(String.format("touchDown screenX = %s  screenY = %s", Gdx.input.getX(), Gdx.input.getY()));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println(String.format("touchUp screenX = %s  screenY = %s", Gdx.input.getX(), Gdx.input.getY()));
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println(String.format("touchDragged screenX = %s  screenY = %s", Gdx.input.getX(), Gdx.input.getY()));
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