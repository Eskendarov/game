package ru.eskendarov;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class StarWars extends Game {

    private Resources resources;
    private Controller controller;

    /*
     * Метод вызывается один раз при создании приложения.
     * */
    @Override
    public void create() {
        new Screen(false);
        resources = new Resources();
        resources.initSound();
        controller = new Controller();
    }

    /*
     * Метод вызывается игровым циклом приложения каждый раз,
     * когда должна быть выполнена визуализация.
     * Обновление логики игры обычно выполняется в этом методе.
     * */
    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Очищает экран.
        controller.film();
    }

    /*
     * Этот метод вызывается при каждом изменении размера экрана в игре и когда игра
     * не находится в состоянии паузы. Вызывается один раз сразу после create() метода.
     * Параметры новой ширины и высоты экрана в пикселях.
     * */
    @Override
    public void resize(int width, int height) {
        //        super.resize(width, height);
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
        resources.dispose();
    }

}