package ru.erked.sflight.menu;

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
import ru.erked.sflight.random.ImgResDraw;
import ru.erked.sflight.random.InfoAndStats;
import ru.erked.sflight.random.ResetTheGame;

public class OptionsScreen implements Screen {

	final String FONT_CHARS_RU = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
	private static final float width = Gdx.graphics.getWidth();
	private static final float height = Gdx.graphics.getHeight();
	
	private Game game;
	private SpriteBatch batch;
	private SFlightInputController controller;
	private static BitmapFont text;
	private Screen options;
	
	//Вращение объектов
	public static float planet1PrevRotation = 0.0F;
	public static float planet2PrevRotation = 0.0F;
	public static float cometPrevRotation;
	
	//Копка "Back"
	private Sprite backButtonInactiveSprite;
	private Sprite backButtonActiveSprite;
	private float backButtonX;
	private float backButtonY;
	private float backButtonWidth;
	private float backButtonHeight;
	public static float backButtonTentionIndex; //Соотношение сторон кнопки
	
	//Копка "RU"
	private Texture ruButtonInactive;
	private Texture ruButtonActive;	
	private Sprite ruButtonInactiveSprite;
	private Sprite ruButtonActiveSprite;
	private float ruButtonX;
	private float ruButtonY;
	private float ruButtonWidth;
	private float ruButtonHeight;
	public static float ruButtonTentionIndex; //Соотношение сторон кнопки
	
	//Копка "US"
	private Texture usButtonInactive;
	private Texture usButtonActive;	
	private Sprite usButtonInactiveSprite;
	private Sprite usButtonActiveSprite;
	private float usButtonX;
	private float usButtonY;
	private float usButtonWidth;
	private float usButtonHeight;
	public static float usButtonTentionIndex; //Соотношение сторон кнопки
		
	//Копка "Reset the game"
	private Texture resetButtonInactive;
	private Texture resetButtonActive;	
	private Sprite resetButtonInactiveSprite;
	private Sprite resetButtonActiveSprite;
	private float resetButtonX;
	private float resetButtonY;
	private float resetButtonWidth;
	private float resetButtonHeight;
	public static float resetButtonTentionIndex; //Соотношение сторон кнопки
	
	private Sprite blackAlpha = new Sprite(new Texture("objects/black.png"));
	private float alp = 1.0F;
	private boolean isTransOptions;
	
	public OptionsScreen(Game game) {
		this.game = game;
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		controller = new SFlightInputController();
		options = new OptionsScreen(game);
		
		MainMenu.music.play();
		
		//Кнопка "Back"//
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
		
		//Кнопка "RU"//
		ruButtonInactive = new Texture("btns/button_RU_inactive.png");
		ruButtonActive = new Texture("btns/button_RU_active.png");
		ruButtonInactiveSprite = new Sprite(ruButtonInactive);
		ruButtonActiveSprite = new Sprite(ruButtonActive);
		ruButtonTentionIndex = (float)ruButtonInactive.getWidth()/ruButtonInactive.getHeight();
		ruButtonWidth = 0.2F*width;
		ruButtonHeight = ruButtonWidth/ruButtonTentionIndex;
		ruButtonX = 0 + 0.015F*width;
		ruButtonY = height - ruButtonHeight - 0.15F*height;
		ruButtonInactiveSprite.setBounds(ruButtonX, ruButtonY, ruButtonWidth, ruButtonHeight);
		ruButtonActiveSprite.setBounds(ruButtonX, ruButtonY, ruButtonWidth, ruButtonHeight);
		
		//Кнопка "US"//
		usButtonInactive = new Texture("btns/button_US_inactive.png");
		usButtonActive = new Texture("btns/button_US_active.png");
		usButtonInactiveSprite = new Sprite(usButtonInactive);
		usButtonActiveSprite = new Sprite(usButtonActive);
		usButtonTentionIndex = (float)usButtonInactive.getWidth()/usButtonInactive.getHeight();
		usButtonWidth = 0.2F*width;
		usButtonHeight = usButtonWidth/usButtonTentionIndex;
		usButtonX = 0 + 0.02F*width + ruButtonWidth;
		usButtonY = height - usButtonHeight - 0.15F*height;
		usButtonInactiveSprite.setBounds(usButtonX, usButtonY, usButtonWidth, usButtonHeight);
		usButtonActiveSprite.setBounds(usButtonX, usButtonY, usButtonWidth, usButtonHeight);
	
		//Кнопка "Reset the game"//
		resetButtonInactive = new Texture("btns/button_reset_the_game_inactive.png");
		resetButtonActive = new Texture("btns/button_reset_the_game_active.png");
		resetButtonInactiveSprite = new Sprite(resetButtonInactive);
		resetButtonActiveSprite = new Sprite(resetButtonActive);
		if(InfoAndStats.lngRussian){
			resetButtonInactiveSprite.setTexture(ImgResDraw.resetButtonInactiveRU);
			resetButtonActiveSprite.setTexture(ImgResDraw.resetButtonActiveRU);
		}
		resetButtonTentionIndex = (float)resetButtonInactive.getWidth()/resetButtonInactive.getHeight();
		resetButtonWidth = 0.4F*width;
		resetButtonHeight = resetButtonWidth/resetButtonTentionIndex;
		resetButtonX = 0;
		resetButtonY = usButtonY - 1.2F*resetButtonHeight;
		resetButtonInactiveSprite.setBounds(resetButtonX, resetButtonY, resetButtonWidth, resetButtonHeight);
		resetButtonActiveSprite.setBounds(resetButtonX, resetButtonY, resetButtonWidth, resetButtonHeight);
		
		//Шрифты\\
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
		
		text.getData().setScale((float)(0.001F*width));
		
		genRU.dispose();
		genUS.dispose();
		
		blackAlpha.setBounds(0.0F, 0.0F, width, height);
		blackAlpha.setAlpha(1.0F);
		isTransOptions = false;
	}

