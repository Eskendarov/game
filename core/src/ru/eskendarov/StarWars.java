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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;
import java.util.Random;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static ru.eskendarov.Stars.stars;

@EqualsAndHashCode(callSuper = true)
@Data
public class StarWars extends ApplicationAdapter {


    private Random random;
    private int screenWidth = 360;
    private int screenHeight = 720;
    private SpriteBatch spriteBatch; // Работает с графическим процессором.
    private Texture backgroundImage;
    private Texture starImage;
    private Texture shipImage;
    private Music backgroundMusic;
    private Sound newGameSound;
    private Sound bulletSound;
    private OrthographicCamera camera;
    private Rectangle spaceShip;
    private Ship ship;
    private Vector2 speedStar = new Vector2();
    private int currentSize;
    private static float NUM = 1.5f;

    /*
     * Метод вызывается один раз при создании приложения.
     * */
    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        shipImage = new Texture(Gdx.files.internal("textures/raptor.png"));
        starImage = new Texture(Gdx.files.internal("textures/star.png"));
        spaceShip = new Rectangle();
        backgroundImage = new Texture(Gdx.files.internal("textures/space.jpg"));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/space1.mp3"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/blaster.wav"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, screenWidth, screenHeight);
        spaceShip.x = screenWidth / 2 - Ship.halfSize;
        spaceShip.y = 20;
        stars = new Array<Rectangle>();

        backgroundMusic.setLooping(true);
        backgroundMusic.play();
        bulletSound.play();
        //        newGameSound.play();
    }

    private void starFall() {
        final Rectangle star = new Rectangle();
        star.x = MathUtils.random(screenWidth);
        star.y = screenHeight;
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
        spriteBatch.draw(backgroundImage, 0, 0, screenWidth, screenHeight);
        spriteBatch.end();

        if (Gdx.input.isTouched()) {
            Ship.touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(Ship.touchPosition);
            spaceShip.x = Ship.touchPosition.x - Ship.size / 2;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            spaceShip.y = spaceShip.y + 5;
            currentSize = Ship.size - 2;
            System.out.println(String.format("x= %s y= %s", spaceShip.x, spaceShip.y));
        } else {
            currentSize = Ship.size;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            spaceShip.y = spaceShip.y - 5f;
            currentSize = Ship.size + 2;
            System.out.println(String.format("x= %s y= %s", spaceShip.x, spaceShip.y));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            spaceShip.x -= 500 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            spaceShip.x += 500 * Gdx.graphics.getDeltaTime();
        }
        if (spaceShip.x < -Ship.halfSize) {
            spaceShip.x = -Ship.halfSize;
        }
        if (spaceShip.x > screenWidth - Ship.halfSize) {
            spaceShip.x = screenWidth - Ship.halfSize;
        }
        if (spaceShip.y < 0) {
            spaceShip.y = 0;
        }
        if (spaceShip.y > screenHeight - Ship.size) {
            spaceShip.y = screenHeight - Ship.size;
        }
        final Iterator<Rectangle> iterator = stars.iterator();
        while (iterator.hasNext()) {
            final Rectangle stars = iterator.next();
            stars.y -= 100 * Gdx.graphics.getDeltaTime();
            if (stars.y < 0) iterator.remove();
        }
        spriteBatch.begin();
        for (final Rectangle stars : stars) {
            spriteBatch.draw(starImage, stars.x, stars.y, 5, 5);
        }
        starFall();
        spriteBatch.draw(shipImage, spaceShip.x, spaceShip.y, currentSize, currentSize);
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
    }

}
