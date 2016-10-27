package ru.erked.sflight.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector3;

import ru.erked.sflight.controllers.SFlightInputController;
import ru.erked.sflight.menu.MainMenu;
import ru.erked.sflight.random.InfoAndStats;
import ru.erked.sflight.tech.SFButtonA;

public class PlanetScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	public static OrthographicCamera camera;
	
	//Background
	private Texture backgroundTexture;
	public static Sprite backgroundSprite;
	
	//"Back" Button
	private SFButtonA back;
	
	//Scroll
	private static float prevDragX;
	private static float prevDragY;
	
	//Fonts
	private static BitmapFont text;
	
	public PlanetScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {

		batch = new SpriteBatch();
		controller = new SFlightInputController();
		
		camera = new OrthographicCamera(width, height);
		camera.position.set(new Vector3(0.0F, 0.0F, 0));
		
		MainMenu.music.play();
		
		backgroundTexture = new Texture("bckgrnd/planetChoice.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setBounds(0.0F, 0.0F, width, 4.0F*width);
		
		if(!InfoAndStats.lngRussian){
			back = new SFButtonA("btns/back", 0.132F*width, 0.8523F*width, 0.005F*height, camera, 1.0F);
		}else{
			back = new SFButtonA("btns/RU/backR", 0.132F*width, 0.8523F*width, 0.005F*height, camera, 1.0F);
		}

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
		
		touchUpdate();
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		
		backgroundSprite.draw(batch);
		
		back.setCoordinates();
		back.getSprite().draw(batch);
		if(controller.isOnGameStaticHangar(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}

		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Planet choice", camera.position.x - 0.475F*width, camera.position.y + 0.465F*height);
		}else{
			text.draw(batch, "Выбор планеты", camera.position.x - 0.475F*width, camera.position.y + 0.465F*height);
		}
		
		batch.end();
		
		if(controller.isClickedGameHangar(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new StartPanelScreen(game));
			this.dispose();
		}
	
		resourcesCheck();
		
	}
	
	private void touchUpdate(){
		if(prevDragX != 0.0F && SFlightInputController.touchDragX != 0.0F)
			camera.position.x -= SFlightInputController.touchDragX - prevDragX;
		if(prevDragY != 0.0F && SFlightInputController.touchDragY != 0.0F)
			camera.position.y += SFlightInputController.touchDragY - prevDragY;
		prevDragX = SFlightInputController.touchDragX;
		prevDragY = SFlightInputController.touchDragY;
			
		if(camera.position.x < backgroundSprite.getX() + width/2)
			camera.position.set(new Vector3(backgroundSprite.getX() + width/2, camera.position.y, 0));
		if(camera.position.y < backgroundSprite.getY() + height/2)
			camera.position.set(new Vector3(camera.position.x, backgroundSprite.getY() + height/2, 0));
		if(camera.position.x > (backgroundSprite.getX() + backgroundSprite.getWidth()) - width/2)
			camera.position.set(new Vector3((backgroundSprite.getX() + backgroundSprite.getWidth()) - width/2, camera.position.y, 0));
		if(camera.position.y > (backgroundSprite.getY() + backgroundSprite.getHeight()) - height/2)
			camera.position.set(new Vector3(camera.position.x, (backgroundSprite.getY() + backgroundSprite.getHeight()) - height/2, 0));
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