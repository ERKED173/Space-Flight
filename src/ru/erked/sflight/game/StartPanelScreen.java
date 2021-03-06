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

public class StartPanelScreen implements Screen{

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
	private String schBack;
	
	//"Start" Button
	private SFButtonS start;
	private SFButtonS monogram;
	
	//Fonts
	private static BitmapFont text;
	
	public StartPanelScreen(final StartSFlight game){
		this.game = game;
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
			back = new SFButtonS("btns/back", 0.132F*width, width - 0.2F*width, -0.005F*height, 1.0F);
		}else{
			back = new SFButtonS("btns/RU/backR", 0.132F*width, width - 0.2F*width, -0.005F*height, 1.0F);
		}

		start = new SFButtonS("btns/start", 0.3F*width, 0.65F*width, 0.5F*height, 1.0F);
		start.setY(0.75F*height - 0.5F*start.getHeight());
		monogram = new SFButtonS("btns/monogramm", 0.5F*width, 0.05F*width, 0.5F*height, 1.0F);
		monogram.setY(0.75F*height - 0.5F*monogram.getHeight());
		
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param2.color = Color.WHITE;
		param2.size = 40;
		if(INF.lngRussian){
			param2.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param2);
		}else{
			text = genUS.generateFont(param2);
		}
		text.getData().setScale((float)(0.00075F*width));
		
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

		if(!INF.lngRussian){
			text.draw(game.batch, "Control panel", 0.4F*width, 0.965F*height);
			if(!INF.currentPlanet.equals("null")){
				text.draw(game.batch, "Current planet: " + CurPR.getCurPlanet().getNameUS(), 0.05F*width, 0.4F*height);
			}else{
				text.draw(game.batch, "The planet is not selected.", 0.05F*width, 0.4F*height);
			}
			if(!INF.currentRocket.equals("null")){
				text.draw(game.batch, "Current rocket: " + CurPR.getCurRocket().getNameUS(), 0.05F*width, 0.4F*height - 1.5F*text.getCapHeight());
			}else{
				text.draw(game.batch, "The rocket is not selected.", 0.05F*width, 0.4F*height - 1.5F*text.getCapHeight());
			}
			if(!INF.currentGun.equals("null")){
				text.draw(game.batch, "Current space gun: " + CurPR.getCurGun().getNameUS(), 0.05F*width, 0.4F*height - 3.0F*text.getCapHeight());
			}else{
				text.draw(game.batch, "The space gun is not selected.", 0.05F*width, 0.4F*height - 3.0F*text.getCapHeight());
			}
		}else{
			if(!INF.currentPlanet.equals("null")){
				text.draw(game.batch, "Текущая планета: " + CurPR.getCurPlanet().getNameRU(), 0.05F*width, 0.4F*height);
			}else{
				text.draw(game.batch, "Планета не выбрана.", 0.05F*width, 0.4F*height);
			}
			if(!INF.currentRocket.equals("null")){
				text.draw(game.batch, "Текущая ракета: " + CurPR.getCurRocket().getNameRU(), 0.05F*width, 0.4F*height - 1.5F*text.getCapHeight());
			}else{
				text.draw(game.batch, "Ракета не выбрана.", 0.05F*width, 0.4F*height - 1.5F*text.getCapHeight());
			}
			if(!INF.currentGun.equals("null")){
				text.draw(game.batch, "Текущее космооружие: " + CurPR.getCurGun().getNameRU(), 0.05F*width, 0.4F*height - 3.0F*text.getCapHeight());
			}else{
				text.draw(game.batch, "Космооружие не выбрано.", 0.05F*width, 0.4F*height - 3.0F*text.getCapHeight());
			}
		}
		if(!INF.lngRussian){
			if(!INF.currentPlanet.equals("null") && !INF.currentRocket.equals("null") && !INF.currentGun.equals("null")){
				text.draw(game.batch, "Rocket is ready to start.", 0.05F*width, 0.4F*height - 6.0F*text.getCapHeight());
			}
		}else{
			if(!INF.currentPlanet.equals("null") && !INF.currentRocket.equals("null") && !INF.currentGun.equals("null")){
				text.draw(game.batch, "Ракета готова к запуску.", 0.05F*width, 0.4F*height - 6.0F*text.getCapHeight());
			}
		}
		
		
		if(controller.isOn(start.getX(), start.getY(), start.getWidth(), start.getHeight())){
			start.setMode(true);
			start.setY(0.65F*height - 0.51F*start.getHeight());
		}else{
			start.setMode(false);
			start.setY(0.65F*height - 0.5F*start.getHeight());
		}
		start.getSprite().draw(game.batch);
		
		if(controller.isOn(monogram.getX(), monogram.getY(), monogram.getWidth(), monogram.getHeight())){
			monogram.setMode(true);
			monogram.setY(0.65F*height - 0.51F*monogram.getHeight());
		}else{
			monogram.setMode(false);
			monogram.setY(0.65F*height - 0.5F*monogram.getHeight());
		}
		monogram.getSprite().draw(game.batch);
		
		game.batch.end();
		
		buttonListener();
		
	}
	
	private void buttonListener(){
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new ControlCentreScreen(game));
			this.dispose();
		}
		
		if(controller.isClicked(monogram.getX(), monogram.getY(), monogram.getWidth(), monogram.getHeight())){
			game.setScreen(new PlanetScreen(game));
			this.dispose();
		}
		
		if(controller.isClicked(start.getX(), start.getY(), start.getWidth(), start.getHeight())){
			if(CurPR.getCurRocket() != null){
				if(CurPR.getCurPlanet() != null){
					if(CurPR.getCurGun() != null){
						INF.fuel -= CurPR.getCurPlanet().getFuelTo();
						INF.isLaunch = true;
						game.setScreen(new GameScreen(game));
						this.dispose();
					}
				}
			}
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