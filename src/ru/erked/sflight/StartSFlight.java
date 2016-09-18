package ru.erked.sflight;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import ru.erked.sflight.splash.SplashScreen;

public class StartSFlight extends Game {
	
	@Override
	public void create () {
		Gdx.input.setCatchBackKey(true);
		setScreen(new SplashScreen(this));
	}
}
