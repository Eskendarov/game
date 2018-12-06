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
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static ru.eskendarov.Stars.stars;

@EqualsAndHashCode(callSuper = true)
@Data
public class StarWars extends ApplicationAdapter {

    private int enemyShipPos;
    private int currentSizeShip;
    private final static int SCREEN_WIDTH = 360;
    private final static int SCREEN_HEIGHT = 720;
    private SpriteBatch spriteBatch; // Работает с графическим процессором.
    private Texture backgroundImage;
    private Texture starImage;
    private Texture shipImage;
    private Music backgroundMusic;
    private Sound newGameSound;
    private Sound bulletSound;
    private OrthographicCamera camera;
    private Texture enemyShipImage;
    private Rectangle spaceShipRec;
    private Rectangle enemyShipRec;

    /*
     * Метод вызывается один раз при создании приложения.
     * */
    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        shipImage = new Texture(Gdx.files.internal("textures/gunship.png"));
        enemyShipImage = new Texture(Gdx.files.internal("textures/skyWalker.png"));
        starImage = new Texture(Gdx.files.internal("textures/star.png"));
        backgroundImage = new Texture(Gdx.files.internal("textures/space.jpg"));

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/space1.mp3"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/blaster2.wav"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

        spaceShipRec = new Rectangle();
        spaceShipRec.x = SCREEN_WIDTH / 2 - Ship.halfSize;
        spaceShipRec.y = Ship.halfSize / 3;

        enemyShipRec = new Rectangle();
        enemyShipRec.x = SCREEN_WIDTH / 2 - Ship.halfSize;
        enemyShipRec.y = SCREEN_HEIGHT - Ship.size - Ship.halfSize / 3;

        stars = new Array<Rectangle>();

        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        //        newGameSound.play();
    }

    private void starFall() {
        final Rectangle star = new Rectangle();
        star.x = MathUtils.random(SCREEN_WIDTH);
        star.y = MathUtils.random(SCREEN_HEIGHT);
        stars.add(star);
    }

    /*
     * Метод вызывается игровым циклом приложения каждый раз,
     * когда должны быть выполнена визуализация.
     * Обновление логики игры обычно выполняется в этом методе.
     * */
    @Override
    public void render() { // 60 раз в минуту.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1); // Позиции объектов.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Очищает экран.
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(backgroundImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        spriteBatch.end();

        if (Gdx.input.isTouched()) {
            Ship.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(Ship.touchPosition);
            spaceShipRec.x = Ship.touchPosition.x - Ship.size / 2;
            bulletSound.play();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            bulletSound.play();//todo
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            spaceShipRec.y = spaceShipRec.y + 5;
            currentSizeShip = Ship.size - 2;
            System.out.println(String.format("x= %s y= %s", spaceShipRec.x, spaceShipRec.y));
        } else {
            currentSizeShip = Ship.size;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            spaceShipRec.y = spaceShipRec.y - 5f;
            currentSizeShip = Ship.size + 2;
            System.out.println(String.format("x= %s y= %s", spaceShipRec.x, spaceShipRec.y));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            spaceShipRec.x -= 500 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            spaceShipRec.x += 500 * Gdx.graphics.getDeltaTime();
        }
        if (spaceShipRec.x < -Ship.halfSize) {
            spaceShipRec.x = -Ship.halfSize;
        }
        if (spaceShipRec.x > SCREEN_WIDTH - Ship.halfSize) {
            spaceShipRec.x = SCREEN_WIDTH - Ship.halfSize;
        }
        if (spaceShipRec.y < Ship.halfSize / 3) {
            spaceShipRec.y = Ship.halfSize / 3;
        }
        if (spaceShipRec.y > SCREEN_HEIGHT - Ship.size) {
            spaceShipRec.y = SCREEN_HEIGHT - Ship.size;
        }
        final Iterator<Rectangle> iterator = stars.iterator();
        while (iterator.hasNext()) {
            final Rectangle stars = iterator.next();
            stars.y -= 70 * Gdx.graphics.getDeltaTime();
            if (stars.y < 0) iterator.remove();
        }
        spriteBatch.begin();
        for (final Rectangle stars : stars) {
            spriteBatch.draw(starImage, stars.x, stars.y, 3, 3);
        }
        starFall();
        spriteBatch.draw(enemyShipImage, MathUtils.random(SCREEN_WIDTH), enemyShipRec.y, Ship.size, Ship.size);
        spriteBatch.draw(shipImage, spaceShipRec.x, spaceShipRec.y, currentSizeShip, currentSizeShip);
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