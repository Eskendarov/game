package ru.eskendarov.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.eskendarov.StarWars;

public class DesktopLauncher {

    public static void main(final String[] arg) {
        final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Star Wars";
        config.width = 540;
        config.height = 960;
        new LwjglApplication(new StarWars(), config);
    }

}