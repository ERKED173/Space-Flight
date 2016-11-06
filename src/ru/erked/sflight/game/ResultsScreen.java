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
import ru.erked.sflight.tech.CurPR;
import ru.erked.sflight.tech.SFButtonS;

public class ResultsScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private final StartSFlight game;
	private SFlightInputController controller;
	
	//Resources
	private int exCoin;
	private int exFuel;
	private int exMetal;
	private long survTime;
	private int kills;
	
	//Background
	private Texture backgroundTexture;
	public static Sprite backgroundSprite;
	
	//"Continue" Button
	private SFButtonS back;
	private String schBack;
	
	//Fonts
	private static BitmapFont text;
	
	public ResultsScreen(final StartSFlight game, int exCoin, int exFuel, int exMetal, long survTime, int kills){
		this.game = game;
		this.exCoin = exCoin;
		this.exFuel = exFuel;
		this.exMetal = exMetal;
		this.survTime = survTime;
		this.kills = kills;
	}
	
	@Override
	public void show() {

		controller = new SFlightInputController();
		
		MainMenu.music.play();
		
		backgroundTexture = new Texture("bckgrnd/resource/resource_1.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setBounds(0.0F, 0.0F, width, height);
		schBack = "bckgrnd/resource/resource_1.png";
		
		if(!INF.lngRussian){
			back = new SFButtonS("btns/continue", 0.185F*width, width - 0.25F*width, 0.035F*height, 1.0F);
		}else{
			back = new SFButtonS("btns/RU/continueR", 0.25F*width, width - 0.35F*width, 0.035F*height, 1.0F);
		}
		
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param2.color = Color.WHITE;
		param2.size = 40;
		if(INF.lngRussian){
			param2.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param2);
			text.getData().setScale((float)(0.00125F*height));
		}else{
			text = genUS.generateFont(param2);
			text.getData().setScale((float)(0.00150F*height));
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
		
		drawBackground();
		
		game.batch.begin();
		
		backgroundSprite.draw(game.batch);
		
		if(controller.isOn(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}
		back.getSprite().draw(game.batch);

		long sec = (survTime/60)%60;
		long min = survTime/3600;
		if(!INF.lngRussian){
			text.draw(game.batch, "Results", 0.45F*width, 0.965F*height);
			text.draw(game.batch, "Extracted:", 0.05F*width, 0.965F*height-3.0F*text.getCapHeight());
			text.draw(game.batch, exCoin + " cosmocoins", 0.05F*width, 0.965F*height-4.5F*text.getCapHeight());
			text.draw(game.batch, exFuel + " fuel", 0.05F*width, 0.965F*height-6.0F*text.getCapHeight());
			text.draw(game.batch, exMetal + " metal", 0.05F*width, 0.965F*height-7.5F*text.getCapHeight());
			if(kills > INF.killsRecord){
				text.draw(game.batch, "You have destroyed " + kills + " enemies  -  NEW RECORD!", 0.05F*width, 0.965F*height-10.5F*text.getCapHeight());
				INF.killsRecord = kills;
			}else{
				text.draw(game.batch, "You have destroyed " + kills + " enemies", 0.05F*width, 0.965F*height-10.5F*text.getCapHeight());
			}
			if(survTime > INF.survTimeRecord){
				text.draw(game.batch, "You have survived " + min + " min " + sec + " sec   -  NEW RECORD!", 0.05F*width, 0.965F*height-12.0F*text.getCapHeight());
				INF.survTimeRecord = survTime;
			}else{
				text.draw(game.batch, "You have survived " + min + " min " + sec + " sec ", 0.05F*width, 0.965F*height-12.0F*text.getCapHeight());
			}
			text.draw(game.batch, "Bonus:", 0.05F*width, 0.965F*height-15.0F*text.getCapHeight());
			text.draw(game.batch, (int)(kills/30) + " cosmocoins", 0.05F*width, 0.965F*height-16.5F*text.getCapHeight());
			text.draw(game.batch, min + " fuel", 0.05F*width, 0.965F*height-18.0F*text.getCapHeight());
			text.draw(game.batch, CurPR.getCurPlanet().getLevel() + " metal", 0.05F*width, 0.965F*height-19.5F*text.getCapHeight());
		}else{
			text.draw(game.batch, "Результаты", 0.4F*width, 0.965F*height);
			text.draw(game.batch, "Добыто:", 0.05F*width, 0.965F*height-3.0F*text.getCapHeight());
			text.draw(game.batch, exCoin + " космокоинов", 0.05F*width, 0.965F*height-4.5F*text.getCapHeight());
			text.draw(game.batch, exFuel + " топлива", 0.05F*width, 0.965F*height-6.0F*text.getCapHeight());
			text.draw(game.batch, exMetal + " металла", 0.05F*width, 0.965F*height-7.5F*text.getCapHeight());
			if(kills > INF.killsRecord){
				text.draw(game.batch, "Вы уничтожили " + kills + " противников  -  НОВЫЙ РЕКОРД!", 0.05F*width, 0.965F*height-10.5F*text.getCapHeight());
				INF.killsRecord = kills;
			}else{
				text.draw(game.batch, "Вы уничтожили " + kills + " противников", 0.05F*width, 0.965F*height-10.5F*text.getCapHeight());
			}
			if(survTime > INF.survTimeRecord){
				text.draw(game.batch, "Вы выжили " + min + " мин " + sec + " сек   -  НОВЫЙ РЕКОРД!", 0.05F*width, 0.965F*height-12.0F*text.getCapHeight());
				INF.survTimeRecord = survTime;
			}else{
				text.draw(game.batch, "Вы выжили " + min + " мин " + sec + " сек ", 0.05F*width, 0.965F*height-12.0F*text.getCapHeight());
			}
			text.draw(game.batch, "Бонус:", 0.05F*width, 0.965F*height-15.0F*text.getCapHeight());
			text.draw(game.batch, (int)(kills/30) + " космокоинов", 0.05F*width, 0.965F*height-16.5F*text.getCapHeight());
			text.draw(game.batch, min + " топлива", 0.05F*width, 0.965F*height-18.0F*text.getCapHeight());
			text.draw(game.batch, CurPR.getCurPlanet().getLevel() + " металла", 0.05F*width, 0.965F*height-19.5F*text.getCapHeight());
		}
		
		game.batch.end();
		
		buttonListener();
		
	}
	
	private void buttonListener(){
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			/***/
			INF.money+=(int)(kills/30);
			INF.fuel+=survTime/3600;
			INF.metal+=CurPR.getCurPlanet().getLevel();
			INF.money+=exCoin;
			INF.fuel+=exFuel;
			INF.metal+=exMetal;
			INF.launch++;
			INF.currentGun = "null";
			INF.currentPlanet = "null";
			INF.currentRocket = "null";
			/***/
			game.setScreen(new GameScreen(game));
			this.dispose();
		}
	}
	
	private void drawBackground(){
		if(INF.elapsedTime % 15 == 0){
			backgroundSprite.setTexture(new Texture(schBack));
			if(schBack.equals("bckgrnd/resource/resource_1.png")) schBack = "bckgrnd/resource/resource_2.png";
			else if(schBack.equals("bckgrnd/resource/resource_2.png")) schBack = "bckgrnd/resource/resource_3.png";
			else if(schBack.equals("bckgrnd/resource/resource_3.png")) schBack = "bckgrnd/resource/resource_4.png";
			else if(schBack.equals("bckgrnd/resource/resource_4.png")) schBack = "bckgrnd/resource/resource_5.png";
			else if(schBack.equals("bckgrnd/resource/resource_5.png")) schBack = "bckgrnd/resource/resource_6.png";
			else if(schBack.equals("bckgrnd/resource/resource_6.png")) schBack = "bckgrnd/resource/resource_7.png";
			else if(schBack.equals("bckgrnd/resource/resource_7.png")) schBack = "bckgrnd/resource/resource_8.png";
			else if(schBack.equals("bckgrnd/resource/resource_8.png")) schBack = "bckgrnd/resource/resource_9.png";
			else if(schBack.equals("bckgrnd/resource/resource_9.png")) schBack = "bckgrnd/resource/resource_10.png";
			else if(schBack.equals("bckgrnd/resource/resource_10.png")) schBack = "bckgrnd/resource/resource_11.png";
			else if(schBack.equals("bckgrnd/resource/resource_11.png")) schBack = "bckgrnd/resource/resource_12.png";
			else if(schBack.equals("bckgrnd/resource/resource_12.png")) schBack = "bckgrnd/resource/resource_13.png";
			else if(schBack.equals("bckgrnd/resource/resource_13.png")) schBack = "bckgrnd/resource/resource_1.png";
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
		text.dispose();
	}

}