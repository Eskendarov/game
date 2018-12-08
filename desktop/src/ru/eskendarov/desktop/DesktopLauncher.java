package ru.eskendarov.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.eskendarov.StarWars;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Star Wars";
        //		config.useGL20 = false;
        config.width = 360;
        config.height = 640;
        new LwjglApplication(new StarWars(), config);
    }

}