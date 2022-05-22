package com.ionops.tryandrage;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.ionops.tryandrage.TryAndRage;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Try and Rage");
		config.setResizable(false);
		config.setWindowedMode(720, 1280);
		new Lwjgl3Application(new TryAndRage(), config);
	}
}
