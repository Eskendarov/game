package ru.eskendarov.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import lombok.Getter;

public class ScreenOption {

    public final static int WIDTH = 540;
    public final static int HEIGHT = 960;
    public final static int HALF_WIDTH = WIDTH / 2;
    public final static int HALF_HEIGHT = HEIGHT / 2;
    public final static OrthographicCamera camera = new OrthographicCamera();

    ScreenOption() {
        camera.setToOrtho(false, WIDTH, HEIGHT);
        Gdx.graphics.setResizable(false);
    }

}
