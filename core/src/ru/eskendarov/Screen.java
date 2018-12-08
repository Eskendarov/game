package ru.eskendarov;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import lombok.Getter;

@Getter
public class Screen {

    public final static int WIDTH = 360;
    public final static int HALF_WIDTH = WIDTH / 2;
    public final static int HEIGHT = 720;
    public final static OrthographicCamera camera = new OrthographicCamera();
    public final static Vector3 touchPosition = new Vector3();

    Screen(final boolean yDownOrient) {
        camera.setToOrtho(yDownOrient, WIDTH, HEIGHT);
    }

}
