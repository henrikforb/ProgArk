package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ImpossibleGravity;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = ImpossibleGravity.WIDTH;
		config.height = ImpossibleGravity.HEIGHT;
		config.title = ImpossibleGravity.TITLE;
		new LwjglApplication(new ImpossibleGravity(), config);
	}
}
