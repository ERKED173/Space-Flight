package ru.erked.sflight.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import ru.erked.sflight.random.InfoAndStats;

public class TechnicScreen implements Screen{

	private Game game;
	private float timer;
	private Screen screen;
	private float time;
	
	public TechnicScreen(Game game, Screen screen, float time){
		this.game = game;
		this.screen = screen;
		this.time = time;
	}
	
	@Override
	public void show() {
		timer = 0.0F;
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		timer+=delta;
		
		if(timer > time){
			this.dispose();
			game.setScreen(screen);
		}
	}

	@Override
	public void resize(int width, int height) {
	
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
