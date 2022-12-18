package com.ytoraman;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.f1.CheeseGame;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class CheeseLauncher {
	public static void main (String[] arg) {
		CheeseGame config = new CheeseGame();
		Lwjgl3Application launcher = new Lwjgl3Application(config);
	}
}
