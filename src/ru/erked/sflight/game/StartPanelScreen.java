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
import ru.erked.sflight.random.InfoAndStats;
import ru.erked.sflight.tech.SFButtonS;

public class StartPanelScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	
	//Background
	private Texture backgroundTexture;
	public static Sprite backgroundSprite;
	
	//"Back" Button
	private SFButtonS back;
	private String schBack;
	
	//Fonts
	private static BitmapFont text;
	
	public StartPanelScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {

		batch = new SpriteBatch();
		controller = new SFlightInputController();
		
		MainMenu.music.play();
		
		backgroundTexture = new Texture("bckgrnd/resource/resource_1.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setBounds(0.0F, 0.0F, width, height);
		schBack = "bckgrnd/resource/resource_1.png";
		
		back = new SFButtonS("btns/back", 0.132F*width, width - 0.2F*width, -0.005F*height);

		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param2.color = Color.WHITE;
		param2.size = 40;
		if(InfoAndStats.lngRussian){
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
		InfoAndStats.elapsedTime++;
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		drawBackground();
		
		batch.begin();
		
		backgroundSprite.draw(batch);
		
		if(controller.isOn(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}
		back.getSprite().draw(batch);

		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Control panel", 0.4F*width, 0.965F*height);
		}else{
			text.draw(batch, "Панель управления", 0.35F*width, 0.965F*height);
		}
		
		batch.end();
		
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new ControlCentreScreen(game));
			this.dispose();
		}
		
		resourcesCheck();
		
	}
	
	private void drawBackground(){
		if(InfoAndStats.elapsedTime % 15 == 0){
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
		if(InfoAndStats.elapsedTime%(3600/InfoAndStats.moneyAmount) == 0){
			InfoAndStats.money++;
		}
		if(InfoAndStats.elapsedTime%(3600/InfoAndStats.fuelAmount) == 60){
			InfoAndStats.fuel++;
		}
		if(InfoAndStats.elapsedTime%(3600/InfoAndStats.metalAmount) == 120){
			InfoAndStats.metal++;
		}
		if(InfoAndStats.money>InfoAndStats.moneyFull) InfoAndStats.money = InfoAndStats.moneyFull;
		if(InfoAndStats.fuel>InfoAndStats.fuelFull) InfoAndStats.fuel = InfoAndStats.fuelFull;
		if(InfoAndStats.metal>InfoAndStats.metalFull) InfoAndStats.metal = InfoAndStats.metalFull;
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
		text.dispose();
	}

}