package ru.erked.sflight.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import ru.erked.sflight.controllers.SFlightInputController;
import ru.erked.sflight.menu.MainMenu;
import ru.erked.sflight.random.ImgResDraw;
import ru.erked.sflight.random.InfoAndStats;

public class StatisticScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	
	//Фон
	private Texture backgroundTexture; //Текстура фона
	public static Sprite backgroundSprite; //Спрайт фона
	
	//Копка "Back"
	private Sprite backButtonInactiveSprite;
	private Sprite backButtonActiveSprite;
	private float backButtonX;
	private float backButtonY;
	private float backButtonWidth;
	private float backButtonHeight;
	public static float backButtonTentionIndex; //Соотношение сторон кнопки
	
	//Шрифты
	private static BitmapFont header;
	private static BitmapFont text;
	public static String statsUS = "STATISTICS";
	public static String statsRU = "СТАТИСТИКА";
	public static String elapsedTime;
	private static long hours;
	private static long minutes;
	private static long seconds;
	public static long launches;
	
	public StatisticScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {

		batch = new SpriteBatch();
		controller = new SFlightInputController();
		
		MainMenu.music.play();
		
		//Фон\\
		backgroundTexture = new Texture("bckgrnd/scoreboard.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setBounds(0.0F, 0.0F, width, height);
		
		//Кнопка "Back"\\
		backButtonInactiveSprite = new Sprite(ImgResDraw.backButtonInactive);
		backButtonActiveSprite = new Sprite(ImgResDraw.backButtonActive);
		if(InfoAndStats.lngRussian){
			backButtonInactiveSprite.setTexture(ImgResDraw.backButtonInactiveRU);
			backButtonActiveSprite.setTexture(ImgResDraw.backButtonActiveRU);
		}
		backButtonTentionIndex = (float)ImgResDraw.backButtonInactive.getWidth()/ImgResDraw.backButtonInactive.getHeight();
		backButtonWidth = 0.132F*width;
		backButtonHeight = backButtonWidth/backButtonTentionIndex;
		backButtonX = width - 0.015F*width - backButtonWidth;
		backButtonY = 0 + 0.005F*height;
		backButtonInactiveSprite.setBounds(backButtonX, backButtonY, backButtonWidth, backButtonHeight);
		backButtonActiveSprite.setBounds(backButtonX, backButtonY, backButtonWidth, backButtonHeight);
		
		//Шрифты\\
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.color = Color.SKY;
		param.size = 40;
		if(InfoAndStats.lngRussian){
			param.characters = FONT_CHARS_RU;
			header = genRU.generateFont(param);
		}else{
			header = genUS.generateFont(param);
		}
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param2.color = Color.WHITE;
		param2.size = 40;
		if(InfoAndStats.lngRussian){
			param2.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param2);
		}else{
			text = genUS.generateFont(param2);
		}
		
		header.getData().setScale((float)(0.0015F*width));
		text.getData().setScale((float)(0.00075F*width));
		
		genRU.dispose();
		genUS.dispose();
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		backgroundSprite.draw(batch);
		
		//Отрисовка кнопки "Back"//
		if(controller.isOn(backButtonX, backButtonY, backButtonWidth, backButtonHeight))
			backButtonActiveSprite.draw(batch);
		else
			backButtonInactiveSprite.draw(batch);
		
		//Отрисовка шрифтов//
		seconds = InfoAndStats.elapsedTime/60;
		minutes = (int)seconds/60;
		hours = (int)minutes/60;
		launches = InfoAndStats.launch;
		if(!InfoAndStats.lngRussian){
			elapsedTime = "Time in the game: " + Integer.toString((int) hours) + "h " + Integer.toString((int) minutes%60) + "m " + Integer.toString((int) seconds%60) + "s";
			header.draw(batch, statsUS, width/2 - (int)(0.055F*width)*2.5F, 0.9F*height);
			text.draw(batch, elapsedTime, 0.075F*width, 0.75F*height);
			text.draw(batch, "Rocket launches: " + Long.toString((int)(launches)), 0.075F*width, 0.7F*height);
			text.draw(batch, "Cosmocoins: " + Long.toString((int)(InfoAndStats.money)), 0.075F*width, 0.65F*height);
			text.draw(batch, "Fuel: " + Long.toString((int)(InfoAndStats.fuel)), 0.075F*width, 0.6F*height);
			text.draw(batch, "Metal: " + Long.toString((int)(InfoAndStats.metal)), 0.075F*width, 0.55F*height);
		}else{
			elapsedTime = "Время в игре: " + Integer.toString((int) hours) + "ч " + Integer.toString((int) minutes%60) + "м " + Integer.toString((int) seconds%60) + "с";
			header.draw(batch, statsRU, width/2 - (int)(0.185F*width), 0.9F*height);
			text.draw(batch, elapsedTime, 0.075F*width, 0.75F*height);
			text.draw(batch, "Запусков ракет: " + Long.toString((int)(launches)), 0.075F*width, 0.7F*height);
			text.draw(batch, "Космокоины: " + Long.toString((int)(InfoAndStats.money)), 0.075F*width, 0.65F*height);
			text.draw(batch, "Топливо: " + Long.toString((int)(InfoAndStats.fuel)), 0.075F*width, 0.6F*height);
			text.draw(batch, "Метал: " + Long.toString((int)(InfoAndStats.metal)), 0.075F*width, 0.55F*height);
		}
		
		batch.end();
		
		//Слушатель нажатия на кнопку "Back"//
		if(controller.isClicked(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
			game.setScreen(new AnalyticCentreScreen(game));
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
		backgroundTexture.dispose();
		game.dispose();
		batch.dispose();
		header.dispose();
		text.dispose();
	}

}