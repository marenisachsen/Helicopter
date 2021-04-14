package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.mygdx.game.Helicopter;
import com.mygdx.game.HelicopterGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = HelicopterGame.WORLD_WIDTH;
		config.height = HelicopterGame.WORLD_HEIGHT;
		config.title = HelicopterGame.title;
		new LwjglApplication(new HelicopterGame(), config);
	}
}







