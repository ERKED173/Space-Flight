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
import ru.erked.sflight.tech.SFButtonS;

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
	
	//Planets 
	private Sprite earth;
	private SFButtonS loon;
	
	//"Select" button
	private SFButtonS select;
	
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
		
		earth = new Sprite(new Texture("objects/menu_planet_1.png"));
		earth.setBounds(-0.125F*width, -0.85F*width, 1.25F*width, 1.25F*width);
		earth.setOriginCenter();
		earth.setRotation((float)(359.0D*Math.random()));
		
		if(!InfoAndStats.lngRussian){
			back = new SFButtonA("btns/back", 0.132F*width, 0.8523F*width, 0.005F*height, camera, 1.0F);
		}else{
			back = new SFButtonA("btns/RU/backR", 0.132F*width, 0.8523F*width, 0.005F*height, camera, 1.0F);
		}

		loon = new SFButtonS("planets/loon", 0.15F*width, 0.1F*width, 0.15F*backgroundSprite.getHeight(), 1.0F);
		
		select = new SFButtonS("btns/button", 0.132F*width, 0.6F*width, 0.16F*backgroundSprite.getHeight(), 1.0F);
		select.getSprite().setColor(Color.CYAN);
		
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param2.color = Color.WHITE;
		param2.size = 40;
		if(InfoAndStats.lngRussian){
			param2.characters = FONT_CHARS_RU;
			text = genRU.generateFont(param2);
			text.getData().setScale((float)(0.0005F*width));
		}else{
			text = genUS.generateFont(param2);
			text.getData().setScale((float)(0.00075F*width));
		}
		
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
		
		earth.rotate(0.0166666666666667F);
		earth.draw(batch);
		
		loon.getSprite().draw(batch);
		
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
		
		drawPlanetInformation();
		
		batch.end();
		
		if(controller.isClickedGameHangar(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			game.setScreen(new StartPanelScreen(game));
			this.dispose();
		}
	
		buttonListener();
		resourcesCheck();
		
	}
	
	private void drawPlanetInformation(){
		/***/
		if(loon.isActiveMode()){
			select.getSprite().draw(batch);
			if(InfoAndStats.planetLoon.isSelected() && !InfoAndStats.currentPlanet.equals("planetLoon")){
				select.getSprite().setColor(Color.CYAN);
				select.setX(0.6F*width);
				select.setY(0.16F*backgroundSprite.getHeight());
				if(controller.isOnGameHangar(select.getX(), select.getY(), select.getWidth(), select.getHeight())){
					select.setMode(true);
				}else{
					select.setMode(false);
				}
				if(controller.isClickedGameHangar(select.getX(), select.getY(), select.getWidth(), select.getHeight())){
					InfoAndStats.currentPlanet = "planetLoon";
				}
			}else{
				select.getSprite().setColor(Color.TEAL);
			}
			if(!InfoAndStats.lngRussian){
				text.draw(batch, "Select", 1.0375F*select.getX(), 1.09F*select.getY());
				text.draw(batch, "Planet's name: " + InfoAndStats.planetLoon.getNameUS(), 3.0F*loon.getX(), 1.27F*loon.getY());
				text.draw(batch, "Level: " + InfoAndStats.planetLoon.getLevel(), 3.0F*loon.getX(), 1.27F*loon.getY() - 1.5F*text.getCapHeight());
				text.draw(batch, "You need " + InfoAndStats.planetLoon.getFuelTo() + " fuel", 3.0F*loon.getX(), 1.27F*loon.getY() - 3.0F*text.getCapHeight());
				text.draw(batch, "to reach the planet", 3.0F*loon.getX(), 1.27F*loon.getY() - 4.5F*text.getCapHeight());
				if(InfoAndStats.currentPlanet.equals("planetLoon")) text.draw(batch, "Selected", 3.0F*loon.getX(), 1.27F*loon.getY() - 6.0F*text.getCapHeight());
			}else{
				text.draw(batch, "Выбрать", 1.0375F*select.getX(), 1.08F*select.getY());
				text.draw(batch, "Название планеты: " + InfoAndStats.planetLoon.getNameRU(), 3.0F*loon.getX(), 1.225F*loon.getY());
				text.draw(batch, "Уровень: " + InfoAndStats.planetLoon.getLevel(), 3.0F*loon.getX(), 1.225F*loon.getY() - 1.5F*text.getCapHeight());
				text.draw(batch, "Для достижения планеты", 3.0F*loon.getX(), 1.225F*loon.getY() - 3.0F*text.getCapHeight());
				text.draw(batch, "нужно " + InfoAndStats.planetLoon.getFuelTo() + " топлива", 3.0F*loon.getX(), 1.225F*loon.getY() - 4.5F*text.getCapHeight());
				if(InfoAndStats.currentPlanet.equals("planetLoon")) text.draw(batch, "Выбрана", 3.0F*loon.getX(), 1.225F*loon.getY() - 6.0F*text.getCapHeight());
			}
		}
		/***/
	}
	
	private void buttonListener(){
		if(controller.isClickedGameHangar(loon.getX(), loon.getY(), loon.getWidth(), loon.getHeight())){
			if(!loon.isActiveMode()){
				loon.setMode(true);
			}else{
				loon.setMode(false);
			}
		}
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