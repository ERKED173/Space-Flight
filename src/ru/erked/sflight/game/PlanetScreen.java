package ru.erked.sflight.game;

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

import ru.erked.sflight.StartSFlight;
import ru.erked.sflight.controllers.SFlightInputController;
import ru.erked.sflight.menu.MainMenu;
import ru.erked.sflight.random.INF;
import ru.erked.sflight.tech.SFButtonA;
import ru.erked.sflight.tech.SFButtonS;

public class PlanetScreen implements Screen{

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private final StartSFlight game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	public static OrthographicCamera camera;
	
	//Background
	private Texture backgroundTexture;
	public static Sprite backgroundSprite;
	
	//Planets 
	private Sprite earth;
	private SFButtonS loon;
	private SFButtonS emion;
	
	//Resources
	private Sprite fuel;
	
	//"Select" button
	private SFButtonS select;
	
	//"Back" Button
	private SFButtonA back;
	
	//Scroll
	private static float prevDragX;
	private static float prevDragY;
	
	//Fonts
	private static BitmapFont text;
	
	public PlanetScreen(final StartSFlight game){
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
		fuel  = new Sprite(new Texture("objects/fuel.png"));
		fuel.setBounds(0.025F*width, 0.025F*width, 0.05F*width, 0.05F*width);
		
		
		if(!INF.lngRussian){
			back = new SFButtonA("btns/back", 0.132F*width, 0.8523F*width, 0.005F*height, camera, 1.0F);
		}else{
			back = new SFButtonA("btns/RU/backR", 0.132F*width, 0.8523F*width, 0.005F*height, camera, 1.0F);
		}

		loon = new SFButtonS("planets/loon", 0.15F*width, 0.1F*width, 0.15F*backgroundSprite.getHeight(), 1.0F);
		emion = new SFButtonS("planets/emion", 0.2F*width, 0.75F*width, 0.2F*backgroundSprite.getHeight(), 1.0F);
		
		select = new SFButtonS("btns/button", 0.132F*width, 0.6F*width, 0.16F*backgroundSprite.getHeight(), 1.0F);
		select.getSprite().setColor(Color.CYAN);
		
		FreeTypeFontGenerator genUS = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prototype.ttf"));
		FreeTypeFontGenerator genRU = new FreeTypeFontGenerator(Gdx.files.internal("fonts/9840.otf"));
		FreeTypeFontParameter param2 = new FreeTypeFontParameter();
		param2.color = Color.WHITE;
		param2.size = 40;
		if(INF.lngRussian){
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
		INF.elapsedTime++;
		resourcesCheck();
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		touchUpdate();
		
		fuel.setX((float)(camera.position.x - (float)width/2.0F) + (float)0.025F*width);
		fuel.setY((float)(camera.position.y - (float)height/2.0F) + (float)0.025F*width);
		
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		
		backgroundSprite.draw(batch);
		
		earth.rotate(0.0166666666666667F);
		earth.draw(batch);
		fuel.draw(batch);
		
		loon.getSprite().draw(batch);
		emion.getSprite().draw(batch);
		
		text.draw(batch, ": " + INF.fuel, (camera.position.x - width/2.0F) + 0.085F*width, (camera.position.y - height/2.0F) + 0.06F*width);
		
		back.setCoordinates();
		back.getSprite().draw(batch);
		if(controller.isOnGameStaticHangar(back.getX(), back.getY(), back.getWidth(), back.getHeight())){
			back.setMode(true);
		}else{
			back.setMode(false);
		}
		
		if(!INF.lngRussian){
			text.draw(batch, "Selecting the planet", camera.position.x - 0.475F*width, camera.position.y + 0.465F*height);
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
		
	}
	
	private void drawPlanetInformation(){
		/***/
		if(loon.isActiveMode()){
			select.getSprite().draw(batch);
			select.setX(0.6F*width);
			select.setY(0.16F*backgroundSprite.getHeight());
			if(INF.planetLoon.isAvailable() && !INF.currentPlanet.equals("planetLoon") && INF.fuel >= INF.planetLoon.getFuelTo()){
				select.getSprite().setColor(Color.CYAN);
				if(controller.isOnGameHangar(select.getX(), select.getY(), select.getWidth(), select.getHeight())){
					select.setMode(true);
				}else{
					select.setMode(false);
				}
				if(controller.isClickedGameHangar(select.getX(), select.getY(), select.getWidth(), select.getHeight())){
					INF.currentPlanet = "planetLoon";
				}
			}else{
				select.getSprite().setColor(Color.TEAL);
			}
			if(!INF.lngRussian){
				text.draw(batch, "Select", 1.0375F*select.getX(), 1.09F*select.getY());
				text.draw(batch, "Planet's name: " + INF.planetLoon.getNameUS(), 3.0F*loon.getX(), 1.27F*loon.getY());
				text.draw(batch, "Level: " + INF.planetLoon.getLevel(), 3.0F*loon.getX(), 1.27F*loon.getY() - 1.5F*text.getCapHeight());
				text.draw(batch, "You need " + INF.planetLoon.getFuelTo() + " fuel", 3.0F*loon.getX(), 1.27F*loon.getY() - 3.0F*text.getCapHeight());
				text.draw(batch, "to reach the planet", 3.0F*loon.getX(), 1.27F*loon.getY() - 4.5F*text.getCapHeight());
				if(INF.currentPlanet.equals("planetLoon")) text.draw(batch, "Selected", 3.0F*loon.getX(), 1.27F*loon.getY() - 6.0F*text.getCapHeight());
			}else{
				text.draw(batch, "Выбрать", 1.0375F*select.getX(), 1.08F*select.getY());
				text.draw(batch, "Название планеты: " + INF.planetLoon.getNameRU(), 3.0F*loon.getX(), 1.225F*loon.getY());
				text.draw(batch, "Уровень: " + INF.planetLoon.getLevel(), 3.0F*loon.getX(), 1.225F*loon.getY() - 1.5F*text.getCapHeight());
				text.draw(batch, "Для достижения планеты", 3.0F*loon.getX(), 1.225F*loon.getY() - 3.0F*text.getCapHeight());
				text.draw(batch, "нужно " + INF.planetLoon.getFuelTo() + " топлива", 3.0F*loon.getX(), 1.225F*loon.getY() - 4.5F*text.getCapHeight());
				if(INF.currentPlanet.equals("planetLoon")) text.draw(batch, "Выбрана", 3.0F*loon.getX(), 1.225F*loon.getY() - 6.0F*text.getCapHeight());
			}
		}
		/***/
		if(emion.isActiveMode()){
			select.getSprite().draw(batch);
			if(INF.planetEmion.isAvailable() && !INF.currentPlanet.equals("planetEmion") && INF.fuel >= INF.planetEmion.getFuelTo()){
				select.getSprite().setColor(Color.CYAN);
				if(controller.isOnGameHangar(select.getX(), select.getY(), select.getWidth(), select.getHeight())){
					select.setMode(true);
				}else{
					select.setMode(false);
				}
				if(controller.isClickedGameHangar(select.getX(), select.getY(), select.getWidth(), select.getHeight())){
					INF.currentPlanet = "planetEmion";
				}
			}else{
				select.getSprite().setColor(Color.TEAL);
			}
			if(!INF.lngRussian){
				text.draw(batch, "Select", 1.115F*select.getX(), 1.07F*select.getY());
				text.draw(batch, "Planet's name: " + INF.planetEmion.getNameUS(), 0.5F*emion.getX(), 1.22F*emion.getY());
				text.draw(batch, "Level: " + INF.planetEmion.getLevel(), 0.5F*emion.getX(), 1.22F*emion.getY() - 1.5F*text.getCapHeight());
				text.draw(batch, "You need " + INF.planetEmion.getFuelTo() + " fuel", 0.5F*emion.getX(), 1.22F*emion.getY() - 3.0F*text.getCapHeight());
				text.draw(batch, "to reach the planet", 0.5F*emion.getX(), 1.22F*emion.getY() - 4.5F*text.getCapHeight());
				if(INF.currentPlanet.equals("planetEmion")) text.draw(batch, "Selected", 0.5F*emion.getX(), 1.22F*emion.getY() - 6.0F*text.getCapHeight());
			}else{
				text.draw(batch, "Выбрать", 1.115F*select.getX(), 1.06F*select.getY());
				text.draw(batch, "Название планеты: " + INF.planetEmion.getNameRU(), 0.5F*emion.getX(), 1.18F*emion.getY());
				text.draw(batch, "Уровень: " + INF.planetEmion.getLevel(), 0.5F*emion.getX(), 1.18F*emion.getY() - 1.5F*text.getCapHeight());
				text.draw(batch, "Для достижения планеты", 0.5F*emion.getX(), 1.18F*emion.getY() - 3.0F*text.getCapHeight());
				text.draw(batch, "нужно " + INF.planetEmion.getFuelTo() + " топлива", 0.5F*emion.getX(), 1.18F*emion.getY() - 4.5F*text.getCapHeight());
				if(INF.currentPlanet.equals("planetEmion")) text.draw(batch, "Выбрана", 0.5F*emion.getX(), 1.18F*emion.getY() - 6.0F*text.getCapHeight());
			}
		}
		/***/
	}
	
	private void buttonListener(){
		if(controller.isClickedGameHangar(loon.getX(), loon.getY(), loon.getWidth(), loon.getHeight())){
			if(!loon.isActiveMode()){
				loon.setMode(true);
				emion.setMode(false);
			}else{
				loon.setMode(false);
			}
		}
		/***/
		if(controller.isClickedGameHangar(emion.getX(), emion.getY(), emion.getWidth(), emion.getHeight())){
			if(!emion.isActiveMode()){
				emion.setMode(true);
				loon.setMode(false);
				select.setX(0.2F*width);
				select.setY(0.2125F*backgroundSprite.getHeight());
			}else{
				emion.setMode(false);
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
		batch.dispose();
		backgroundTexture.dispose();
		text.dispose();
	}

}