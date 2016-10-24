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

public class ScienceCentreScreen implements Screen{
	
	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	
	//Background
	private Texture backgroundTexture;
	public static Sprite backgroundSprite;
	private float backgroundX;
	private float backgroundY;
	public static final float backgroundTentionIndex = (float)width/600.0F;
	
	//"Back" Button
	private SFButtonS back;
	
	//Text
	private static BitmapFont text;
	
	private Sprite blackAlpha = new Sprite(new Texture("objects/black.png"));
	private float alp = 1.0F;
	private boolean isTransScience;
	
	public ScienceCentreScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		controller = new SFlightInputController();

		MainMenu.music.play();
		
		backgroundTexture = new Texture("bckgrnd/scienceCentreInside.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundX = 0.0F;
		backgroundY = (-1)*(337*backgroundTentionIndex)/2 + height/2;
		backgroundSprite.setBounds(backgroundX, backgroundY, width, backgroundTentionIndex*337.0F);
		
		if(!InfoAndStats.lngRussian){
			back = new SFButtonS("btns/back", 0.132F*width, width - 0.147F*width, 0.005F*height, 1.0F);
		}else{
			back = new SFButtonS("btns/RU/backR", 0.132F*width, width - 0.147F*width, 0.005F*height, 1.0F);
		}
		
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		FreeTypeFontParameter paramFail = new FreeTypeFontParameter();
		FreeTypeFontParameter paramSuc = new FreeTypeFontParameter();
		param.color = Color.WHITE;
		param.size = 40;
		paramFail.color = Color.RED;
		paramFail.size = 40;
		paramSuc.color = Color.GREEN;
		paramSuc.size = 40;
		if(InfoAndStats.lngRussian){
			param.characters = FONT_CHARS_RU;
			paramFail.characters = FONT_CHARS_RU;
			paramSuc.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param);

		}else{
			text = genUS.generateFont(param);
		}
		text.getData().setScale((float)(0.0006F*width));
		
		isTransScience = false;
		blackAlpha.setBounds(0.0F, 0.0F, width, height);
		blackAlpha.setAlpha(1.0F);
		
	}
	
	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		if(alp>0.0F && (!isTransScience)){
			blackAlpha.setAlpha(alp);
			alp-=0.05F;
		}else if(!isTransScience){
			blackAlpha.setAlpha(0.0F);
			alp = 0.0F;
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		backgroundSprite.draw(batch);
		
		if(controller.isOn(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}
		back.getSprite().draw(batch);
		
		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Science centre", 0.01F*width, 0.99F*height);
		}else{
			text.draw(batch, "Научный центр", 0.01F*width, 0.99F*height);
		}
		
		blackAlpha.draw(batch);
		
		batch.end();
		
		btnListeners();
		resourcesCheck();
		
	}
	
	private void btnListeners(){
		if(controller.isClicked(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new GameScreen(game));
			this.dispose();
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
	
	private void textureDispose(){
		backgroundTexture.dispose();
	}
	
	@Override
	public void dispose() {
		game.dispose();
		batch.dispose();
		text.dispose();
		textureDispose();
		GameScreen.isFromMenu = false;
	}
	
}