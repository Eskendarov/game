package ru.eskendarov;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

import static ru.eskendarov.Stars.stars;

public class StarWars extends ApplicationAdapter {

    private final static int SCREEN_WIDTH = 360;
    private final static int SCREEN_HEIGHT = 720;
    private SpriteBatch spriteBatch;
    private Texture backgroundImage;
    private Texture starImage;
    private Texture shipImage;
    private Music backgroundMusic;
    private Sound newGameSound;
    private Sound bulletSound;
    private OrthographicCamera camera;
    private Texture enemyShipImage;
    private Rectangle shipRec;
    private Rectangle enemyShipRec;
    private boolean right;
    private Vector3 touchPosition;
    private long lastStarTime;
    private Ship ship;


    /*
     * Метод вызывается один раз при создании приложения.
     * */
    @Override
    public void create() {
        ship = new Ship();
        touchPosition = new Vector3();
        spriteBatch = new SpriteBatch();
        shipImage = new Texture(Gdx.files.internal("textures/gunship.png"));
        enemyShipImage = new Texture(Gdx.files.internal("textures/skyWalker.png"));
        starImage = new Texture(Gdx.files.internal("textures/star.png"));
        backgroundImage = new Texture(Gdx.files.internal("textures/space.jpg"));

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/backMus.mp3"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/blaster2.wav"));
        newGameSound = Gdx.audio.newSound(Gdx.files.internal("sounds/new_game.wav"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        shipRec = new Rectangle();
        shipRec.x = SCREEN_WIDTH / 2 - ship.getHalfSize();
        shipRec.y = ship.getSize() / 3;

        enemyShipRec = new Rectangle();
        enemyShipRec.x = SCREEN_WIDTH / 2 - ship.getHalfSize();
        enemyShipRec.y = SCREEN_HEIGHT - ship.getSize() - ship.getHalfSize() / 3;
        //        backgroundMusic.setLooping(true);
        //        backgroundMusic.play();
        //        newGameSound.play(); //todo music

        stars = new Array<Rectangle>();
    }

    private void starFall() {
        final Rectangle star = new Rectangle();
        star.x = MathUtils.random(SCREEN_WIDTH);
        star.y = SCREEN_HEIGHT;
        stars.add(star);
        lastStarTime = TimeUtils.nanoTime();
    }

    /*
     * Метод вызывается игровым циклом приложения каждый раз,
     * когда должны быть выполнена визуализация.
     * Обновление логики игры обычно выполняется в этом методе.
     * */
    @Override
    public void render() { // 60 раз в секунду.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1); // Позиции объектов.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Очищает экран.
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(backgroundImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        spriteBatch.end();
        int currentSizeShip;
        if (Gdx.input.isTouched()) {
            touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPosition);
            shipRec.x = touchPosition.x - ship.getSize() / 2;
            bulletSound.play();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            bulletSound.play();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            shipRec.y = shipRec.y + 5;
            currentSizeShip = ship.getSize() - 2;
        } else {
            currentSizeShip = ship.getSize();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            shipRec.y = shipRec.y - 5;
            currentSizeShip = ship.getSize() + 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            shipRec.x -= 500 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            shipRec.x += 500 * Gdx.graphics.getDeltaTime();
        }
        if (shipRec.x < -ship.getHalfSize()) {
            shipRec.x = -ship.getHalfSize();
        }
        if (shipRec.x > SCREEN_WIDTH - ship.getHalfSize()) {
            shipRec.x = SCREEN_WIDTH - ship.getHalfSize();
        }
        if (shipRec.y < ship.getHalfSize() / 3) {
            shipRec.y = ship.getHalfSize() / 3;
        }
        if (shipRec.y > SCREEN_HEIGHT - ship.getSize()) {
            shipRec.y = SCREEN_HEIGHT - ship.getSize();
        }
        final Iterator<Rectangle> iterator = stars.iterator();
        while (iterator.hasNext()) {
            final Rectangle stars = iterator.next();
            stars.y -= 110 * Gdx.graphics.getDeltaTime();
            if (stars.y < 0) iterator.remove();
        }
        if (shipRec.overlaps(enemyShipRec)) { // Сталкновение
            bulletSound.play();
        }
        /*
         * проверяем, сколько времени прошло, с тех пор как была создана
         * новая звезда и если необходимо, создаем еще одну новую звезду.
         * */
        if (TimeUtils.nanoTime() - lastStarTime > 10000000) starFall();
        spriteBatch.begin();
        for (final Rectangle stars : stars) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                stars.x += 20 * Gdx.graphics.getDeltaTime();
                if (stars.x >= SCREEN_WIDTH) {
                    stars.x = 0;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                stars.x -= 20 * Gdx.graphics.getDeltaTime();
                if (stars.x <= 0) {
                    stars.x = SCREEN_WIDTH;
                }
            }
            spriteBatch.draw(starImage, stars.x, stars.y, 5, 5);
        }
        if (right) {
            enemyShipRec.x += 50 * Gdx.graphics.getDeltaTime();
            if (enemyShipRec.x >= SCREEN_WIDTH - ship.getSize()) {
                right = false;
            }
        }
        if (!right) {
            enemyShipRec.x -= 50 * Gdx.graphics.getDeltaTime();
            if (enemyShipRec.x <= 0) {
                right = true;
            }
        }
        spriteBatch.draw(enemyShipImage, enemyShipRec.x, enemyShipRec.y, ship.getSize(), ship.getSize());
        spriteBatch.draw(shipImage, shipRec.x, shipRec.y, currentSizeShip, currentSizeShip);
        spriteBatch.end();
    }

    /*
     * Этот метод вызывается при каждом изменении размера экрана в игре и когда игра
     * не находится в состоянии паузы. Вызывается один раз сразу после create() метода.
     * Параметры новой ширины и высоты экрана в пикселях.
     * */
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    /*
     * На Android этот метод вызывается, когда нажата кнопка Home или при входящем звонке.
     * На Desktop этот метод вызывается при выходе из приложения, перед dispose().
     * Это хорошее место, чтобы сохранить состояние игры.
     * */
    @Override
    public void pause() {
        super.pause();
    }

    /*
     * Этот метод вызывается только на Android,
     * когда приложение возобновляет работу из состояния приостановки (паузы).
     * */
    @Override
    public void resume() {
        super.resume();
    }

    /*
     * Вызывается когда приложение уничтожено. Ему предшествует вызов pause().
     * */
    @Override
    public void dispose() {
        spriteBatch.dispose();
        backgroundImage.dispose();
        backgroundMusic.dispose();
        bulletSound.dispose();
        shipImage.dispose();
        starImage.dispose();
        enemyShipImage.dispose();
    }

}