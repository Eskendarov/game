package ru.eskendarov.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.eskendarov.StarWars;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Star Wars";
//		config.useGL20 = false;
		config.width = 320;
		config.height = 480;
		new LwjglApplication(new StarWars(), config);
	}
}
