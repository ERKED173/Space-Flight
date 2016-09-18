package ru.erked.sflight.splash;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.erked.sflight.menu.MainMenu;
import ru.erked.sflight.random.InfoAndStats;

public class SplashScreen implements Screen {

	/**Переменные*/
	private Game game;
	
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	public static final float splashTentionIndex = width/256;
	
	private SpriteBatch batch;
	private Texture splashImg;
	private Sprite splash;
	
	private float splashTimer;

	private float splashX;
	private float splashY;
	
	/**Конструктор SplashScreen*/
	public SplashScreen(Game game){
		this.game = game;
	}
	
	/**Вызывается при открытии скрина Splash*/
	@Override
	public void show() {
		/**Инициализация*/
		splashTimer = 0.0F;
		
		splashX = 0.0F;
		splashY = (-1)*(256*splashTentionIndex)/2 + height/2; //Выставляю относительно центра устройства по Y
		
		batch = new SpriteBatch();
		splashImg = new Texture("bckgrnd/splash_screen_2.png");
		splash = new Sprite(splashImg);
		/**Инициализация*/
		
		splash.setBounds(splashX, splashY, width, 256*splashTentionIndex);
	}

	/**Вызывается при показе скрина Splash*/
	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;

		/**Необходимо для уничтожения эффекта следов*/
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		splashTimer+=delta;
		
		/**Через примерно 3-4 секунды переключаемся на меню*/
		if(splashTimer < 3.0F){
			batch.begin();
			splash.draw(batch);
			batch.end();
		}else{
			this.dispose();
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
		splashImg.dispose();
		game.setScreen(new MainMenu(game));
	}

}
