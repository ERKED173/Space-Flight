package ru.erked.sflight.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import ru.erked.sflight.StartSFlight;
import ru.erked.sflight.controllers.SFlightInputController;
import ru.erked.sflight.menu.MainMenu;
import ru.erked.sflight.random.INF;
import ru.erked.sflight.tech.SFButtonS;

public class StatisticScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private final StartSFlight game;
	private SFlightInputController controller;
	
	//Background
	private Texture backgroundTexture;
	public static Sprite backgroundSprite;
	
	//"Back" Button
	private SFButtonS back;
	
	//Fonts
	private static BitmapFont header;
	private static BitmapFont text;
	public static String elapsedTime;
	private static long hours;
	private static long minutes;
	private static long seconds;
	public static long launches;
	
	public StatisticScreen(final StartSFlight game){
		this.game = game;
	}
	
	@Override
	public void show() {

		controller = new SFlightInputController();
		
		MainMenu.music.play();
		
		backgroundTexture = new Texture("bckgrnd/scoreboard.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setBounds(0.0F, 0.0F, width, height);
		
		if(!INF.lngRussian){
			back = new SFButtonS("btns/back", 0.132F*width, width - 0.185F*width, 0.045F*height, 1.0F);
		}else{
			back = new SFButtonS("btns/RU/backR", 0.132F*width, width - 0.185F*width, 0.045F*height, 1.0F);
		}
		
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		param.color = Color.SKY;
		param.size = 40;
		if(INF.lngRussian){
			param.characters = FONT_CHARS_RU;
			header = genRU.generateFont(param);
		}else{
			header = genUS.generateFont(param);
		}
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param2.color = Color.WHITE;
		param2.size = 40;
		if(INF.lngRussian){
			param2.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param2);
			header.getData().setScale((float)(0.0025F*height));
			text.getData().setScale((float)(0.00115F*height));
		}else{
			text = genUS.generateFont(param2);
			header.getData().setScale((float)(0.003F*height));
			text.getData().setScale((float)(0.0015F*height));
		}
		
		genRU.dispose();
		genUS.dispose();
	}

	@Override
	public void render(float delta) {
		INF.elapsedTime++;
		resourcesCheck();
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		
		backgroundSprite.draw(game.batch);
		
		if(controller.isOn(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}
		back.getSprite().draw(game.batch);
		
		seconds = INF.elapsedTime/60;
		minutes = (int)seconds/60;
		hours = (int)minutes/60;
		launches = INF.launch;
		long sec = (INF.survTimeRecord/60)%60;
		long min = INF.survTimeRecord/3600;
		if(!INF.lngRussian){
			elapsedTime = "Time in the game: " + Integer.toString((int) hours) + "h " + Integer.toString((int) minutes%60) + "m " + Integer.toString((int) seconds%60) + "s";
			header.draw(game.batch, "STATISTICS", 0.315F*width, 0.9F*height);
			text.draw(game.batch, elapsedTime, 0.075F*width, 0.9F*height - 2.0F*header.getCapHeight());
			text.draw(game.batch, "Rocket launches: " + Long.toString((int)(launches)), 0.075F*width, 0.9F*height - 2.85F*header.getCapHeight());
			text.draw(game.batch, "Record of the enemy destruction: " + INF.killsRecord, 0.075F*width, 0.9F*height - 4.55F*header.getCapHeight());
			text.draw(game.batch, "Record of the survived time: " + min + " min " + sec + " sec", 0.075F*width, 0.9F*height - 5.4F*header.getCapHeight());
		}else{
			elapsedTime = "Время в игре: " + Integer.toString((int) hours) + "ч " + Integer.toString((int) minutes%60) + "м " + Integer.toString((int) seconds%60) + "с";
			header.draw(game.batch, "СТАТИСТИКА", 0.315F*width, 0.9F*height);
			text.draw(game.batch, elapsedTime, 0.075F*width, 0.9F*height - 2.0F*header.getCapHeight());
			text.draw(game.batch, "Запусков ракет: " + Long.toString((int)(launches)), 0.075F*width, 0.9F*height - 2.85F*header.getCapHeight());
			text.draw(game.batch, "Рекорд уничтожения противников: " + INF.killsRecord, 0.075F*width, 0.9F*height - 4.55F*header.getCapHeight());
			text.draw(game.batch, "Рекорд прожитого времени: " + min + " мин " + sec + " сек", 0.075F*width, 0.9F*height - 5.4F*header.getCapHeight());
		}
		
		game.batch.end();
		
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new AnalyticCentreScreen(game));
			this.dispose();
		}
		
	}

	private void resourcesCheck(){
		if(INF.elapsedTime%(3600/INF.moneyAmount) == 0){
			INF.money++;
		}
		if(INF.elapsedTime%(3600/INF.fuelAmount) == 60){
			INF.fuel++;
		}
		if(INF.elapsedTime%(3600/INF.metalAmount) == 120){
			INF.metal++;
		}
		if(INF.money>INF.moneyFull) INF.money = INF.moneyFull;
		if(INF.fuel>INF.fuelFull) INF.fuel = INF.fuelFull;
		if(INF.metal>INF.metalFull) INF.metal = INF.metalFull;
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
		header.dispose();
		text.dispose();
	}

}