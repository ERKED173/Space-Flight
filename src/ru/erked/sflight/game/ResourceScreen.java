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

public class ResourceScreen implements Screen{

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
	private String schBack;
	
	//Иконка ресурсов
	private Sprite moneySprite;
	private Sprite fuelSprite;
	private Sprite metalSprite;
	
	//Шрифты
	private static BitmapFont header;
	private static BitmapFont text;
	
	public ResourceScreen(Game game){
		this.game = game;
	}
	
	@Override
	public void show() {

		batch = new SpriteBatch();
		controller = new SFlightInputController();
		
		MainMenu.music.play();
		
		//Фон\\
		backgroundTexture = new Texture("bckgrnd/resource/resource_1.png");
		backgroundSprite = new Sprite(backgroundTexture);
		backgroundSprite.setBounds(0.0F, 0.0F, width, height);
		schBack = "bckgrnd/resource/resource_1.png";
		
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
		
		resourcesInit();
		
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
		
		//Отрисовка кнопки "Back"//
		if(controller.isOn(backButtonX, backButtonY, backButtonWidth, backButtonHeight))
			backButtonActiveSprite.draw(batch);
		else
			backButtonInactiveSprite.draw(batch);

		if(!InfoAndStats.lngRussian){
			text.draw(batch, "Information about resources", 0.315F*width, 0.965F*height);
			text.draw(batch, InfoAndStats.money + "/" + InfoAndStats.moneyFull + " cosmocoins", 0.125F*width, 0.8F*height - 0.0F*text.getCapHeight());
			text.draw(batch, "1 cosmocoin per " + 60/InfoAndStats.moneyAmount + " seconds", 0.125F*width, 0.8F*height - 1.5F*text.getCapHeight());
			text.draw(batch, InfoAndStats.fuel + "/" + InfoAndStats.fuelFull + " fuel", 0.125F*width, 0.8F*height - 6.0F*text.getCapHeight());
			text.draw(batch, "1 fuel per " + 60/InfoAndStats.fuelAmount + " seconds", 0.125F*width, 0.8F*height - 7.5F*text.getCapHeight());
			text.draw(batch, InfoAndStats.metal + "/" + InfoAndStats.metalFull + " metal", 0.125F*width, 0.8F*height - 12.0F*text.getCapHeight());
			text.draw(batch, "1 metal per " + 60/InfoAndStats.metalAmount + " seconds", 0.125F*width, 0.8F*height - 13.5F*text.getCapHeight());
		}else{
			text.draw(batch, "Информация о ресурсах", 0.315F*width, 0.965F*height);
		}
		
		drawResources();
		
		batch.end();
		
		//Слушатель нажатия на кнопку "Back"//
		if(controller.isClicked(backButtonX, backButtonY, backButtonWidth, backButtonHeight)){
			game.setScreen(new AnalyticCentreScreen(game));
			this.dispose();
		}
		
		resourcesCheck();
		
	}

	private void resourcesInit(){
		moneySprite = new Sprite(new Texture("objects/cosmocoin.png"));
		fuelSprite = new Sprite(new Texture("objects/fuel.png"));
		metalSprite = new Sprite(new Texture("objects/metal.png"));
		moneySprite.setBounds(0.025F*width, 0.8F*height - 3.0F*text.getCapHeight(), 0.08F*width, 0.08F*width);
		fuelSprite.setBounds(0.025F*width, 0.8F*height - 9.0F*text.getCapHeight(), 0.08F*width, 0.08F*width);
		metalSprite.setBounds(0.025F*width, 0.8F*height - 15.0F*text.getCapHeight(), 0.08F*width, 0.08F*width);
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
	private void drawResources(){
		moneySprite.draw(batch);
		fuelSprite.draw(batch);
		metalSprite.draw(batch);
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
		header.dispose();
		text.dispose();
	}

}