	@Override
	public void render(float delta) {
		InfoAndStats.elapsedTime++;
		
		if(alp>0.0F && !isTransOptions){
			blackAlpha.setAlpha(alp);
			alp-=0.05F;
		}else if(!isTransOptions){
			blackAlpha.setAlpha(0.0F);
			alp = 0.0F;
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		/***Отрисовка менюшных объектов*/
		MainMenu.planet1Sprite.setOriginCenter();
		MainMenu.planet1Sprite.rotate(0.0175F);
		
		MainMenu.planet2Sprite.setOriginCenter();
		MainMenu.planet2Sprite.rotate(-0.01F);
		MainMenu.planet2Sprite.setY((float)(MainMenu.planet2Sprite.getY() + 0.03F));
		MainMenu.planet2Sprite.setX(MainMenu.planet2Sprite.getX() - 0.3F);
		if(MainMenu.planet2Sprite.getX() < (0-1)*width){
			MainMenu.planet2Sprite.setX(1.05F*width + MainMenu.planet2Sprite.getWidth());
			MainMenu.planet2Sprite.setY(0.05F*height);
		}

		MainMenu.cometSprite.setOrigin(2*width, 2*height);
		MainMenu.cometSprite.rotate(0.25F);
		/***Отрисовка менюшных объектов*/
		
		batch.begin();
		
		/***Отрисовка менюшных объектов*/
		MainMenu.backgroundSprite.draw(batch);
		MainMenu.planet1Sprite.draw(batch);
		MainMenu.planet2Sprite.draw(batch);		
		MainMenu.cometSprite.draw(batch);
		/***Отрисовка менюшных объектов*/
		
		//Отрисовка кнопки "Back"//
		if(controller.isOn(backButtonX, backButtonY, backButtonWidth, backButtonHeight))
			backButtonActiveSprite.draw(batch);
		else
			backButtonInactiveSprite.draw(batch);
		//Отрисовка кнопки "RU"//
		if(controller.isOn(ruButtonX, ruButtonY, ruButtonWidth, ruButtonHeight) || InfoAndStats.lngRussian)
			ruButtonActiveSprite.draw(batch);
		else
			ruButtonInactiveSprite.draw(batch);
		//Отрисовка кнопки "US"//
		if(controller.isOn(usButtonX, usButtonY, usButtonWidth, usButtonHeight) || !InfoAndStats.lngRussian)
			usButtonActiveSprite.draw(batch);
		else
			usButtonInactiveSprite.draw(batch);
		//Отрисовка кнопки "Reset the game"//
		if(controller.isOn(resetButtonX, resetButtonY, resetButtonWidth, resetButtonHeight))
			resetButtonActiveSprite.draw(batch);
		else
			resetButtonInactiveSprite.draw(batch);	
		
		//Отрисовка шрифтов//
		if(!InfoAndStats.lngRussian)
			text.draw(batch, "Language:", 0.02F*width, 0.95F*height);
		else
			text.draw(batch, "Язык:", 0.02F*width, 0.95F*height);
		
		blackAlpha.draw(batch);
		
		batch.end();
		
		//Запоминаем ротэйт//
		cometPrevRotation = MainMenu.cometSprite.getRotation();
		planet1PrevRotation = MainMenu.planet1Sprite.getRotation();
		planet2PrevRotation = MainMenu.planet2Sprite.getRotation();
		
		//Слушатель нажатия на кнопку "Back"//
		if(controller.isClicked(backButtonX, backButtonY, backButtonWidth, backButtonHeight) || isTransOptions){
			isTransOptions = true;
			if(alp>1.0F){
				this.dispose();
				game.setScreen(new MainMenu(game));
			}else{
				blackAlpha.setAlpha(alp);
				alp+=0.05F;
			}
		}
		//Слушатель нажатия на кнопку "RU"//
		if(controller.isClicked(ruButtonX, ruButtonY, ruButtonWidth, ruButtonHeight)){
			InfoAndStats.lngRussian = true;
			this.dispose();
			game.setScreen(new TechnicScreen(game, options, 0.1F));
		}
		//Слушатель нажатия на кнопку "US"//
		if(controller.isClicked(usButtonX, usButtonY, usButtonWidth, usButtonHeight)){
			InfoAndStats.lngRussian = false;
			this.dispose();
			game.setScreen(new TechnicScreen(game, options, 0.1F));
		}
		//Слушатель нажатия на кнопку "Reset"//
		if(controller.isClicked(resetButtonX, resetButtonY, resetButtonWidth, resetButtonHeight)){
			ResetTheGame.reset();
			game.setScreen(new TechnicScreen(game, options, 0.1F));
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

	private void textureDispose(){
		ruButtonInactive.dispose();
		ruButtonActive.dispose();
		usButtonInactive.dispose();
		usButtonActive.dispose();
		resetButtonInactive.dispose();
		resetButtonActive.dispose();
	}
	
	@Override
	public void dispose() {
		//Возвращаем текущие координаты спрайта красной планеты в меню//
		MainMenu.planet2X = MainMenu.planet2Sprite.getX();
		MainMenu.planet2Y = MainMenu.planet2Sprite.getY();
		
		MainMenu.isFirstScreen = false;
		
		text.dispose();
		game.dispose();
		batch.dispose();
		textureDispose();
	}

}