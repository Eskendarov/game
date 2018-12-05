package ru.eskendarov;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StarWars extends ApplicationAdapter {

    private SpriteBatch spriteBatch; // Работает с графическим процессором.
    private Texture texture;

    /*
     * Метод вызывается один раз при создании приложения.
     * */
    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        texture = new Texture("textures/sky.jpg");
    }

    /*
     * Метод вызывается игровым циклом приложения каждый раз,
     * когда должны быть выполнена визуализация.
     * Обновление логики игры обычно выполняется в этом методе.
     * */
    @Override
    public void render() { // 60 раз в минуту.
        Gdx.gl.glClearColor(0, 0, 0, 1); // Позиции объектов.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Очищает экран.
        spriteBatch.begin();
        spriteBatch.draw(texture, 0, 0); // Задает цвет фона.
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
        texture.dispose();
    }

}
